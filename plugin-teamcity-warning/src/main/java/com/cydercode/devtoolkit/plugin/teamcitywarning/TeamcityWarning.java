package com.cydercode.devtoolkit.plugin.teamcitywarning;

import com.cydercode.devtoolkit.plugin.Plugin;
import com.cydercode.devtoolkit.plugin.PluginRunner;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static java.util.Base64.getEncoder;

public class TeamcityWarning implements Plugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamcityWarning.class);

    private Properties properties;

    @Override
    public void onStart() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("plugin_window.fxml"));
        MainWindowController controller = new MainWindowController();
        loader.setController(controller);
        Parent root = null;
        try {
            root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setTitle("Dev-toolkit Example Plugin");
        } catch (IOException e) {
            e.printStackTrace();
        }

        properties = loadProperties();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                Map lastBuild = getLastBuild();
                LOGGER.info("Last build: {}", lastBuild);
                controller.setStatus(lastBuild);
            } catch (Exception e) {
                LOGGER.error("Unable to fetch or set build status", e);
            }

        }, 0, parseLong(properties.getProperty("interval", "10")), TimeUnit.SECONDS);
    }

    private Map getLastBuild() {
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String host = properties.getProperty("teamcity");
        String buildTypeId = properties.getProperty("buildTypeId");

        RestTemplate rest = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();


        httpHeaders.add(HttpHeaders.AUTHORIZATION, createAuthorizationHeader(username, password));
        HttpEntity<String> entity = new HttpEntity<>("asd", httpHeaders);

        ResponseEntity<Map> response = rest.exchange(host + "/httpAuth/app/rest/builds?locator=buildType:(id:" + buildTypeId + ")", HttpMethod.GET, entity, Map.class);
        return (Map) ((List) response.getBody().get("build")).get(0);
    }

    private String createAuthorizationHeader(String username, String password) {
        return format("Basic %s", getEncoder().encodeToString(format("%s:%s", username, password).getBytes()));
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("plugin-teamcity-warning.properties")));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onAction() {

    }

    @Override
    public void onStop() {

    }

    public static void main(String[] args) {
        new PluginRunner().start(args);
    }
}
