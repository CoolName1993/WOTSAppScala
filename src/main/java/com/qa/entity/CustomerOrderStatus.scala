package com.qa.entity

/**
 * @author cboucher
 */
case class CustomerOrderStatus(idCOS: Int, stat: String) extends Entity{
  val idCustomerOrderStatus
  val status
}