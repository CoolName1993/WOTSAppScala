package com.qa.application

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color._
import com.qa.data.sql.SQLConnector
import com.qa.data.mongo.MongoConnector
import com.qa.data.mongo.Field
import com.qa.gui.scene.LoginWindow

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