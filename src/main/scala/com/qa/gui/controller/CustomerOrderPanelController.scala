package com.qa.gui.controller

import com.qa.data.entity.QueryLoader
import scalafx.scene.layout.VBox
import com.qa.gui.panel.CustomerOrderBar
import com.qa.data.entity.CustomerOrder
import com.qa.application.Session
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.ScrollPane

/**
 * Controls the logic behind the customer order panel.
 * @author cboucher
 */
class CustomerOrderPanelController {

  /**
   * Creates a table that contains all customer orders in their own customer order bars.
   * @return The filled table as a pane.
   */
  def createTable(scene: BorderPane): VBox = {
    val table = new VBox
    val orders = QueryLoader.searchCustomerOrder(new CustomerOrder(null, null, null, null, null, null, null, null))
    def addRow(i: Int) {
      if (i < orders.size) {
        table.children.add(new CustomerOrderBar(orders(i),scene))
        addRow(i + (1))
      }
    }
    if (orders != null) {
      addRow(0)
    }
    table
  }
  
  /**
   * Adds the table created by the controller to the scene.
   * @param scene The border pane to be used with refreshing.
   */
  def createPanel(scene: BorderPane): Unit = {
    val orders = new ScrollPane
    orders.setContent(createTable(scene))
    scene.center = orders
  }

  /**
   * Sets the current selected customer order depending on user input.
   * @param customerOrder The selected customer order.
   */
  def selectCustomerOrder(customerOrder: CustomerOrder): Unit = {
    Session.currentCustomerOrder = customerOrder
  }
}