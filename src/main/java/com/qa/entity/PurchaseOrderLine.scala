package com.qa.entity

import com.qa.sql.{ Entity, Column }

/**
 * Represents a Purchase Order Line from the MySQL database.
 * @param idPurchaseOrder_ The purchase order ID.
 * @param idItem_ The item ID.
 * @param quantity_ The quantity of the item.
 * @param quantityDamaged_ The quantity of the item that was damaged.
 * @author cboucher
 */
case class PurchaseOrderLine(idPurchaseOrder_ : Int, idItem_ : Int, quantity_ : Int, quantityDamaged_ : Int) extends Entity {
  val tableName: String = "purchaseorderline"
  val idPurchaseOrder = new Column("idPurchaseOrder", idPurchaseOrder_)
  val idItem = new Column("idItem", idItem_)
  val quantity = new Column("quantity", quantity_)
  val quantityDamaged = new Column("quantityDamaged", quantityDamaged_)
}