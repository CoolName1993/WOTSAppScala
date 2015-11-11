package com.qa.gui.pathfinder

/**
 * @author cboucher
 */
case class Vertex(name: String) extends Ordered[Vertex] {

  // Global variables
  var adjacencies: Array[Edge] = _
  var minDistance = Double.PositiveInfinity
  var previous: Vertex = _
  var xPos: Int = 0
  var yPos: Int = 0
  var visited = false

  // Return the vertex name
  override def toString() = name

  override def compare(that: Vertex): Int = {
    // Return the difference between the two
    minDistance.compare(that.minDistance)
  }

  // Compare this vertex to another
  override def compareTo(that: Vertex): Int = {

    // Return the difference between the two
    minDistance.compare(that.minDistance)
  }
}