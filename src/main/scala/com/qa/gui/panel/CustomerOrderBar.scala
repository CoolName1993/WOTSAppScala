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
import com.qa.gui.controller.CustomerOrderPanelController

/**
 * One order bar in the available customer order tab in the GUI
 * @author cboucher
 */
class CustomerOrderBar(customerOrder: CustomerOrder, scene: BorderPane) extends BorderPane {

  var expanded = false
  var current = "button-bad"

  /**
   * Creates the status button which can be toggled on and off.
   * @return A stack pane that acts like a button.
   */
  def status: StackPane = {
    var disabled = false
    val text = new Text("") {
      id = "table-light"
    }
    if (Session.currentCustomerOrder != null) {
      if (Session.currentCustomerOrder.idCustomerOrder.getValue.equals(customerOrder.idCustomerOrder.getValue)) {
        current = "button-good"
        text.text = "Assigned"
      } else {
        disabled = true
        current = "button-disabled"
        text.text = "Unassigned"
        text.id = "table-dark"
      }
    } else {
      text.text = "Unassigned"
    }
    text.setMouseTransparent(true)

    val statusBox = new Rectangle {
      width = 100
      height = 50
      id = current
    }

    if (!disabled) {
      statusBox.onMouseClicked = (me: MouseEvent) => {
        if (current == "button-good") {
          new CustomerOrderPanelController().selectCustomerOrder(null)
          new CustomerOrderPanelController().createPanel(scene)
          
        } else {
          new CustomerOrderPanelController().selectCustomerOrder(customerOrder)
          new CustomerOrderPanelController().createPanel(scene)
        }
      }
      statusBox.onMouseEntered = (me: MouseEvent) => {
        if (current == "button-good") {
          statusBox.id = "button-good-highlight"
        } else {
          statusBox.id = "button-bad-highlight"
        }
      }
      statusBox.onMouseExited = (me: MouseEvent) => {
        statusBox.id = current
      }
    }

    val stack = new StackPane()
    stack.children.addAll(statusBox, text)
    stack
  }

  /**
   * Creates the dropdown button which is used to expand the row.
   * @param border The border pane to attach the dropdown menu to.
   * @return A stack pane that acts like a button.
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
   *  @param setID The id of the box.
   *  @param setTextID The text id of the box.
   *  @param setText The text in the box
   *  @return A stack pane with a rectangle with text on top of it.
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
   * @return A HBox with info on the customer order.
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
   * @return A VBox with info on all items in the customer order
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