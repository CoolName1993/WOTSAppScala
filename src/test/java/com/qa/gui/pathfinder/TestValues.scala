package com.qa.gui.pathfinder

import com.qa.data.entity.Location

/**
 * A class to store values used in testing methods in the pathfinder package.
 * @author cboucher
 */
object TestValues {

  val blankVertexMap: Array[Array[Vertex]] = createBlankVertexMap
  val vertexArray: Array[Vertex] = createVertexArray
  val locationArray: Array[Array[Location]] = createLocationArray

  /**
   * Creates a few vertexes with adjacencies
   */
  def createVertexArray: Array[Vertex] = {
    val vertexArray = new Array[Vertex](4)

    // Add adjacencies
    vertexArray(0) = new Vertex("X: 0 Y: 5")
    vertexArray(0).adjacencies = Array(new Edge(new Vertex("X: 1 Y: 5"), 1))
    vertexArray(1) = new Vertex("X: 6 Y: 4")
    vertexArray(1).adjacencies = Array(new Edge(new Vertex("X: 6 Y: 5"), 1))
    vertexArray(2) = new Vertex("X: 10 Y: 9")
    vertexArray(2).adjacencies = Array(new Edge(new Vertex("X: 9 Y: 9"), 1))
    vertexArray(3) = new Vertex("X: 8 Y: 8")
    vertexArray(3).adjacencies = Array(new Edge(new Vertex("X: 8 Y: 9"), 1))

    vertexArray
  }

  /**
   * Creates a location array.
   * @return The location array.
   */
  def createLocationArray: Array[Array[Location]] = {

    val locationArray = new Array[Array[Location]](4)

    // Add values to the array
    locationArray(0)(0) = new Location(0, 0, 0, 5, 1)
    locationArray(1)(0) = new Location(0, 0, 6, 4, 1)
    locationArray(2)(0) = new Location(0, 0, 10, 9, 1)
    locationArray(3)(0) = new Location(0, 0, 8, 8, 1)

    // Return the result
    locationArray
  }

