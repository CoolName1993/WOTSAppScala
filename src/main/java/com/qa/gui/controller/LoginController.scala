package com.qa.gui.controller

import com.qa.data.sql.SQLConnector
import com.qa.data.entity.User
import com.qa.data.sql.Column
import scalafx.application.JFXApp.PrimaryStage
import com.qa.gui.scene.DashboardWindow
import java.security.MessageDigest
import java.util.Formatter

/**
 * @author cboucher
 */
class LoginController(stage: PrimaryStage, userName: String, password: String) {
  def checkCredentials() {
    def encryptPassword(): String = {
      def byteToHex(hash: Array[Byte]): String = {
        var formatter: Formatter = new Formatter
        var result: String = ""

        for (i <- 0 to hash.length - 1) {
          var b: Byte = hash(i)
          result ++= "%02x".format(b).toString()
        }
        result
      }
      try {
        var crypt = MessageDigest.getInstance("SHA1")
        crypt.reset()
        crypt.update(password.getBytes("UTF-8"))
        byteToHex(crypt.digest())
      } catch {
        case e: Exception => {
          e.printStackTrace()
          null
        }
      }
    }
    val searchValues = Array(new Column("idUser", userName), new Column("password", encryptPassword))
    val currentUser = SQLConnector.read("user", searchValues)
    if (currentUser != null) {
      new DashboardWindow(stage)
    }
  }
  checkCredentials
}