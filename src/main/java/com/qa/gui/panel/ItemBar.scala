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

  def itemRow(title: String): StackPane = {
    var stack = new StackPane()
    var text = new Text(title) {
      fill = Black
      font = Font.font("Tahoma", 14)
    }
    text.setMouseTransparent(true)
    var back = new Rectangle() {
      width = 135
      height = 50
      fill = White
      onMouseClicked = (me: MouseEvent) => {
          selectedItem = title
          fill = LightGrey
      }
      onMouseEntered = (me: MouseEvent) => {
        fill = LightGrey
      }
      onMouseExited = (me: MouseEvent) => {
        fill = White
      }
    }
    stack.children.addAll(back, text)
    stack
  }
  
  def getRows(): ScrollPane = {
    var scroll = new ScrollPane
    var vbox = new VBox
    def makeRow(i: Int) {
      if(i < 10) { // change this
        vbox.children.add(itemRow("Placeholder")) // change this
        makeRow(i +(1))
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
      fill = White
      font = Font.font("Tahoma")
    }
    text.setMouseTransparent(true)
    var dropdownBox = new Rectangle() {
      width = 53
      height = 50
      fill = Grey
      onMouseClicked = (me: MouseEvent) => {
        if (expanded) {
          border.bottom = new Rectangle()
          fill = Grey
          text.rotate = 0
          text.fill = White
          expanded = false
        } else {
          border.bottom = getRows
          expanded = true
          fill = LightGrey
          text.rotate = 180
          text.fill = Black
        }
      }
      onMouseEntered = (me: MouseEvent) => {
        fill = LightGrey
        text.fill = Black
      }
      onMouseExited = (me: MouseEvent) => {
        if (expanded) {
          fill = LightGrey
          text.fill = Black
        } else {
          fill = Grey
          text.fill = White
        }
      }
    }
    stack.children.addAll(dropdownBox, text)
    stack
  }

  def createPanel() {
    var border = new BorderPane()
    var stack = new StackPane()
    var text = new Text(selectedItem) {
      fill = White
      font = Font.font("Tahoma", 14)
    }
    text.setMouseTransparent(true)
    var back = new Rectangle() {
      width = 100
      height = 50
      fill = White
    }
    stack.children.addAll(back, text)
    this.right = dropdown(this)
    this.left = stack
  }
  createPanel
}