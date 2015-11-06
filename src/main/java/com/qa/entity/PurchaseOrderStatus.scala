package com.qa.entity

import com.qa.sql.{ Entity, Column }

/**
 * Represents a Purchase Order Status from the MySQL database.
 * @param idPurchaseOrderStatus_ The purchase order status ID.
 * @param status_ The string representation of the status.
 * @author cboucher
 */
case class PurchaseOrderStatus(idPurchaseOrderStatus_ : Int, status_ : String) extends Entity {
  val tableName = "purchaseorderstatus"
  val idPurchaseOrderStatus = new Column("idPurchaseOrderStatus", idPurchaseOrderStatus_)
  val status = new Column("status", status_)
}