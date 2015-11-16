package com.qa.data.entity

import com.qa.TestBase

/**
 * @author cboucher
 */
class AddressTest extends TestBase {
  
  "An Address" should "be initialised with the correct values" in {
    val addressLines = Array("17 Test Road","14 Test Road","12 Test Road")
    val result = new Address(0,addressLines,"Test Town","Test Sussex","T35 T12")
    assert(result.idAddress.getValue.equals(0))
    assert(result.addressLines(0).getValue.equals("17 Test Road"))
    assert(result.addressLines(1).getValue.equals("14 Test Road"))
    assert(result.addressLines(2).getValue.equals("12 Test Road"))
    assert(result.city.getValue.equals("Test Town"))
    assert(result.county.getValue.equals("Test Sussex"))
    assert(result.postcode.getValue.equals("T35 T12"))
  }
}