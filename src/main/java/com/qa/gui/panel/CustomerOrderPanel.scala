package com.qa.gui.panel
import scalafx.scene.shape.Rectangle
import scalafx.scene.layout.BorderPane
import scalafx.scene.paint.Color._

/**
 * @author cboucher
 */
class CustomerOrderPanel extends BorderPane {
  def createPanel() {
    val test = new Rectangle() {
      width = 100
      height = 100
      fill = Green
    }
    this.children.add(test)
  }

  createPanel
}