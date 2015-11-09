package com.qa.data.entity

import java.sql.Date

/**
 * @author cboucher
 */
object EntityConvertor {
  def convertToUser(input: Array[_]): User = {
    new User(input(0).asInstanceOf[Int], input(1).asInstanceOf[String], input(2).asInstanceOf[String], input(3).asInstanceOf[String], input(4).asInstanceOf[String], input(5).asInstanceOf[Int])
  }

  def convertToAddress(input: Array[_]): Address = {
    new Address(input(0).asInstanceOf[Int], input(1).asInstanceOf[Array[String]], input(2).asInstanceOf[String], input(3).asInstanceOf[String], input(4).asInstanceOf[String])
  }

  def convertToCustomerOrder(input: Array[_]): CustomerOrder = {
    new CustomerOrder(input(0).asInstanceOf[Int], input(1).asInstanceOf[Date], input(2).asInstanceOf[Date], input(3).asInstanceOf[Boolean], input(4).asInstanceOf[Int], input(5).asInstanceOf[Int], input(6).asInstanceOf[Int], input(7).asInstanceOf[Int])
  }
}