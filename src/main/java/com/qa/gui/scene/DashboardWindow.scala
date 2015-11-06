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

/**
 * @author cboucher
 */
class DashboardWindow(stage: PrimaryStage) {
  stage.setTitle("Log In")
  def createScene(): Scene = {

    // Set up the grid
    var grid = new GridPane()
    grid.setAlignment(Pos.Center)
    grid.setHgap(10)
    grid.setVgap(10)
    grid.setPadding(Insets(25, 25, 25, 25))

    // Create the scene
    val scene = new Scene(grid, 300, 275) {
      fill = new LinearGradient(
        endX = 0,
        stops = Stops(Cyan, DodgerBlue))
    }

    // Create a welcome label
    val scenetitle = new Text() {
      text = "LOGGED IN"
      style = "-fx-font-size: 48pt"
      font = Font.font("Tahoma", FontWeight.Normal, 20)
      fill = new LinearGradient(
        endX = 0,
        stops = Stops(Cyan, DodgerBlue))
      effect = new DropShadow {
        color = DodgerBlue
        radius = 25
        spread = 0.25
      }
    }
    grid.add(scenetitle, 0, 0, 2, 1)
    scene
  }
  stage.setScene(createScene)
}