
package com.cydercode.devtoolkit.configuration.model;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{https://cydercode.com/dev-toolkit/}child-preset" maxOccurs="unbounded" minOccurs="0"/>
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
    "childPreset"
})
@XmlRootElement(name = "child-presets", namespace = "https://cydercode.com/dev-toolkit/")
public class ChildPresets {

    @XmlElement(name = "child-preset", namespace = "https://cydercode.com/dev-toolkit/")
    protected List<ChildPreset> childPreset;

    /**
     * Gets the value of the childPreset property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the childPreset property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildPreset().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChildPreset }
     * 
     * 
     */
    public List<ChildPreset> getChildPreset() {
        if (childPreset == null) {
            childPreset = new ArrayList<ChildPreset>();
        }
        return this.childPreset;
    }

}
