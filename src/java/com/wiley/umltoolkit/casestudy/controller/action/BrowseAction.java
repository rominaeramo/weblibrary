package com.wiley.umltoolkit.casestudy.controller.action;
import com.wiley.umltoolkit.casestudy.controller.common.BaseAction;
import com.wiley.umltoolkit.casestudy.common.Constants;
import com.wiley.umltoolkit.casestudy.common.ManagerException;
import com.wiley.umltoolkit.casestudy.business.title.TitleMgr;
import com.wiley.umltoolkit.casestudy.business.item.ItemMgr;
import com.wiley.umltoolkit.casestudy.vo.TitleVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Collection;

/** Processes actions when browsing Titles
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class BrowseAction extends BaseAction  {
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward load(ActionMapping mapping,   ActionForm form,   HttpServletRequest request,   HttpServletResponse response)  {
        logger.debug("--load() method called--");
        ActionForward forward = null;
        
        String titleId = request.getParameter(Constants.TITLE_ID);
        if (titleId == null || "".equalsIgnoreCase(titleId))  {
            titleId = (String) request.getSession().getAttribute(Constants.TITLE_ID);
        }
        try  {
            TitleVo title = TitleMgr.getInstance().load(titleId);
            request.setAttribute(Constants.TITLE,  title);
            
            Collection items = ItemMgr.getInstance().findAll(titleId);
            request.setAttribute(Constants.ITEMS,  items);
            
            forward = mapping.findForward("viewItems");
        } catch (ManagerException e)  {
            logger.debug(e.getMessage(),  e);
            mapping.findForward("home");
        }
        
        return forward;
    }
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward view(ActionMapping mapping,   ActionForm form,   HttpServletRequest request,   HttpServletResponse response)  {
        logger.debug("--view() method called--");
        ActionForward forward = null;
        try  {
            Collection titles = TitleMgr.getInstance().findAllTitles();
            request.setAttribute(Constants.TITLES,  titles);
            forward = mapping.findForward("viewTitles");
        } catch (ManagerException e)  {
            logger.debug(e.getMessage(),  e);
            mapping.findForward("home");
        }
        
        return forward;
    }
}