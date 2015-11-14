package com.qa.gui.pathfinder
import com.qa.TestBase
import com.qa.data.entity.Location
import scala.collection.mutable.ListBuffer

/**
 * @author cboucher
 */
class AddLocationTest extends TestBase {
  "An addLocationX" should "add specified locations from the vertex map to the vertex list" in {
    val result = new ListBuffer[Vertex]
    new AddLocation().addLocationX(0, TestValues.locationArray, TestValues.blankVertexMap, result)
    // finish this
  }
}