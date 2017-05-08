package com.wiley.umltoolkit.casestudy.common;

/** Contains constants used throughout the system 
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class Constants  {
    
    /**
     * This holds the value of the flow thru an Action class derived from the BaseAction class
     */
    public static final String SUB_ACTION = "subAction";
    
    /** Values for the SUB_ACTION above */
    public static final String LOAD = "load";
    public static final String STORE = "store";
    public static final String REMOVE = "remove";
    public static final String VIEW = "view";
    public static final String CONFIRM = "confirm";
    public static final String CANCEL = "cancel";
    public static final String VERIFY = "verify";
    public static final String ADD = "add";
    public static final String CREATE = "create";
    public static final String HOME = "home";
    public static final String LOGIN = "login";
    
    /** Used to represent a Title */
    public static final String TITLE_ID = "titleId";
    
    /** Used to represent an Item */
    public static final String ITEM_ID = "itemId";
    
    /** Used to hold the user valueobject of the logged in user in session*/
    public static final String USER_INFO = "user_info";
    
    /** List of titles for the request/session */
    public static final String TITLES = "titles";
    
    /** TitleVo in request/session */
    public static final String TITLE = "title";
    
    /** List of items for the request/session */
    public static final String ITEMS = "items";
    
    /** ItemVo in request/session */
    public static final String ITEM = "item";
    
    /** Used to represent the key for the logon message**/
    public static final String LOGON_MSG = "LOGON_MSG";
    
    /** Used to represent the key for the logon message**/
    public static final String LOGON_ERROR_MSG = "logon.error";
    
    /** Used to represent the key for the logoff message**/
    public static final String LOGOFF_MSG = "LOGOFF_MSG";
    
    /** Used to represent the successful logoff msg on the login page **/
    public static final String LOGOFF_MSG_SUCCESS = "logoff.message.success";
    
    /** Used to represent the failure logoff msg on the login page **/
    public static final String LOGOFF_MSG_FAILURE = "logoff.message.failure";
    
}
