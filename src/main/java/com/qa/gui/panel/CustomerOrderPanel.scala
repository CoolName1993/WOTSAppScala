package com.qa.gui.panel
import scalafx.scene.shape.Rectangle
import scalafx.scene.layout.BorderPane
import scalafx.scene.paint.Color._
import scalafx.scene.layout.VBox
import scalafx.scene.control.ScrollPane
import com.qa.gui.controller.CustomerOrderPanelController

/**
 * Represents the available customer order tab in the GUI.
 * @author cboucher
 */
class CustomerOrderPanel extends BorderPane {

  def createPanel: Unit = {
    val orders = new ScrollPane
    orders.setContent(new CustomerOrderPanelController().createTable)
    this.center = orders
  }
  
  createPanel
}