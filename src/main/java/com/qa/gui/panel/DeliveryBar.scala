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
 * @author cboucher
 */
class DeliveryBar extends BorderPane {
  val good = Color.rgb(82, 167, 7)
  val goodHighlight = Color.rgb(120, 214, 36)
  val bad = Color.rgb(186, 13, 8)
  val badHighlight = Color.rgb(239, 46, 41)
  var expanded = false
  var current = bad
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

  def boxTitle = new Rectangle() {
    width = 100
    height = 50
    fill = LightGrey
  }
  def boxContent = new Rectangle() {
    width = 100
    height = 50
    fill = White
  }
  def largeBoxContent = new Rectangle() {
    width = 200
    height = 50
    fill = White
  }

  def deliveryInfo(): HBox = {
    var deliveryBox = new HBox()
    var expectedTitle = new StackPane()
    var expected = new StackPane()
    var idTitle = new StackPane()
    var id = new StackPane()
    var employeeTitle = new StackPane()
    var employee = new StackPane()
    expectedTitle.children.addAll(boxTitle, new Text("Date expected:"))
    expected.children.addAll(boxContent, new Text("10/10/2015"))
    idTitle.children.addAll(boxTitle, new Text("ID:"))
    id.children.addAll(boxContent, new Text("2345"))
    employeeTitle.children.addAll(boxTitle, new Text("Assignee:"))
    employee.children.addAll(boxContent, new Text("Al Stock"))
    deliveryBox.children.addAll(idTitle, id, expectedTitle, expected, employeeTitle, employee)
    deliveryBox
  }

  def moreInfo(): VBox = {
    def lineInfo(): HBox = {
      var deliveryBox = new HBox()
      var idTitle = new StackPane()
      var id = new StackPane()
      var itemNameTitle = new StackPane()
      var itemName = new StackPane()
      var quantityTitle = new StackPane()
      var quantity = new StackPane()
      idTitle.children.addAll(boxTitle, new Text("ID:"))
      id.children.addAll(boxContent, new Text("12"))
      itemNameTitle.children.addAll(boxTitle, new Text("Item name:"))
      itemName.children.addAll(largeBoxContent, new Text("Cool Gnome woo"))
      quantityTitle.children.addAll(boxTitle, new Text("Quantity:"))
      quantity.children.addAll(boxContent, new Text("12"))
      deliveryBox.children.addAll(idTitle, id, itemNameTitle, itemName, quantityTitle, quantity)
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