package com.qa.gui.scene

import scalafx.Includes._
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.HBox
import scalafx.geometry.Pos
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color._
import scalafx.geometry.Insets
import scalafx.scene.text.Text
import scalafx.scene.text.Font
import scalafx.scene.text.FontWeight
import scalafx.scene.control.Label
import scalafx.scene.control.TextField
import scalafx.scene.control.PasswordField
import scalafx.event._
import scalafx.geometry.Insets.sfxInsets2jfx
import scalafx.geometry.Pos.sfxEnum2jfx
import scalafx.scene.Scene.sfxScene2jfx
import scalafx.scene.control.Button.sfxButton2jfx
import scalafx.scene.layout.GridPane.sfxGridPane2jfx
import scalafx.scene.layout.HBox.sfxHBox2jfx
import scalafx.scene.text.Font.sfxFont2jfx
import scalafx.scene.text.FontWeight.sfxEnum2jfx
import scalafx.scene.text.Text.sfxText2jfx
import scalafx.stage.Stage.sfxStage2jfx
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.effect.DropShadow
import scalafx.scene.shape.Rectangle
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.VBox
import scalafx.scene.layout.FlowPane
import scalafx.scene.input.MouseEvent
import com.qa.gui.panel.DeliveryPanel
import scalafx.scene.layout.StackPane
import com.qa.gui.panel._
import scalafx.scene.paint.Color
import com.qa.application.Session

/**
 * @author cboucher
 */
class DashboardWindow(stage: PrimaryStage) {
  val HEIGHT = 768
  val WIDTH = 1024

  /**
   * Creates a button to be used to navigate to other pages.
   * @param title The text on top of the button.
   * @param border The border pane to edit when the button is pressed.
   * @panel panel The panel to navigate to when the button is pressed.
   * @return A stack pane that acts like a button.
   */
  def createButton(title: String, border: BorderPane, panel: (BorderPane) => Unit): StackPane = {
    val stack = new StackPane
    val button = new Rectangle {
      width = 200
      height = 50
      id = "button-default"
      onMouseClicked = (me: MouseEvent) => {
        panel(border)
      }
      onMouseEntered = (me: MouseEvent) => {
        id = "button-default-highlight"
      }
      onMouseExited = (me: MouseEvent) => {
        id = "button-default"
      }
    }
    val text = new Text(title) {
      id = "light-colour"
    }
    text.setMouseTransparent(true)
    stack.children.addAll(button, text)
    stack
  }

  /**
   * Creates the title above each section of buttons.
   * @param title The text on top of the title rectangle.
   * @return A stack pane with the title on top.
   */
  def createTitle(title: String): StackPane = {
    val stack = new StackPane
    val back = new Rectangle {
      width = 200
      height = 100
      id = "title"
    }
    val text = new Text(title) {
      id = "dark-colour"
    }
    stack.children.addAll(back, text)
    stack
  }

  /**
   * Sets the centre panel to the delivery panel
   * @param border The border panel to add the panel to.
   */
  def setDeliveries(border: BorderPane): Unit = {
    border.center = new DeliveryPanel
    println(Session.currentCustomerOrder)
  }

  /**
   * Sets the centre panel to the delivery map panel
   * @param border The border panel to add the panel to.
   */
  def setDeliveryMap(border: BorderPane): Unit = {
    border.center = new DeliveryMapPanel
    println(Session.currentCustomerOrder)
  }

  /**
   * Sets the centre panel to the customer order panel
   * @param border The border panel to add the panel to.
   */
  def setCustomers(border: BorderPane): Unit = {
    border.center = new CustomerOrderPanel
    println(Session.currentCustomerOrder)
  }

  /**
   * Sets the centre panel to the customer order map panel
   * @param border The border panel to add the panel to.
   */
  def setCustomerMap(border: BorderPane): Unit = {
    border.center = new CustomerOrderMapPanel
    println(Session.currentCustomerOrder)
  }

  /**
   * Creates the scene and adds the sidebar.
   * @return The created scene with a navigation sidebar and toolbar at the top.
   */
  def createScene: Scene = {
    val border = new BorderPane
    val flow = new FlowPane
    flow.setPrefWrapLength(150)

    // Create the scene
    val scene = new Scene(border, WIDTH, HEIGHT)
    scene.stylesheets = List(getClass.getResource("/stylesheet.css").toExternalForm)
    flow.children.addAll(createTitle("Deliveries"), createButton("Available", border, setDeliveries), createButton("Assigned", border, setDeliveryMap), createTitle("Customer Orders"), createButton("Available", border, setCustomers), createButton("Assigned", border, setCustomerMap))
    border.left = flow
    border.top = new Toolbar(stage)
    border.center = new DeliveryPanel
    scene
  }
  
  stage.setTitle("Warehouse Order Tracking Application")
  stage.setScene(createScene)
  stage.centerOnScreen
}