package com.wiley.umltoolkit.casestudy.common;

/** Handles exceptions when reserving an Item in the system 
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class ReserveItemException extends ManagerException  {
    
    /** Process an exception when reserving an Item
     * @param msg String containing additional information for Exception
     */
    public ReserveItemException(String msg)  {
        super(msg);
    }
}