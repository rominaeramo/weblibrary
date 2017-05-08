package com.wiley.umltoolkit.casestudy.vo.common;
import java.beans.Introspector;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.BeanInfo;
import java.lang.reflect.Method;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Base Value Object for defining generic behavior for all Value Objects
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class BaseVo  {
    
    /** String representation of all properties in implementing class
     * @return String containg the String representation of the Java Bean
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    
}