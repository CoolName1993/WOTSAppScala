package com.qa.data.entity
import com.qa.data.sql.{ Entity, Column }

/**
 * @author cboucher
 */
class Location(idLocation_ : Any, idItem_ : Any, row_ : Any, col_ : Any, quantity_ : Any) extends Entity {
  val tableName = "location"
  val idLocation = new Column("idLocation", idLocation_)
  val idItem = new Column("idItem", idItem_)
  val row = new Column("row", row_)
  val col = new Column("col", col_)
  val quantity = new Column("quantity", quantity_)
}