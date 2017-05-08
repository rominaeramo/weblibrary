package com.wiley.umltoolkit.casestudy.dao;
import com.wiley.umltoolkit.casestudy.dao.common.MySqlDaoFactory;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import com.wiley.umltoolkit.casestudy.vo.ReservationVo;
import java.util.Collection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/** Base Data Access Object for a Reservaiton
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public abstract class BaseReservationDao implements ReservationDao {
    
    /** Creates a new instance of BaseReservationDao */
    public BaseReservationDao() {
    }
    
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    private static final String RESERVE_STMT = "INSERT INTO RESERVATION "
    + " ( ITEM_ID,  USER_ID,  DATE_TIME_CREATED) VALUES (?, ?, ?)";
    
    private static final String UNRESERVE_STMT = "DELETE FROM RESERVATION WHERE ITEM_ID = ? ";
    private static final String LOAD_STMT = "SELECT RESERVATION_ID,  ITEM_ID,  DATE_TIME_CREATED,  USER_ID FROM RESERVATION WHERE RESERVATION_ID = ? ";
    private static final String FIND_ITEM_STMT = "SELECT RESERVATION_ID,  ITEM_ID,  DATE_TIME_CREATED,  USER_ID FROM RESERVATION WHERE ITEM_ID = ? ";
    private static final String FIND_ITEM_USER_STMT = "SELECT RESERVATION_ID,  ITEM_ID,  DATE_TIME_CREATED,  USER_ID FROM RESERVATION WHERE ITEM_ID = ? and USER_ID = ? ";
    
    public void reserve(String reserverId,  String itemId,  String reservedDateTime) throws DatabaseException {
        logger.debug("Calling ItemDaoImpl.reserve reserverId=" + reserverId + ",  itemId=" + itemId + ",  reservedDateTime=" + reservedDateTime);
        Connection conn = getConnection();
        try  {
            PreparedStatement pStmt = conn.prepareStatement(RESERVE_STMT);
            pStmt.setString(1,  itemId);
            pStmt.setString(2,  reserverId);
            pStmt.setString(3,  reservedDateTime);
            pStmt.executeUpdate();
            conn.commit();
            closeDbConnection(null, pStmt, conn);
            logger.debug("Finished ReservationDaoImpl.reserve");
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
    }
    
    public void unreserve(String itemId) throws DatabaseException {
        logger.debug("Calling ItemDaoImpl.unreserve itemId=" + itemId);
        Connection conn = getConnection();
        try  {
            PreparedStatement pStmt = conn.prepareStatement(UNRESERVE_STMT);
            pStmt.setString(1,  itemId);
            pStmt.executeUpdate();
            conn.commit();
            closeDbConnection(null, pStmt, conn);
            logger.debug("Finished ReservationDaoImpl.unreserve");
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
    }
    public void remove(String itemId) throws DatabaseException {
        logger.debug("Calling ReservationDaoImpl.remove itemId=" + itemId);
    }
    public void store(Object o) throws DatabaseException {
        logger.debug("Calling ReservationDaoImpl.store");
    }
    public Object load(String reservationId) throws DatabaseException {
        logger.debug("Calling ReservationDaoImpl.load reservationId=" + reservationId);
        Connection conn = getConnection();
        ResultSet rs = null;
        ReservationVo resv = null;
        try  {
            PreparedStatement pStmt = conn.prepareStatement(LOAD_STMT);
            pStmt.setString(1,  reservationId);
            rs = pStmt.executeQuery();
            while (rs.next())  {
                resv = new ReservationVo();
                resv.setReservationId(rs.getString("RESERVATION_ID"));
                resv.setItemId(rs.getString("ITEM_ID"));
                resv.setDateTimeReserved(rs.getString("DATE_TIME_CREATED"));
                resv.setUserId(rs.getString("USER_ID"));
                
            }
            if (resv != null) {
                logger.debug(",  resv.reservationId=" + resv.getReservationId());
            }
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
        }
        return resv;
    }
    public ReservationVo find(String itemId) throws DatabaseException {
        logger.debug("Calling ReservationDaoImpl.find itemId=" + itemId);
        Connection conn = getConnection();
        ResultSet rs = null;
        ReservationVo resv = null;
        try  {
            PreparedStatement pStmt = conn.prepareStatement(FIND_ITEM_STMT);
            pStmt.setString(1,  itemId);
            rs = pStmt.executeQuery();
            while (rs.next())  {
                resv = new ReservationVo();
                resv.setReservationId(rs.getString("RESERVATION_ID"));
                resv.setItemId(rs.getString("ITEM_ID"));
                resv.setDateTimeReserved(rs.getString("DATE_TIME_CREATED"));
                resv.setUserId(rs.getString("USER_ID"));
                
            }
            if (resv != null) {
                logger.debug(",  resv.reservationId=" + resv.getReservationId());
            }
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
        }
        return resv;
    }
    public void create(Object o) throws DatabaseException {
        logger.debug("Calling ReservationDaoImpl.create");
    }
    public Collection loadAll() throws DatabaseException {
        logger.debug("Calling ReservationDaoImpl.loadAll");
        return null;
    }
    
}
