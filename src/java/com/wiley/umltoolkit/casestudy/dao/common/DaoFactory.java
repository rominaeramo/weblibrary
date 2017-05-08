package com.wiley.umltoolkit.casestudy.dao.common;
import com.wiley.umltoolkit.casestudy.dao.ItemDao;
import com.wiley.umltoolkit.casestudy.dao.ReservationDao;
import com.wiley.umltoolkit.casestudy.dao.BorrowerDao;
import com.wiley.umltoolkit.casestudy.dao.UserDao;
import com.wiley.umltoolkit.casestudy.dao.TitleDao;

/** Base DAO Factory used by concrete DAO Factories
 * Derived from "Core J2EE Patterns" (Chapter 9) by Deepak Alur, et al
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public abstract class DaoFactory  {
    
    // DAO types supported by the factory
    public static final int MYSQL = 1;
    public static final int ACCESS = 2;
    
    /*
     Requisite "Abstract Base Class with Virtual Methods"
     There will be a method for each DAO that can be
     created. The concrete factories will have to implement these methods.
     */
    public abstract ItemDao getItemDao();
    
    public abstract TitleDao getTitleDao();
    
    public abstract UserDao getUserDao();
    
    public abstract BorrowerDao getBorrowerDao();
    
    public abstract ReservationDao getReservationDao();
    
    
    /** Returns the requested DAO Factory concrete implementation
     * @param factoryType int containing a Constant the represents the DAO
     * Factory type
     * @return DaoFactory
     */
    public static DaoFactory getDaoFactory(int factoryType)  {
        switch (factoryType)  {
            case MYSQL:
                return new MySqlDaoFactory();
            case ACCESS:
                return new AccessDaoFactory();
            default:
                return null;
        }
    }
    
}