package com.wiley.umltoolkit.casestudy.controller.form;

import com.wiley.umltoolkit.casestudy.controller.common.BaseForm;
import com.wiley.umltoolkit.casestudy.vo.ItemVo;

/** Get form data when checking out an Item
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class CheckoutItemForm extends BaseForm  {

    private ItemVo item;
    public CheckoutItemForm()  {
    }
    public ItemVo getItem()  {
        return item;
    }
    public void setItem(ItemVo item)  {
        this.item = item;
    }
}