package com.wiley.umltoolkit.casestudy.controller.form;

import com.wiley.umltoolkit.casestudy.controller.common.BaseForm;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionMapping;

/** Get form data when searching Titles
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class SearchForm extends BaseForm  {
    private String searchText;
    
    public SearchForm()  {
    }
    
    public String getSearchText()  {
        return searchText;
    }
    
    public void setSearchText(String searchText)  {
        this.searchText = searchText;
    }
    
    public ActionErrors validate(ActionMapping mapping,  HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (searchText == null || searchText.length() == 0)  {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.searchText.required"));
        }
        return errors;
    }
}