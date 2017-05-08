package com.wiley.umltoolkit.casestudy.dao;
import com.wiley.umltoolkit.casestudy.dao.common.MySqlDaoFactory;
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

/** Base Data Access Object class that handles the create, read, update, and
 * delete methods for a Borrower.  This base class is used for defining behavior that is similiar
 * between the different data sources
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public abstract class BaseBorrowerDao implements BorrowerDao {
    
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    private static final String SELECT_STMT = "SELECT BORROWER_ID,  FIRST_NAME,  "
    + " LAST_NAME,  ADDRESS,  CITY,  STATE,  ZIP FROM BORROWER WHERE BORROWER_ID = ?";
    private static final String SELECT_ALL_STMT = "SELECT BORROWER_ID,  "
    + " FIRST_NAME,  LAST_NAME,  ADDRESS,  CITY,  STATE,  ZIP FROM BORROWER";
    private static final String INSERT_STMT = "INSERT INTO BORROWER "
    + " (FIRST_NAME,  LAST_NAME,  ADDRESS,  CITY,  STATE,  ZIP) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_STMT = "UPDATE BORROWER SET FIRST_NAME = ?,  "
    + "LAST_NAME = ?,  ADDRESS = ?,  CITY = ?,  STATE = ?,  ZIP = ? WHERE BORROWER_ID = ?  ";
    private static final String DELETE_STMT = "DELETE FROM BORROWER WHERE BORROWER_ID = ?";
    
    /** Creates a new instance of BaseBorrowerDao */
    public BaseBorrowerDao() {
    }    
    
    /** Load (Select) an existing record from the database
     * @return Object containing values retrieved from the for a single record
     * in the BORROWER table
     * @param borrowerId String containing a unique value corresponding to the
     * borrower_id primary key in the BORROWER table
     * @throws DatabaseException thrown when unable to load this Borrower record
     */
    public Object load(String borrowerId) throws DatabaseException  {
        logger.debug("Calling BorrowerDao.load");
        Connection conn = getConnection();
        ResultSet rs = null;
        String id = null;
        String firstName = null;
        String lastName = null;
        String address = null;
        String city = null;
        String state = null;
        Object zip = null;
        BorrowerVo borrower = null;
        try  {
            PreparedStatement pStmt = conn.prepareStatement(SELECT_STMT);
            pStmt.setString(1,  borrowerId);
            rs = pStmt.executeQuery();
            while (rs.next())  {
                borrower = new BorrowerVo();
                borrower.setId(rs.getString("BORROWER_ID"));
                borrower.setFirstName(rs.getString("FIRST_NAME"));
                borrower.setLastName(rs.getString("LAST_NAME"));
                borrower.setAddress(rs.getString("ADDRESS"));
                borrower.setCity(rs.getString("CITY"));
                borrower.setState(rs.getString("STATE"));
                borrower.setZip(rs.getString("ZIP"));
            }
            if (borrower != null)  {
                logger.debug("borrower.getLastName" + borrower.getLastName());
            }
        } catch (SQLException e)  {
            logger.debug(e.getMessage(), e);
        }
        return borrower;
    }
    
    /** Loads All (Selects All) Borrower records from the BORROWER table in
     * the database
     * @return Collection of Object objects (each of which contain values
     * retrieved from the Library database
     * @throws DatabaseException thrown when unable to load all Borrower records
     */
    public Collection loadAll() throws DatabaseException  {
        logger.debug("Calling BorrowerDaoImpl.loadAll");
        Connection conn = getConnection();
        Collection borrowers = new ArrayList();
        ResultSet rs = null;
        BorrowerVo borrower = null;
        try  {
            PreparedStatement pStmt = conn.prepareStatement(SELECT_ALL_STMT);
            rs = pStmt.executeQuery();
            while (rs.next())  {
                borrower = new BorrowerVo();
                borrower.setId(rs.getString("BORROWER_ID"));
                borrower.setFirstName(rs.getString("FIRST_NAME"));
                borrower.setLastName(rs.getString("LAST_NAME"));
                borrower.setAddress(rs.getString("ADDRESS"));
                borrower.setCity(rs.getString("CITY"));
                borrower.setState(rs.getString("STATE"));
                borrower.setZip(rs.getString("ZIP"));
                borrowers.add(borrower);
                borrower = null;
            }
            if (borrowers != null)  {
                logger.debug("BorrowerDaoImpl.loadAll. Number of Records=" + borrowers.size());
            }
            closeDbConnection(rs,  pStmt,  conn);
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
        }
        return borrowers;
    }
    
    /** Create one Borrower record in the BORROWER table in the database
     * @param myBorrower Generic object cast to a BorrowerVo object
     * @throws DatabaseException thrown when unable to create this Borrower
     */
    public void create(Object myBorrower) throws DatabaseException  {
        logger.debug("Calling BorrowerDaoImpl.create");
        BorrowerVo borrower = (BorrowerVo) myBorrower;
        Connection conn = getConnection();
        try  {
            PreparedStatement pStmt = conn.prepareStatement(INSERT_STMT);
            pStmt.setString(1,  borrower.getFirstName());
            pStmt.setString(2,  borrower.getLastName());
            pStmt.setString(3,  borrower.getAddress());
            pStmt.setString(4,  borrower.getCity());
            pStmt.setString(5,  borrower.getState());
            pStmt.setString(6,  borrower.getZip());
            pStmt.execute();
            conn.commit();
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
        }
        
    }
    
    /** Stores (Updates) data entered on an HTML form to an EXISTING Borrower record
     * in the BORROWER table in the database
     * @param myBorrower Generic object cast to a BorrowerVo object
     * @throws DatabaseException thrown when unable to store this Borrower
     */
    public void store(Object myBorrower) throws DatabaseException  {
        logger.debug("Calling BorrowerDaoImpl.store");
        BorrowerVo borrower = (BorrowerVo) myBorrower;
        Connection conn = getConnection();
        try  {
            PreparedStatement pStmt = conn.prepareStatement(UPDATE_STMT);
            pStmt.setString(1,  borrower.getFirstName());
            pStmt.setString(2,  borrower.getLastName());
            pStmt.setString(3,  borrower.getAddress());
            pStmt.setString(4,  borrower.getCity());
            pStmt.setString(5,  borrower.getState());
            pStmt.setString(6,  borrower.getZip());
            pStmt.setString(7,  borrower.getId());
            pStmt.execute();
            conn.commit();
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
        }
    }
    
    /** Removes an EXISTING Borrower record from the BORROWER table in the database
     * @param borrowerId String representing a borrower ID referred to in the
     * BorrowerVo
     * @throws DatabaseException thrown when unable to remove this Borrower
     */
    public void remove(String borrowerId) throws DatabaseException  {
        logger.debug("Calling BorrowerDaoImpl.remove borrowerId=" + borrowerId);
        Connection conn = getConnection();
        try  {
            PreparedStatement pStmt = conn.prepareStatement(DELETE_STMT);
            pStmt.setString(1,  borrowerId);
            pStmt.execute();
            conn.commit();
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
        }
        
    }
    
}
