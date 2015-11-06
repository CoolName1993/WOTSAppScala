package com.qa.data.sql

/**
 * Abstract class used to define a single entity in an SQL table. <br/>
 * tableName specifies the name of the table the entity belongs to
 * 
 * @author cboucher
 */
trait Entity {
  val tableName: String
}