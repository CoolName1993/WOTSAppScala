package com.qa.application

import com.qa.data.entity.CustomerOrder
import com.qa.data.entity.PurchaseOrder
import com.qa.data.entity.User

/**
 * @author cboucher
 */
object Session {
  var currentUser: User = null
  var currentPurchaseOrder: PurchaseOrder = null
  var currentCustomerOrder: CustomerOrder = null
}