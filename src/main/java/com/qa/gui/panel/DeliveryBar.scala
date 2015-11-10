package com.qa.gui.panel

import scalafx.Includes._
import scalafx.scene.layout.BorderPane
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.scene.input.MouseEvent
import scalafx.scene.text._
import scalafx.scene.layout.StackPane
import javafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color

/**
 * One delivery bar in the available delivery tab in the GUI.
 * @author cboucher
 */
class DeliveryBar extends BorderPane {
  val good = Color.rgb(82, 167, 7)
  val goodHighlight = Color.rgb(120, 214, 36)
  val bad = Color.rgb(186, 13, 8)
  val badHighlight = Color.rgb(239, 46, 41)
  var expanded = false
  var current = bad

  /**
   * Creates the status button which can be toggled on and off
   */
  def status(): StackPane = {
    var text = new Text("Inactive") {
      fill = White
      font = Font.font("Tahoma")
    }
    text.setMouseTransparent(true)
    var statusBox = new Rectangle() {
      width = 100
      height = 50
      fill = current
      onMouseClicked = (me: MouseEvent) => {
        if (current == good) {
          current = bad
          fill = badHighlight
          text.text = "Inactive"
        } else {
          current = good
          fill = goodHighlight
          text.text = "Active"
        }
      }
      onMouseEntered = (me: MouseEvent) => {
        if (current == good) {
          fill = goodHighlight
        } else {
          fill = badHighlight
        }
      }
      onMouseExited = (me: MouseEvent) => {
        fill = current
      }
    }
    var stack = new StackPane()
    stack.children.addAll(statusBox, text)
    stack
  }

  /**
   * Creates the dropdown button which is used to expand the row
   */
  def dropdown(border: BorderPane): StackPane = {
    var text = new Text("V") {
      fill = White
      font = Font.font("Tahoma")
    }
    text.setMouseTransparent(true)
    var dropdownBox = new Rectangle() {
      width = 100
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
          border.bottom = moreInfo
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

    var stack = new StackPane()
    stack.children.addAll(dropdownBox, text)
    stack
  }

  /**
   *  Creates a box in the bar
   */
  def box(setWidth: Int, colour: Color): Rectangle = {
    new Rectangle() {
      width = setWidth
      height = 50
      fill = colour
    }
  }

  /**
   * Creates the main bar
   */
  def deliveryInfo(): HBox = {
    var deliveryBox = new HBox()
    var expectedTitle = new StackPane()
    var expected = new StackPane()
    var idTitle = new StackPane()
    var id = new StackPane()
    var employeeTitle = new StackPane()
    var employee = new StackPane()
    expectedTitle.children.addAll(box(100, LightGrey), new Text("Date expected:"))
    expected.children.addAll(box(100, White), new Text("10/10/2015"))
    idTitle.children.addAll(box(100, LightGrey), new Text("ID:"))
    id.children.addAll(box(100, White), new Text("2345"))
    employeeTitle.children.addAll(box(100, LightGrey), new Text("Assignee:"))
    employee.children.addAll(box(100, White), new Text("Al Stock"))
    deliveryBox.children.addAll(idTitle, id, expectedTitle, expected, employeeTitle, employee)
    deliveryBox
  }
  /**
   * Creates the more info bar which displays each item in the delivery
   */
  def moreInfo(): VBox = {
    def lineInfo(): HBox = {
      var deliveryBox = new HBox()
      var idTitle = new StackPane()
      var id = new StackPane()
      var itemNameTitle = new StackPane()
      var itemName = new StackPane()
      var quantityTitle = new StackPane()
      var quantity = new StackPane()
      idTitle.children.addAll(box(100, LightGrey), new Text("ID:"))
      id.children.addAll(box(100, White), new Text("12"))
      itemNameTitle.children.addAll(box(100, LightGrey), new Text("Item name:"))
      itemName.children.addAll(box(200, White), new Text("Cool Gnome woo"))
      quantityTitle.children.addAll(box(100, LightGrey), new Text("Quantity:"))
      quantity.children.addAll(box(100, White), new Text("12"))
      deliveryBox.children.addAll(box(100, WhiteSmoke), idTitle, id, itemNameTitle, itemName, quantityTitle, quantity)
      deliveryBox
    }
    var infoPane = new VBox()
    def addRow(i: Int) {
      if (i < 5) {
        infoPane.children.add(lineInfo)
        addRow(i + (1))
      }
    }
    addRow(0)
    infoPane
  }

  this.left = status
  this.center = deliveryInfo
  this.right = dropdown(this)
}