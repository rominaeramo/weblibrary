package com.wiley.umltoolkit.casestudy.common;

/** Exception Handler for Presentation exceptions
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class PresentationException extends LibraryException  {
    
    /** Process an exception when a presentation exception occurs
     */
    public PresentationException()  {
    }
    
    /** Process an exception when a presentation exception occurs
     * @param msg String containing additional information for Exception
     */
    public PresentationException(String msg)  {
        super(msg);
    }
    
}