package com.qa.data.sql

/**
 * Represents a column name and value for an entity
 * @param name The name of the column in the database
 * @param value The value stored in the column
 * @author cboucher
 */
class Column(name: String, value: Any) {
  def getColumnName: String = name
  def getValue: Any = value
}