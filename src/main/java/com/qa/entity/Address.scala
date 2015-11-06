package com.qa.entity
import com.qa.mongo.{ Entity, Field }

/**
 * Represents an Address from the MongoDB database.
 * @param idAddress_ The address ID.
 * @param addressLines_ An array of lines in the address.
 * @param city_ The city in the address.
 * @param county_ The county in the address.
 * @param postcode_ The postcode in the address.
 * @author cboucher
 */
case class Address(idAddress_ : Int, addressLines_ : Array[String], city_ : String, county_ : String, postcode_ : String) extends Entity {
  def getAddressLines(): Array[Field] = {
    var output = new Array[Field](addressLines_.size)
    def createLine(i: Int) {
      if (i < addressLines_.size) {
        output(i) = new Field("AddressLine" + i.+(1), addressLines_(i))
        createLine(i + (1))
      }
    }
    createLine(0)
    output
  }
  val collectionName = "Address"
  val idAddress = new Field("idAddress", idAddress_)
  val addressLines = getAddressLines
  val city = new Field("City", city_)
  val county = new Field("County", county_)
  val postcode = new Field("PostCode", postcode_)
}