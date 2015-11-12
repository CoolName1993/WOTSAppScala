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
import com.qa.application.Session

/**
 * One delivery bar in the available delivery tab in the GUI.
 * @author cboucher
 */
case class DeliveryBar(purchaseOrder: PurchaseOrder) extends BorderPane {
  var expanded = false
  var current = "button-bad"

  /**
   * Creates the status button which can be toggled on and off
   */
  def status(): StackPane = {
    var text = new Text("Inactive") {
      id = "table-light"
    }
    text.setMouseTransparent(true)
    var statusBox = new Rectangle {
      width = 100
      height = 50
      id = current
      onMouseClicked = (me: MouseEvent) => {
        if (current == "button-good") {
          current = "button-bad"
          id = "button-bad-highlight"
          text.text = "Inactive"
          Session.currentPurchaseOrder = null
        } else {
          Session.currentPurchaseOrder = purchaseOrder
          current = "button-good"
          id = "button-good-highlight"
          text.text = "Active"
        }
      }
      onMouseEntered = (me: MouseEvent) => {
        if (current == "button-good") {
          id = "button-good-highlight"
        } else {
          id = "button-bad-highlight"
        }
      }
      onMouseExited = (me: MouseEvent) => {
        id = current
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
      id = "table-light"
    }
    text.setMouseTransparent(true)
    var dropdownBox = new Rectangle {
      width = 100
      height = 50
      id = "button-default"
      onMouseClicked = (me: MouseEvent) => {
        if (expanded) {
          border.bottom = new Rectangle
          id = "button-default"
          text.rotate = 0
          expanded = false
        } else {
          border.bottom = moreInfo
          expanded = true
          id = "button-default-highlight"
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

    var stack = new StackPane()
    stack.children.addAll(dropdownBox, text)
    stack
  }

  /**
   *  Creates a box in the bar
   */
  def field(setWidth: Int, setID: String, setTextID: String, setText: String): StackPane = {
    var back = new Rectangle {
      width = setWidth
      height = 50
      id = setID
    }
    var text = new Text(setText) {
      id = setTextID
    }
    var stack = new StackPane
    stack.children.addAll(back, text)
    stack
  }

  /**
   * Creates the main bar
   */
  def deliveryInfo(): HBox = {
    val deliveryBox = new HBox()
    val expectedTitle = field(100, "table-title", "table-dark", "Expected:")
    val expected = field(100, "table-field", "table-dark", purchaseOrder.dateExpected.getValue.toString)
    val idTitle = field(100, "table-title", "table-dark", "Order ID:")
    val id = field(100, "table-field", "table-dark", purchaseOrder.idPurchaseOrder.getValue.toString)
    val employeeTitle = field(100, "table-title", "table-dark", "Assignee:")
    val employee = field(100, "table-field", "table-dark", purchaseOrder.idEmployee.getValue.toString)
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
        val deliveryBox = new HBox()
        val idTitle = field(100, "table-title", "table-dark", "Item ID:")
        val id = field(100, "table-field", "table-dark", lineList(i).idItem.getValue.toString)
        val quantityTitle = field(100, "table-title", "table-dark", "Quantity:")
        val quantity = field(100, "table-field", "table-dark", lineList(i).quantity.getValue.toString)
        val quantityDTitle = field(100, "table-title", "table-dark", "Damaged:")
        val quantityD = field(100, "table-field", "table-dark", lineList(i).quantityDamaged.getValue.toString)
        val empty = field(100, "table-field", "table-dark", "")
        deliveryBox.children.addAll(empty, idTitle, id, quantityTitle, quantity, quantityDTitle, quantityD)
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