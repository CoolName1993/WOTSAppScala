package sql

import java.sql.{ Connection, DriverManager }
import java.sql.ResultSet
import java.sql.Types
import java.sql.ResultSetMetaData
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource
import javax.sql.DataSource

/**
 * @author cboucher
 */
object SQLConnector extends App {
  def dataSource(): DataSource = {
    val dataSource = new MysqlDataSource()
    dataSource.setDatabaseName("nbgardensdata")
    dataSource.setUser("root")
    dataSource.setPassword("password")
    dataSource.setServerName("localhost")
    dataSource
  }
  var connection: Connection = _

  // Establish database connection
  def connect(): Unit = {
    try {
      connection = dataSource.getConnection
    } catch {
      case e: Exception => e.printStackTrace
    }
  }

  // Close database connection
  def disconnect(): Unit = {
    try {
      connection.close
    } catch {
      case e: Exception => e.printStackTrace
    }
  }

  // Create entries in a table
  def create(tableName: String, columns: Array[Column[Any]], values: Array[Any]): Unit = {

    // Creates the column section of the query
    def createColumnString(): String = {
      var output = ""
      def addString(i: Int): Unit = {
        if (i == 0) {
          output = columns(i).getColumnName
        } else {
          output = output + ", " + columns(i).getColumnName
        }
        if (i < columns.size) {
          addString(i + (1))
        }
      }
      addString(0)
      output
    }

    // Creates the value section of the query
    def createValueString(): String = {
      var output = ""
      def addString(i: Int, value: Any): Unit = {
        if (i == 0) {
          output = "" + value
        } else {
          output = output + ", " + value
        }
        if (i < values.size) {
          addString(i + (1), values(i + (1)))
        }
      }
      addString(0, values(0))
      output
    }

    // Attempt to perform query
    try {
      connect

      // Configure to be updatable
      val statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)

      // Prepare the statement
      val preparedStatement = connection.prepareStatement("INSERT INTO " + tableName + " (" + createColumnString + ") VALUES (" + createValueString + ")")

      // Execute the insert
      preparedStatement.executeUpdate

    } catch {
      case e: Exception => e.printStackTrace
    } finally {
      disconnect
    }
  }

  // Read entries from a table
  def read(tableName: String, columns: Array[Column[Any]], values: Array[Any]): Array[Array[Any]] = {
    def createColumnValuePairs(): String = {
      var output = ""
      def createPairs(i: Int) {
        if (i == 0) {
          output = columns(i).getColumnName + "='" + values(i) + "'"
        } else {
          output = output + " AND " + columns(i).getColumnName + "='" + values(i) + "'"
        }
        if (i < values.size) {
          createPairs(i + (1))
        }
      }
      createPairs(0)
      output
    }
    try {
      connect

      // Configure to be read only and allow movement both up and down
      val statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)

      // Execute the query
      val resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE " + createColumnValuePairs)

      // Get result length
      var rowCount: Int = 0;
      if (resultSet.last()) {
        rowCount = resultSet.getRow()
        resultSet.beforeFirst()
      }
      
      // Create the output as a 2D array of any type
      var outputArray: Array[Array[Any]] = Array ofDim[Any](rowCount,columns.size)
      
      // Iterate over the result set
      def storeObjectRow(x: Int) {
        def storeObjectColumn(y: Int) {
          if(y <= columns.size) {
            var rsMetaData: ResultSetMetaData = resultSet.getMetaData
            val resultType = rsMetaData.getColumnType(y)
            resultType match {
              case Types.INTEGER => outputArray(x)(y) = resultSet.getInt(columns(y).getColumnName)
              case Types.DATE => outputArray(x)(y) = resultSet.getDate(columns(y).getColumnName)
              case Types.CHAR => outputArray(x)(y) = resultSet.getString(columns(y).getColumnName)
              case Types.VARCHAR => outputArray(x)(y) = resultSet.getString(columns(y).getColumnName)
              case Types.TINYINT => outputArray(x)(y) = resultSet.getInt(columns(y).getColumnName)
              case _ => outputArray(x)(y) = null
            }
            storeObjectColumn(y +(1))
          }
        }
        if (resultSet.next) {
          storeObjectColumn(0)
          storeObjectRow(x +(1))
        }
      }
      
      // Run the above code and return the output array
      storeObjectRow(0)
      disconnect
      outputArray
    } catch {
      case e: Exception => { 
        e.printStackTrace
        disconnect
        null
      }
    }
  }

  // Update entries in a table
  def update(tableName: String, columns: Array[Column[Any]], values: Array[Any], primaryKeys: Array[Any], primaryKeyColumns: Array[Column[Any]]): Unit = {
    def createColumnUpdate(): String = {
      var output = ""
      def createPairs(i: Int) {
        if (i == 0) {
          output = columns(i).getColumnName + "='" + values(i) + "'"
        } else {
          output = output + ", " + columns(i).getColumnName + "='" + values(i) + "'"
        }
        if (i < values.size) {
          createPairs(i + (1))
        }
      }
      createPairs(0)
      output
    }
    def createPrimaryKeyString(): String = {
      var output = ""
      def createKeyPair(i: Int) {
        if (i == 0) {
          output = primaryKeyColumns(i).getColumnName + "='" + primaryKeys(i) + "'"
        } else {
          output = output + " AND " + primaryKeyColumns(i).getColumnName + "='" + primaryKeys(i) + "'"
        }
        if (i < values.size) {
          createKeyPair(i + (1))
        }
      }
      createKeyPair(0)
      output
    }
    try {
      connect
      // Configure to be updatable
      val statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)

      // Prepare the statement
      val preparedStatement = connection.prepareStatement("UPDATE " + tableName + " SET "+ createColumnUpdate + " WHERE " + createPrimaryKeyString)

      // Execute the update
      preparedStatement.executeUpdate
    } catch {
      case e: Exception => e.printStackTrace
    } finally {
      disconnect
    }
  }

  // Delete entries in a table
  def delete(): Unit = {
    try {
      connect
      // Shouldn't need to implement this any time soon
    } catch {
      case e: Exception => e.printStackTrace
    } finally {
      disconnect
    }
  }
}