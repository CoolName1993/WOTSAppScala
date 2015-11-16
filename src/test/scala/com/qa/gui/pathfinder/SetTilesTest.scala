package com.qa.gui.pathfinder

import com.qa.TestBase

/**
 * @author cboucher
 */
class SetTilesTest extends TestBase {
  
  "setTiles" should "reset the vertexes to match those on the vertex map" in {
    val vMap = new VertexMap().createMap
    val vertexList = (new TestValues().vertexList)
    val nextVertex = vertexList(0)
    val resultNextVertex = new SetTiles().setTiles(0, vertexList, vMap, nextVertex)
    assert(vertexList(1).name.equals(vMap(0)(7).name))
    assert(resultNextVertex.name.equals(vMap(0)(5).name))    
  }
}