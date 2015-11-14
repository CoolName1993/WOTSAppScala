package com.qa.data.entity

import com.qa.data.sql.{ Entity, Column }

/**
 * Represents a Customer Order Status from the MySQL database.
 * @param idCustomerOrderStatus_ The customer order status ID.
 * @param status_ The string representation of the status.
 * @author cboucher
 */
class CustomerOrderStatus(idCustomerOrderStatus_ : Any, status_ : String) extends Entity {
  val tableName = "customerorderstatus"
  val idCustomerOrderStatus = new Column("idCustomerOrderStatus", idCustomerOrderStatus_)
  val status = new Column("status", status_)
}