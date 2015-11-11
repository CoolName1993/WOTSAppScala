package com.qa.gui.panel

import scalafx.Includes._
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.VBox
import scalafx.scene.layout.StackPane
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color._
import scalafx.scene.text.Text
import scalafx.scene.text.Font
import scalafx.scene.input.MouseEvent
import com.qa.gui.controller.CustomerOrderMapController

/**
 * @author cboucher
 */
class CustomerOrderMapPanel extends BorderPane {

  def addButton(title: String, clickFunction: () => Unit): StackPane = {
    var stack = new StackPane()
    var text = new Text(title) {
      fill = White
      font = Font.font("Tahoma", 14)
    }
    text.setMouseTransparent(true)
    var back = new Rectangle() {
      width = 153
      height = 50
      fill = Grey //Replace
      onMouseClicked = (me: MouseEvent) => {
        clickFunction()
      }
      onMouseEntered = (me: MouseEvent) => {
        fill = White //replace
      }
      onMouseExited = (me: MouseEvent) => {
        fill = Grey //replace
      }
    }
    stack.children.addAll(back, text)
    stack
  }

  def placeholder() {
    println("hello")
  }

  def rightPanel(): VBox = {
    var vbox = new VBox()
    vbox.children.addAll(addButton("Collect", placeholder), addButton("Complete", placeholder))
    vbox
  }

  this.left = new CustomerOrderMapController().getCurrentMap
  this.center = rightPanel

}