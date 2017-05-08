package com.wiley.umltoolkit.casestudy.business.item;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import com.wiley.umltoolkit.casestudy.common.ManagerException;
import com.wiley.umltoolkit.casestudy.dao.common.DaoFactory;
import com.wiley.umltoolkit.casestudy.common.CheckoutException;
import com.wiley.umltoolkit.casestudy.common.ReturnItemException;
import com.wiley.umltoolkit.casestudy.common.ReserveItemException;
import com.wiley.umltoolkit.casestudy.vo.ItemVo;
import com.wiley.umltoolkit.casestudy.vo.ReservationVo;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/** Item is an instance of a Title - one or more items are associated to a
 * single Title
 * The ItemMgr manages all of the business logic for an Item
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class ItemMgr  {
    
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
    private DaoFactory dbFactory = DaoFactory.getDaoFactory(DaoFactory.ACCESS);
    /** Uncomment to use MySQL
     * private DaoFactory dbFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
     */
    
    /** Do not allow instances to be created from constructor */
    private ItemMgr() {
    }
    
    /** Returns an instance of this class
     * @return ItemMgr containing an instance of this object
     */
    public static ItemMgr getInstance() {
        return new ItemMgr();
    }
    
    /** Retrieve all items that belong to the unique key for the title.
     * @param titleId id for the Title representing all the Items that are being requested.
     * @return Collection containing items in the system (of ItemVos)
     * @throws ManagerException error on retrieving all of the items that belong to this title.
     */
    public Collection findAll(String titleId) throws ManagerException {
        Collection items = null;
        try {
            items = dbFactory.getItemDao().loadAll(titleId);
            ArrayList aList = new ArrayList(items);
            for (int i = 0; i < aList.size(); i++) {
                ItemVo item = (ItemVo) aList.get(i);
                ReservationVo reservation = dbFactory.getReservationDao().find(item.getItemId());
                if (reservation == null) {
                    reservation = new ReservationVo();
                }
                item.setReservation(reservation);
            }
        } catch (DatabaseException databaseException) {
            throw new ManagerException(databaseException.getMessage());
        }
        return items;
    }
    
    /** Check out a specific Item such as a Book or Magazine to
     * a Borrower. Cannot checkout an item that has already been checked out
     * @param borrowerId id representing the borrower checking out the item
     * @param itemId id representing the item being checked out by Borrower
     * @throws ManagerException general error on validation or unable to checkout item
     */
    public void checkOutItem(String borrowerId,  String itemId) throws ManagerException {
        /** Create a dummy ItemVo to be used to pass information to a DAO method */
        ItemVo item = new ItemVo();
        /** Set the itemId*/
        item.setItemId(itemId);
        /** Assign the borrower */
        item.getBorrower().setId(borrowerId);
        /** Capture the current date & time and set the checked out date & time.    */
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        Date currentTime_1 = new Date();
        String dateString = formatter.format(currentTime_1);
        item.setCheckedOut(dateString);
        /** Set the due back date */
        /**Currently,  there is no business logic defined to tell
         * how long the user has till the book needs to be returned.
         */
        item.setDueBack(null);
        try {
            /** Redundant check,  the web application will not display
             *  items with the ability to check out the item.
             */
            if (!this.isCheckedOut(itemId)) {
                if(isReserved(itemId, borrowerId)){
                    /** Remove the reservation for the item
                     */
                    dbFactory.getReservationDao().unreserve(itemId);
                }
                dbFactory.getItemDao().checkout(item);
            }
        } catch (DatabaseException databaseException) {
            throw new CheckoutException("Unable to complete the checkout");
        }
        
    }
    /** Determine if the item is already checked out by someone else.
     * @param itemId id representing the item being checked out by Borrower
     * @return boolean - true-item is checked out, false-item is not checked out
     * @throws ManagerException general error on validation or unable determine
     * if the item is checked out
     */
    public boolean isCheckedOut(String itemId) throws ManagerException {
        boolean result = false;
        ItemVo item = new ItemVo();
        item.setItemId(itemId);
        try {
            item = (ItemVo) dbFactory.getItemDao().load(item.getItemId());
            result = item.isCheckedOut();
        } catch (DatabaseException databaseException) {
            throw new ManagerException(databaseException.getMessage());
        }
        return result;
    }
    /** Determine if the item is already checked out by a specific person.
     * @param borrowerId String containing a unique id identifying a Borrower in the system
     * @param itemId id representing the item being checked out by Borrower
     * @return boolean - true-item is checked out, false-item is not checked out
     * @throws ManagerException general error on validation or unable determine
     * if the item is checked out
     */
    public boolean isCheckedOut(String itemId,  String borrowerId) throws ManagerException {
        boolean result = false;
        ItemVo item = new ItemVo();
        item.setItemId(itemId);
        if (itemId == null) {
            throw new ManagerException("itemId is null");
        }
        if (borrowerId == null) {
            throw new ManagerException("borrowerId is null");
        }
        
        try {
            item = (ItemVo) dbFactory.getItemDao().load(item.getItemId());
            if (item.getBorrower().getId() == null) {
                logger.debug("item.getBorrower().getId() is null");
            }
            if (item.isCheckedOut()) {
                if (borrowerId.equalsIgnoreCase(item.getBorrower().getId())) {
                    result = true;
                }
            }
        } catch (DatabaseException databaseException) {
            throw new ManagerException(databaseException.getMessage());
        }
        return result;
    }
    /** Return an Item that has already been checked out by a Borrower. Cannot
     * return an item if the item was not checked out by Borrower or was not
     * checked out
     * @param borrowerId id representing the borrower returning the item
     * @param itemId id representing the item being returned by the Borrower
     * @throws ManagerException general error on validation or unable to checkin item
     */
    public void returnItem(String borrowerId,  String itemId) throws ManagerException {
        /**
         * If the ITEM is checked-out by this borrower
         * AND the ITEM is checked-out,  then
         */
        if (this.isCheckedOut(itemId, borrowerId)) {
            try {
                dbFactory.getItemDao().checkin(itemId);
            } catch (DatabaseException databaseException) {
                throw new ReturnItemException("Unable to complete the checkin,  due to database error");
            }
            /**
             * The ITEM is NOT checked-out by this borrower
             * OR the ITEM is NOT checked-out.
             */
        } else {
            
            throw new ReturnItemException("Unable to complete the checkin,  either the item was not borrowed by this person or the item is not checked out.");
        }
        
    }
    /** Return an Item that has already been checked out.  Cannot
     * return an item if the item was not checked
     * @param itemId id representing the item being returned by the Borrower
     * @throws ManagerException general error on validation or unable to checkin item
     */
    public void returnItem(String itemId) throws ManagerException {
        /**
         * If the ITEM is checked-out,  then
         */
        if (this.isCheckedOut(itemId)) {
            try {
                dbFactory.getItemDao().checkin(itemId);
            } catch (DatabaseException databaseException) {
                throw new ReturnItemException("Unable to complete the returning of the item");
            }
            /**
             * The ITEM is NOT checked-out.
             */
        } else {
            throw new ReturnItemException("Unable to complete the checkin,  the item was not checked out.");
        }
        
    }
    
    /** Reserve an Item so that it may be checked out by Borrower once it has
     * been checked in
     * @todo reserverId purpose
     * @param reserverId String representing the item being reserved by Borrower.
     * @param itemId id representing the item being reserved by the Borrower
     * @return boolean true-item has been reserved, false-item is not reserved
     * @throws ManagerException general error on validation or unable to reserve item
     */
    public boolean reserveItem(String reserverId,  String itemId) throws ManagerException {
        boolean returnBoolean = false;
        /**
         * If the ITEM is checked-out,  then
         */
        if (this.isCheckedOut(itemId)) {
            /**
             * If the ITEM is NOT reserved,  then
             */
            if (!this.isReserved(itemId, reserverId)) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
                    Date currentTime_1 = new Date();
                    String dateString = formatter.format(currentTime_1);
                    
                    dbFactory.getReservationDao().reserve(reserverId,  itemId,  dateString);
                    returnBoolean = true;
                } catch (DatabaseException databaseException) {
                    throw new ReserveItemException("Unable to complete reserving the item");
                }
                /**
                 * The ITEM is reserved.
                 */
            } else {
                throw new ReserveItemException("Unable to complete the reservation,  the item is already reserved by someone.");
            }
            /**
             * If the ITEM is NOT checked-out,  then
             */
        } else {
            throw new ReserveItemException("Unable to complete the reservation,  the item should be checked-out instead of reserving it.");
        }
        return returnBoolean;
    }
    
    /** Reserve an Item so that it may be checked out by Borrower once it has
     * been checked in
     * @todo reserverId purpose
     * @param reserverId String representing the item being unreserved by Borrower.
     * @param itemId id representing the item being unreserved by the Borrower
     * @return boolean true-item has been un-reserved, false-item is not un-reserved
     * @throws ManagerException general error on validation or unable to un-reserve item
     */
    public boolean unreserveItem(String reserverId,  String itemId) throws ManagerException {
        boolean returnBoolean = false;
        /**
         * If the ITEM is checked-out,  then
         */
        if (this.isCheckedOut(itemId)) {
            if (this.isReserved(itemId, reserverId)) {
                try {
                    dbFactory.getReservationDao().unreserve(itemId);
                    returnBoolean = true;
                } catch (DatabaseException databaseException) {
                    throw new ReturnItemException("Unable to complete reserving the item");
                }
                /**
                 * The ITEM is NOT reserved (unreserved).
                 */
            } else {
                throw new ReturnItemException("Unable to remove the reservation,  the item is already unreserved.");
            }
            /**
             * If the ITEM is NOT checked-out,  then
             */
        } else {
            throw new ReserveItemException("Unable to remove the reservation,  the item should be checked-in instead of unreserving it.");
        }
        return returnBoolean;
    }
    
    /** Determine if the item is already reserved by someone else.
     * @param itemId id representing the item being reserved by Borrower
     * @return boolean true-item is reserved, false-item is not reserved
     * @throws ManagerException general error on validation or unable determine
     * if the item is reserved
     */
    public boolean isReserved(String itemId) throws ManagerException {
        boolean result = false;
        try {
            ReservationVo reservation = dbFactory.getReservationDao().find(itemId);
            if (reservation != null) {
                result = true;
            }
        } catch (DatabaseException de) {
            throw new ManagerException(de.getMessage());
        }
        return result;
    }
    
    /** Determine if the item is already reserved by a specific person.
     * @return boolean true-item is reserved, false-item is not reserved
     * @param reserverId String containing a unique id identifying a Reservation in the system
     * @param itemId id representing the item being reserved by Borrower
     * @throws ManagerException general error on validation or unable determine
     * if the item is reserved
     */
    public boolean isReserved(String itemId,  String reserverId) throws ManagerException {
        boolean result = false;
        try {
            ReservationVo reservation = dbFactory.getReservationDao().find(itemId);
            if (reservation != null && reservation.getUserId().equalsIgnoreCase(reserverId)) {
                result = true;
            }
        } catch (DatabaseException de) {
            throw new ManagerException(de.getMessage());
        }
        return result;
    }
}