package com.qa.data.entity

import com.qa.data.sql.{ Entity, Column }

/**
 * Represents a Customer Order Line from the MySQL database.
 * @param idItem_ The item ID.
 * @param idCustomerOrder_ The customer order ID.
 * @param quantity_ The quantity of the item.
 * @author cboucher
 */
class CustomerOrderLine(idItem_ : Any, idCustomerOrder_ : Any, quantity_ : Any) extends Entity {
  val tableName = "customerorderline"
  val idItem = new Column("idItem", idItem_)
  val idCustomerOrder = new Column("idCustomerOrder", idCustomerOrder_)
  val quantity = new Column("quantity", quantity_)
}