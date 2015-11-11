package com.qa.data.entity

import java.sql.Date
import com.mongodb.casbah.commons.MongoDBObject

/**
 * Converts raw data into classes to be used by controllers
 * @author cboucher
 */
object EntityConvertor {
  def convertToUser(input: Array[_]): User = {
    new User(input(0).asInstanceOf[Int], input(1).asInstanceOf[String], input(2).asInstanceOf[String], input(3).asInstanceOf[String], input(4).asInstanceOf[String], input(5).asInstanceOf[Int])
  }

  def convertToAddress(input: Array[_]): Address = {
    new Address(input(0).asInstanceOf[Int], input(1).asInstanceOf[Array[String]], input(2).asInstanceOf[String], input(3).asInstanceOf[String], input(4).asInstanceOf[String])
  }

  def convertToCustomerOrder(input: Array[_]): CustomerOrder = {
    new CustomerOrder(input(0).asInstanceOf[Int], input(1).asInstanceOf[Date], input(2).asInstanceOf[Date], input(3).asInstanceOf[Boolean], input(4).asInstanceOf[Int], input(5).asInstanceOf[Int], input(6).asInstanceOf[Int], input(7).asInstanceOf[Int])
  }

  def convertToPurchaseOrder(input: Array[_]): PurchaseOrder = {
    new PurchaseOrder(input(0).asInstanceOf[Int], input(1).asInstanceOf[Date], input(2).asInstanceOf[Date], input(3).asInstanceOf[Int], input(4).asInstanceOf[Int], input(5).asInstanceOf[Int])
  }
  
  def convertToPurchaseOrderLine(input: Array[_]): PurchaseOrderLine = {
    new PurchaseOrderLine(input(0).asInstanceOf[Int], input(1).asInstanceOf[Int], input(2).asInstanceOf[Int], input(3).asInstanceOf[Int])
  }
  
  def convertToCustomerOrderLine(input: Array[_]): CustomerOrderLine = {
    new CustomerOrderLine(input(0).asInstanceOf[Int], input(1).asInstanceOf[Int], input(2).asInstanceOf[Int])
  }
  
  def convertToLocation(input: Array[_]): Location = {
    new Location(input(0).asInstanceOf[Int], input(1).asInstanceOf[Int], input(2).asInstanceOf[Int], input(3).asInstanceOf[Int], input(4).asInstanceOf[Int])
  }
  
  def convertToItem(input: MongoDBObject): Item = {
    val itemID = input.get("idItem").toString
    val itemName = input.get("ItemName").get.toString
    val imageLocation = input.get("ImageLocation").toString
    val isPorousWare = input.get("IsPorousware").toString.equals("true")
    new Item(itemID,itemName,imageLocation,isPorousWare)
  }
}