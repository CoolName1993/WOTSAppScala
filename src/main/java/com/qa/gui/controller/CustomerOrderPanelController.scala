package com.qa.gui.controller

import com.qa.data.entity.QueryLoader
import scalafx.scene.layout.VBox
import com.qa.gui.panel.CustomerOrderBar
import com.qa.data.entity.CustomerOrder

/**
 * Controls the logic behind the customer order panel.
 * @author cboucher
 */
class CustomerOrderPanelController {

  /**
   * Creates a table that contains all customer orders in their own customer order bars
   * @return The filled table as a pane.
   */
  def createTable: VBox = {
    val table = new VBox
    val orders = QueryLoader.searchCustomerOrder(new CustomerOrder(null, null, null, null, null, null, null, null))
    def addRow(i: Int) {
      if (i < orders.size) {
        table.children.add(new CustomerOrderBar(orders(i)))
        addRow(i + (1))
      }
    }
    if (orders != null) {
      addRow(0)
    }
    table
  }
}