package com.qa.entity

import com.qa.sql.{ Entity, Column }
import java.sql.Date

/**
 * Represents a Purchase Order from the MySQL database.
 * @param idPO The purchase order ID.
 * @param dateP The date the purchase order was placed.
 * @param dateE The date the purchase order is expected.
 * @param idPOS The purchase order status ID.
 * @param idS The supplier ID.
 * @param idE The employee ID.
 * @author cboucher
 */
case class PurchaseOrder(idPO: Int, dateP: Date, dateE: Date, idPOS: Int, idS: Int, idE: Int) extends Entity {
  val tableName: String = "purchaseorder"
  val idPurchaseOrder = new Column("idPurchaseOrder", idPO)
  val datePlaced = new Column("datePlaced", dateP)
  val dateExpected = new Column("dateExpected", dateE)
  val idPurchaseOrderStatus = new Column("idPurchaseOrderStatus", idPOS)
  val idSupplier = new Column("idSupplier", idS)
  val idEmployee = new Column("idEmployee", idE)
}