//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.19 at 08:43:54 PM CEST 
//


package com.cydercode.devtoolkit.configuration.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


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
@XmlRootElement(name = "preset")
public class Preset
    implements Equals, HashCode, ToString
{

    protected String application;
    protected String cmd;
    protected String group;
    @XmlElement(name = "child-presets")
    protected ChildPresets childPresets;
    protected String description;
    @XmlElement(defaultValue = "false")
    protected Boolean qtoolbox = false;
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

    public Preset withApplication(String value) {
        setApplication(value);
        return this;
    }

    public Preset withCmd(String value) {
        setCmd(value);
        return this;
    }

    public Preset withGroup(String value) {
        setGroup(value);
        return this;
    }

    public Preset withChildPresets(ChildPresets value) {
        setChildPresets(value);
        return this;
    }

    public Preset withDescription(String value) {
        setDescription(value);
        return this;
    }

    public Preset withQtoolbox(Boolean value) {
        setQtoolbox(value);
        return this;
    }

    public Preset withName(String value) {
        setName(value);
        return this;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Preset)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Preset that = ((Preset) object);
        {
            String lhsApplication;
            lhsApplication = this.getApplication();
            String rhsApplication;
            rhsApplication = that.getApplication();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "application", lhsApplication), LocatorUtils.property(thatLocator, "application", rhsApplication), lhsApplication, rhsApplication)) {
                return false;
            }
        }
        {
            String lhsCmd;
            lhsCmd = this.getCmd();
            String rhsCmd;
            rhsCmd = that.getCmd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "cmd", lhsCmd), LocatorUtils.property(thatLocator, "cmd", rhsCmd), lhsCmd, rhsCmd)) {
                return false;
            }
        }
        {
            String lhsGroup;
            lhsGroup = this.getGroup();
            String rhsGroup;
            rhsGroup = that.getGroup();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "group", lhsGroup), LocatorUtils.property(thatLocator, "group", rhsGroup), lhsGroup, rhsGroup)) {
                return false;
            }
        }
        {
            ChildPresets lhsChildPresets;
            lhsChildPresets = this.getChildPresets();
            ChildPresets rhsChildPresets;
            rhsChildPresets = that.getChildPresets();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "childPresets", lhsChildPresets), LocatorUtils.property(thatLocator, "childPresets", rhsChildPresets), lhsChildPresets, rhsChildPresets)) {
                return false;
            }
        }
        {
            String lhsDescription;
            lhsDescription = this.getDescription();
            String rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
                return false;
            }
        }
        {
            Boolean lhsQtoolbox;
            lhsQtoolbox = this.isQtoolbox();
            Boolean rhsQtoolbox;
            rhsQtoolbox = that.isQtoolbox();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "qtoolbox", lhsQtoolbox), LocatorUtils.property(thatLocator, "qtoolbox", rhsQtoolbox), lhsQtoolbox, rhsQtoolbox)) {
                return false;
            }
        }
        {
            String lhsName;
            lhsName = this.getName();
            String rhsName;
            rhsName = that.getName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theApplication;
            theApplication = this.getApplication();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "application", theApplication), currentHashCode, theApplication);
        }
        {
            String theCmd;
            theCmd = this.getCmd();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cmd", theCmd), currentHashCode, theCmd);
        }
        {
            String theGroup;
            theGroup = this.getGroup();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "group", theGroup), currentHashCode, theGroup);
        }
        {
            ChildPresets theChildPresets;
            theChildPresets = this.getChildPresets();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "childPresets", theChildPresets), currentHashCode, theChildPresets);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);
        }
        {
            Boolean theQtoolbox;
            theQtoolbox = this.isQtoolbox();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "qtoolbox", theQtoolbox), currentHashCode, theQtoolbox);
        }
        {
            String theName;
            theName = this.getName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            String theApplication;
            theApplication = this.getApplication();
            strategy.appendField(locator, this, "application", buffer, theApplication);
        }
        {
            String theCmd;
            theCmd = this.getCmd();
            strategy.appendField(locator, this, "cmd", buffer, theCmd);
        }
        {
            String theGroup;
            theGroup = this.getGroup();
            strategy.appendField(locator, this, "group", buffer, theGroup);
        }
        {
            ChildPresets theChildPresets;
            theChildPresets = this.getChildPresets();
            strategy.appendField(locator, this, "childPresets", buffer, theChildPresets);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            Boolean theQtoolbox;
            theQtoolbox = this.isQtoolbox();
            strategy.appendField(locator, this, "qtoolbox", buffer, theQtoolbox);
        }
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        return buffer;
    }

}