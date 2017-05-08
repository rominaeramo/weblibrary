package com.wiley.umltoolkit.casestudy.controller.action;
import com.wiley.umltoolkit.casestudy.controller.form.LoginForm;
import com.wiley.umltoolkit.casestudy.controller.common.BaseAction;
import com.wiley.umltoolkit.casestudy.business.user.UserMgr;
import com.wiley.umltoolkit.casestudy.common.ManagerException;
import com.wiley.umltoolkit.casestudy.common.Constants;
import com.wiley.umltoolkit.casestudy.vo.UserVo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** Processes actions when logging into application
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class LoginAction extends BaseAction  {
    
    /** Processes actions when logging into application
     */
    public ActionForward login(ActionMapping mapping,   ActionForm form,   HttpServletRequest request,   HttpServletResponse response)  {
        logger.debug("I am in the login method!!!");
        ActionForward forward = null;
        ActionErrors errors = new ActionErrors();
        //super.execute(mapping,  form, request, response);
        LoginForm loginForm = (LoginForm) form;
        String currentRole = null;
        logger.debug("username=" + loginForm.getUsername());
        logger.debug("loginForm TOSTRING=" + loginForm.toString());
        try  {
            /** Determine if the information provided is a valid user */
            UserVo user = UserMgr.getUserInstance().login(loginForm.getUsername(),  loginForm.getPassword());
            if (user != null)  {
                request.getSession().setAttribute(Constants.USER_INFO, user);
                forward = mapping.findForward(Constants.HOME);
                currentRole = UserMgr.getUserInstance().getAssignedRole(user);
                user.setCurrentRole(currentRole);
                logger.debug("------SUCCESSFUL LOGIN---------user.getUsername()="
                + user.getUsername() + " user.getCurrentRole()=" + user.getCurrentRole());
            } else  {
                errors.add(Constants.LOGON_MSG, new ActionError("figure out how to put the value of the 'LOGON_ERROR_MSG' property here."));
                this.saveErrors(request, errors);
                forward = mapping.getInputForward();
                logger.debug("------ERROR IN LOGIN---------");
            }
        } catch (ManagerException e)  {
            logger.debug(e.getMessage(),  e);
            mapping.findForward("login");
        }
        return forward;
    }
    
    
}
