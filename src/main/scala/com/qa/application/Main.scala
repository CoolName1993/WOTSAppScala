package com.qa.application

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color._
import com.qa.data.sql.SQLConnector
import com.qa.data.mongo.MongoConnector
import com.qa.data.mongo.Field
import com.qa.gui.scene.LoginWindow
import scalafx.stage.StageStyle

/**
 * @author cboucher
 */
object Main extends JFXApp {

  def run: Unit = {
    stage = new PrimaryStage()
    stage.resizable = false
    stage.initStyle(StageStyle.UNDECORATED)
    val loginWindow = new LoginWindow(stage)
  }
  
  run
}