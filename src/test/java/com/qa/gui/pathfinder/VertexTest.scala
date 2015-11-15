package com.qa.gui.pathfinder

import com.qa.TestBase

/**
 * @author cboucher
 */
class VertexTest extends TestBase {
  
  "toString" should "return the name value of the vertex" in {
    val name = "X: 0 Y: 5"
    val vertex = new Vertex("X: 0 Y: 5")
    val result = vertex.toString
    assert(result.equals(name))
  }
  
  "compare" should "return the comparison between the minimum distances of 2 vectors" in {
    val vertex1 = new Vertex("X: 0 Y: 5")
    vertex1.minDistance = 10
    val vertex2 = new Vertex("X: 5 Y: 5")
    vertex2.minDistance = 5
    val result = vertex1.compare(vertex2)
    assert(result > 0)
  }
  
  "compareTo" should "return the comparison between the minimum distances of 2 vectors" in {
    val vertex1 = new Vertex("X: 0 Y: 5")
    vertex1.minDistance = 10
    val vertex2 = new Vertex("X: 5 Y: 5")
    vertex2.minDistance = 5
    val result = vertex1.compareTo(vertex2)
    assert(result > 0)
  }
}