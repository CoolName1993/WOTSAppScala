package com.qa.gui

import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.HBox
import scalafx.geometry.Pos
import scalafx.scene.layout.GridPane
import scalafx.geometry.Insets
import scalafx.scene.text.Text
import scalafx.scene.text.Font
import scalafx.scene.text.FontWeight
import scalafx.scene.control.Label
import scalafx.scene.control.TextField
import scalafx.scene.control.PasswordField
import scalafx.beans.property.StringProperty

/**
 * @author cboucher
 */
class LoginWindow(stage: PrimaryStage) {
  stage.setTitle("Log In")
  def createScene(): Scene = {

    // Set up the grid
    var grid = new GridPane()
    grid.setAlignment(Pos.Center)
    grid.setHgap(10)
    grid.setVgap(10)
    grid.setPadding(Insets(25, 25, 25, 25))

    // Create the scene
    val scene = new Scene(grid, 300, 275)

    // Create a welcome label
    val scenetitle = new Text("Welcome");
    scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    grid.add(scenetitle, 0, 0, 2, 1);

    // Create a user name label
    val userName = new Label("User Name:");
    grid.add(userName, 0, 1);

    // Create the user name text field
    val userTextField = new TextField();
    grid.add(userTextField, 1, 1);

    // Create a a password label
    val pw = new Label("Password:");
    grid.add(pw, 0, 2);

    // Create the password text field
    val pwBox = new PasswordField();
    grid.add(pwBox, 1, 2);

    // Set up the log in button
    val btn = new Button("Log in")
    var hbBtn = new HBox(10)
    hbBtn.setAlignment(Pos.BottomRight)
    hbBtn.getChildren().add(btn)
    grid.add(hbBtn, 1, 4)
    scene
  }
  stage.setScene(createScene)

}