package com.qa.entity

import com.qa.sql.{ Entity, Column }

/**
 * @author cboucher
 */
case class CustomerOrderStatus(idCOS: Int, stat: String) extends Entity {
  val tableName = "customerorderstatus"
  val idCustomerOrderStatus = new Column("idCustomerOrderStatus", idCOS)
  val status = new Column("status", stat)
}