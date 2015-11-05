package com.qa.entity

import com.qa.sql.{Entity, Column}

/**
 * @author cboucher
 */
case class PurchaseOrderStatus(idPOS: Int, stat: String) extends Entity {
  val tableName = "purchaseorderstatus"
  val idPurchaseOrderStatus = new Column("idPurchaseOrderStatus", idPOS)
  val status = new Column("status", stat)
}