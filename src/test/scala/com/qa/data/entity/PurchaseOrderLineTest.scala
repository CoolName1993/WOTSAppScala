package com.qa.data.entity

import com.qa.TestBase

/**
 * @author cboucher
 */
class PurchaseOrderLineTest extends TestBase {
  
  "A PurchaseOrderLine" should "be initialised with the correct values" in {
    val result = new PurchaseOrderLine(0,1,2,3)
    assert(result.idPurchaseOrder.getValue.equals(0))
    assert(result.idItem.getValue.equals(1))
    assert(result.quantity.getValue.equals(2))
    assert(result.quantityDamaged.getValue.equals(3))
  }
}