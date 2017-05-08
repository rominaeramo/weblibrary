package com.wiley.umltoolkit.casestudy.dao;
import com.wiley.umltoolkit.casestudy.dao.common.BaseDao;
import com.wiley.umltoolkit.casestudy.vo.UserVo;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;

/** Adds any User specific data processing not handled by CRUD (Create,
 * Read,  Update,  Delete)
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public interface UserDao extends BaseDao {

    /** Verifies that the username and password exists in the database; if it
     * does,  it returns a populated User value object
     * @param username String containing a username
     * @param password String containing a password
     * @throws DatabaseException Username or Password is incorrect or unable to
     * retrieve necessary data to populate object
     * @return UserVo containing populated User value object in success
     */
    public UserVo login(String username,  String password) throws DatabaseException;

    public String getAssignedRole(UserVo user) throws DatabaseException;

}