package com.qa.gui.controller

import com.qa.data.sql.SQLConnector
import com.qa.data.entity.User
import com.qa.data.sql.Column
import scalafx.application.JFXApp.PrimaryStage
import com.qa.gui.scene.DashboardWindow
import java.security.MessageDigest
import java.util.Formatter
import com.qa.data.entity.EntityConvertor
import com.qa.application.Session
import com.qa.data.entity.QueryLoader

/**
 * Controller behind the login window. Used to check for a valid user and change scene.
 * @param stage The current stage in use
 * @param userName The data entered into the username field
 * @param password The data entered into the password field
 * @author cboucher
 */
class LoginController(stage: PrimaryStage, userName: String, password: String) {

  /**
   * Checks the inputted data with users on the database
   */
  def checkCredentials {
    def encryptPassword: String = {
      def byteToHex(hash: Array[Byte]): String = {
        val formatter: Formatter = new Formatter
        var result: String = ""

        for (i <- 0 to hash.length - 1) {
          var b: Byte = hash(i)
          result ++= "%02x".format(b).toString
        }
        result
      }
      try {
        val crypt = MessageDigest.getInstance("SHA1")
        crypt.reset
        crypt.update(password.getBytes("UTF-8"))
        byteToHex(crypt.digest)
      } catch {
        case e: Exception => {
          e.printStackTrace
          null
        }
      }
    }
    try {
      val selectedUser = new User(userName.toInt, encryptPassword, null, null, null, 1)
      var user = QueryLoader.searchUser(selectedUser)
      if (user.idUser != null) {
        Session.currentUser = user
        val dashboard = new DashboardWindow(stage)
      }
    } catch {
      case e: Exception => e.printStackTrace
    }

  }
  
  checkCredentials
}