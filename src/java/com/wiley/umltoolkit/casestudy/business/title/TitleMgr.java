package com.wiley.umltoolkit.casestudy.business.title;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import com.wiley.umltoolkit.casestudy.common.ManagerException;
import com.wiley.umltoolkit.casestudy.dao.common.DaoFactory;
import com.wiley.umltoolkit.casestudy.vo.TitleVo;
import java.util.Collection;
import org.apache.log4j.Logger;

/** A Title represents general information about a Item
 * There is ONE title to zero or more ITEMS
 * The TitleMgr manages all of the business logic for a Title such as finding
 * a Title
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class TitleMgr  {
    
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private DaoFactory dbFactory = DaoFactory.getDaoFactory(DaoFactory.ACCESS);
    /** Uncomment to use MySQL
     * private DaoFactory dbFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
     */
    
    /** Do not allow instances to be created from constructor */
    private TitleMgr() {
    }
    
    /** Returns an instance of the class
     * @return TitleMgr returns an instance of this class
     */
    public static TitleMgr getInstance() {
        return new TitleMgr();
    }
    
    /**
     * Find all titles within the library.
     * @return Collection the list of TitleVo(s) which contain information about each title
     * @throws ManagerException if an error occurs when retrieving titles
     */
    public Collection findAllTitles() throws ManagerException  {
        Collection titles = null;
        try {
            titles = dbFactory.getTitleDao().loadAll();
        } catch (DatabaseException databaseException) {
            throw new ManagerException(databaseException.getMessage());
        }
        return titles;
    }
    /** Find all titles within the library given following title pattern (search criteria)
     * @return Collection the list of TitleVo(s) which contain information about each title
     * @param titlePattern String containing the search criteria entered for a Title
     * @throws ManagerException if an error occurs when retrieving titles
     */
    public Collection findAllTitles(String titlePattern) throws ManagerException  {
        Collection titles = null;
        try {
            titles = dbFactory.getTitleDao().loadAll(titlePattern);
        } catch (DatabaseException databaseException) {
            throw new ManagerException(databaseException.getMessage());
        }
        return titles;
    }
    /** Load the information for the given unique key for the title.
     * @return Collection the list of TitleVo(s) which contain information about each title.
     * @param titleId String containing a unique Title in the system
     * @throws ManagerException if an error occurs when loading a specific title
     */
    public TitleVo load(String titleId) throws ManagerException {
        TitleVo title = null;
        try {
            title = (TitleVo) dbFactory.getTitleDao().load(titleId);
        } catch (DatabaseException databaseException) {
            throw new ManagerException(databaseException.getMessage());
        }
        return title;
    }
}