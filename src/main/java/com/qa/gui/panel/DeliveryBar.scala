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
import com.qa.data.entity.PurchaseOrder
import com.qa.data.entity.QueryLoader

/**
 * One delivery bar in the available delivery tab in the GUI.
 * @author cboucher
 */
case class DeliveryBar(purchaseOrder: PurchaseOrder) extends BorderPane {
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
    expected.children.addAll(box(100, White), new Text(purchaseOrder.dateExpected.getValue.toString))
    idTitle.children.addAll(box(100, LightGrey), new Text("Order ID:"))
    id.children.addAll(box(100, White), new Text(purchaseOrder.idPurchaseOrder.getValue.toString))
    employeeTitle.children.addAll(box(100, LightGrey), new Text("Assignee:"))
    employee.children.addAll(box(100, White), new Text(purchaseOrder.idEmployee.getValue.toString))
    deliveryBox.children.addAll(idTitle, id, expectedTitle, expected, employeeTitle, employee)
    deliveryBox
  }
  /**
   * Creates the more info bar which displays each item in the delivery
   */
  def moreInfo(): VBox = {
    var lineList = QueryLoader.searchPurchaseOrderLine(purchaseOrder)
    var infoPane = new VBox()
    def lineInfo(i: Int): Unit = {
      if (i < lineList.size) {
        var deliveryBox = new HBox()
        var idTitle = new StackPane()
        var id = new StackPane()
        var quantityTitle = new StackPane()
        var quantity = new StackPane()
        var quantityDTitle = new StackPane()
        var quantityD = new StackPane()
        idTitle.children.addAll(box(100, LightGrey), new Text("Item ID:"))
        id.children.addAll(box(100, White), new Text(lineList(i).idItem.getValue.toString()))
        quantityTitle.children.addAll(box(100, LightGrey), new Text("Quantity:"))
        quantity.children.addAll(box(100, White), new Text(lineList(i).quantity.getValue.toString()))
        quantityDTitle.children.addAll(box(100, LightGrey), new Text("Damaged:"))
        quantityD.children.addAll(box(100, White), new Text(lineList(i).quantityDamaged.getValue.toString()))
        deliveryBox.children.addAll(box(100, WhiteSmoke), idTitle, id, quantityTitle, quantity, quantityDTitle, quantityD)
        infoPane.children.add(deliveryBox)
        lineInfo(i + (1))
      }
    }
    lineInfo(0)
    infoPane
  }

  this.left = status
  this.center = deliveryInfo
  this.right = dropdown(this)
}