package com.qa.data.mongo

import com.qa.TestBase

/**
 * @author cboucher
 */
class MongoConnectorTest extends TestBase{
   
  "read" should "return the results of a search when given parameters" in {
    val fields = Array(new Field("idItem",1))
    val result = MongoConnector.read("Item", fields)
    assert(result.length > 0)
  }
}