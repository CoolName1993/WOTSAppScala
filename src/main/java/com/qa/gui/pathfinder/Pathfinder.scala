package com.qa.gui.pathfinder

import scala.collection.mutable.MutableList
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.PriorityQueue
import com.qa.data.entity.Location

/**
 * Calculates the best possible route through the warehouse.
 * @author cboucher
 */
class Pathfinder {

  /**
   *  Find the nearest vertex and call recursively.
   *  @param source The current vertex to travel from.
   *  @param vertexList A list of all target vertexes.
   *  @param outputMap The final map with all paths added on.
   *  @param vMap The map of all vertexes in the warehouse.
   */
  def pathfinder(source: Vertex, vertexList: ListBuffer[Vertex], outputMap: Array[Array[Int]], vMap: Array[Array[Vertex]]): Unit = {

    // Reset values
    source.previous = null
    var nextVertex: Vertex = null

    // Find all paths from the current vertex
    new ComputePath().computePaths(source)

    // For each vertex in the vertex list
    nextVertex = new CompareDistance().compareEachVertex(0, vertexList, Double.PositiveInfinity, source, nextVertex)

    // Create a path of coordinates from the path finder
    val path = new GetShortestPath().getShortestPathTo(nextVertex, vMap)

    // Draw the path on the warehouse WarehouseMap.map
    new AddPath().addPath(0, path, outputMap)

    // Remove the vertex from the list
    vertexList -= source

    // Reset the vertex map
    val vMap2 = new VertexMap().vertexMap

    nextVertex = new SetTiles().setTiles(0, vertexList, vMap2, nextVertex)

    // If there more than one vertex left
    if (vertexList.length > 1) {

      // Do it again using the target vertex
      pathfinder(nextVertex, vertexList, outputMap,vMap2)
    }
  }

  /**
   * Performs the pathfinder algorithm on the vertex list and returns a map with the paths on it.
   * @param locationArray An array of locations to travel to.
   */
  def execute(locationArray: Array[Array[Location]], outputMap: Array[Array[Int]]): Unit = {

    // Create a new vertex list
    val vertexList = new ListBuffer[Vertex]

    val vMap = new VertexMap().vertexMap

    vertexList += (vMap(0)(5))
    new AddLocation().addLocationX(0, locationArray, vMap, vertexList)
    pathfinder(vertexList(0), vertexList, outputMap, vMap)
    //outputMap
  }
}