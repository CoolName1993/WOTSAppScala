package com.qa.data.sql

import com.qa.TestBase

/**
 * @author cboucher
 */
class ConnectionTest extends TestBase {
  "The database connection" should "be open in order to run the tests" in {
    SQLConnector.connect
    if (SQLConnector.connection.isClosed) {
      cancel
    }
  }

}