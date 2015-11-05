package com.qa.entity

import com.qa.sql.{ Entity, Column }

/**
 * Represents a Purchase Order Line from the MySQL database.
 * @param idPO The purchase order ID.
 * @param idI The item ID.
 * @param quan The quantity of the item.
 * @param quanD The quantity of the item that was damaged.
 * @author cboucher
 */
case class PurchaseOrderLine(idPO: Int, idI: Int, quan: Int, quanD: Int) extends Entity {
  val tableName: String = "purchaseorderline"
  val idPurchaseOrder = new Column("idPurchaseOrder", idPO)
  val idItem = new Column("idItem", idI)
  val quantity = new Column("quantity", quan)
  val quantityDamaged = new Column("quantityDamaged", quanD)
}