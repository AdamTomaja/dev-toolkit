
package com.cydercode.devtoolkit.configuration.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="application" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cmd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="group" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{https://cydercode.com/dev-toolkit/}child-presets" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qtoolbox" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "application",
    "cmd",
    "group",
    "childPresets",
    "description",
    "qtoolbox"
})
@XmlRootElement(name = "preset", namespace = "https://cydercode.com/dev-toolkit/")
public class Preset {

    @XmlElement(namespace = "https://cydercode.com/dev-toolkit/")
    protected String application;
    @XmlElement(namespace = "https://cydercode.com/dev-toolkit/")
    protected String cmd;
    @XmlElement(namespace = "https://cydercode.com/dev-toolkit/")
    protected String group;
    @XmlElement(name = "child-presets", namespace = "https://cydercode.com/dev-toolkit/")
    protected ChildPresets childPresets;
    @XmlElement(namespace = "https://cydercode.com/dev-toolkit/")
    protected String description;
    @XmlElement(namespace = "https://cydercode.com/dev-toolkit/", defaultValue = "false")
    protected Boolean qtoolbox;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the application property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplication() {
        return application;
    }

    /**
     * Sets the value of the application property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplication(String value) {
        this.application = value;
    }

    /**
     * Gets the value of the cmd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmd() {
        return cmd;
    }

    /**
     * Sets the value of the cmd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmd(String value) {
        this.cmd = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroup(String value) {
        this.group = value;
    }

    /**
     * Gets the value of the childPresets property.
     * 
     * @return
     *     possible object is
     *     {@link ChildPresets }
     *     
     */
    public ChildPresets getChildPresets() {
        return childPresets;
    }

    /**
     * Sets the value of the childPresets property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChildPresets }
     *     
     */
    public void setChildPresets(ChildPresets value) {
        this.childPresets = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the qtoolbox property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isQtoolbox() {
        return qtoolbox;
    }

    /**
     * Sets the value of the qtoolbox property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQtoolbox(Boolean value) {
        this.qtoolbox = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
