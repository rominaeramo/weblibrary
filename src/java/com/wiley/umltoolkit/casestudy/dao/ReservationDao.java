package com.wiley.umltoolkit.casestudy.dao;
import com.wiley.umltoolkit.casestudy.dao.common.BaseDao;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import com.wiley.umltoolkit.casestudy.vo.ReservationVo;

/** Adds any Reservation specific data processing not handled by CRUD (Create,
 * Read,  Update,  Delete)
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public interface ReservationDao extends BaseDao  {
    
    public void reserve(String userId,  String itemId,  String reservedDateTime) throws DatabaseException;
    public void unreserve(String itemId) throws DatabaseException;
    public ReservationVo find(String itemId) throws DatabaseException;
}