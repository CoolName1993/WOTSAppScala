package mongo

import com.mongodb.casbah.Imports._

/**
 * Connects to the MongoDB database and performs R operations.
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
      //val mongoDB = connection("nbgardensdata")
      //val items = mongoDB("Item")
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
   */
  def read(collectionName: String, field: Array[String], entity: Array[Any]): MongoDBObject = {
    def createMongoObject(): MongoDBObject = {
      var output = MongoDBObject.empty
      def addField(i: Int) {
        if (i <= entity.size) {
          output.put(field(i), entity(i))
        }
      }
      output
    }
    try {
      val collection = connection(databaseName)(collectionName)
      var searchItem = createMongoObject
      val returnItem = collection.findOne(searchItem)
      disconnect
      null
      //returnItem
    } catch {
      case e: Exception => {
        e.printStackTrace
        disconnect
        null
      }
    }
  }

  /**
   *
   * returns an Item entity based on idItem
   * @param id: Takes an int which is the idItem of a given item
   * @return returns an Item object containing all the information for the given item
   */
  //  public Item getItem(int id) {
  //    //Connect to MongoDB
  //    mdbc.mongoConnect();
  //    
  //    DBCollection collection = this.getCollection(itemCol);
  //    
  //    BasicDBObject searchItem = new BasicDBObject();
  //    searchItem.put("idItem", id);
  //    DBObject itemObj = collection.findOne(searchItem);
  //    
  //    itemInfs.clear();
  //    
  //    Item newItem = makeItemEntityFromMongoObject(itemObj);
  //    
  //    //Disconnect from MongoDB
  //    mdbc.mongoDisconnect();
  //    
  //    return newItem;
  //  }
}