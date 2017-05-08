package com.wiley.umltoolkit.casestudy.common;

/** Exception Handler for Data Access Objects in the
 * com.wiley.umltoolkit.casestudy.dao package
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class DatabaseException extends LibraryException  {
    
    /** Handles a Database Exception calling super class */
    public DatabaseException()  {
        super();
    }
    /** Handles a Database Exception calling super class
     * @param msg String allowing you to send additional information about cause of Exception
     */
    public DatabaseException(String msg)  {
        super(msg);
    }
}
