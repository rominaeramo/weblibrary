package com.wiley.umltoolkit.casestudy.business.user;
import com.wiley.umltoolkit.casestudy.dao.common.DaoFactory;
import com.wiley.umltoolkit.casestudy.common.ManagerException;
import com.wiley.umltoolkit.casestudy.vo.UserVo;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import org.apache.log4j.Logger;

/** A user represents someone that logs into a Library application
 * UserMgr manages all of the business logic for a User
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class UserMgr {
    
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private DaoFactory dbFactory = DaoFactory.getDaoFactory(DaoFactory.ACCESS);
    /** Uncomment to use MySQL
     * private DaoFactory dbFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
     */
    
    /** Do not allow instances to be created from constructor */
    private UserMgr() {
        
    }
    /** Returns an instance of this class
     * @return UserMgr containing an object of this class
     */
    public static UserMgr getUserInstance()  {
        return new UserMgr();
    }
    
    /** Authenticates a user with the database
     * @param username String containing the entered username
     * @param password String containing the entered password
     * @return UserVo Value object; if successful will contain populated user data
     * @throws ManagerException general exception
     */
    public UserVo login(String username,  String password) throws ManagerException {
        UserVo user = null;
        try  {
            user = dbFactory.getUserDao().login(username, password);
        } catch (DatabaseException e)  {
            logger.debug(e.getMessage(),  e);
        }
        return user;
    }
    
    /** Gets the role assigned to the User
     * @param user UserVo containing populated value object
     * @return String containing the Role assigned to this user
     * @throws ManagerException general exception
     */
    public String getAssignedRole(UserVo user) throws ManagerException  {
        String roleName = null;
        try  {
            roleName = dbFactory.getUserDao().getAssignedRole(user);
        } catch (DatabaseException e)  {
            logger.debug(e.getMessage(),  e);
        }
        return roleName;
    }
    
}