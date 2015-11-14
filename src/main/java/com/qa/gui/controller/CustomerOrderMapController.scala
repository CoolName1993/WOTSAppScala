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

    // Create a default map
    val map = Array(
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

    // If there is currently a customer order selected.  
    if (Session.currentCustomerOrder != null) {
      val orderLines = QueryLoader.searchCustomerOrderLine(Session.currentCustomerOrder)
      val locationList = new Array[Array[Location]](orderLines.size)

      def loopItem(i: Int) {
        if (i < orderLines.size) {
          val item = QueryLoader.searchItem(new Item(orderLines(i).idItem.getValue, null, null, null))
          val locations = QueryLoader.searchLocation(item)
          locationList(i) = locations
          loopItem(i + (1))
        }
      }
      loopItem(0)
      new Pathfinder().execute(locationList, map)
      new CustomerOrderMap(map)
    }
    new CustomerOrderMap(map)
  }
}