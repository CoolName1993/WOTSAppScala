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
 * The panel that displays a map of the current delivery
 * alongside buttons that allow the user to place items into the warehouse.
 * @author cboucher
 */
class DeliveryMapPanel extends BorderPane {

  /**
   * Creates a button that performs a function when pressed.
   * @param title The text on the button.
   * @param default The default id of the button.
   * @param highlight The highlight id of the button.
   * @param clickfunction The function that will be performed when the button is clicked.
   * @return A stack pane that acts like a button.
   */
  def addButton(title: String, default: String, highlight: String, clickFunction: () => Unit): StackPane = {
    val stack = new StackPane
    val text = new Text(title) {
      id = "light-colour"
    }
    text.setMouseTransparent(true)
    val back = new Rectangle() {
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

  /**
   * Creates a section that prompts the user to enter a quantity
   * @return A VBox containing a stack pane and a text field.
   */
  def inputAmount: VBox = {
    val vbox = new VBox
    val stack = new StackPane
    val text = new Text("Quantity") {
      id = "dark-colour"
    }
    var back = new Rectangle {
      width = 153
      height = 50
      id = "table-field"
    }
    stack.children.addAll(back, text)
    val textField = new TextField
    textField.font = Font.font("Tahoma", 24)
    textField.prefWidth = 153
    textField.setPromptText("Quantity")
    vbox.children.addAll(stack, textField)
    vbox
  }

  /**
   * Placeholder for functions that will be performed when buttons are pressed.
   */
  def placeholder() {
    println("To be implemented")
  }

  /**
   * Creates the left panel that includes buttons that do stuff.
   * @return A flow pane containing buttons made from stack panes.
   */
  def leftPanel: FlowPane = {
    var flow = new FlowPane
    flow.setPrefWrapLength(153)
    flow.children.add(new ItemBar)
    flow.children.add(inputAmount)
    flow.children.add(addButton("Add item", "button-default", "button-default-highlight", placeholder))
    flow.children.add(addButton("Submit delivery", "button-good", "button-good-highlight", placeholder))
    flow
  }

  /**
   * Creates a panel that displays the delivery map and other buttons.
   */
  def createPanel: Unit = {
    this.center = leftPanel
    this.left = new DeliveryMapController().getCurrentMap
  }

  createPanel
}