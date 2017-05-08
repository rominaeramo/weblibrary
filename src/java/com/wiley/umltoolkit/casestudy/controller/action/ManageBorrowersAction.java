package com.wiley.umltoolkit.casestudy.controller.action;
import com.wiley.umltoolkit.casestudy.controller.common.BaseAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** Processes actions when managing borrowers in the system
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class ManageBorrowersAction extends BaseAction  {
    
    public ActionForward view(ActionMapping mapping,   ActionForm form,   HttpServletRequest request,   HttpServletResponse response) {
        return mapping.findForward("viewBorrowers");
    }
    
}