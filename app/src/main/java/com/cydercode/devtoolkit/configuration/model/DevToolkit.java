
package com.cydercode.devtoolkit.configuration.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{https://cydercode.com/dev-toolkit/}groups" minOccurs="0"/>
 *         &lt;element ref="{https://cydercode.com/dev-toolkit/}applications" minOccurs="0"/>
 *         &lt;element ref="{https://cydercode.com/dev-toolkit/}parameters" minOccurs="0"/>
 *         &lt;element ref="{https://cydercode.com/dev-toolkit/}presets" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "groups",
    "applications",
    "parameters",
    "presets"
})
@XmlRootElement(name = "dev-toolkit", namespace = "https://cydercode.com/dev-toolkit/")
public class DevToolkit {

    @XmlElement(namespace = "https://cydercode.com/dev-toolkit/")
    protected Groups groups;
    @XmlElement(namespace = "https://cydercode.com/dev-toolkit/")
    protected Applications applications;
    @XmlElement(namespace = "https://cydercode.com/dev-toolkit/")
    protected Parameters parameters;
    @XmlElement(namespace = "https://cydercode.com/dev-toolkit/")
    protected Presets presets;

    /**
     * Gets the value of the groups property.
     * 
     * @return
     *     possible object is
     *     {@link Groups }
     *     
     */
    public Groups getGroups() {
        return groups;
    }

    /**
     * Sets the value of the groups property.
     * 
     * @param value
     *     allowed object is
     *     {@link Groups }
     *     
     */
    public void setGroups(Groups value) {
        this.groups = value;
    }

    /**
     * Gets the value of the applications property.
     * 
     * @return
     *     possible object is
     *     {@link Applications }
     *     
     */
    public Applications getApplications() {
        return applications;
    }

    /**
     * Sets the value of the applications property.
     * 
     * @param value
     *     allowed object is
     *     {@link Applications }
     *     
     */
    public void setApplications(Applications value) {
        this.applications = value;
    }

    /**
     * Gets the value of the parameters property.
     * 
     * @return
     *     possible object is
     *     {@link Parameters }
     *     
     */
    public Parameters getParameters() {
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parameters }
     *     
     */
    public void setParameters(Parameters value) {
        this.parameters = value;
    }

    /**
     * Gets the value of the presets property.
     * 
     * @return
     *     possible object is
     *     {@link Presets }
     *     
     */
    public Presets getPresets() {
        return presets;
    }

    /**
     * Sets the value of the presets property.
     * 
     * @param value
     *     allowed object is
     *     {@link Presets }
     *     
     */
    public void setPresets(Presets value) {
        this.presets = value;
    }

}
