package com.qa.gui.panel
import scalafx.scene.layout.BorderPane
import scalafx.scene.shape.Rectangle
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.VBox
import com.qa.gui.controller.DeliveryPanelController

/**
 * @author cboucher
 */
class DeliveryPanel() extends BorderPane {

  var deliveries = new ScrollPane()
  deliveries.setContent(new DeliveryPanelController().createTable())

  this.center = deliveries
}