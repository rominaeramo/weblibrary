package com.wiley.umltoolkit.casestudy.controller.action;
import com.wiley.umltoolkit.casestudy.controller.common.BaseAction;
import com.wiley.umltoolkit.casestudy.controller.form.SearchForm;

import com.wiley.umltoolkit.casestudy.common.Constants;
import com.wiley.umltoolkit.casestudy.common.ManagerException;
import com.wiley.umltoolkit.casestudy.business.title.TitleMgr;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.log4j.Logger;

/** Processes action when searching a Title in the system
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class SearchAction extends BaseAction  {
    
    public ActionForward search(ActionMapping mapping,  ActionForm form,  HttpServletRequest request,  HttpServletResponse response)  {
        logger.debug("BEGIN search method");
        ActionForward forward = null;
        SearchForm searchForm = (SearchForm) form;
        logger.debug("searchForm.searchText=" + searchForm.getSearchText());
        String titlePattern = searchForm.getSearchText();
        try {
            Collection titles = TitleMgr.getInstance().findAllTitles(titlePattern);
            request.setAttribute(Constants.TITLES,  titles);
            forward = mapping.findForward("viewTitles");
        } catch (ManagerException e) {
            logger.debug("problem finding all titles", e);
            mapping.findForward("home");
        }
        
        return forward;
    }
}