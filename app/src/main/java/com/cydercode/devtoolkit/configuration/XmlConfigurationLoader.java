package com.cydercode.devtoolkit.configuration;

import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.configuration.model.DevToolkit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;

public class XmlConfigurationLoader extends AbstractFileConfigurationLoader {

    public XmlConfigurationLoader(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    public Configuration load() throws Exception {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DevToolkit.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return new Configuration((DevToolkit) unmarshaller.unmarshal(getReader()), file.getAbsolutePath());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
