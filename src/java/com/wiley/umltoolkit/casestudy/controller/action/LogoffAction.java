package com.wiley.umltoolkit.casestudy.controller.action;
import com.wiley.umltoolkit.casestudy.common.LibraryException;
import com.wiley.umltoolkit.casestudy.controller.common.BaseAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** Processes actions when logging out of the application
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class LogoffAction extends BaseAction  {
    
    public ActionForward logoff(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws LibraryException  {
        
        ActionForward forward = null;
        
        try {
            /**
             * remove the user information from session,  at this point you would also want to
             * remove anything else specific to the user.  Hesitate to invalidate the session at
             * this point.
             */
            request.getSession().invalidate();
            
        } catch (IllegalStateException ise) {
            //the session has already been invalidated.
            logger.debug("-------------------ERROR IN LOGOUT--------------------");
            
        }
        
        forward = mapping.getInputForward();
        return forward;
    }
    
}