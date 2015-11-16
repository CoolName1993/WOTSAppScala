package com.qa.gui.controller

import com.qa.application.Session
import com.qa.data.entity.QueryLoader
import com.qa.data.entity.Location
import com.qa.data.entity.Item
import scala.collection.mutable.ListBuffer
import com.qa.gui.pathfinder.Pathfinder
import com.qa.gui.panel.CustomerOrderMap
import com.qa.gui.pathfinder.WarehouseMap
import scalafx.scene.layout.BorderPane

/**
 * Gets the values to fill the customer order map with.
 * @author cboucher
 */
class CustomerOrderMapController {

  /**
   * Uses the currently selected customer order and returns a map with the calculated path
   * @return A customer order map panel with the generated map included.
   */
  def getCurrentMap(locationList: Array[Array[Location]], scene: BorderPane): Unit = {

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
      new Pathfinder().execute(locationList, map)
      scene.left = new CustomerOrderMap(map,scene)
    } else {
      scene.left = new CustomerOrderMap(map,scene)
    }

  }

  /**
   * Queries the database for all locations in the customer order.
   * @return A list of locations of items
   */
  def queryOrder: Array[Array[Location]] = {
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

    locationList
  }

  /**
   * Removes the selected location from the location list
   * @param row The row of the selected location.
   * @param col The column of the selected location.
   * @param scene The scene to add the map to.
   */
  def removeItem(row: Int, col: Int, scene: BorderPane) {
    val locationList = queryOrder

    def loopListX(x: Int) {

      def loopListY(y: Int) {
        if (y < locationList(x).length) {
          if (locationList(x)(y).row.equals(row) && locationList(x)(y).col.equals(col)) {
            locationList(x)(y) = null
          }
          loopListY(y + (1))
        }
      }
      if (x < locationList.length) {
        loopListY(0)
        loopListX(x + (1))
      }
    }

    if (locationList != null) {
      loopListX(0)
    }

    getCurrentMap(locationList, scene)
  }
}