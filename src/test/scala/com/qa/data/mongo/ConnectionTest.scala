package com.qa.data.mongo

import com.qa.TestBase

/**
 * @author cboucher
 */
class ConnectionTest extends TestBase {
  
  def testConnect: Boolean = {
    try {
      MongoConnector.connect
      MongoConnector.connection.getDatabaseNames
      true
    } catch {
      case e: Exception => {
        cancel
        false
      }
    }
  }
  
  "The database" should "be online in order to run the other tests" in {
    assert(testConnect == true)
    MongoConnector.disconnect
  }
}