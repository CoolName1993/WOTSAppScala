package com.qa.data.sql

import com.qa.TestBase

/**
 * @author cboucher
 */
class SQLConnectorTest extends TestBase {

  "read" should "search the database for matching entities" in {
    val columns = Array(new Column("idItem", null), new Column("idCustomerOrder", 1), new Column("quantity", null))
    val results = SQLConnector.read("customerorderline", columns)
    assert(results.length > 0)
  }
}