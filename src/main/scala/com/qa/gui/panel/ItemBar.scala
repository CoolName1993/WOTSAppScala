package com.qa.gui.panel

import scalafx.Includes._
import scalafx.scene.layout._
import scalafx.scene.text._
import scalafx.scene.shape._
import scalafx.scene.paint.Color._
import scalafx.scene.input.MouseEvent
import scalafx.scene.control.ScrollPane
import scalafx.scene.control.TextField

/**
 * A dropdown menu used to select an item in an order.
 * @author cboucher
 */
class ItemBar extends BorderPane {

  var selectedItem = ""
  val selectedStack: StackPane = new StackPane

  /**
   * Creates a cell in the dropdown menu.
   * @param title The name of the item.
   * @return A stack pane that acts like a cell in a combo box.
   */
  def itemRow(title: String): StackPane = {
    val stack = new StackPane
    val text = new Text(title) {
      id = "table-dark"
    }
    text.setMouseTransparent(true)
    val back = new Rectangle {
      width = 135
      height = 50
      id = "table-field"
      onMouseClicked = (me: MouseEvent) => {
        selectedItem = title
        update
        id = "table-title"
      }
      onMouseEntered = (me: MouseEvent) => {
        id = "table-title"
      }
      onMouseExited = (me: MouseEvent) => {
        id = "table-field"
      }
    }
    stack.children.addAll(back, text)
    stack
  }

  /**
   * Creates the dropdown section that consists of a scroll pane containing a list of item cells.
   * @return A scroll pane containing stack panes.
   */
  def getRows: ScrollPane = {
    val scroll = new ScrollPane
    val vbox = new VBox
    
    /**
     * Placeholder function to put fake values in.
     */
    def makeRow(i: Int) {
      if (i < 10) {
        vbox.children.add(itemRow("Item " + i))
        makeRow(i + (1))
      }
    }
    makeRow(0)
    scroll.prefHeight = 200
    scroll.setContent(vbox)
    scroll
  }

  /**
   * Creates a button that allows the user to expand the bar like a combo box
   * @param border The border pane to add the scroll pane to.
   * @return A stack pane that acts like a button.
   */
  def dropdown(border: BorderPane): StackPane = {
    val stack = new StackPane
    var expanded = false
    val text = new Text("V") {
      id = "table-light"
    }
    text.setMouseTransparent(true)
    val dropdownBox = new Rectangle() {
      width = 53
      height = 50
      id = "button-default"
      onMouseClicked = (me: MouseEvent) => {
        if (expanded) {
          border.bottom = new Rectangle()
          id = "button-default-highlight"
          text.rotate = 0
          expanded = false
        } else {
          border.bottom = getRows
          expanded = true
          id = "button-default"
          text.rotate = 180
        }
      }
      onMouseEntered = (me: MouseEvent) => {
        id = "button-default-highlight"
      }
      onMouseExited = (me: MouseEvent) => {
        if (expanded) {
          id = "button-default-highlight"
        } else {
          id = "button-default"
        }
      }
    }
    stack.children.addAll(dropdownBox, text)
    stack
  }

  /**
   * Updates the selected item, similar to selected item in a combo box
   */
  def update {
    val text = new Text(selectedItem) {
      id = "table-dark"
    }
    text.setMouseTransparent(true)
    val back = new Rectangle() {
      width = 100
      height = 50
      id = "table-field"
    }
    selectedStack.children.addAll(back, text)
  }

  /**
   * Creates the item bar.
   */
  def createPanel {
    val border = new BorderPane
    update
    this.right = dropdown(this)
    this.left = selectedStack
  }
  
  createPanel
}