  /**
   * Creates a blank vertex map.
   * @return The blank vertex map.
   */
  def createBlankVertexMap: Array[Array[Vertex]] = {

    val vMap: Array[Array[Vertex]] = Array.ofDim[Vertex](11, 11)

    // Add values to the array
    vMap(0)(0) = new Vertex("X: 0 Y: 0")
    vMap(0)(1) = new Vertex("X: 0 Y: 1")
    vMap(0)(2) = new Vertex("X: 0 Y: 2")
    vMap(0)(3) = new Vertex("X: 0 Y: 3")
    vMap(0)(4) = new Vertex("X: 0 Y: 4")
    vMap(0)(5) = new Vertex("X: 0 Y: 5")
    vMap(0)(6) = new Vertex("X: 0 Y: 6")
    vMap(0)(7) = new Vertex("X: 0 Y: 7")
    vMap(0)(8) = new Vertex("X: 0 Y: 8")
    vMap(0)(9) = new Vertex("X: 0 Y: 9")
    vMap(0)(10) = new Vertex("X: 0 Y: 10")

    vMap(1)(0) = new Vertex("X: 1 Y: 0")
    vMap(1)(1) = new Vertex("X: 1 Y: 1")
    vMap(1)(2) = new Vertex("X: 1 Y: 2")
    vMap(1)(3) = new Vertex("X: 1 Y: 3")
    vMap(1)(4) = new Vertex("X: 1 Y: 4")
    vMap(1)(5) = new Vertex("X: 1 Y: 5")
    vMap(1)(6) = new Vertex("X: 1 Y: 6")
    vMap(1)(7) = new Vertex("X: 1 Y: 7")
    vMap(1)(8) = new Vertex("X: 1 Y: 8")
    vMap(1)(9) = new Vertex("X: 1 Y: 9")
    vMap(1)(10) = new Vertex("X: 1 Y: 10")

    vMap(2)(0) = new Vertex("X: 2 Y: 0")
    vMap(2)(1) = new Vertex("X: 2 Y: 1")
    vMap(2)(2) = new Vertex("X: 2 Y: 2")
    vMap(2)(3) = new Vertex("X: 2 Y: 3")
    vMap(2)(4) = new Vertex("X: 2 Y: 4")
    vMap(2)(5) = new Vertex("X: 2 Y: 5")
    vMap(2)(6) = new Vertex("X: 2 Y: 6")
    vMap(2)(7) = new Vertex("X: 2 Y: 7")
    vMap(2)(8) = new Vertex("X: 2 Y: 8")
    vMap(2)(9) = new Vertex("X: 2 Y: 9")
    vMap(2)(10) = new Vertex("X: 2 Y: 10")

    vMap(3)(0) = new Vertex("X: 3 Y: 0")
    vMap(3)(1) = new Vertex("X: 3 Y: 1")
    vMap(3)(2) = new Vertex("X: 3 Y: 2")
    vMap(3)(3) = new Vertex("X: 3 Y: 3")
    vMap(3)(4) = new Vertex("X: 3 Y: 4")
    vMap(3)(5) = new Vertex("X: 3 Y: 5")
    vMap(3)(6) = new Vertex("X: 3 Y: 6")
    vMap(3)(7) = new Vertex("X: 3 Y: 7")
    vMap(3)(8) = new Vertex("X: 3 Y: 8")
    vMap(3)(9) = new Vertex("X: 3 Y: 9")
    vMap(3)(10) = new Vertex("X: 3 Y: 10")

    vMap(4)(0) = new Vertex("X: 4 Y: 0")
    vMap(4)(1) = new Vertex("X: 4 Y: 1")
    vMap(4)(2) = new Vertex("X: 4 Y: 2")
    vMap(4)(3) = new Vertex("X: 4 Y: 3")
    vMap(4)(4) = new Vertex("X: 4 Y: 4")
    vMap(4)(5) = new Vertex("X: 4 Y: 5")
    vMap(4)(6) = new Vertex("X: 4 Y: 6")
    vMap(4)(7) = new Vertex("X: 4 Y: 7")
    vMap(4)(8) = new Vertex("X: 4 Y: 8")
    vMap(4)(9) = new Vertex("X: 4 Y: 9")
    vMap(4)(10) = new Vertex("X: 4 Y: 10")

    vMap(5)(0) = new Vertex("X: 5 Y: 0")
    vMap(5)(1) = new Vertex("X: 5 Y: 1")
    vMap(5)(2) = new Vertex("X: 5 Y: 2")
    vMap(5)(3) = new Vertex("X: 5 Y: 3")
    vMap(5)(4) = new Vertex("X: 5 Y: 4")
    vMap(5)(5) = new Vertex("X: 5 Y: 5")
    vMap(5)(6) = new Vertex("X: 5 Y: 6")
    vMap(5)(7) = new Vertex("X: 5 Y: 7")
    vMap(5)(8) = new Vertex("X: 5 Y: 8")
    vMap(5)(9) = new Vertex("X: 5 Y: 9")
    vMap(5)(10) = new Vertex("X: 5 Y: 10")

    vMap(6)(0) = new Vertex("X: 6 Y: 0")
    vMap(6)(1) = new Vertex("X: 6 Y: 1")
    vMap(6)(2) = new Vertex("X: 6 Y: 2")
    vMap(6)(3) = new Vertex("X: 6 Y: 3")
    vMap(6)(4) = new Vertex("X: 6 Y: 4")
    vMap(6)(5) = new Vertex("X: 6 Y: 5")
    vMap(6)(6) = new Vertex("X: 6 Y: 6")
    vMap(6)(7) = new Vertex("X: 6 Y: 7")
    vMap(6)(8) = new Vertex("X: 6 Y: 8")
    vMap(6)(9) = new Vertex("X: 6 Y: 9")
    vMap(6)(10) = new Vertex("X: 6 Y: 10")

    vMap(7)(0) = new Vertex("X: 7 Y: 0")
    vMap(7)(1) = new Vertex("X: 7 Y: 1")
    vMap(7)(2) = new Vertex("X: 7 Y: 2")
    vMap(7)(3) = new Vertex("X: 7 Y: 3")
    vMap(7)(4) = new Vertex("X: 7 Y: 4")
    vMap(7)(5) = new Vertex("X: 7 Y: 5")
    vMap(7)(6) = new Vertex("X: 7 Y: 6")
    vMap(7)(7) = new Vertex("X: 7 Y: 7")
    vMap(7)(8) = new Vertex("X: 7 Y: 8")
    vMap(7)(9) = new Vertex("X: 7 Y: 9")
    vMap(7)(10) = new Vertex("X: 7 Y: 10")

    vMap(8)(0) = new Vertex("X: 8 Y: 0")
    vMap(8)(1) = new Vertex("X: 8 Y: 1")
    vMap(8)(2) = new Vertex("X: 8 Y: 2")
    vMap(8)(3) = new Vertex("X: 8 Y: 3")
    vMap(8)(4) = new Vertex("X: 8 Y: 4")
    vMap(8)(5) = new Vertex("X: 8 Y: 5")
    vMap(8)(6) = new Vertex("X: 8 Y: 6")
    vMap(8)(7) = new Vertex("X: 8 Y: 7")
    vMap(8)(8) = new Vertex("X: 8 Y: 8")
    vMap(8)(9) = new Vertex("X: 8 Y: 9")
    vMap(8)(10) = new Vertex("X: 8 Y: 10")

    vMap(9)(0) = new Vertex("X: 9 Y: 0")
    vMap(9)(1) = new Vertex("X: 9 Y: 1")
    vMap(9)(2) = new Vertex("X: 9 Y: 2")
    vMap(9)(3) = new Vertex("X: 9 Y: 3")
    vMap(9)(4) = new Vertex("X: 9 Y: 4")
    vMap(9)(5) = new Vertex("X: 9 Y: 5")
    vMap(9)(6) = new Vertex("X: 9 Y: 6")
    vMap(9)(7) = new Vertex("X: 9 Y: 7")
    vMap(9)(8) = new Vertex("X: 9 Y: 8")
    vMap(9)(9) = new Vertex("X: 9 Y: 9")
    vMap(9)(10) = new Vertex("X: 9 Y: 10")

    vMap(10)(0) = new Vertex("X: 10 Y: 0")
    vMap(10)(1) = new Vertex("X: 10 Y: 1")
    vMap(10)(2) = new Vertex("X: 10 Y: 2")
    vMap(10)(3) = new Vertex("X: 10 Y: 3")
    vMap(10)(4) = new Vertex("X: 10 Y: 4")
    vMap(10)(5) = new Vertex("X: 10 Y: 5")
    vMap(10)(6) = new Vertex("X: 10 Y: 6")
    vMap(10)(7) = new Vertex("X: 10 Y: 7")
    vMap(10)(8) = new Vertex("X: 10 Y: 8")
    vMap(10)(9) = new Vertex("X: 10 Y: 9")
    vMap(10)(10) = new Vertex("X: 10 Y: 10")

    // Return the result
    vMap
  }

  /**
   * Adds adjacencies to the vertex map.
   * @return The vertex map with adjacencies.
   */
  def createVertexMapWithAdjacencies: Array[Array[Vertex]] = {
    val vMap = createBlankVertexMap
    vMap
  }
}