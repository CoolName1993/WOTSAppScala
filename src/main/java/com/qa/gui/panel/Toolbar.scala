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
 * @author cboucher
 */
class Toolbar(stage: PrimaryStage) extends BorderPane {
  val HEIGHT = 40
  val bad = Color.rgb(186, 13, 8)
  val badHighlight = Color.rgb(239, 46, 41)
  def close(): StackPane = {
    var close = new StackPane()
    var text = new Text("X") {
      fill = Grey
      font = Font.font("Tahoma", 16)
    }
    var rect = new Rectangle() {
      width = 60
      height = HEIGHT
      fill = LightGrey
      onMouseClicked = (me: MouseEvent) => {
        System.exit(1)
      }
      onMouseEntered = (me: MouseEvent) => {
        fill = badHighlight
        text.fill = White
      }
      onMouseExited = (me: MouseEvent) => {
        fill = LightGrey
        text.fill = Grey
      }
    }
    text.setMouseTransparent(true)
    close.children.addAll(rect, text)
    close
  }

  def buttons(): HBox = {
    var hbox = new HBox()
    hbox.children.addAll(close)
    hbox
  }

  def logo(): ImageView = {
    var image = new Image("logo.png", 150, HEIGHT, true, true)
    var imgview = new ImageView(image)
    imgview
  }

  this.right = buttons
  this.left = logo
}