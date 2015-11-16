package com.qa.gui.panel
import scalafx.scene.layout.BorderPane
import scalafx.scene.shape.Rectangle
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.VBox
import com.qa.gui.controller.DeliveryPanelController

/**
 * The panel used to display all purchase orders in a table.
 * @author cboucher
 */
class DeliveryPanel extends BorderPane {

  /**
   * Creates the delivery panel which contains a table in a scroll pane.
   */
  def createPanel: Unit = {
    var deliveries = new ScrollPane
    deliveries.setContent(new DeliveryPanelController().createTable)
    this.center = deliveries
  }

  createPanel
}