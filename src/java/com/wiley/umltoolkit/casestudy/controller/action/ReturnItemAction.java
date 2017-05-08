package com.wiley.umltoolkit.casestudy.controller.action;
import com.wiley.umltoolkit.casestudy.controller.common.BaseAction;
import com.wiley.umltoolkit.casestudy.common.LibraryException;
import com.wiley.umltoolkit.casestudy.business.item.ItemMgr;
import com.wiley.umltoolkit.casestudy.vo.UserVo;
import com.wiley.umltoolkit.casestudy.common.Constants;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Processes actions returning an Item in the system
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class ReturnItemAction extends BaseAction  {
    
    
    public ActionForward store(ActionMapping mapping,   ActionForm form,   HttpServletRequest request,   HttpServletResponse response) throws LibraryException {
        logger.debug("BEGIN store method");
        ActionForward forward = null;
        ActionErrors errors = new ActionErrors();
        
        UserVo user = (UserVo) request.getSession().getAttribute(Constants.USER_INFO);
        String itemId = (String) request.getParameter(Constants.ITEM_ID);
        logger.debug("itemId=" + itemId);
        ItemMgr.getInstance().returnItem(user.getId(),  request.getParameter(Constants.ITEM_ID));
        /** Set the titleId to get back to the list after the returnItem */
        //grab the titleId from the request and put it on the session so that when
        //the user hits F5 to refresh the page it doesn't throw an exception.
        request.getSession().setAttribute(Constants.TITLE_ID,  request.getParameter(Constants.TITLE_ID));
        forward = mapping.findForward("reloadViewItems");
        return forward;
    }
    
}