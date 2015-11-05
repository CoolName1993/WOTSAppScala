package com.qa.entity

import com.qa.mongo.{ Entity, Field }


/**
 * Represents an Item from the MongoDB database.
 * @param idI The item ID.
 * @param name The name of the item.
 * @param image The location of the item's image.
 * @param porous Whether the item has porousware.
 * @author cboucher
 */
case class Item(idI: Int, name: String, image: String, porous: Boolean) extends Entity {
  val collectionName: String = "Item"
  val idItem = new Field("idItem", idI)
  val itemName = new Field("ItemName", name)
  val imageLocation = new Field("ImageLocation", image)
  val isPorousware = new Field("IsPorousware", porous)
}