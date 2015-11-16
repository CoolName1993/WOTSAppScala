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

  // Get the customer panel controller to create the scene.
  new CustomerOrderPanelController().createPanel(this)
}