package com.qa.gui.pathfinder

import com.qa.TestBase

/**
 * @author cboucher
 */
class VertexMapTest extends TestBase {
  
  "vertexMap" should "create a new blank vertex map" in {

    val result = new VertexMap().vertexMap

    def loopX(x: Int) {

      def loopY(y: Int) {
        if (y < result(x).length) {
          assert(result(x)(y).name.equals(new TestValues().blankVertexMap(x)(y).name))
          loopY(y + (1))
        }
      }
      if (x < result.length) {
        loopY(0)
        loopX(x + (1))
      }
    }
    loopX(0)
  }

  "fillMap" should "add correct adjacencies to the vertex map" in {

    val result = (new TestValues().blankVertexMap)
    new VertexMap().fillMap(result)

    assert(result(0)(5).adjacencies(0).target.name.equals(new TestValues().vertexArray(0).adjacencies(0).target.name))
    assert(result(6)(4).adjacencies(0).target.name.equals(new TestValues().vertexArray(1).adjacencies(0).target.name))
    assert(result(10)(9).adjacencies(0).target.name.equals(new TestValues().vertexArray(2).adjacencies(0).target.name))
    assert(result(8)(8).adjacencies(0).target.name.equals(new TestValues().vertexArray(3).adjacencies(0).target.name))

  }

  "createMap" should "create a vertex map filled with adjacencies" in {

    val result = new VertexMap().createMap

    assert(result(0)(5).adjacencies(0).target.name.equals(new TestValues().vertexArray(0).adjacencies(0).target.name))
    assert(result(6)(4).adjacencies(0).target.name.equals(new TestValues().vertexArray(1).adjacencies(0).target.name))
    assert(result(10)(9).adjacencies(0).target.name.equals(new TestValues().vertexArray(2).adjacencies(0).target.name))
    assert(result(8)(8).adjacencies(0).target.name.equals(new TestValues().vertexArray(3).adjacencies(0).target.name))
  }
}