package com.qa.gui.pathfinder

import scala.collection.mutable.MutableList

/**
 * Gets the shortest path to the target vertex.
 * @author cboucher
 */
class GetShortestPath {
  
  /**
   *  Get the shortest path to the target vertex.
   *  @param target The target vertex.
   *  @param vMap The vertex map.
   */
  def getShortestPathTo(target: Vertex, vMap: Array[Array[Vertex]]): MutableList[Array[Int]] = {

    // Initialise variables
    var path = new MutableList[Vertex]
    var output = new MutableList[Array[Int]]

    // Iterate the loop by navigating the previous vertexes of each vertex
    // in the path
    def loop(vertex: Vertex) {

      // For each row in the WarehouseMap.map
      def row(x: Int) {

        // For each column in the WarehouseMap.map
        def col(y: Int) {
          if (y < WarehouseMap.map(0).length) {

            // If the current vertex is the same as the WarehouseMap.map vertex
            if (vMap(x)(y).name.equals(vertex.name)) {

              // Get the coordinates of the vertex
              val coordinates = Array(x, y)

              // Add the coordinates to the path
              output.+=(coordinates)
            }
            col(y + (1))
          }
        }
        if (x < WarehouseMap.map.length) {
          col(0)
          row(x + (1))
        }
      }
      if (vertex != null) {
        row(0)
        loop(vertex.previous)
      }
    }

    loop(target)

    // Reverse the path
    output.reverse
  }

}