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
 * Creates the customer order map panel that allows the user to see the order in the warehouse.
 * @author cboucher
 */
class CustomerOrderMapPanel extends BorderPane {

  /**
   * Creates a button that performs a specified function.
   * @param title The text on the button.
   * @param default The default colour of the button.
   * @param highlight The highlighted colour of the button.
   * @param clickFunction The function to perform when the button is pressed.
   * @return A stack pane that acts like a button.
   */
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

  /**
   * A placeholder function for unimplemented features.
   */
  def placeholder() {
    println("unimplemented")
  }

  /**
   * The right panel of the map panel.
   * @return A VBox with buttons for collecting items and completing the order.
   */
  def rightPanel: VBox = {
    val vbox = new VBox
    vbox.children.addAll(addButton("Collect", "button-default", "button-default-highlight", placeholder), addButton("Complete", "button-good", "button-good-highlight", placeholder))
    vbox
  }

  /**
   * Creates the panel.
   */
  def createPanel: Unit = {
    new CustomerOrderMapController().getCurrentMap(new CustomerOrderMapController().queryOrder, this)
    this.center = rightPanel
  }

  createPanel

}