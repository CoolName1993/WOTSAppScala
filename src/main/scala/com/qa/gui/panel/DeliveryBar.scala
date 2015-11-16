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
import com.qa.gui.controller.DeliveryPanelController

/**
 * One delivery bar in the available delivery tab in the GUI.
 * @author cboucher
 */
class DeliveryBar(purchaseOrder: PurchaseOrder) extends BorderPane {
  var expanded = false
  var current = "button-bad"

  /**
   * Creates the status button which can be toggled on and off
   * @return A stack pane that acts like a button.
   */
  def status: StackPane = {
    val text = new Text("Inactive") {
      id = "table-light"
    }
    text.setMouseTransparent(true)
    val statusBox = new Rectangle {
      width = 100
      height = 50
      id = current
      onMouseClicked = (me: MouseEvent) => {
        if (current == "button-good") {
          current = "button-bad"
          id = "button-bad-highlight"
          text.text = "Inactive"
          new DeliveryPanelController().selectPurchaseOrder(null)
        } else {
          new DeliveryPanelController().selectPurchaseOrder(purchaseOrder)
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
    val stack = new StackPane
    stack.children.addAll(statusBox, text)
    stack
  }

  /**
   * Creates the dropdown button which is used to expand the row
   * @param border The border pane to edit.
   * @return A stack pane that acts like a button
   */
  def dropdown(border: BorderPane): StackPane = {
    val text = new Text("V") {
      id = "table-light"
    }
    text.setMouseTransparent(true)
    val dropdownBox = new Rectangle {
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

    val stack = new StackPane
    stack.children.addAll(dropdownBox, text)
    stack
  }

  /**
   *  Creates a box in the bar
   *  @param setWidth The width of the box.
   *  @param setID The id of the box used for css.
   *  @param setTextID The id of the text used for css.
   *  @param setText The text on top of the box.
   */
  def field(setWidth: Int, setID: String, setTextID: String, setText: String): StackPane = {
    val back = new Rectangle {
      width = setWidth
      height = 50
      id = setID
    }
    val text = new Text(setText) {
      id = setTextID
    }
    val stack = new StackPane
    stack.children.addAll(back, text)
    stack
  }

  /**
   * Creates the main bar.
   * @return A horizontal box with the main info about the delivery.
   */
  def deliveryInfo: HBox = {
    val deliveryBox = new HBox
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
   * Creates the more info bar which displays each item in the delivery.
   * @return A vertical box with the info about each item in horizontal boxes.
   */
  def moreInfo: VBox = {
    val lineList = QueryLoader.searchPurchaseOrderLine(purchaseOrder)
    val infoPane = new VBox
    def lineInfo(i: Int): Unit = {
      if (i < lineList.size) {
        val deliveryBox = new HBox
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

  /**
   * Creates the panel.
   */
  def createPanel: Unit = {
    this.left = status
    this.center = deliveryInfo
    this.right = dropdown(this)
  }

  createPanel

}