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
import com.qa.gui.controller.LoginController
import scalafx.scene.layout.BorderPane
import com.qa.gui.panel.Toolbar
import scalafx.scene.layout.StackPane
import scalafx.scene.shape.Rectangle
import scalafx.scene.input.MouseEvent
import scalafx.scene.paint.Color

/**
 * @author cboucher
 */
class LoginWindow(stage: PrimaryStage) {
  stage.setTitle("Log In")
  def createScene(): Scene = {

    var border = new BorderPane()

    // Set up the grid
    var grid = new GridPane()
    grid.setAlignment(Pos.Center)
    grid.setHgap(10)
    grid.setVgap(10)
    grid.setPadding(Insets(25, 25, 25, 25))

    // Create the scene
    val scene = new Scene(border, 300, 275)

    // Create a welcome label
    val scenetitle = new Text("Welcome")
    scenetitle.setFont(Font.font("Tahoma", FontWeight.Normal, 20))
    grid.add(scenetitle, 0, 0, 2, 1)

    // Create a user name label
    val userName = new Label("User Name:")
    grid.add(userName, 0, 1)

    // Create the user name text field
    val userTextField = new TextField()
    grid.add(userTextField, 1, 1)

    // Create a a password label
    val pw = new Label("Password:")
    grid.add(pw, 0, 2)

    // Create the password text field
    val pwBox = new PasswordField()
    grid.add(pwBox, 1, 2)

    // Set up the log in button
    def login(): StackPane = {
      val good = Color.rgb(82, 167, 7)
      val goodHighlight = Color.rgb(120, 214, 36)
      var close = new StackPane()
      var text = new Text("Log in") {
        fill = White
        font = Font.font("Tahoma", 16)
      }
      var rect = new Rectangle() {
        width = 60
        height = 40
        fill = good
        onMouseClicked = (me: MouseEvent) => {
          new LoginController(stage, userTextField.text.value, pwBox.getText)
        }
        onMouseEntered = (me: MouseEvent) => {
          fill = goodHighlight
        }
        onMouseExited = (me: MouseEvent) => {
          fill = good
        }
      }
      text.setMouseTransparent(true)
      close.children.addAll(rect, text)
      close
    }
    var hbBtn = new HBox(10)
    hbBtn.setAlignment(Pos.BottomRight)
    hbBtn.getChildren().add(login)
    grid.add(hbBtn, 1, 4)
    border.center = grid
    border.top = new Toolbar(stage)
    scene
  }
  stage.setScene(createScene)

}