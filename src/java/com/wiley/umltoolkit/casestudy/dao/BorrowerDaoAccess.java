package com.wiley.umltoolkit.casestudy.dao;
import com.wiley.umltoolkit.casestudy.dao.common.AccessDaoFactory;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import com.wiley.umltoolkit.casestudy.vo.BorrowerVo;
import java.util.Collection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/** Manages Microsoft Access specific Borrower data access 
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class BorrowerDaoAccess extends BaseBorrowerDao {
    
    /** Constructs a new BorrowerDao for Microsoft Access */
    public BorrowerDaoAccess()  {
        // initialization
    }
    
    /** Get a connection to the Microsoft Access Library database
     * @return Connection containing a connection to Database
     * @throws DatabaseException if unable to get Connection
     */
    public Connection getConnection() throws DatabaseException {
        Connection conn = AccessDaoFactory.getConnection();
        return conn;
    }
    
    /** Close the connection to the Microsoft Access Library database
     * @param rs ResultSet A table of data representing a database result set
     * @param stmt Statement The object used for executing a static SQL statement
     * and obtaining the results produced by it
     * @param conn Connection A connection (session) with a specific database
     * @throws DatabaseException if unable to close Connection
     */
    public void closeDbConnection(ResultSet rs,  Statement stmt,  Connection conn) throws DatabaseException {
        AccessDaoFactory.closeDbConnection(rs, stmt, conn);
    }
    
}