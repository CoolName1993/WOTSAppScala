package com.qa.data.entity

import java.sql.Date
import com.qa.data.sql.{ Entity, Column }

/**
 * Represents a Customer Order from the MySQL database.
 * @param idCustomerOrder_ The customer order ID.
 * @param datePlaced_ The date the customer order was placed.
 * @param dateShipped_ The date the customer order was shipped.
 * @param isPaid_ Whether the order has been paid or not.
 * @param idAddress_ The address ID.
 * @param idCustomerOrderStatus_ The customer order status ID.
 * @param idEmployee_ The employee ID.
 * @param idCustomer_ The customer ID.
 * @author cboucher
 */
case class CustomerOrder(idCustomerOrder_ : Int, datePlaced_ : Date, dateShipped_ : Date, isPaid_ : Boolean, idAddress_ : Int, idCustomerOrderStatus_ : Int, idEmployee_ : Int, idCustomer_ : Int) extends Entity {
  val tableName = "customerorder"
  val idCustomerOrder = new Column("idCustomerOrder", idCustomerOrder_)
  val datePlaced = new Column("datePlaced", datePlaced_)
  val dateShipped = new Column("dateShipped", dateShipped_)
  val isPaid = new Column("isPaid", isPaid_)
  val idAddress = new Column("idAddress", idAddress_)
  val idCustomerOrderStatus = new Column("idCustomerOrderStatus", idCustomerOrderStatus_)
  val idEmployee = new Column("idEmployee", idEmployee_)
  val idCustomer = new Column("idCustomer", idCustomer_)
}