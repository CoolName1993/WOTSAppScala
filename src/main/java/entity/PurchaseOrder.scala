package entity

import sql._
import java.sql.Date

/**
 * @author cboucher
 */
case class PurchaseOrder(idPO : Int, dateP: Date, dateE: Date, idPOS: Int, idS: Int, idE: Int) extends Entity{
  val tableName: String = "purchaseorder"
  val idPurchaseOrder = new Column("idPurchaseOrder", idPO)
  val datePlaced = new Column("datePlaced", dateP)
  val dateExpected = new Column("dateExpected", dateE)
  val idPurchaseOrderStatus = new Column("idPurchaseOrderStatus", idPOS)
  val idSupplier = new Column("idSupplier", idS)
  val idEmployee = new Column("idEmployee", idE)
}