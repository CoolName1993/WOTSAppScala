package com.qa.gui.panel

import scalafx.Includes._
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.FlowPane
import scalafx.scene.layout.StackPane
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color._
import scalafx.scene.text.Font
import scalafx.scene.text.Text
import scalafx.scene.control.ScrollPane
import scalafx.scene.paint.Color
import scalafx.scene.layout.VBox
import scalafx.scene.control.TextField
import scalafx.scene.input.MouseEvent
import com.qa.gui.controller.DeliveryMapController

/**
 * @author cboucher
 */
class DeliveryMapPanel() extends BorderPane {

  def addButton(title: String, default: String, highlight: String, clickFunction: () => Unit): StackPane = {
    var stack = new StackPane()
    var text = new Text(title) {
      id = "light-colour"
    }
    text.setMouseTransparent(true)
    var back = new Rectangle() {
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

  def inputAmount(): VBox = {
    var vbox = new VBox
    var stack = new StackPane()
    var text = new Text("Quantity") {
      id = "dark-colour"
    }
    var back = new Rectangle() {
      width = 153
      height = 50
      id = "table-field"
    }
    stack.children.addAll(back, text)
    var textField = new TextField()
    textField.font = Font.font("Tahoma", 24)
    textField.prefWidth = 153
    textField.setPromptText("Quantity")
    vbox.children.addAll(stack, textField)
    vbox
  }

  def placeholder() { // replace with controller stuff
    println("HELLO")
  }

  def leftPanel(): FlowPane = {
    var flow = new FlowPane()
    flow.setPrefWrapLength(153)
    flow.children.add(new ItemBar())
    flow.children.add(inputAmount)
    flow.children.add(addButton("Add item", "button-default", "button-default-highlight", placeholder))
    flow.children.add(addButton("Submit delivery", "button-good", "button-good-highlight", placeholder))
    flow
  }
  this.center = leftPanel
  this.left = new DeliveryMapController().getCurrentMap
}