package com.qa.entity

import com.qa.sql.{ Entity, Column }

/**
 * Represents a Customer Order Status from the MySQL database.
 * @param idCustomerOrderStatus_ The customer order status ID.
 * @param status_ The string representation of the status.
 * @author cboucher
 */
case class CustomerOrderStatus(idCustomerOrderStatus_ : Int, status_ : String) extends Entity {
  val tableName = "customerorderstatus"
  val idCustomerOrderStatus = new Column("idCustomerOrderStatus", idCustomerOrderStatus_)
  val status = new Column("status", status_)
}