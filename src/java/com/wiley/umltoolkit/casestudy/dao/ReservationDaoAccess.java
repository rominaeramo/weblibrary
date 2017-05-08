package com.wiley.umltoolkit.casestudy.dao;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import java.util.Collection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import com.wiley.umltoolkit.casestudy.vo.ReservationVo;
import com.wiley.umltoolkit.casestudy.dao.common.AccessDaoFactory;
import org.apache.log4j.Logger;

/** ReservationDaoAccess implementation of the ItemDao interface. This class can
 * contain all Access specific code and SQL statements. Therefore, the client
 * does not need to know the implementation details
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class ReservationDaoAccess extends BaseReservationDao {
    
    /** Creates a new instance of ReservationDaoAccess */
    public ReservationDaoAccess()  {
        // initialization
    }
    
    public Connection getConnection() throws DatabaseException {
        Connection conn = AccessDaoFactory.getConnection();
        return conn;
    }
    public void closeDbConnection(ResultSet rs,  Statement stmt,  Connection conn) throws DatabaseException {
        AccessDaoFactory.closeDbConnection(rs, stmt, conn);
    }
    
}