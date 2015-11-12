package com.qa.data.mongo

import com.mongodb.casbah.Imports._

/**
 * Connects to the MongoDB database and performs R operations. <br/>
 * Does not require initialisation.
 * @author cboucher
 */
object MongoConnector {

  /**
   * String to specify URL to MongoDB
   */
  val mongoURL = "localhost"
  /*
   * The name of the MongoDB database
   */
  val databaseName = "nbgardensdata"

  /**
   * The connection to the database
   */
  var connection: MongoConnection = _

  /**
   * Establishes the connection to the database.
   */
  def connect(): Unit = {
    try {
      connection = MongoConnection(mongoURL, 27017)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

  /**
   * Closes the connection to the database.
   */
  def disconnect(): Unit = {
    connection.close()
  }

  /**
   * Searches a collection in the database for matching entities.
   * @param collectionName The name of the collection to access
   * @param fields An array of fields used as search parameters
   */
  def read(collectionName: String, fields: Array[Field]): Array[MongoDBObject] = {
    connect
    def createMongoObject(): MongoDBObject = {
      var output = MongoDBObject.empty
      def addField(i: Int) {
        if (i < fields.size) {
          output.put(fields(i).getFieldName, fields(i).getValue)
          addField(i +(1))
        }
      }
      addField(0)
      output
    }
    try {
      val collection = connection(databaseName)(collectionName)
      var searchItem = createMongoObject()
      val cursor = collection.find(searchItem)
      var outputArray = new Array[MongoDBObject](cursor.size)
      def fillArray(i : Int){
        if(cursor.hasNext && i <= outputArray.size) {
          outputArray(i) = cursor.next
          fillArray(i +(1))
        }
      }
      fillArray(0)
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
}