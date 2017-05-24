package com.cydercode.devtoolkit.configuration;

import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.configuration.model.*;
import com.google.common.collect.ImmutableMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlConfigurationLoader extends AbstractFileConfigurationLoader {

    protected XmlConfigurationLoader(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    public Configuration load() throws Exception {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DevToolkit.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            DevToolkit devToolkit = (DevToolkit) unmarshaller.unmarshal(getReader());

            Configuration configuration = new Configuration();

            Applications applications = devToolkit.getApplications();

            if (applications != null) {
                configuration.setApplications(new HashMap<>());
                applications.getApplication().forEach(app -> {
                    Map<String, String> properties = new HashMap<>();
                    properties.put(Configuration.PATH, app.getPath());
                    configuration.getApplications().put(app.getName(), properties);
                });
            }

            Parameters parameters = devToolkit.getParameters();
            if (parameters != null) {
                configuration.setParameters(new HashMap<>());

                parameters.getParameter().forEach(parameter -> {
                    Map<String, Object> properties = new HashMap<>();
                    properties.put(Configuration.TYPE, parameter.getType().value());
                    properties.put(Configuration.GROUP, parameter.getGroup());
                    properties.put(Configuration.DEFAULT, parameter.getDefault());
                    properties.put(Configuration.HIDDEN, String.valueOf(parameter.isHidden()));
                    Values values = parameter.getValues();
                    if (values != null) {
                        properties.put(Configuration.VALUES, values.getValue());
                    }
                    configuration.getParameters().put(parameter.getName(), properties);
                });
            }

            Presets presets = devToolkit.getPresets();
            if (presets != null) {
                configuration.setPresets(new HashMap<>());
                devToolkit.getPresets().getPreset().forEach(preset -> {
                    Map<String, Object> properties = new HashMap<>();
                    properties.put(Configuration.APPLICATION, preset.getApplication());
                    properties.put(Configuration.GROUP, preset.getGroup());
                    properties.put(Configuration.CMD, preset.getCmd());
                    properties.put(Configuration.Q_TOOLBOX, preset.isQtoolbox());
                    ChildPresets childPresets = preset.getChildPresets();
                    if (childPresets != null) {
                        List<Map<String, Object>> childPresetsList = new ArrayList<>();
                        childPresets.getChildPreset().forEach(childPreset -> {
                            childPresetsList.add(ImmutableMap.<String, Object>builder()
                                    .put(Configuration.PRESET, childPreset.getPreset())
                                    .put(Configuration.IGNORABLE, childPreset.isIgnorable())
                                    .build());
                        });
                        properties.put(Configuration.PRESETS, childPresetsList);
                    }

                    configuration.getPresets().put(preset.getName(), properties);
                });
            }
            return configuration;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
