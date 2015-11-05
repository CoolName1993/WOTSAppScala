package com.qa.entity

import java.sql.Date
import com.qa.sql.{ Entity, Column }

/**
 * @author cboucher
 */
case class CustomerOrder(idCO: Int, dateP: Date, dateS: Date, isP: Boolean, idA: Int, idCOS: Int, idE: Int, idC: Int) extends Entity {
  val tableName = "customerorder"
  val idCustomerOrder = new Column("idCustomerOrder", idCO)
  val datePlaced = new Column("datePlaced", dateP)
  val dateShipped = new Column("dateShipped", dateS)
  val isPaid = new Column("isPaid", isP)
  val idAddress = new Column("idAddress", isP)
  val idCustomerOrderStatus = new Column("idCustomerOrderStatus", idCOS)
  val idEmployee = new Column("idEmployee", idE)
  val idCustomer = new Column("idCustomer", idC)
}