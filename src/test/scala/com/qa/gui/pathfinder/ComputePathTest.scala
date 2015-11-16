package com.qa.gui.pathfinder

import com.qa.TestBase
import scala.collection.mutable.ListBuffer

/**
 * @author cboucher
 */
class ComputePathTest extends TestBase{
  
  "computePaths" should "compute all distances from the selected vertex" in {
    val vMap = new VertexMap().createMap
    val vertexList = new ListBuffer[Vertex]
    val locationArray = (new TestValues().locationArray)
    new AddLocation().addLocationX(0, locationArray, vMap, vertexList)
    new ComputePath().computePaths(vertexList(0))
    val result = new CompareDistance().compareEachVertex(0, vertexList, Double.PositiveInfinity, vertexList(0), null)
    assert(result.name.equals("X: 6 Y: 4"))
  }
}