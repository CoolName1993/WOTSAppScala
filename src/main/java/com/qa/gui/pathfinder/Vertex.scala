package com.qa.gui.pathfinder

/**
 * @author cboucher
 */
case class Vertex(name: String) extends Ordered[Vertex] {

  // Global variables
  var adjacencies: Array[Edge]
  var minDistance = Double.PositiveInfinity
  var previous: Vertex
  var xPos: Int
  var yPos: Int
  var visited = false

  // Return the vertex name
  override def toString() = name

  // Compare this vertex to another
  def compareTo(other: Vertex): Int = {

    // Return the difference between the two
    minDistance.compare(other.minDistance)
  }
}