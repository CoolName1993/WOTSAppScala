package com.qa.gui.panel
import scalafx.scene.layout.BorderPane
import scalafx.scene.shape.Rectangle
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.VBox

/**
 * @author cboucher
 */
class DeliveryPanel() extends BorderPane {

  def tablePanel(): VBox = {
    var table = new VBox
    def addRow(i: Int) {
      if (i < 30) {
        table.children.add(new DeliveryBar())
        addRow(i + (1))
      }
    }
    addRow(0)
    table
  }

  var deliveries = new ScrollPane()
  deliveries.setContent(tablePanel)

  this.center = deliveries
}