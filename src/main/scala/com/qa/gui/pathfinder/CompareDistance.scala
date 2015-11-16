package com.qa.gui.pathfinder

import scala.collection.mutable.ListBuffer

/**
 * Compares distances in the vertex list.
 * @author cboucher
 */
class CompareDistance {
  
  /**
   * Compares each distance to find the closest vertex.
   * @param i The index number in the loop.
   * @param vertexList The list containing all target vertexes yet to be visited.
   * @param minimumDistance The current closest distance to the target.
   * @param source The vertex to travel from.
   * @param target The currently selected target vertex.
   */
  def compareEachVertex(i: Int, vertexList: ListBuffer[Vertex], minimumDistance: Double, source: Vertex, target: Vertex): Vertex = {

    // Copy values into a variables
    var minDistance = (minimumDistance)
    var nextVertex = (target)
    
    // If not at the end of the vertex list
    if (i < vertexList.length) {
      
      // If the vertex is not the current vertex
      if (!vertexList(i).equals(source)) {
        
        System.out.println("Distance to " + vertexList(i) + ": " + vertexList(i).minDistance) // DEBUG

        // Check the distance is closer than the closest
        if (vertexList(i).minDistance < minDistance) {

          // Set the vertex to be the closest
          minDistance = vertexList(i).minDistance
          nextVertex = vertexList(i)
        }
      }
      
      // Repeat
      nextVertex = compareEachVertex(i + (1), vertexList, minDistance, source, nextVertex)
    }
    
    // Return the result
    nextVertex
  }
}