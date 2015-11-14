package com.qa.gui.pathfinder

import scala.collection.mutable.MutableList

/**
 * Creates a new vertex map and fills it with all adjacencies.
 * @author cboucher
 */
class VertexMap {

  /**
   * Repeats for each column in the array.
   * @param y The y coordinate in the array.
   * @param x The x coordinate in the array.
   * @param vMap The vertex map to be edited.
   */
  def fillMapColumn(y: Int, x: Int, vMap: Array[Array[Vertex]]) {
    
    // If not at the end of the array
    if (y < WarehouseMap.map(0).length) {

      // Create a new list of edges
      var edgeList = new MutableList[Edge]

      // If the WarehouseMap.map tile is not a wall
      if (WarehouseMap.map(x)(y) == 0) {

        // If the WarehouseMap.map tile is not on the top edge
        if (x != 0) {

          // If the tile above is not a wall
          if (WarehouseMap.map(x - 1)(y) != 1) {

            // Add a new edge to the edge list
            val outputEdge = new Edge(vMap(x - 1)(y), 1)
            edgeList += (outputEdge)
          }
        }

        // If the map tile is not on the bottom edge
        if (x < WarehouseMap.map.length - 1) {

          // If the WarehouseMap.map tile below is not a wall
          if (WarehouseMap.map(x + 1)(y) != 1) {

            // Add a new edge to the edge list
            val outputEdge = new Edge(vMap(x + 1)(y), 1)
            edgeList += (outputEdge)
          }
        }

        // If the map tile is not on the left edge
        if (y != 0) {

          // If the WarehouseMap.map tile to the left is not a wall
          if (WarehouseMap.map(x)(y - 1) != 1) {

            // Add a new edge to the edge list
            val outputEdge = new Edge(vMap(x)(y - 1), 1)
            edgeList += (outputEdge)
          }
        }

        // If the map tile is not on the right edge
        if (y < WarehouseMap.map(0).length - 1) {

          // If the map tile to the right is not a wall
          if (WarehouseMap.map(x)(y + 1) != 1) {

            // Add a new edge to the edge list
            val outputEdge = new Edge(vMap(x)(y + 1), 1)
            edgeList += (outputEdge)
          }
        }

        // If the map tile is under the top row and not connected to entrance
        if (x == 1 && y != 5) {

          // Add a new edge to the edge list
          val outputEdge = new Edge(vMap(x - 1)(y), 1)
          edgeList += (outputEdge)
        }

        // If the map tile is above the bottom row
        if (x == WarehouseMap.map.length - 2) {

          //Add a new edge to the edge list
          val outputEdge = new Edge(vMap(x + 1)(y), 1)
          edgeList += (outputEdge)
        }

        // If the map tile is greater than 0 and not on the top row
        if (y < WarehouseMap.map(x).length - 1 && x != 0 && x < WarehouseMap.map.length - 1) {

          // Add a new edge to the left
          val outputEdge = new Edge(vMap(x)(y - 1), 1)
          edgeList += (outputEdge)
        }

        // If map tile is next to the right wall
        if (y == WarehouseMap.map(x).length - 2 && x < WarehouseMap.map.length - 1) {

          // Add a new edge to the right
          val outputEdge = new Edge(vMap(x)(y + 1), 1)
          edgeList += (outputEdge)
        }

      } // Add nodes to access shelves
      else if (WarehouseMap.map(x)(y) == 1) {

        // Shelf accessed from below
        if (x == 0) {
          val outputEdge = new Edge(vMap(x + 1)(y), 1)
          edgeList += (outputEdge)
        }

        // Shelf accessed from above
        if (x == WarehouseMap.map.length - 1) {
          val outputEdge = new Edge(vMap(x - 1)(y), 1)
          edgeList += (outputEdge)
        }

        //Shelf accessed from right
        if (y < WarehouseMap.map.length - 1 && x != 0 && x < WarehouseMap.map.length - 2) {
          val outputEdge = new Edge(vMap(x)(y + 1), 1)
          edgeList += (outputEdge)
        }

        // Shelf accessed from left
        if (y == WarehouseMap.map.length - 1 && x != 0 && x < WarehouseMap.map.length - 2) {
          val outputEdge = new Edge(vMap(x)(y - 1), 1)
          edgeList += (outputEdge)
        }
      }

      // If the edge list isn't empty
      if (edgeList != null) {

        // Convert the list to an array
        var edgeArray = new Array[Edge](edgeList.size)

        // For each edge in the list
        def loop(z: Int) {
          if (z < edgeArray.size) {
            edgeArray(z) = edgeList(z)
            loop(z + (1))
          }
        }
        loop(0)
        
        // Add the edges to the vertex
        vMap(x)(y).adjacencies = edgeArray
      }
      fillMapColumn(y + (1), x, vMap)
    }
  }
  
  /**
   *  Add edges to all vertexes in the map.
   *  @param vMap The vertex map to be filled.
   */
  def fillMap(vMap: Array[Array[Vertex]]): Unit = {

    /*
     * Repeats for each row in the array.
     */
    def row(x: Int) {
      if (x < WarehouseMap.map.length) {
        fillMapColumn(0, x, vMap)
        row(x + (1))
      }
    }
    row(0)
  }

  /**
   *  Create a new vertex map with new vertexes.
   *  @return The created vertex map.
   */
  def vertexMap: Array[Array[Vertex]] = {

    // Create a vertex map the same length as the warehouse map
    val vertexMap: Array[Array[Vertex]] = Array.ofDim[Vertex](WarehouseMap.map.length, WarehouseMap.map(0).length)

    /**
     * Repeat for each row.
     */
    def mapRow(x: Int) {

      /**
       * Repeat for each column.
       */
      def mapColumn(y: Int) {
        if (y < WarehouseMap.map(0).length) {
          vertexMap(x)(y) = new Vertex("X: " + x + " Y: " + y)
          mapColumn(y + (1))
        }
      }
      if (x < WarehouseMap.map.length) {
        mapColumn(0)
        mapRow(x + (1))
      }
    }
    mapRow(0)
    vertexMap
  }
  
  /**
   * Creates a new vertex map then fills it with adjacencies.
   * @return The finished vertex map.
   */
  def createMap: Array[Array[Vertex]] = {
    val vMap = vertexMap
    fillMap(vMap)
    vMap
    }
}