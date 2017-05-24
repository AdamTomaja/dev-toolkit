
package com.cydercode.devtoolkit.configuration.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parameter-type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="parameter-type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="string"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "parameter-type", namespace = "https://cydercode.com/dev-toolkit/")
@XmlEnum
public enum ParameterType {

    @XmlEnumValue("string")
    STRING("string");
    private final String value;

    ParameterType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ParameterType fromValue(String v) {
        for (ParameterType c: ParameterType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
