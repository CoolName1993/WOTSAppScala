package com.qa.mongo

/**
 * Abstract class used to define a single entity in a MongoDB collection <br/>
 * collectionName specifies the name of the collection the entity belongs to
 * 
 * @author cboucher
 */
trait Entity {
  val collectionName: String
}