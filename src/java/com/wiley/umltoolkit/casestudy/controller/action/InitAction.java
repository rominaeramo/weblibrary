package com.wiley.umltoolkit.casestudy.controller.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wiley.umltoolkit.casestudy.common.PresentationException;
import com.wiley.umltoolkit.casestudy.common.Constants;


/** Processes actions when initializing the application
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class InitAction extends Action  {
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws PresentationException  {
        ActionForward forward = null;
        forward = mapping.findForward(Constants.LOGIN);
        return forward;
    }
    
}