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
 * @author cboucher
 */
class ItemBar extends BorderPane {
  var expanded = false
  var selectedItem = ""
  var selectedStack: StackPane = new StackPane

  def itemRow(title: String): StackPane = {
    var stack = new StackPane()
    var text = new Text(title) {
      id = "table-dark"
    }
    text.setMouseTransparent(true)
    var back = new Rectangle() {
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

  def getRows(): ScrollPane = {
    var scroll = new ScrollPane
    var vbox = new VBox
    def makeRow(i: Int) {
      if (i < 10) { // change this
        vbox.children.add(itemRow("Item " + i)) // change this
        makeRow(i + (1))
      }
    }
    makeRow(0)
    scroll.prefHeight = 200
    scroll.setContent(vbox)
    scroll
  }

  def dropdown(border: BorderPane): StackPane = {
    var stack = new StackPane()
    var text = new Text("V") {
      id = "table-light"
    }
    text.setMouseTransparent(true)
    var dropdownBox = new Rectangle() {
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

  def update() {
    var text = new Text(selectedItem) {
      id = "table-dark"
    }
    text.setMouseTransparent(true)
    var back = new Rectangle() {
      width = 100
      height = 50
      id = "table-field"
    }
    selectedStack.children.addAll(back, text)
  }

  def createPanel() {
    var border = new BorderPane()
    update
    this.right = dropdown(this)
    this.left = selectedStack
  }
  createPanel
}