package com.qa.data.entity

import com.qa.TestBase

/**
 * @author cboucher
 */
class ItemTest extends TestBase {
   
  "An Item" should "be initialised with the correct values" in {
    val result = new Item(0,"Test","/test",false)
    assert(result.idItem.getValue.equals(0))
    assert(result.itemName.getValue.equals("Test"))
    assert(result.imageLocation.getValue.equals("/test"))
    assert(result.isPorousware.getValue.equals(false))
  }
}