package com.wiley.umltoolkit.casestudy.controller.common;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.apache.struts.validator.ValidatorForm;

/** BaseForm for all FormBean implementating classes. It is important that all
 * FormBeans extend from this class because it is referred to in BaseAction
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class BaseForm extends ValidatorForm implements java.io.Serializable  {
    
    protected Logger logger = Logger.getLogger(this.getClass().getName());
    private String subAction;
    
    public BaseForm()  {
    }
    
    public String getSubAction()  {
        return subAction;
    }
    
    public void setSubAction(String subAction)  {
        logger.debug(this.getClass().getName() + ": Setting subAction property = " + subAction);
        this.subAction = subAction;
    }
    
    /** String representation of all properties in implementing class
     * @return String containg the String representation of the Java Bean
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
}