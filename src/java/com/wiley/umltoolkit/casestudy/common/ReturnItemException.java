package com.wiley.umltoolkit.casestudy.common;

/** Handles exceptions when returning an Item to the system 
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class ReturnItemException extends ManagerException {
    
    /** Process an exception when returning an Item
     * @param msg String containing additional information for Exception
     */
    public ReturnItemException(String msg) {
        super(msg);
    }
}
