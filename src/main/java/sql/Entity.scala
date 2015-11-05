package sql

/**
 * Abstract class used to define a single entity in a table.
 * tableName specifies the name of the table the entity belongs to
 * 
 * @author cboucher
 */
trait Entity {
  def tableName: String
  def create(): Unit
  def read(): Unit
  def update(): Unit
  def delete(): Unit
}