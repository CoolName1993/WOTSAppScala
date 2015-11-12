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
      var orderLines = QueryLoader.searchCustomerOrderLine(Session.currentCustomerOrder)
      var locationList = new Array[Array[Location]](orderLines.size)
      def loopItem(i: Int) {
        if (i < orderLines.size) {
          val item = QueryLoader.searchItem(new Item(orderLines(i).idItem.getValue, null, null, null))
          val locations = QueryLoader.searchLocation(item)
          locationList(i) = locations
          loopItem(i + (1))
        }
      }
      loopItem(0)
      map = new Pathfinder().pathfind(locationList)
      new CustomerOrderMap(map)
    }
    new CustomerOrderMap(map)
  }
}