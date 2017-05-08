package com.wiley.umltoolkit.casestudy.controller.form;
import java.io.Serializable;
import org.apache.struts.action.ActionForm;
import com.wiley.umltoolkit.casestudy.vo.TitleVo;

/** Get form data when processing Titles
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class TitleForm extends ActionForm implements Serializable  {
    
    public TitleForm()  {
    }
    
    private TitleVo title;
    
    public void setTitle(TitleVo title)  {
        this.title = title;
    }
    
    public TitleVo getTitle()  {
        return title;
    }
    
}