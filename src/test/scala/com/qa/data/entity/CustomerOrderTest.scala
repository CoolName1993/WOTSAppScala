package com.qa.data.entity

import com.qa.TestBase
import java.sql.Date

/**
 * @author cboucher
 */
class CustomerOrderTest extends TestBase {

  "A CustomerOrder" should "be initialised with the correct values" in {
    val result = new CustomerOrder(0, new Date(10, 10, 2010), new Date(10, 10, 2010), true, 0, 0, 0, 0)
    assert(result.idCustomerOrder.getValue.equals(0))
    assert(result.datePlaced.getValue.equals(new Date(10, 10, 2010)))
    assert(result.dateShipped.getValue.equals(new Date(10, 10, 2010)))
    assert(result.isPaid.getValue.equals(true))
    assert(result.idAddress.getValue.equals(0))
    assert(result.idCustomerOrderStatus.getValue.equals(0))
    assert(result.idEmployee.getValue.equals(0))
    assert(result.idCustomer.getValue.equals(0))
  }
}