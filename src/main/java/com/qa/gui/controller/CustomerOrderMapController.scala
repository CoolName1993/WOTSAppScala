package com.qa.gui.controller

import com.qa.application.Session
import com.qa.data.entity.QueryLoader
import com.qa.data.entity.Location
import com.qa.data.entity.Item
import scala.collection.mutable.ListBuffer
import com.qa.gui.pathfinder.Pathfinder
import com.qa.gui.panel.CustomerOrderMap

/**
 * @author cboucher
 */
class CustomerOrderMapController {
  def getCurrentMap: CustomerOrderMap = {
    var map = Array(
      Array(2, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2),
      Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
      Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
      Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
      Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
      Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
      Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
      Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
      Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
      Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
      Array(2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2))
    if (Session.currentCustomerOrder != null) {
      val orderLines = QueryLoader.searchCustomerOrderLine(Session.currentCustomerOrder)
      var locationList = new Array[Array[Location]](orderLines.size)
      var itemList = new Array[Item](orderLines.size)
      def loopItem(i: Int) {
        if (i < orderLines.size) {
          val item = QueryLoader.searchItem(new Item(orderLines(i).idItem_, null, null, null))
          val locations = QueryLoader.searchLocation(item)
          itemList(i) = item
          locationList(i) = locations
        }
      }
      loopItem(0)
      println(locationList.length)
      println(itemList.length)
      var outputList = new Array[ListBuffer[Int]](locationList.size)
      def loopLocationList(i: Int) {
        def loopLocationArray(j: Int, quantityNeeded: Int) {
          println(j)
          if (j < locationList(i).length) {
            if (quantityNeeded < locationList(i)(j).quantity.getValue.asInstanceOf[Int]) {
              println("WORKING")
              outputList(i).+=(locationList(i)(j).col.getValue.asInstanceOf[Int], locationList(i)(j).row.getValue.asInstanceOf[Int])
              println(outputList(i))
            } else {
              println("ALSO WORKING")
              outputList(i).+=(locationList(i)(j).col.getValue.asInstanceOf[Int], locationList(i)(j).row.getValue.asInstanceOf[Int])
              var quantityLeft = quantityNeeded - locationList(i)(j).quantity.getValue.asInstanceOf[Int]
              loopLocationArray(j + (1), quantityLeft)
            }
          }
        }
        if (i < locationList.length) {
          loopLocationArray(0, orderLines(i).quantity.getValue.asInstanceOf[Int])
          loopLocationList(i + (1))
        }
      }
      loopLocationList(0)
      map = new Pathfinder().pathfind(outputList)
      new CustomerOrderMap(map)
    }
    new CustomerOrderMap(map)
  }
}