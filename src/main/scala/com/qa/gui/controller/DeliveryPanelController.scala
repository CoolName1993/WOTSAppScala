package com.qa.gui.controller

import com.qa.data.entity.QueryLoader
import scalafx.scene.layout.VBox
import com.qa.gui.panel.DeliveryBar
import com.qa.data.entity.PurchaseOrder
import com.qa.application.Session

/**
 * Controls the logic behind the delivery panel.
 * @author cboucher
 */
class DeliveryPanelController {
  
  /**
   * Creates a table that contains all purchase orders in their own purchase order bars
   * @return The filled table as a pane.
   */
  def createTable: VBox = {
    val table = new VBox
    val orders = QueryLoader.searchPurchaseOrder(new PurchaseOrder(null,null,null,null,null,null))
    def addRow(i: Int) {
      if(i < orders.size) {
        table.children.add(new DeliveryBar(orders(i)))
        addRow(i +(1))
      }
    }
    if(orders != null) {
      addRow(0)
    }
    table
  }
  
  /**
   * Sets the current selected purchase order depending on user input.
   * @param purchaseOrder The selected purchase order.
   */
  def selectPurchaseOrder(purchaseOrder: PurchaseOrder): Unit = {
    Session.currentPurchaseOrder = purchaseOrder
  }
}