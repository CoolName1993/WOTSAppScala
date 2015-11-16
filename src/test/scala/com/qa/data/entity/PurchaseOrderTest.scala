package com.qa.data.entity

import com.qa.TestBase
import java.sql.Date

/**
 * @author cboucher
 */
class PurchaseOrderTest extends TestBase {
  
  "A PurchaseOrder" should "be initialised with the correct values" in {
    val result = new PurchaseOrder(0,new Date(10,10,2010),new Date(10,10,2010),1,2,3)
    assert(result.idPurchaseOrder.getValue.equals(0))
    assert(result.datePlaced.getValue.equals(new Date(10,10,2010)))
    assert(result.dateExpected.getValue.equals(new Date(10,10,2010)))
    assert(result.idPurchaseOrderStatus.getValue.equals(1))
    assert(result.idSupplier.getValue.equals(2))
    assert(result.idEmployee.getValue.equals(3))
  }
}