package com.qa.entity

import com.qa.mongo.{ Entity, Field }


/**
 * Represents an Item from the MongoDB database.
 * @param idItem_ The item ID.
 * @param itemName_ The name of the item.
 * @param imageLocation_ The location of the item's image.
 * @param isPorousware_ Whether the item has porousware.
 * @author cboucher
 */
case class Item(idItem_ : Int, itemName_ : String, imageLocation_ : String, isPorousware_ : Boolean) extends Entity {
  val collectionName: String = "Item"
  val idItem = new Field("idItem", idItem_)
  val itemName = new Field("ItemName", itemName_)
  val imageLocation = new Field("ImageLocation", imageLocation_)
  val isPorousware = new Field("IsPorousware", isPorousware_)
}