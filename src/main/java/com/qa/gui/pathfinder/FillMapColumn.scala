package com.qa.gui.pathfinder

import scala.collection.mutable.MutableList

/**
 * Adds edges to all vertexes on the given row of the vertex map
 * @author cboucher
 */
class FillMapColumn {

  /**
   * Repeats for each column in the array.
   * @param y The y coordinate in the array.
   * @param x The x coordinate in the array.
   * @param vMap The vertex map to be edited.
   */
  def column(y: Int, x: Int, vMap: Array[Array[Vertex]]) {
    if (y < WarehouseMap.map(0).length) {
      // Create a new list of edges
      var edgeList = new MutableList[Edge]

      // If the WarehouseMap.map tile is not a wall
      if (WarehouseMap.map(x)(y) == 0) {

        // If the WarehouseMap.map tile is not on the top edge
        if (x != 0) {
          // Add a new edge to the edge list
          val outputEdge = new Edge(vMap(x - 1)(y), 1)
          edgeList.+=(outputEdge)
        }

        // If the WarehouseMap.map tile is not on the left edge
        if (y != 0) {

          // If the WarehouseMap.map tile above is not a wall
          if (WarehouseMap.map(x)(y - 1) != 1) {

            // Add a new edge to the edge list
            val outputEdge = new Edge(vMap(x)(y - 1), 1)
            edgeList.+=(outputEdge)
          }
        }

        // If the WarehouseMap.map tile is under the top row and now connected to entrance
        if (x == 1 && y != 5) {
          // Add a new edge to the edge list
          val outputEdge = new Edge(vMap(x - 1)(y), 1)
          edgeList.+=(outputEdge)
        } // If the WarehouseMap.map tile is above the bottom row
        if (x == WarehouseMap.map.length - 2) {
          //Add a new edge to the edge list
          val outputEdge = new Edge(vMap(x + 1)(y), 1)
          edgeList.+=(outputEdge)
        } // If the WarehouseMap.map tile is greater than 0 and not on the top row
        if (y < WarehouseMap.map(x).length - 1 && x != 0) {
          // Add a new edge to the left
          val outputEdge = new Edge(vMap(x)(y - 1), 1)
          edgeList.+=(outputEdge)
        }
        if (y == WarehouseMap.map(x).length - 2) {
          // Add a new edge to the right
          val outputEdge = new Edge(vMap(x)(y + 1), 1)
          edgeList.+=(outputEdge)
        }

        // If the WarehouseMap.map tile is not on the right edge
        if (x < WarehouseMap.map.length - 1) {

          // If the WarehouseMap.map tile to the right is not a wall
          if (WarehouseMap.map(x + 1)(y) != 1) {

            // Add a new edge to the edge list
            val outputEdge = new Edge(vMap(x + 1)(y), 1)
            edgeList.+=(outputEdge)
          }
        }

        // If the WarehouseMap.map tile is not on the bottom edge
        if (y < WarehouseMap.map(0).length - 1) {

          // If the WarehouseMap.map tile below is not a wall
          if (WarehouseMap.map(x)(y + 1) != 1) {

            // Add a new edge to the edge list
            val outputEdge = new Edge(vMap(x)(y + 1), 1)
            edgeList.+=(outputEdge)
          }
        }
      } // Add nodes to access shelves
      else if (WarehouseMap.map(x)(y) == 1) {
        // Shelf accessed from below
        if (x == 0) {
          val outputEdge = new Edge(vMap(x + 1)(y), 1)
          //              println("X: " + x + " Y: " + y + " below added")
          edgeList.+=(outputEdge)
        } // Shelf accessed from above
        if (x == WarehouseMap.map.length - 1) {
          val outputEdge = new Edge(vMap(x - 1)(y), 1)
          //              println("X: " + x + " Y: " + y + " above added")
          edgeList.+=(outputEdge)
        } //Shelf accessed from right
        if (y < WarehouseMap.map.length - 1 && x != 0 && x != WarehouseMap.map.length - 1) {
          val outputEdge = new Edge(vMap(x)(y + 1), 1)
          //              println("X: " + x + " Y: " + y + " right added")
          edgeList.+=(outputEdge)
        } // Shelf accessed from left
        if (y == WarehouseMap.map.length - 1 && x != 0 && x != WarehouseMap.map.length - 1) {
          val outputEdge = new Edge(vMap(x)(y - 1), 1)
          //              println("X: " + x + " Y: " + y + " left added")
          edgeList.+=(outputEdge)
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
      column(y + (1), x, vMap)
    }
  }
}