package com.wiley.umltoolkit.casestudy.business.borrower;

import com.wiley.umltoolkit.casestudy.dao.common.DaoFactory;
import org.apache.log4j.Logger;

/** Manage creating,  updating,  retrieving,  and removing Borrowers for the
 * application.  A Borrower is a user of the application that may checkout
 * books and make reservations
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class BorrowerMgr  {
    
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private DaoFactory dbFactory = DaoFactory.getDaoFactory(DaoFactory.ACCESS);
    /** Uncomment to use MySQL
     * private DaoFactory dbFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
     */
    
    /** Do not allow instances to be created from constructor */
    private BorrowerMgr()  {
    }
    
    /** Returns a new BorrowerMgr (this way the constructor is not used
     * for creating new object which allows you to handle exeptions better)
     * @return BorrowerMgr
     */
    public static BorrowerMgr getInstance()  {
        return new BorrowerMgr();
    }
    
}