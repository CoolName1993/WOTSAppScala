package com.qa.gui.panel
import scalafx.Includes._
import scalafx.scene.paint.Color._
import scalafx.scene.paint.Color
import scalafx.scene.layout.StackPane
import scalafx.scene.text.Text
import scalafx.scene.shape.Rectangle
import scalafx.scene.input.MouseEvent
import scalafx.scene.text.Font
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import com.qa.data.entity.{ CustomerOrder, Item }
import com.qa.data.entity.QueryLoader
import com.qa.application.Session

/**
 * One order bar in the available customer order tab in the GUI
 * @author cboucher
 */
case class CustomerOrderBar(customerOrder: CustomerOrder) extends BorderPane {

  var expanded = false
  var current = "button-bad"

  /**
   * Creates the status button which can be toggled on and off
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
          Session.currentCustomerOrder = null
        } else {
          Session.currentCustomerOrder = customerOrder
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
    val stack = new StackPane()
    stack.children.addAll(statusBox, text)
    stack
  }

  /**
   * Creates the dropdown button which is used to expand the row
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
   * Creates the main bar
   */
  def customerOrderInfo: HBox = {
    val deliveryBox = new HBox
    val expectedTitle = field(100, "table-title", "table-dark", "Created:")
    val expected = field(100, "table-field", "table-dark", customerOrder.datePlaced.getValue.toString)
    val idTitle = field(100, "table-title", "table-dark", "Order ID:")
    val id = field(100, "table-field", "table-dark", customerOrder.idCustomerOrder.getValue.toString)
    val employeeTitle = field(100, "table-title", "table-dark", "Assignee:")
    val employee = field(100, "table-field", "table-dark", customerOrder.idEmployee.getValue.toString)
    deliveryBox.children.addAll(idTitle, id, expectedTitle, expected, employeeTitle, employee)
    deliveryBox
  }

  /**
   * Creates the more info bar which displays each item in the customer order
   */
  def moreInfo: VBox = {
    val lineList = QueryLoader.searchCustomerOrderLine(customerOrder)
    val infoPane = new VBox()
    def lineInfo(i: Int): Unit = {
      if (i < lineList.size) {
        val currentItem = QueryLoader.searchItem(new Item(lineList(i).idItem.getValue, null, null, null))
        val customerBox = new HBox
        val idTitle = field(100, "table-title", "table-dark", "Item ID:")
        val id = field(100, "table-field", "table-dark", lineList(i).idItem.getValue.toString)
        val itemNameTitle = field(100, "table-title", "table-dark", "Item name:")
        val itemName = field(200, "table-field", "table-dark", currentItem.itemName.getValue.toString)
        val quantityTitle = field(100, "table-title", "table-dark", "Quantity:")
        val quantity = field(100, "table-field", "table-dark", lineList(i).quantity.getValue.toString)
        val empty = field(100, "table-field", "table-light", "")
        customerBox.children.addAll(empty, idTitle, id, itemNameTitle, itemName, quantityTitle, quantity)
        infoPane.children.add(customerBox)
        println(i)
        lineInfo(i + (1))
      }
    }
    lineInfo(0)
    infoPane
  }

  /**
   * Sets the panes on the panel.
   */
  def createPanel: Unit = {
    this.left = status
    this.center = customerOrderInfo
    this.right = dropdown(this)
  }
  
  createPanel
}