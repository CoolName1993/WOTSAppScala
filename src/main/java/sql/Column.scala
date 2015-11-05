package sql

/**
 * A generic column class of type [T]
 * @constructor Create a new column with a type and column name
 * @param name The name of the column in the database
 * @author cboucher
 */
class Column[T](name: String) {
  var returnType: T = _
  def getReturnType: T = returnType
  def getColumnName: String = name
}