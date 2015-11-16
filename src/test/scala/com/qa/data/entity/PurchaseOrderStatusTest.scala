package com.qa.data.entity

import com.qa.TestBase

/**
 * @author cboucher
 */
class PurchaseOrderStatusTest extends TestBase {
  
  "A PurchaseOrderStatus" should "be initialised with the correct values" in {
    val result = new PurchaseOrderStatus(0,"Test")
    assert(result.idPurchaseOrderStatus.getValue.equals(0))
    assert(result.status.getValue.equals("Test"))
  }
}