package com.qa.data.entity

import java.sql.Date
import com.mongodb.casbah.commons.MongoDBObject

/**
 * Converts raw data into classes to be used by controllers
 * @author cboucher
 */
object EntityConvertor {
  
  /**
   * Converts raw data into a user object.
   * @param input An array of values to add to the user object.
   * @return A user object.
   */
  def convertToUser(input: Array[_]): User = {
    new User(input(0).asInstanceOf[Int], input(1).asInstanceOf[String], input(2).asInstanceOf[String], input(3).asInstanceOf[String], input(4).asInstanceOf[String], input(5).asInstanceOf[Int])
  }

  /**
   * Converts raw data into an address object.
   * @param input An array of values to add to the address object.
   * @return An address object.
   */
  def convertToAddress(input: Array[_]): Address = {
    new Address(input(0).asInstanceOf[Int], input(1).asInstanceOf[Array[String]], input(2).asInstanceOf[String], input(3).asInstanceOf[String], input(4).asInstanceOf[String])
  }

  /**
   * Converts raw data into a customer order object.
   * @param input An array of values to add to the customer order object.
   * @return A customer order object.
   */
  def convertToCustomerOrder(input: Array[_]): CustomerOrder = {
    new CustomerOrder(input(0).asInstanceOf[Int], input(1).asInstanceOf[Date], input(2).asInstanceOf[Date], input(3).asInstanceOf[Boolean], input(4).asInstanceOf[Int], input(5).asInstanceOf[Int], input(6).asInstanceOf[Int], input(7).asInstanceOf[Int])
  }

  /**
   * Converts raw data into a purchase order object.
   * @param input An array of values to add to the purchase order object.
   * @return A purchase order object.
   */
  def convertToPurchaseOrder(input: Array[_]): PurchaseOrder = {
    new PurchaseOrder(input(0).asInstanceOf[Int], input(1).asInstanceOf[Date], input(2).asInstanceOf[Date], input(3).asInstanceOf[Int], input(4).asInstanceOf[Int], input(5).asInstanceOf[Int])
  }
  
  /**
   * Converts raw data into a purchase order line object.
   * @param input An array of values to add to the purchase order line object.
   * @return A purchase order line object.
   */
  def convertToPurchaseOrderLine(input: Array[_]): PurchaseOrderLine = {
    new PurchaseOrderLine(input(0).asInstanceOf[Int], input(1).asInstanceOf[Int], input(2).asInstanceOf[Int], input(3).asInstanceOf[Int])
  }
  
  /**
   * Converts raw data into a customer order line object.
   * @param input An array of values to add to the customer order line object.
   * @return A customer order line object.
   */
  def convertToCustomerOrderLine(input: Array[_]): CustomerOrderLine = {
    new CustomerOrderLine(input(0).asInstanceOf[Int], input(1).asInstanceOf[Int], input(2).asInstanceOf[Int])
  }
  
  /**
   * Converts raw data into a location object.
   * @param input An array of values to add to the location object.
   * @return A location object.
   */
  def convertToLocation(input: Array[_]): Location = {
    new Location(input(0).asInstanceOf[Int], input(1).asInstanceOf[Int], input(2).asInstanceOf[Int], input(3).asInstanceOf[Int], input(4).asInstanceOf[Int])
  }
  
  /**
   * Converts raw data into an item object.
   * @param input An array of values to add to the item object.
   * @return An item object.
   */
  def convertToItem(input: MongoDBObject): Item = {
    val itemID = input.get("idItem").get.toString.toInt
    val itemName = input.get("ItemName").get.toString
    val imageLocation = input.get("ImageLocation").get.toString
    val isPorousWare = input.get("IsPorousware").get.toString.equals("true")
    new Item(itemID,itemName,imageLocation,isPorousWare)
  }
}