package com.qa.gui.controller

import com.qa.gui.panel.DeliveryMap
import com.qa.data.entity.PurchaseOrder
import com.qa.application.Session
import com.qa.gui.pathfinder.Pathfinder
import com.qa.data.entity.PurchaseOrderLine
import com.qa.data.sql.SQLConnector
import com.qa.data.entity.EntityConvertor
import com.qa.data.entity.QueryLoader
import com.qa.data.entity.Item
import com.qa.data.entity.Location

/**
 * @author cboucher
 */
class DeliveryMapController {

  def getCurrentMap(): DeliveryMap = {
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
    if (Session.currentPurchaseOrder != null) {
      val orderLines = QueryLoader.searchPurchaseOrderLine(Session.currentPurchaseOrder)
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
      var outputList = new Array[Array[Int]](locationList.size)
      def loopLocationList(i: Int) {
        if (i < locationList.length) {
          def loopLocationArray(j: Int) {
            if (j < locationList(i).size) {
              
            }
          }
        }
      }
    }
    new DeliveryMap(map)
  }

}