package com.wiley.umltoolkit.casestudy.dao;
import com.wiley.umltoolkit.casestudy.dao.common.BaseDao;
import com.wiley.umltoolkit.casestudy.vo.ItemVo;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import java.util.Collection;

/** Adds any Item specific data processing not handled by CRUD (Create,
 * Read,  Update,  Delete)
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public interface ItemDao extends BaseDao  {

    public Collection loadAll(String titleId) throws DatabaseException;
    public void checkout(ItemVo item) throws DatabaseException;
    public void checkin(String itemId) throws DatabaseException;

}