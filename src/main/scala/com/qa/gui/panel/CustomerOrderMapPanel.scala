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

  def addButton(title: String, default: String, highlight: String, clickFunction: () => Unit): StackPane = {
    val stack = new StackPane
    val text = new Text(title) {
      id = "light-colour"
    }
    text.setMouseTransparent(true)
    val back = new Rectangle {
      width = 153
      height = 50
      id = default
      onMouseClicked = (me: MouseEvent) => {
        clickFunction()
      }
      onMouseEntered = (me: MouseEvent) => {
        id = highlight
      }
      onMouseExited = (me: MouseEvent) => {
        id = default
      }
    }
    stack.children.addAll(back, text)
    stack
  }

  def placeholder() {
    println("hello")
  }

  def rightPanel: VBox = {
    val vbox = new VBox
    vbox.children.addAll(addButton("Collect", "button-default", "button-default-highlight", placeholder), addButton("Complete", "button-good", "button-good-highlight", placeholder))
    vbox
  }

  def createPanel: Unit = {
    new CustomerOrderMapController().getCurrentMap(new CustomerOrderMapController().queryOrder, this)
    this.center = rightPanel
  }

  createPanel

}