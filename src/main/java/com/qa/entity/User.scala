package com.qa.entity

/**
 * @author cboucher
 */
case class User(idU: Int, pass: String, forN: String, surN: String, eM: String, isE: Boolean) {
  val idUser
  val password
  val forename
  val surname
  val email
  val isEmployee
}