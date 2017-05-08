package com.wiley.umltoolkit.casestudy.vo;
import com.wiley.umltoolkit.casestudy.vo.common.BaseVo;

/** ReservationVo is a value object that contains data about a reservation. A
 * borrower may reserve an item if it is not checked out nor reserved by another
 * borrower
 */
public class ReservationVo extends BaseVo  {
    
    private String reservationId;
    private String itemId;
    private String userId;
    private String dateTimeReserved;
    
    public ReservationVo()  {
    }
    public String getDateTimeReserved()  {
        return dateTimeReserved;
    }
    public void setDateTimeReserved(String dateTimeReserved)  {
        this.dateTimeReserved = dateTimeReserved;
    }
    public String getItemId()  {
        return itemId;
    }
    public void setItemId(String itemId)  {
        this.itemId = itemId;
    }
    public String getReservationId()  {
        return reservationId;
    }
    public void setReservationId(String reservationId)  {
        this.reservationId = reservationId;
    }
    public String getUserId()  {
        return userId;
    }
    public void setUserId(String userId)  {
        this.userId = userId;
    }
}