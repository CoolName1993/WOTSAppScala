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

/**
 * @author cboucher
 */
class Toolbar(stage: PrimaryStage) extends BorderPane {
  val HEIGHT = 40
  def close(): StackPane = {
    var close = new StackPane()
    var rect = new Rectangle() {
      width = 60
      height = HEIGHT
      fill = Crimson
      onMouseClicked = (me: MouseEvent) => {
        System.exit(1)
      }
      onMouseEntered = (me: MouseEvent) => {
        fill = Red
      }
      onMouseExited = (me: MouseEvent) => {
        fill = Crimson
      }
    }
    var text = new Text("X") {
      fill = White
      font = Font.font("Tahoma", 16)
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
    var image = new Image("logo.png",150,HEIGHT,true,true)
    var imgview = new ImageView(image)
    imgview
  }

  this.right = buttons
  this.left = logo
}