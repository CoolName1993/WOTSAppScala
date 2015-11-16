package com.qa.gui.pathfinder

import com.qa.TestBase
import scala.collection.mutable.ListBuffer

/**
 * @author cboucher
 */
class GetShortestPathTest extends TestBase {

  "getShortestPathTo" should "get the shortest path to the target vertex" in {
    val vMap = new VertexMap().createMap
    val vertexList = new ListBuffer[Vertex]
    new AddLocation().addLocationX(0, new TestValues().locationArray, vMap, vertexList)
    new ComputePath().computePaths(vertexList(0))
    val target = new CompareDistance().compareEachVertex(0, vertexList, Double.PositiveInfinity, vertexList(0), null)
    val result = new GetShortestPath().getShortestPathTo(target, vMap)
    assert(result.length == target.minDistance.asInstanceOf[Int] + 1)
  }
}