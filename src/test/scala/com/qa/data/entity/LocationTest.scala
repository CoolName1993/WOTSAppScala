package com.qa.data.entity

import com.qa.TestBase

/**
 * @author cboucher
 */
class LocationTest extends TestBase {
  
  "A Location" should "be initialised with the correct values" in {
    val result = new Location(0,1,2,3,4)
    assert(result.idLocation.getValue.equals(0))
    assert(result.idItem.getValue.equals(1))
    assert(result.row.getValue.equals(2))
    assert(result.col.getValue.equals(3))
    assert(result.quantity.getValue.equals(4))
  }
}