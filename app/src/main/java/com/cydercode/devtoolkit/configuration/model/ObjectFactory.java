
package com.cydercode.devtoolkit.configuration.model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cydercode.devtoolkit.configuration.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cydercode.devtoolkit.configuration.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Presets }
     * 
     */
    public Presets createPresets() {
        return new Presets();
    }

    /**
     * Create an instance of {@link Preset }
     * 
     */
    public Preset createPreset() {
        return new Preset();
    }

    /**
     * Create an instance of {@link ChildPresets }
     * 
     */
    public ChildPresets createChildPresets() {
        return new ChildPresets();
    }

    /**
     * Create an instance of {@link ChildPreset }
     * 
     */
    public ChildPreset createChildPreset() {
        return new ChildPreset();
    }

    /**
     * Create an instance of {@link Application }
     * 
     */
    public Application createApplication() {
        return new Application();
    }

    /**
     * Create an instance of {@link Values }
     * 
     */
    public Values createValues() {
        return new Values();
    }

    /**
     * Create an instance of {@link Parameter }
     * 
     */
    public Parameter createParameter() {
        return new Parameter();
    }

    /**
     * Create an instance of {@link Groups }
     * 
     */
    public Groups createGroups() {
        return new Groups();
    }

    /**
     * Create an instance of {@link Group }
     * 
     */
    public Group createGroup() {
        return new Group();
    }

    /**
     * Create an instance of {@link Parameters }
     * 
     */
    public Parameters createParameters() {
        return new Parameters();
    }

    /**
     * Create an instance of {@link DevToolkit }
     * 
     */
    public DevToolkit createDevToolkit() {
        return new DevToolkit();
    }

    /**
     * Create an instance of {@link Applications }
     * 
     */
    public Applications createApplications() {
        return new Applications();
    }

}
