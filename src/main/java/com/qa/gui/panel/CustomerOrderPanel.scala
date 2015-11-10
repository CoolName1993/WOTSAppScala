package com.qa.gui.panel
import scalafx.scene.shape.Rectangle
import scalafx.scene.layout.BorderPane
import scalafx.scene.paint.Color._
import scalafx.scene.layout.VBox
import scalafx.scene.control.ScrollPane

/**
 * Represents the available customer order tab in the GUI.
 * @author cboucher
 */
class CustomerOrderPanel extends BorderPane {

  /**
   * Creates the table of customer orders
   */
  def tablePanel(): VBox = {
    var table = new VBox
    def addRow(i: Int) {
      if (i < 30) {
        table.children.add(new CustomerOrderBar())
        addRow(i + (1))
      }
    }
    addRow(0)
    table
  }

  var orders = new ScrollPane()
  orders.setContent(tablePanel)

  this.center = orders
}