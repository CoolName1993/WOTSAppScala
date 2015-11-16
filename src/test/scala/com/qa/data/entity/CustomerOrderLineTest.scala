package com.qa.data.entity

import com.qa.TestBase

/**
 * @author cboucher
 */
class CustomerOrderLineTest extends TestBase {
  
  "A CustomerOrderLine" should "be initialised with the correct values" in {
    val result = new CustomerOrderLine(0,1,2)
    assert(result.idItem.getValue.equals(0))
    assert(result.idCustomerOrder.getValue.equals(1))
    assert(result.quantity.getValue.equals(2))
  }
}