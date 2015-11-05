package com.qa.entity

import com.qa.sql.{ Entity, Column }

/**
 * @author cboucher
 */
case class CustomerOrderLine(idI: Int, idCO: Int, quan: Int) extends Entity {
  val tableName = "customerorderline"
  val idItem = new Column("idItem", idI)
  val idCustomerOrder = new Column("idCustomerOrder", idCO)
  val quantity = new Column("quantity", quan)
}