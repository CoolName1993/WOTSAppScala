package com.qa.gui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{ Stops, LinearGradient }
import scalafx.scene.text.Text
import com.qa.sql.SQLConnector
import com.qa.mongo.MongoConnector
import com.qa.mongo.Field

/**
 * @author cboucher
 */
object Main extends JFXApp {

  //TEST//
  SQLConnector.connect
  SQLConnector.disconnect
  val fields = Array(new Field("IsPorousware", true), new Field("idSupplier", 2))
  val results = MongoConnector.read("Item", fields)
  for (result <- results) {
    println(result.toString())
  }
  //TEST//

  stage = new PrimaryStage()
  val loginWindow = new LoginWindow(stage)

}