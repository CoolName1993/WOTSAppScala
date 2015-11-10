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

/**
 * @author cboucher
 */
class DeliveryMapPanel extends BorderPane {
  val good = Color.rgb(82, 167, 7)
  val goodHighlight = Color.rgb(120, 214, 36)
  val bad = Color.rgb(186, 13, 8)
  val badHighlight = Color.rgb(239, 46, 41)

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
      fill = bad //Replace
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

  def inputAmount(): VBox = {
    var vbox = new VBox
    var stack = new StackPane()
    var text = new Text("Quantity")
    var back = new Rectangle() {
      width = 153
      height = 50
      fill = White
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

    def addChildren() {
      //get list of selected orders from database
      def addRow(i: Int) {
        if (i < 5) {
          flow.children.add(addButton("placeholder", placeholder))
          addRow(i + (1))
        }
      }
      addRow(0)
    }

    addChildren
    flow.children.add(new ItemBar())
    flow.children.add(inputAmount)
    flow.children.add(addButton("Add", placeholder))
    flow.children.add(addButton("Submit", placeholder))
    flow
  }
  this.left = leftPanel
  this.center = new DeliveryMap()
}