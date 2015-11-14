package com.qa.gui.controller

import com.qa.data.entity.QueryLoader
import scalafx.scene.layout.VBox
import com.qa.gui.panel.CustomerOrderBar
import com.qa.data.entity.CustomerOrder

/**
 * @author cboucher
 */
class CustomerOrderPanelController {
  def createTable(): VBox = {
    var table = new VBox
    val orders = QueryLoader.searchCustomerOrder(new CustomerOrder(null,null,null,null,null,null,null,null))
    def addRow(i: Int) {
      if(i < orders.size) {
        println(orders(i))
        table.children.add(new CustomerOrderBar(orders(i)))
        addRow(i +(1))
      }
    }
    if(orders != null) {
      addRow(0)
    }
    table
  }
}