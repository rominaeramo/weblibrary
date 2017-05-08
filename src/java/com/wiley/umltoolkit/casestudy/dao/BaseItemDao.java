package com.wiley.umltoolkit.casestudy.dao;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import java.util.Collection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import com.wiley.umltoolkit.casestudy.vo.ItemVo;
import org.apache.log4j.Logger;

/** Base Data Access Object class that handles the create, read, update, and
 * delete methods for an Item.  This base class is used for defining behavior
 * that is similiar between the different data sources
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public abstract class BaseItemDao implements ItemDao {
    
    /** Creates a new instance of BaseItemDao */
    public BaseItemDao() {
    }
    
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    // Define the "Constants" used in this class
    private static final String SELECT_STMT = "SELECT ITEM_ID,  TITLE_ID,  "
    + " ADDED_DATE,  CHECKED_OUT,  USER_ID,  DUE_BACK,  COMMENTS FROM ITEM WHERE ITEM_ID = ? ";
    private static final String SELECT_ALL_STMT_FOR_TITLE = " SELECT ITEM_ID,  TITLE_ID,  "
    + " ADDED_DATE,  CHECKED_OUT,  USER_ID,  DUE_BACK,  COMMENTS FROM ITEM WHERE TITLE_ID = ? ";
    private static final String SELECT_ALL_STMT = "SELECT ITEM_ID,  TITLE_ID,  "
    + " ADDED_DATE,  CHECKED_OUT,  USER_ID,  DUE_BACK,  COMMENTS FROM ITEM WHERE ITEM_ID = ? ";
    private static final String INSERT_STMT = "INSERT INTO ITEM "
    + " ( TITLE_ID,  USER_ID,  COMMENTS) VALUES (?, ?, ?)";
    private static final String UPDATE_STMT = "UPDATE ITEM SET TITLE_ID = ?,  "
    + " ADDED_DATE = ?,  CHECKED_OUT = ?,  USER_ID = ?,  DUE_BACK = ? ,  COMMENTS = ?"
    + " WHERE ITEM_ID = ?  ";
    private static final String CHECKOUT_STMT = "UPDATE ITEM SET CHECKED_OUT = ?,  "
    + " USER_ID = ?,  DUE_BACK = ? WHERE ITEM_ID = ?  ";
    private static final String CHECKIN_STMT = "UPDATE ITEM SET CHECKED_OUT = ?,  "
    + " USER_ID = ?,  DUE_BACK = ? WHERE ITEM_ID = ?  ";
    private static final String DELETE_STMT = "DELETE FROM ITEM WHERE ITEM_ID = ?";    
    
    /** Load (Select) an existing record from the database
     * @param itemId String containing a unique value corresponding to the
     * item_id primary key in the ITEM table
     * @return Object containing values retrieved from the Item database
     */
    public Object load(String itemId) throws DatabaseException  {
        logger.debug("Calling ItemDaoImpl.load");
        Connection conn = getConnection();
        ResultSet rs = null;
        ItemVo item = null;
        try  {
            PreparedStatement pStmt = conn.prepareStatement(SELECT_STMT);
            pStmt.setString(1,  itemId);
            rs = pStmt.executeQuery();
            while (rs.next())  {
                item = new ItemVo();
                item.setItemId(rs.getString("ITEM_ID"));
                item.setTitleId(rs.getString("TITLE_ID"));
                item.getBorrower().setId(rs.getString("USER_ID"));
                item.setAddedDate(rs.getString("ADDED_DATE"));
                item.setCheckedOut(rs.getString("CHECKED_OUT"));
                item.setDueBack(rs.getString("DUE_BACK"));
                item.setComments(rs.getString("COMMENTS"));
            }
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
        }
        return item;
    }
    
    /** Loads All (Selects All) Item records from the ITEM table in the database
     * @return Collection of Object objects (each of which contain values
     * retrieved from the Library database
     */
    public Collection loadAll(String titleId) throws DatabaseException {
        logger.debug("Calling ItemDaoImpl.loadAll titleId=" + titleId);
        Connection conn = getConnection();
        Collection items = new ArrayList();
        ResultSet rs = null;
        ItemVo item = null;
        try  {
            PreparedStatement pStmt = conn.prepareStatement(SELECT_ALL_STMT_FOR_TITLE);
            pStmt.setString(1, titleId);
            rs = pStmt.executeQuery();
            while (rs.next())  {
                item = new ItemVo();
                item.setItemId(rs.getString("ITEM_ID"));
                item.setTitleId(rs.getString("TITLE_ID"));
                item.setAddedDate(rs.getString("ADDED_DATE"));
                item.getBorrower().setId(rs.getString("USER_ID"));
                item.setCheckedOut(rs.getString("CHECKED_OUT"));
                item.setDueBack(rs.getString("DUE_BACK"));
                item.setComments(rs.getString("COMMENTS"));
                items.add(item);
                item = null;
            }
            if (items != null)  {
                logger.debug("ItemDaoImpl.loadAll. Number of Records=" + items.size());
            }
            closeDbConnection(rs,  pStmt,  conn);
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
        return items;
    }
    
    /** Loads All (Selects All) Item records from the ITEM table in the database
     * @return Collection of Object objects (each of which contain values
     * retrieved from the Library database
     */
    public Collection loadAll() throws DatabaseException  {
        logger.debug("Calling ItemDaoImpl.loadAll");
        Connection conn = getConnection();
        Collection items = new ArrayList();
        ResultSet rs = null;
        ItemVo item = null;
        try  {
            PreparedStatement pStmt = conn.prepareStatement(SELECT_ALL_STMT);
            rs = pStmt.executeQuery();
            while (rs.next())  {
                item = new ItemVo();
                item.setItemId(rs.getString("ITEM_ID"));
                item.setTitleId(rs.getString("TITLE_ID"));
                item.getBorrower().setId(rs.getString("USER_ID"));
                item.setAddedDate(rs.getString("ADDED_DATE"));
                item.setCheckedOut(rs.getString("CHECKED_OUT"));
                item.setDueBack(rs.getString("DUE_BACK"));
                item.setComments(rs.getString("COMMENTS"));
                items.add(item);
                item = null;
            }
            if (items != null)  {
                logger.debug("ItemDaoImpl.loadAll. Number of Records=" + items.size());
            }
            closeDbConnection(rs,  pStmt,  conn);
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
        }
        return items;
    }
    /** Checks out an EXISTING item with a given check-out date,  borrower,  due-back date.
     * @param ItemVo contains values to checkout the item.
     */
    public void checkout(ItemVo item) throws DatabaseException {
        logger.debug("Calling ItemDaoImpl.checkout");
        Connection conn = getConnection();
        try  {
            PreparedStatement pStmt = conn.prepareStatement(CHECKOUT_STMT);
            pStmt.setString(1,  item.getCheckedOut());
            pStmt.setString(2,  item.getBorrower().getId());
            pStmt.setString(3,  item.getDueBack());
            pStmt.setString(4,  item.getItemId());
            pStmt.executeUpdate();
            conn.commit();
            closeDbConnection(null, pStmt, conn);
            logger.debug("Finished ItemDaoImpl.checkout");
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
    }
    /** Returns an ITEM to an available state.
     * @param ItemVo contains values to checkout the item.
     */
    public void checkin(String itemId) throws DatabaseException {
        logger.debug("Calling ItemDaoImpl.checkin");
        Connection conn = getConnection();
        try  {
            PreparedStatement pStmt = conn.prepareStatement(CHECKIN_STMT);
            pStmt.setString(1,  null);
            pStmt.setString(2,  null);
            pStmt.setString(3,  null);
            pStmt.setString(4,  itemId);
            pStmt.executeUpdate();
            conn.commit();
            closeDbConnection(null, pStmt, conn);
            logger.debug("Finished ItemDaoImpl.checkin");
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
    }
    
    /** Create one Item record in the ITEM table in the database
     * @param Object containing values retrieved from the user's input
     * on an HTML form (JSP)
     */
    public void create(Object myItem) throws DatabaseException {
        logger.debug("Calling ItemDaoImpl.create");
        ItemVo item = (ItemVo) myItem;
        Connection conn = getConnection();
        try  {
            PreparedStatement pStmt = conn.prepareStatement(INSERT_STMT);
            pStmt.setString(1,  item.getTitleId());
            pStmt.setString(2,  item.getBorrower().getId());
            pStmt.setString(3,  item.getComments());
            pStmt.executeUpdate();
            conn.commit();
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
        
    }
    
    /** Stores (Updates) data entered on an HTML form to an EXISTING item record
     * in the ITEM table in the database
     * @param Object containing values retrieved from the user's input
     * on an HTML form (JSP)
     */
    public void store(Object myItem) throws DatabaseException {
        logger.debug("Calling ItemDaoImpl.store");
        ItemVo item = (ItemVo) myItem;
        Connection conn = getConnection();
        logger.debug("ItemVo=" + item.toString());
        try  {
            PreparedStatement pStmt = conn.prepareStatement(UPDATE_STMT);
            pStmt.setString(1,  item.getTitleId());
            pStmt.setString(2,  item.getAddedDate());
            pStmt.setString(3,  item.getCheckedOut());
            pStmt.setString(4,  item.getBorrower().getId());
            pStmt.setString(5,  item.getDueBack());
            pStmt.setString(6,  item.getComments());
            pStmt.setString(6,  item.getItemId());
            pStmt.execute();
            conn.commit();
            closeDbConnection(null,  pStmt, conn);
            logger.debug("Finished ItemDaoImpl.store");
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
    }
    
    /** Removes an EXISTING Item record from the ITEM table in the database
     * @param String containing the unique ID corresponding to the Item record
     * in the ITEM table in the database
     */
    public void remove(String itemId) throws DatabaseException {
        logger.debug("Calling ItemDaoImpl.remove itemId=" + itemId);
        Connection conn = getConnection();
        ResultSet rs = null;
        String myUsername = null;
        String myPassword = null;
        try  {
            PreparedStatement pStmt = conn.prepareStatement(DELETE_STMT);
            pStmt.setString(1,  itemId);
            pStmt.executeUpdate();
            conn.commit();
            closeDbConnection(null, pStmt, conn);
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
        
    }
    
}
