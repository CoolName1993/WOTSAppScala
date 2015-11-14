package com.qa.data.entity

import com.qa.data.sql.SQLConnector
import com.qa.data.mongo.MongoConnector

/**
 * @author cboucher
 */
object QueryLoader {

  // Searches the database for matching purchase orders
  def searchPurchaseOrder(selectedPurchaseOrder: PurchaseOrder): Array[PurchaseOrder] = {
    val searchOrder = new PurchaseOrder(selectedPurchaseOrder.idPurchaseOrder.getValue, null, null, null, null, null)
    val searchValues = Array(searchOrder.idPurchaseOrder, searchOrder.datePlaced, searchOrder.dateExpected, searchOrder.idPurchaseOrderStatus, searchOrder.idSupplier, searchOrder.idEmployee)
    val currentOrders = SQLConnector.read(searchOrder.tableName, searchValues)
    println(currentOrders.size)
    if (currentOrders.size > 0) {
      var output = new Array[PurchaseOrder](currentOrders.length)
      def loop(i: Int) {
        if (i < currentOrders.size) {
          output(i) = EntityConvertor.convertToPurchaseOrder(currentOrders(i))
          loop(i + (1))
        }
      }
      loop(0)
      output
    } else {
      null
    }
  }

  // Searches the database for matching customer orders
  def searchCustomerOrder(selectedCustomerOrder: CustomerOrder): Array[CustomerOrder] = {
    val searchOrder = new CustomerOrder(selectedCustomerOrder.idCustomerOrder.getValue, null, null, null, null, null, null, null)
    val searchValues = Array(selectedCustomerOrder.idCustomerOrder, selectedCustomerOrder.datePlaced, selectedCustomerOrder.dateShipped, selectedCustomerOrder.isPaid, selectedCustomerOrder.idAddress, selectedCustomerOrder.idCustomerOrderStatus, selectedCustomerOrder.idEmployee, selectedCustomerOrder.idCustomer)
    val currentOrders = SQLConnector.read(searchOrder.tableName, searchValues)
    println(currentOrders.size)
    if (currentOrders.size > 0) {
      var output = new Array[CustomerOrder](currentOrders.length)
      def loop(i: Int) {
        if (i < currentOrders.size) {
          output(i) = EntityConvertor.convertToCustomerOrder(currentOrders(i))
          loop(i + (1))
        }
      }
      loop(0)
      output
    } else {
      null
    }
  }

  // Searches the database for all lines in the current customer order
  def searchCustomerOrderLine(selectedCustomerOrder: CustomerOrder): Array[CustomerOrderLine] = {
    val searchOrderLine = new CustomerOrderLine(null, selectedCustomerOrder.idCustomerOrder.getValue, null)
    val searchValues = Array(searchOrderLine.idItem, searchOrderLine.idCustomerOrder, searchOrderLine.quantity)
    val currentOrderLines = SQLConnector.read(searchOrderLine.tableName, searchValues)
    if (currentOrderLines.size > 0) {
      var output = new Array[CustomerOrderLine](currentOrderLines.length)
      def loop(i: Int) {
        if (i < currentOrderLines.size) {
          output(i) = EntityConvertor.convertToCustomerOrderLine(currentOrderLines(i))
          loop(i + (1))
        }
      }
      loop(0)
      output
    } else {
      null
    }
  }

  // Searches the database for all lines in the current purchase order
  def searchPurchaseOrderLine(selectedPurchaseOrder: PurchaseOrder): Array[PurchaseOrderLine] = {
    val searchOrderLine = new PurchaseOrderLine(selectedPurchaseOrder.idPurchaseOrder.getValue.asInstanceOf[Int], null, null, null)
    val searchValues = Array(searchOrderLine.idPurchaseOrder, searchOrderLine.idItem, searchOrderLine.quantity, searchOrderLine.quantityDamaged)
    val currentOrderLines = SQLConnector.read(searchOrderLine.tableName, searchValues)
    if (currentOrderLines.size > 0) {
      var output = new Array[PurchaseOrderLine](currentOrderLines.length)
      def loop(i: Int) {
        if (i < currentOrderLines.size) {
          output(i) = EntityConvertor.convertToPurchaseOrderLine(currentOrderLines(i))
          loop(i + (1))
        }
      }
      loop(0)
      output
    } else {
      null
    }
  }

  // Searches the database for all locations associated with an item
  def searchLocation(selectedItem: Item): Array[Location] = {
    println("CURRENT ITEM: " + selectedItem.idItem.getValue)
    val searchLocation = new Location(null, selectedItem.idItem.getValue, null, null, null)
    val searchValues = Array(searchLocation.idLocation, searchLocation.idItem, searchLocation.row, searchLocation.col, searchLocation.quantity)
    val currentLocations = SQLConnector.read(searchLocation.tableName, searchValues)
    if (currentLocations.size > 0) {
      var output = new Array[Location](currentLocations.size)
      def loop(i: Int) {
        if (i < currentLocations.size) {
          output(i) = EntityConvertor.convertToLocation(currentLocations(i))
          loop(i + (1))
        }
      }
      loop(0)
      output
    } else {
      null
    }
  }

  // Searches the database for a matching user
  def searchUser(selectedUser: User): User = {
    val searchUser = new User(selectedUser.idUser_, selectedUser.password_, null, null, null, 1)
    val searchValues = Array(searchUser.idUser, searchUser.password, searchUser.forename, searchUser.surname, searchUser.email, searchUser.isEmployee)
    val currentUser = SQLConnector.read(searchUser.tableName, searchValues)
    if (currentUser.size != 0) {
      var output = EntityConvertor.convertToUser(currentUser(0))
      output
    } else {
      null
    }
  }

  // Searches the database for a matching item
  def searchItem(selectedItem: Item): Item = {
    val searchValues = Array(selectedItem.idItem)
    val currentItem = MongoConnector.read(selectedItem.collectionName, searchValues)
    if (currentItem.size != 0) {
      var output = EntityConvertor.convertToItem(currentItem(0))
      output
    } else {
      null
    }
  }

}