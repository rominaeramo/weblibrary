package com.wiley.umltoolkit.casestudy.controller.action;
import com.wiley.umltoolkit.casestudy.controller.common.BaseAction;
import com.wiley.umltoolkit.casestudy.common.LibraryException;
import com.wiley.umltoolkit.casestudy.business.item.ItemMgr;
import com.wiley.umltoolkit.casestudy.vo.UserVo;
import com.wiley.umltoolkit.casestudy.common.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** Processes actions when making a reservation in the system
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class MakeReservationAction extends BaseAction  {
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws LibraryException
     * @return
     */
    public ActionForward reserve(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws LibraryException  {
        
        ActionForward forward = null;
        ActionErrors errors = new ActionErrors();
        UserVo user = (UserVo) request.getSession().getAttribute(Constants.USER_INFO);
        //get the itemId from the request
        String itemId = request.getParameter(Constants.ITEM_ID);
        
        ItemMgr.getInstance().reserveItem(user.getId(),  itemId);
        
        this.saveErrors(request, errors);
        //grab the titleId from the request and put it on the session so that when
        //the user hits F5 to refresh the page it doesn't throw an exception.
        request.getSession().setAttribute(Constants.TITLE_ID,  request.getParameter(Constants.TITLE_ID));
        forward = mapping.findForward("reloadViewItems");
        return forward;
    }
}