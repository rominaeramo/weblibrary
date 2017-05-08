package com.wiley.umltoolkit.casestudy.dao.common;
import com.wiley.umltoolkit.casestudy.dao.BorrowerDao;
import com.wiley.umltoolkit.casestudy.dao.ItemDao;
import com.wiley.umltoolkit.casestudy.dao.ReservationDao;
import com.wiley.umltoolkit.casestudy.dao.TitleDao;
import com.wiley.umltoolkit.casestudy.dao.UserDao;
import com.wiley.umltoolkit.casestudy.dao.BorrowerDaoAccess;
import com.wiley.umltoolkit.casestudy.dao.ItemDaoAccess;
import com.wiley.umltoolkit.casestudy.dao.ReservationDaoAccess;
import com.wiley.umltoolkit.casestudy.dao.TitleDaoAccess;
import com.wiley.umltoolkit.casestudy.dao.UserDaoMySql;
import com.wiley.umltoolkit.casestudy.dao.BorrowerDaoMySql;
import com.wiley.umltoolkit.casestudy.dao.ItemDaoMySql;
import com.wiley.umltoolkit.casestudy.dao.ReservationDaoMySql;
import com.wiley.umltoolkit.casestudy.dao.TitleDaoMySql;
import com.wiley.umltoolkit.casestudy.dao.UserDaoMySql;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/** Concrete Factory for creating MySQL Data Access Objects 
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class MySqlDaoFactory extends DaoFactory  {

    private static Logger logger = Logger.getLogger(MySqlDaoFactory.class.getName());
    // @todo Get from a properties file
    private static String URL = "jdbc:mysql://localhost/library";
    private static String DRIVER = "com.mysql.jdbc.Driver";

    /** Returns a MySQL connection
     * @throws DatabaseException thrown when unable to get connection
     * @return Connection containing a connection to the Library database
     */
    public static Connection getConnection() throws DatabaseException  {
        Connection conn = null;

        try  {
            /** used by MySQL */
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(URL,  "",  "");
            /** used by MySQL */
            conn.setAutoCommit(false);

        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        } catch (ClassNotFoundException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        } catch (InstantiationException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        } catch (IllegalAccessException e)  {
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
    public static void closeDbConnection(ResultSet rs,  Statement stmt,  Connection conn) throws DatabaseException {
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
        return new ItemDaoMySql();
    }

    /** Returns a new Title object
     * @return TitleDao representing a Title in the system
     */
    public TitleDao getTitleDao()  {
        return new TitleDaoMySql();
    }

    /** Returns a new User object
     * @return UserDao representing a User in the system
     */
    public UserDao getUserDao()  {
        return new UserDaoMySql();
    }

    /** Returns a new Borrower object
     * @return BorrowerDao representing a Borrower in the system
     */
    public BorrowerDao getBorrowerDao()  {
        return new BorrowerDaoMySql();
    }

    /** Returns a new Reservation object
     * @return ReservationDao representing a Reservation in the system
     */
    public ReservationDao getReservationDao()  {
        return new ReservationDaoMySql();
    }

}