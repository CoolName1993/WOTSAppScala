package com.qa.gui.panel

import scalafx.Includes._
import scalafx.scene.paint.Color._
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.StackPane
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.Text
import scalafx.scene.text.Font
import scalafx.scene.input.MouseEvent
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.layout.HBox
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.paint.Color

/**
 * The top bar that contains the company logo and a close button.
 * @author cboucher
 */
class Toolbar(stage: PrimaryStage) extends BorderPane {

  /**
   * Creates the close button.
   * @return A button that closes the program.
   */
  def close: StackPane = {
    val close = new StackPane
    val text = new Text("X") {
      id = "dark-colour"
    }
    val rect = new Rectangle {
      width = 60
      height = 40
      id = "table-field"
      onMouseClicked = (me: MouseEvent) => {
        System.exit(1)
      }
      onMouseEntered = (me: MouseEvent) => {
        id = "button-bad-highlight"
        text.id = "light-colour"
      }
      onMouseExited = (me: MouseEvent) => {
        id = "table-field"
        text.id = "dark-colour"
      }
    }
    text.setMouseTransparent(true)
    close.children.addAll(rect, text)
    close
  }

  /**
   * Creates a HBox that holds all buttons in the top right of the toolbar.
   * @return A HBox containing buttons.
   */
  def buttons: HBox = {
    val hbox = new HBox
    hbox.children.addAll(close)
    hbox
  }

  /**
   * Creates the logo that is displayed in the top left of the toolbar.
   * @return An image pane containing the logo.
   */
  def logo: ImageView = {
    val image = new Image("logo.png", 150, 40, true, true)
    val imgview = new ImageView(image)
    imgview
  }

  /**
   * Creates the toolbar
   */
  def createPanel: Unit = {
    this.right = buttons
    this.left = logo
  }

  createPanel

}