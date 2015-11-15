package com.qa.gui.pathfinder
import com.qa.TestBase
import com.qa.data.entity.Location
import scala.collection.mutable.ListBuffer

/**
 * @author cboucher
 */
class AddLocationTest extends TestBase {
  "addLocationX" should "add specified locations from the vertex map to the vertex list" in {
    val result = new ListBuffer[Vertex]
    val locationArray = (new TestValues().locationArray)
    val vMap = (new TestValues().blankVertexMap)
    new AddLocation().addLocationX(0, locationArray, vMap, result)
    
    def loop(i: Int) {
      if(i < result.length) {
        assert(result(i).name.equals(new TestValues().vertexArray(i).name))
        loop(i +(1))
      }
    }
    
    loop(0)
  }
}