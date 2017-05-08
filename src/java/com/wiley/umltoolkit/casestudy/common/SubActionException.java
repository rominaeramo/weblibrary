package com.wiley.umltoolkit.casestudy.common;

/** Handling exception that occur when a Sub action fails such as
 * the different types of actions that are initiated from a page: view,
 * remove,  store,  cancel,  etc.
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class SubActionException extends PresentationException  {
    
    public SubActionException()  {
    }
    
    public SubActionException(String msg)  {
        super(msg);
    }
}