package com.qa.entity

import com.qa.sql.{ Entity, Column }

/**
 * @author cboucher
 */
case class User(idU: Int, pass: String, forN: String, surN: String, eM: String, isE: Boolean) extends Entity {
  val tableName = "user"
  val idUser = new Column("idUser", idU)
  val password = new Column("password", pass)
  val forename = new Column("forename", forN)
  val surname = new Column("surname", surN)
  val email = new Column("email", eM)
  val isEmployee = new Column("isEmployee", isE)
}