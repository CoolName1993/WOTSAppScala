package com.qa.mongo

/**
 * Represents a field name and value for an entity
 * @param name The name of the field in the database
 * @param value The value stored in the field
 * @author cboucher
 */
class Field(name: String, value: Any) {
  def getFieldName: String = name
  def getValue: Any = value
}