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
 * The log in window.
 * @param stage The primary stage of the application to assign the scene to.
 * @author cboucher
 */
class LoginWindow(stage: PrimaryStage) {

  /**
   * Creates the log in button
   * @param userTexField The username text field.
   * @param pwBox The password text field.
   * @return A stack pane that acts like a button.
   */
  def login(userTextField: TextField, pwBox: PasswordField): StackPane = {
    val close = new StackPane
    val text = new Text("Log in") {
      id = "light-colour"
    }
    val rect = new Rectangle {
      width = 80
      height = 50
      id = "button-good"
      onMouseClicked = (me: MouseEvent) => {
        new LoginController(stage, userTextField.text.value, pwBox.getText)
      }
      onMouseEntered = (me: MouseEvent) => {
        id = "button-good-highlight"
      }
      onMouseExited = (me: MouseEvent) => {
        id = "button-good"
      }
    }
    text.setMouseTransparent(true)
    close.children.addAll(rect, text)
    close
  }

  /**
   * Creates the log in window scene and sets it as the current scene on the stage.
   * @return The created log in scene.
   */
  def createScene: Scene = {

    val border = new BorderPane

    // Set up the grid
    val grid = new GridPane
    grid.setAlignment(Pos.Center)
    grid.setHgap(10)
    grid.setVgap(10)
    grid.setPadding(Insets(25, 25, 25, 25))

    // Create the scene
    val scene = new Scene(border, 1024, 768)
    scene.stylesheets = List(getClass.getResource("/stylesheet.css").toExternalForm)

    // Create a welcome label
    val scenetitle = new Text("Welcome") {
      id = "dark-colour"
    }
    grid.add(scenetitle, 0, 0, 2, 1)

    // Create a user name label
    val userName = new Label("User Name:") {
      id = "dark-colour"
    }
    grid.add(userName, 0, 1)

    // Create the user name text field
    val userTextField = new TextField
    userTextField.font = Font.font("Tahoma", 24)
    userTextField.prefWidth = 153
    userTextField.setPromptText("Username")
    grid.add(userTextField, 1, 1)

    // Create a a password label
    val pw = new Label("Password:") {
      id = "dark-colour"
    }
    grid.add(pw, 0, 2)

    // Create the password text field
    val pwBox = new PasswordField()
    pwBox.font = Font.font("Tahoma", 24)
    pwBox.prefWidth = 153
    pwBox.setPromptText("Password")
    grid.add(pwBox, 1, 2)

    val hbBtn = new HBox(10)
    hbBtn.setAlignment(Pos.BottomRight)
    hbBtn.getChildren().add(login(userTextField, pwBox))
    grid.add(hbBtn, 1, 4)
    border.center = grid
    border.top = new Toolbar(stage)
    scene
  }

  stage.setTitle("Log In")
  stage.setScene(createScene)

}