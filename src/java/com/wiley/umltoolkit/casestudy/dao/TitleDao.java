package com.wiley.umltoolkit.casestudy.dao;
import com.wiley.umltoolkit.casestudy.dao.common.BaseDao;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import java.util.Collection;

/** Adds any Title specific data processing not handled by CRUD (Create,
 * Read,  Update,  Delete)
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public interface TitleDao extends BaseDao  {
    
    /** Finds a collection of business objects (i.e. Value Objects)
     * @return Collection containing collection of business objects (i.e. Value Objects)
     * @throws DatabaseException general db error
     */
    public Collection loadAll(String titlePattern) throws DatabaseException;
    
}
