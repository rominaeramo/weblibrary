package com.wiley.umltoolkit.casestudy.common;

/** Handle exceptions that occur during a checkout 
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class CheckoutException extends ManagerException {
    
    /** Use when an exception is thrown when checking out an Item
     * @param msg String containing the exception text of what occurred
     */
    public CheckoutException(String msg) {
        super(msg);
    }
}
