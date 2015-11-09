package com.qa.gui.panel
import scalafx.scene.layout.BorderPane
import scalafx.scene.shape.Rectangle
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.VBox

/**
 * @author cboucher
 */
class DeliveryPanel() extends BorderPane {

  def createPanel() {
    val test = new Rectangle() {
      width = 400
      height = 400
    }
    this.left = test
  }

  def testPanel(): VBox = {
    var test = new VBox
    def addRow(i: Int) {
      if (i < 30) {
        test.children.add(new DeliveryBar())
        addRow(i + (1))
      }
    }
    addRow(0)
    test
  }

  var deliveries = new ScrollPane()
  deliveries.setContent(testPanel)

  this.center = deliveries
}