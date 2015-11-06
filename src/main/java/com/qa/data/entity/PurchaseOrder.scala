package com.qa.data.entity

import com.qa.data.sql.{ Entity, Column }
import java.sql.Date

/**
 * Represents a Purchase Order from the MySQL database.
 * @param idPurchaseOrder_ The purchase order ID.
 * @param datePlaced_ The date the purchase order was placed.
 * @param dateExpected_ The date the purchase order is expected.
 * @param idPurchaseOrderStatus_ The purchase order status ID.
 * @param idSupplier_ The supplier ID.
 * @param idEmployee_ The employee ID.
 * @author cboucher
 */
case class PurchaseOrder(idPurchaseOrder_ : Int, datePlaced_ : Date, dateExpected_ : Date, idPurchaseOrderStatus_ : Int, idSupplier_ : Int, idEmployee_ : Int) extends Entity {
  val tableName: String = "purchaseorder"
  val idPurchaseOrder = new Column("idPurchaseOrder", idPurchaseOrder_)
  val datePlaced = new Column("datePlaced", datePlaced_)
  val dateExpected = new Column("dateExpected", dateExpected_)
  val idPurchaseOrderStatus = new Column("idPurchaseOrderStatus", idPurchaseOrderStatus_)
  val idSupplier = new Column("idSupplier", idSupplier_)
  val idEmployee = new Column("idEmployee", idEmployee_)
}