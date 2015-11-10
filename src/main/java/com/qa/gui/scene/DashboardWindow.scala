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

/**
 * @author cboucher
 */
class DashboardWindow(stage: PrimaryStage) {
  val HEIGHT = 768
  val WIDTH = 1024
  val titleColour = Color.rgb(99, 206, 168)
  val buttonNormal = Color.rgb(6, 125, 83)
  val buttonHighlight = Color.rgb(27, 161, 114)
  stage.setTitle("Warehouse Order Tracking Application")

  //http://paletton.com/palette.php?uid=3010h0kqzu2gDIgkbyGu-npwFhv

  def createButton(title: String, panel: () => Unit): StackPane = {
    var stack = new StackPane()
    var button = new Rectangle() {
      width = 200
      height = 50
      fill = buttonNormal
      onMouseClicked = (me: MouseEvent) => {
        panel()
      }
      onMouseEntered = (me: MouseEvent) => {
        fill = buttonHighlight
      }
      onMouseExited = (me: MouseEvent) => {
        fill = buttonNormal
      }
    }
    var text = new Text(title) {
      fill = White
      font = Font.font("Tahoma", 16)
    }
    text.setMouseTransparent(true)
    stack.children.addAll(button, text)
    stack
  }

  def createTitle(title: String): StackPane = {
    var stack = new StackPane()
    var back = new Rectangle() {
      width = 200
      height = 100
      fill = titleColour
    }
    var text = new Text(title) {
      fill = Black
      font = Font.font("Tahoma", 16)
    }
    stack.children.addAll(back, text)
    stack
  }

  def createScene(): Scene = {

    var border = new BorderPane()
    var flow = new FlowPane()
    flow.setPrefWrapLength(150)

    // Create the scene
    val scene = new Scene(border, WIDTH, HEIGHT)

    def setDeliveries() {
      border.center = new DeliveryPanel()
    }
    def setDeliveryMap() {
      border.center = new DeliveryMapPanel()
    }
    def setCustomers() {
      border.center = new CustomerOrderPanel()
    }

    flow.children.addAll(createTitle("Deliveries"), createButton("Available", setDeliveries), createButton("Assigned", setDeliveryMap), createTitle("Customer Orders"), createButton("Available", setCustomers), createButton("Assigned", setCustomers))
    border.left = flow
    border.top = new Toolbar(stage)
    scene
  }
  stage.setScene(createScene)
  stage.centerOnScreen()
}