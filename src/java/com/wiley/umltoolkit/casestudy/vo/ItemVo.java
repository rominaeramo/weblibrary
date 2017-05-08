package com.wiley.umltoolkit.casestudy.vo;
import com.wiley.umltoolkit.casestudy.vo.common.BaseVo;
import java.util.HashMap;
import java.util.Map;
import com.wiley.umltoolkit.casestudy.common.Constants;

/** ItemVo is a value object that contains data about an item. An item is
 * an "instance" of a title.  It represents the physical book or magazine.
 * In this application,  what makes an item different is the item id and comments
 * such as the condition of this specific item/book
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class ItemVo extends BaseVo  {

    private String itemId;
    private String titleId;
    private String addedDate;
    private String checkedOut;
    private UserVo borrower;
    private String dueBack;
    private String comments;
    private ReservationVo reservation;

    public ItemVo()  {
        borrower = new UserVo();
        reservation = new ReservationVo();
    }
    public String getAddedDate()  {
        return addedDate;
    }
    public void setAddedDate(String addedDate)  {
        this.addedDate = addedDate;
    }
    public UserVo getBorrower()  {
        return borrower;
    }
    public void setBorrower(UserVo borrower)  {
        this.borrower = borrower;
    }
    public String getCheckedOut()  {
        return checkedOut;
    }
    public void setCheckedOut(String checkedOut)  {
        this.checkedOut = checkedOut;
    }
    public String getDueBack()  {
        return dueBack;
    }
    public void setDueBack(String dueBack)  {
        this.dueBack = dueBack;
    }
    public String getItemId()  {
        return itemId;
    }
    public void setItemId(String itemId)  {
        this.itemId = itemId;
    }
    public String getTitleId()  {
        return titleId;
    }
    public void setTitleId(String titleId)  {
        this.titleId = titleId;
    }
    public Map getItemIdAndTitleId() {
        Map hashMap = new HashMap();
        hashMap.put(Constants.TITLE_ID, this.titleId);
        hashMap.put(Constants.ITEM_ID, this.itemId);
        return hashMap;
    }
    public boolean isCheckedOut() {
        if (this.checkedOut == null) {
            return false;
        } else {
            return true;
        }
    }
    public boolean getIsCheckedOut() {
        return this.isCheckedOut();
    }
    public String getComments()  {
        return comments;
    }
    public void setComments(String comments)  {
        this.comments = comments;
    }
    public ReservationVo getReservation()  {
        return reservation;
    }
    public void setReservation(ReservationVo reservation)  {
        this.reservation = reservation;
    }
    public boolean isReserved() {
        boolean result = false;
        if (reservation != null) {
            if (reservation.getReservationId() != null) {
                result = true;
            }
        }
        return result;
    }
    public boolean getIsReserved() {
        return this.isReserved();
    }
}