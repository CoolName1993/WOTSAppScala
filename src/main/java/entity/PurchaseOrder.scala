package entity

import sql._
import java.sql.Date

/**
 * @author cboucher
 */
case class PurchaseOrder(idPO : Int) extends Entity{
  def tableName: String = "purchaseorder"
  def idPurchaseOrder: Column[Int] = new Column[Int]("idPurchaseOrder")
  def datePlaced: Column[Date] = new Column[Date]("datePlaced")
  def dateExpected: Column[Date] = new Column[Date]("dateExpected")
  def idPurchaseOrderStatus: Column[Int] = new Column[Int]("idPurchaseOrderStatus")
  def idSupplier: Column[Int] = new Column[Int]("idSupplier")
  def idEmployee: Column[Int] = new Column[Int]("idEmployee")
  def create(): Unit = ???
  def delete(): Unit = ???
  def read(): Unit = ???
  def update(): Unit = ???  
}