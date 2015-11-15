package com.qa.gui.pathfinder

import com.qa.TestBase

/**
 * @author cboucher
 */
class CompareDistanceTest extends TestBase {
  
  "compareEachVertex" should "return the closest vertex from the current source vertex" in {
    val vertexList = (new TestValues().vertexList)
    val result = new CompareDistance().compareEachVertex(0,vertexList,Double.PositiveInfinity,vertexList(0),null)
    assert(result.name.equals("X: 0 Y: 6"))
  }
}