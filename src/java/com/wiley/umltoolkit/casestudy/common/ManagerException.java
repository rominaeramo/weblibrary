package com.wiley.umltoolkit.casestudy.common;

/** Handles Manager exceptions that occur in the
 * com.wiley.umltoolkit.casestudy.business.* packages
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class ManagerException extends LibraryException  {
    
    /** Handles an Exception the occurs when using the Managers  */
    public ManagerException()  {
        super();
    }
    
    /** Handles a LibraryException calling super class
     * @param msg String allowing you to send additional information about
     * cause of Exception
     */
    public ManagerException(String msg)  {
        super(msg);
    }
    
}
