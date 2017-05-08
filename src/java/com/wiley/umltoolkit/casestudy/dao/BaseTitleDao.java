package com.wiley.umltoolkit.casestudy.dao;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import java.util.Collection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import com.wiley.umltoolkit.casestudy.vo.TitleVo;
import org.apache.log4j.Logger;

/** Base Data Access Object class that handles the create, read, update, and
 * delete methods for a Title.  This base class is used for defining behavior
 * that is similiar between the different data sources
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public abstract class BaseTitleDao implements TitleDao {
    
    /** Creates a new instance of BaseTitleDao */
    public BaseTitleDao() {
    }
    
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    private static final String SELECT_ALL_STMT = " SELECT T.TITLE_ID,  T.NAME,   T.AUTHOR, "
    + " T.ISBN,  T.TITLE_KIND_ID, T.DESCRIPTION,  TK.NAME AS TITLE_KIND_DESCRIPTION "
    + " FROM TITLE T,  TITLE_KIND TK WHERE T.TITLE_KIND_ID = TK.TITLE_KIND_ID ";
    private static final String SELECT_STMT = " SELECT T.TITLE_ID,  T.NAME,   T.AUTHOR, "
    + " T.ISBN,  T.TITLE_KIND_ID,  T.DESCRIPTION,  TK.NAME AS TITLE_KIND_DESCRIPTION "
    + " FROM TITLE T,  TITLE_KIND TK WHERE T.TITLE_KIND_ID = TK.TITLE_KIND_ID AND T.TITLE_ID = ? ";
    private static final String UPDATE_STMT = " UPDATE TITLE SET NAME = ?,  "
    + " AUTHOR = ?,  ISBN = ? WHERE TITLE_ID = ?  ";
    private static final String INSERT_STMT = "INSERT INTO TITLE "
    + " (NAME,  AUTHOR,  ISBN,  TITLE_KIND_ID,  DESCRIPTION) VALUES (?, ?, ?, ?, ?) ";
    private static final String DELETE_STMT = " DELETE FROM TITLE WHERE TITLE_ID = ? ";
    
    private static final String FIND_TITLE_STMT = " SELECT T.TITLE_ID,  T.NAME,   T.AUTHOR, "
    + " T.ISBN,  T.TITLE_KIND_ID,  T.DESCRIPTION,  TK.NAME AS TITLE_KIND_DESCRIPTION "
    + " FROM TITLE T,  TITLE_KIND TK WHERE T.TITLE_KIND_ID = TK.TITLE_KIND_ID AND T.NAME Like ? ";
    /**
     * Character Description Example
     * % Matches any number of characters. It can be used as the first or last character in the character string. wh% finds what,  white,  and why
     * _ Matches any single alphabetic character. B_ll finds ball,  bell,  and bill
     * [ ] Matches any single character within the brackets. B[ae]ll finds ball and bell but not bill
     * ^ Matches any character not in the brackets. b[^ae]ll finds bill and bull but not ball or bell
     * - Matches any one of a range of characters. You must specify the range in ascending order (A to Z,  not Z to A). b[a-c]d finds bad,  bbd,  and bcd
     *
     */
    private static final String ANY_NUMBER_CHARACTERS = "%";
    private static final String SINGLE_ALPHABETIC_CHARACTER = "_";
    private static final String SINGLE_CHARACTER_WITHIN_BRACKETS_LEFT = "[";
    private static final String SINGLE_CHARACTER_WITHIN_BRACKETS_RIGHT = "]";
    private static final String NOT_IN_BRACKETS = "^";
    private static final String ANY_ONE_OF_RANGE_OF_CHARACTERS = "-";
    
    /** Load all Title records as a Collection of TiteVos
     * @return Collection containing a collecton of TitleVos
     * @throws DatabaseException if an error occrs when load records
     */
    public Collection loadAll() throws DatabaseException  {
        Collection titles = new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        TitleVo title = null;
        try  {
            pStmt = conn.prepareStatement(SELECT_ALL_STMT);
            rs = pStmt.executeQuery();
            while (rs.next())  {
                title = new TitleVo();
                title.setTitleId(rs.getString("TITLE_ID"));
                title.setName(rs.getString("NAME"));
                title.setAuthor(rs.getString("AUTHOR"));
                title.setIsbn(rs.getString("ISBN"));
                title.getTitleKind().setId(rs.getString("TITLE_KIND_ID"));
                title.getTitleKind().setDescription(rs.getString("TITLE_KIND_DESCRIPTION"));
                title.setDescription(rs.getString("DESCRIPTION"));
                titles.add(title);
            }
            closeDbConnection(rs,  pStmt,  conn);
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
        return titles;
    }
    
    /** Load all Title records as a Collection of TiteVos given search criteria
     * @param String a search patttern for a Title
     * @return Collection containing a collecton of TitleVos
     * @throws DatabaseException if an error occrs when load records
     */
    public Collection loadAll(String titlePattern) throws DatabaseException  {
        Collection titles = new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        TitleVo title = null;
        try  {
            logger.debug("Trying SQL = " + FIND_TITLE_STMT);
            pStmt = conn.prepareStatement(FIND_TITLE_STMT);
            pStmt.setString(1,  titlePattern);
            rs = pStmt.executeQuery();
            while (rs.next())  {
                title = new TitleVo();
                title.setTitleId(rs.getString("TITLE_ID"));
                title.setName(rs.getString("NAME"));
                title.setAuthor(rs.getString("AUTHOR"));
                title.setIsbn(rs.getString("ISBN"));
                title.getTitleKind().setId(rs.getString("TITLE_KIND_ID"));
                title.getTitleKind().setDescription(rs.getString("TITLE_KIND_DESCRIPTION"));
                title.setDescription(rs.getString("DESCRIPTION"));
                titles.add(title);
            }
            closeDbConnection(rs,  pStmt,  conn);
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        } catch (Exception e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
        return titles;
    }
    
    /** Load (Select) an existing record from the database
     * @param titleId String containing a unique value corresponding for the Title
     * @return Object containing values retrieved from the Library database as
     * a TitleVo
     */
    public Object load(String titleId) throws DatabaseException {
        logger.debug("Calling TitleDaoImpl.load");
        Connection conn = getConnection();
        ResultSet rs = null;
        String id = null;
        String name = null;
        String author = null;
        String isbn = null;
        String titleType = null;
        String numberAvailable = null;
        TitleVo title = null;
        try  {
            PreparedStatement pStmt = conn.prepareStatement(SELECT_STMT);
            pStmt.setString(1,  titleId);
            rs = pStmt.executeQuery();
            while (rs.next())  {
                title = new TitleVo();
                title.setTitleId(rs.getString("TITLE_ID"));
                title.setName(rs.getString("NAME"));
                title.setAuthor(rs.getString("AUTHOR"));
                title.setIsbn(rs.getString("ISBN"));
                title.getTitleKind().setId(rs.getString("TITLE_KIND_ID"));
                title.getTitleKind().setDescription(rs.getString("TITLE_KIND_DESCRIPTION"));
                title.setDescription(rs.getString("DESCRIPTION"));
            }
            logger.debug("title.getName" + title.getName());
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
        return title;
    }
    
    /** Create one Title record in the TITLE table in the database
     * @param Object cast as a TitleVo containing input entered by user
     * @throws DatabaseException if unable to create a record in DB
     */
    public void create(Object myTitle) throws DatabaseException {
        logger.debug("Calling TitleDaoImpl.create");
        TitleVo title = (TitleVo) myTitle;
        Connection conn = getConnection();
        try  {
            PreparedStatement pStmt = conn.prepareStatement(INSERT_STMT);
            pStmt.setString(1,  title.getName());
            pStmt.setString(2,  title.getAuthor());
            pStmt.setString(3,  title.getIsbn());
            pStmt.setString(4,  title.getTitleKind().getId());
            pStmt.setString(5,  title.getDescription());
            pStmt.execute();
            conn.commit();
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
        
    }    
    
    /** Update one Title record in the TITLE table in the database
     * @param Object cast as a TitleVo containing input entered by user
     * @throws DatabaseException if unable to update this record in DB
     */
    public void store(Object myTitle) throws DatabaseException {
        logger.debug("Calling TitleDaoImpl.store");
        TitleVo title = (TitleVo) myTitle;
        Connection conn = getConnection();
        try  {
            PreparedStatement pStmt = conn.prepareStatement(UPDATE_STMT);
            pStmt.setString(1,  title.getName());
            pStmt.setString(2,  title.getAuthor());
            pStmt.setString(3,  title.getIsbn());
            pStmt.setString(4,  title.getTitleId());
            pStmt.execute();
            conn.commit();
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
    }
    
    /** Remove one Title record in the TITLE table in the database
     * @param String containing the unique id for a Title
     * @throws DatabaseException if unable to remove this record from DB
     */
    public void remove(String titleId) throws DatabaseException {
        logger.debug("Calling TitleDaoImpl.remove titleId=" + titleId);
        Connection conn = getConnection();
        ResultSet rs = null;
        try  {
            PreparedStatement pStmt = conn.prepareStatement(DELETE_STMT);
            pStmt.setString(1,  titleId);
            pStmt.execute();
            conn.commit();
        } catch (SQLException e)  {
            logger.debug(e.getMessage(),  e);
            throw new DatabaseException(e.getMessage());
        }
        
    }
    
}
