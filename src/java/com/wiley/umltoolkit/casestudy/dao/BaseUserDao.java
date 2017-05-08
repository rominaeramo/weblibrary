package com.wiley.umltoolkit.casestudy.dao;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import java.util.Collection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import com.wiley.umltoolkit.casestudy.vo.UserVo;
import org.apache.log4j.Logger;

/** Base Data Access Object class that handles the create, read, update, and
 * delete methods for a User.  This base class is used for defining behavior
 * that is similiar between the different data sources
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public abstract class BaseUserDao implements UserDao {

    /** Creates a new instance of BaseUserDao */
    public BaseUserDao() {
    }

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private static final String LOGIN_STMT = "SELECT user_id,  username,  password,  "
    + " first_name,  last_name,  role_id,  address,  city,  state,  zipcode,  phone "
    + " FROM USER WHERE username = ? AND password = ?";
    private static final String ROLE_STMT = "SELECT name FROM user, role "
    + " WHERE user.role_id = role.role_id AND user.username = ? ";

    /** Force the implementation of this method in concrete class
     * @return Connection containing a connection to Database
     * @throws DatabaseException if unable to get Connection
     */
    public abstract Connection getConnection() throws DatabaseException;

    /** Force the implementation of this method in concrete class
     * @param rs ResultSet A table of data representing a database result set
     * @param stmt Statement The object used for executing a static SQL statement
     * and obtaining the results produced by it
     * @param conn Connection A connection (session) with a specific database
     * @throws DatabaseException if unable to close Connection
     */
    public abstract void closeDbConnection(ResultSet rs,  Statement stmt,  Connection conn) throws DatabaseException;

    /** Authenticates a user given a username and password with the datasource
     * @param username String containing the user's unique username
     * @param password String containing the user's password for username
     * @return UserVo containing the user's values retrieved from datasource
     */
    public UserVo login(String username,  String password) throws DatabaseException  {
        logger.debug("Calling " + this.getClass().getName() + ".login");
        logger.debug("username=" + username);
        logger.debug("password=" + password);
        Connection conn = getConnection();
        logger.debug("--------- 1");
        if (conn == null) {
            throw new DatabaseException("Connection is null");
        }
        ResultSet rs = null;
        UserVo user = null;
        try  {
            PreparedStatement pStmt = conn.prepareStatement(LOGIN_STMT);
            if (pStmt == null) {
                throw new DatabaseException("PreparedStatement is null");
            }
            logger.debug("--------- 2");
            pStmt.setString(1,  username);
            pStmt.setString(2,  password);
            rs = pStmt.executeQuery();
            if (rs == null) {
                throw new DatabaseException("ResultSet is null");
            }
            logger.debug("--------- 3");
            while (rs.next())  {
                user = new UserVo();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getString("user_id"));
                user.setAddress(rs.getString("address"));
                user.setState(rs.getString("state"));
                user.setCity(rs.getString("city"));
                user.setCurrentRole(rs.getString("role_id"));
                user.setPhone(rs.getString("phone"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setZip(rs.getString("zipcode"));
            }
        } catch (Exception e)  {
            logger.debug(e.getMessage(),  e);
            logger.debug("----CLASSNAME=" + e.getClass().getName());
        }
        logger.debug("LEAVING UserDaoImpl.login");
        return user;
    }


    /** Gets the role assigned to the User
     * @param user UserVo containing populated value object
     * @throws DatabaseException general database exception
     * @return String containing the Role assigned to this user
     */
    public String getAssignedRole(UserVo user) throws DatabaseException  {
        logger.debug("Calling UserDaoImpl.getAssignedRole");
        Connection conn = getConnection();
        ResultSet rs = null;
        String roleName = null;
        try  {
            PreparedStatement pStmt = conn.prepareStatement(ROLE_STMT);
            pStmt.setString(1,  user.getUsername());
            rs = pStmt.executeQuery();
            while (rs.next())  {
                user = new UserVo();
                roleName = rs.getString("name");
                user.setCurrentRole(roleName);
            }
            logger.debug("roleName=" + user.getCurrentRole());
        } catch (Exception e)  {
            logger.debug(e.getMessage(),  e);
            logger.debug("----CLASSNAME=" + e.getClass().getName());
        }
        logger.debug("LEAVING UserDaoImpl.getAssignedRole");
        return roleName;
    }
    public void remove(String userId) throws DatabaseException {
        logger.debug("Calling BaseUserDao.remove userId=" + userId);
    }
    public void store(Object o) throws DatabaseException {
        logger.debug("Calling BaseUserDao.store");
    }
    public void create(Object o) throws DatabaseException {
        logger.debug("Calling BaseUserDao.create");
    }
    public Collection loadAll() throws DatabaseException {
        logger.debug("Calling BaseUserDao.loadAll");
        return null;
    }
    public Object load(String userId) throws DatabaseException {
        logger.debug("Calling BaseUserDao.load userId=" + userId);
        return null;
    }
}
