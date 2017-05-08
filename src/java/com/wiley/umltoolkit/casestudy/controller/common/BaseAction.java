package com.wiley.umltoolkit.casestudy.controller.common;
import com.wiley.umltoolkit.casestudy.common.Constants;
import com.wiley.umltoolkit.casestudy.vo.UserVo;
import com.wiley.umltoolkit.casestudy.common.PresentationException;
import com.wiley.umltoolkit.casestudy.common.LibraryException;
import com.wiley.umltoolkit.casestudy.common.SubActionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/** Base class for all Struts action implementation classes
 * All Struts implementing classes need to extend from this class
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public abstract class BaseAction extends Action  {
    
    protected Logger logger = Logger.getLogger(this.getClass().getName());
    
    /** Create a BaseAction object */
    public BaseAction()  {
    }
    
    /** Default method called for an Action object with Struts
     * The form bean used with this action must have assigned a value to the
     *subAction property so that the method may be called dynamically in the
     * implementing class of this class
     * NOTE: Do not define an execute method in implementing class in order to
     * use this default behavior
     * @param mapping ActionMapping represents the information that the controller servlet
     * @param form ActionForm JavaBean optionally associated with one or more ActionMappings
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws LibraryException
     * @throws PresentationException
     * @throws Exception
     * @return ActionForward represents a destination to which the controller servlet
     */
    public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws LibraryException,  PresentationException,  Exception  {
        logger.debug("---------EXECUTE IN BASE ACTION CALLED--------");
        ActionForward forward = null;
        BaseForm bForm = (BaseForm) form;
        bForm.setSubAction(request.getParameter(Constants.SUB_ACTION));
        String subAction = bForm.getSubAction();
        if (subAction == null)  {
            throw new SubActionException(/*"No method found for the given subAction="+bForm.getSubAction()*/);
        }
        UserVo user = (UserVo) request.getSession().getAttribute(Constants.USER_INFO);
        if (user == null && !subAction.equalsIgnoreCase(Constants.LOGIN))  {
            forward = mapping.findForward(Constants.LOGIN);
            logger.debug("---------Changed forward to login--------");
        } else  {
            forward = callSubAction(mapping,  form,  request,  response);
        }
        return forward;
    }
    
    /** Dynamically calls a method in the concrete class based on the
     * name of the subAction property set in the BaseForm concrete class
     * @return ActionForward contains the object (JSP) to be forwarded to
     * "Automatic for the People"
     */
    private ActionForward callSubAction(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws SubActionException,  Exception  {
        ActionForward forward = null;
        BaseForm bForm = (BaseForm) form;
        Method method;
        logger.debug("this.Class.name = " + this.getClass().getName());
        
        Object[] arrMethodArgs = new Object[4];
        arrMethodArgs[0] = mapping;
        arrMethodArgs[1] = form;
        arrMethodArgs[2] = request;
        arrMethodArgs[3] = response;
        
        //  Trying to call the single method directly...
        Class[] parameterTypes = new Class[]
        {ActionMapping.class,
         ActionForm.class,
         HttpServletRequest.class,
         HttpServletResponse.class};
         
         try  {
             method = this.getClass().getDeclaredMethod(bForm.getSubAction(),  parameterTypes);
             try  {
                 forward =  (ActionForward) method.invoke(this,  arrMethodArgs);
             } catch (IllegalAccessException e1)  {
                 logger.debug(e1.getMessage(),  e1);
             } catch (IllegalArgumentException e2)  {
                 logger.debug(e2.getMessage(),  e2);
             } catch (InvocationTargetException e3)  {
                 logger.debug(e3.getMessage(),  e3);
                 throw new Exception(e3.getTargetException().getMessage());
             }
         } catch (NoSuchMethodException e1)  {
             logger.debug(e1.getMessage(),  e1);
         } catch (SecurityException e2)  {
             logger.debug(e2.getMessage(),  e2);
         }
         if (forward == null)  {
             throw new SubActionException("There needs to be a method in your "
             + this.getClass() + " with the following signature: <b>public ActionForward "
             + bForm.getSubAction() + "(ActionMapping mapping,   ActionForm form,   "
             + "HttpServletRequest request,   HttpServletResponse response)</b>");
         }
         return forward;
         
    }
    
}