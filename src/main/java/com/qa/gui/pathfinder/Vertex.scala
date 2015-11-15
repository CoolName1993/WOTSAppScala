package com.qa.gui.pathfinder

/**
 * @author cboucher
 */
class Vertex(name_ : String) extends Ordered[Vertex] {

  val name = name_
  var adjacencies: Array[Edge] = _
  var minDistance = Double.PositiveInfinity
  var previous: Vertex = _

  /**
   *  Return the vertex name.
   */
  override def toString = name

  /**
   * Compare the vertex to another.
   */
  override def compare(that: Vertex): Int = {
    
    // Return the difference between the two
    minDistance.compare(that.minDistance)
  }

  /**
   *  Compare this vertex to another.
   */
  override def compareTo(that: Vertex): Int = {

    // Return the difference between the two
    minDistance.compare(that.minDistance)
  }
}