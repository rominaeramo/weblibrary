package com.wiley.umltoolkit.casestudy.dao.common;
import com.wiley.umltoolkit.casestudy.dao.ItemDao;
import com.wiley.umltoolkit.casestudy.dao.ReservationDao;
import com.wiley.umltoolkit.casestudy.dao.BorrowerDao;
import com.wiley.umltoolkit.casestudy.dao.UserDao;
import com.wiley.umltoolkit.casestudy.dao.TitleDao;
import com.wiley.umltoolkit.casestudy.dao.ItemDaoAccess;
import com.wiley.umltoolkit.casestudy.dao.ReservationDaoAccess;
import com.wiley.umltoolkit.casestudy.dao.BorrowerDaoAccess;
import com.wiley.umltoolkit.casestudy.dao.UserDaoAccess;
import com.wiley.umltoolkit.casestudy.dao.TitleDaoAccess;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import org.apache.log4j.Logger;

/** Concrete Factory for creating Access Data Access Objects
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class AccessDaoFactory extends DaoFactory  {
    
    private static Logger logger = Logger.getLogger(AccessDaoFactory.class.getName());
    // @todo Get from a properties file
    private static String URL = "jdbc:odbc:LIBRARY";
    private static String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
    
    /** Returns a Microsoft Access connection
     * @throws DatabaseException thrown when unable to get connection
     * @return Connection containing a connection to the Library database
     */
    public static Connection getConnection() throws DatabaseException  {
        Connection conn = null;
        
        try  {
            /** used by MS Access */
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,  "",  "");
            
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        } catch (ClassNotFoundException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
        return conn;
    }
    
    /** Convenience method for closing a ResultSet,  Statement,  and Database Connection
     * @param rs ResultSet A table of data representing a database result set
     * @param stmt Statement The object used for executing a static SQL statement
     * and obtaining the results produced by it
     * @param conn Connection A connection (session) with a specific database
     * @throws DatabaseException thrown when unable to close DB Connection
     */
    public static void closeDbConnection(ResultSet rs,  Statement stmt,  Connection conn) throws DatabaseException  {
        try  {
            if (rs != null)  {
                rs.close();
            }
            if (stmt != null)  {
                stmt.close();
            }
            if (conn != null)  {
                conn.close();
            }
        } catch (SQLException se)  {
            logger.debug(se.getMessage(),  se);
            throw new DatabaseException(se.toString());
        }
    }
    
    /** Returns a new Item object
     * @return ItemDao representing an Item in the system
     */
    public ItemDao getItemDao()  {
        return new ItemDaoAccess();
    }
    
    /** Returns a new Title object
     * @return TitleDao representing a Title in the system
     */
    public TitleDao getTitleDao()  {
        return new TitleDaoAccess();
    }
    
    /** Returns a new User object
     * @return UserDao representing a User in the system
     */
    public UserDao getUserDao()  {
        return new UserDaoAccess();
    }
    
    /** Returns a new Borrower object
     * @return BorrowerDao representing a Borrower in the system
     */
    public BorrowerDao getBorrowerDao()  {
        return new BorrowerDaoAccess();
    }
    
    /** Returns a new Reservation object
     * @return ReservationDao representing a Reservation in the system
     */
    public ReservationDao getReservationDao()  {
        return new ReservationDaoAccess();
    }
    
}
