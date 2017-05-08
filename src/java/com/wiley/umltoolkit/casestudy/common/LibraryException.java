package com.wiley.umltoolkit.casestudy.common;

/** General super class application exception extended by all concrete
 * exceptions
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class LibraryException extends Exception  {
    
    /** Handles a Library Exception calling super class */
    public LibraryException()  {
        super();
    }
    
    /** Handles a LibraryException calling super class
     * @param msg String allowing you to send additional information about
     * cause of Exception
     */
    public LibraryException(String msg)  {
        super(msg);
    }
    
}