package com.wiley.umltoolkit.casestudy.controller.action;
import com.wiley.umltoolkit.casestudy.controller.common.BaseAction;

import com.wiley.umltoolkit.casestudy.common.LibraryException;
import com.wiley.umltoolkit.casestudy.business.item.ItemMgr;
import com.wiley.umltoolkit.casestudy.vo.UserVo;
import com.wiley.umltoolkit.casestudy.common.Constants;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/** Processes actions when checking out an Item
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class CheckoutItemAction extends BaseAction  {
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws LibraryException
     * @return
     */
    public ActionForward store(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws LibraryException  {
        logger.debug("BEGIN store method");
        ActionForward forward = null;
        UserVo user = (UserVo) request.getSession().getAttribute(Constants.USER_INFO);
        //logger.debug("USER TOSTRING=" + user.toString());
        String itemId = (String) request.getParameter(Constants.ITEM_ID);
        logger.debug("itemId=" + itemId);
        ItemMgr.getInstance().checkOutItem(user.getId(),  request.getParameter(Constants.ITEM_ID));
        /** Set the titleId to get back to the list after the checkout */
        //grab the titleId from the request and put it on the session so that when
        //the user hits F5 to refresh the page it doesn't throw an exception.
        request.getSession().setAttribute(Constants.TITLE_ID,  request.getParameter(Constants.TITLE_ID));
        forward = mapping.findForward("reloadViewItems");
        
        return forward;
    }
    
}