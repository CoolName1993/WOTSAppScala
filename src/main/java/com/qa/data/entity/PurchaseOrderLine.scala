package com.qa.data.entity

import com.qa.data.sql.{ Entity, Column }

/**
 * Represents a Purchase Order Line from the MySQL database.
 * @param idPurchaseOrder_ The purchase order ID.
 * @param idItem_ The item ID.
 * @param quantity_ The quantity of the item.
 * @param quantityDamaged_ The quantity of the item that was damaged.
 * @author cboucher
 */
class PurchaseOrderLine(idPurchaseOrder_ : Int, idItem_ : Any, quantity_ : Any, quantityDamaged_ : Any) extends Entity {
  val tableName: String = "purchaseorderline"
  val idPurchaseOrder = new Column("idPurchaseOrder", idPurchaseOrder_)
  val idItem = new Column("idItem", idItem_)
  val quantity = new Column("quantity", quantity_)
  val quantityDamaged = new Column("quantityDamaged", quantityDamaged_)
}