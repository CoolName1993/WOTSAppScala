package com.qa.gui.pathfinder

import scala.collection.mutable.ListBuffer

/**
 * Resets the vertexes to match those on the vertex map
 * @author cboucher
 */
class SetTiles {

  /**
   * Resets the vertexes to match those on the vertex map
   */
  def setTiles(z: Int, vertexList: ListBuffer[Vertex], vMap: Array[Array[Vertex]], nVertex: Vertex): Vertex = {

    /**
     *  Repeat for each row in the map
     */
    def row(x: Int) {

      /**
       *  Repeat for each column in the map
       */
      def col(y: Int) {

        // If not at the end of the array
        if (y < WarehouseMap.map(0).length) {
          if (vertexList(z).name.equals(vMap(x)(y).name)) {
            // Set it to the WarehouseMap.map tile
            vertexList(z) = vMap(x)(y)
          }
          col(y + (1))
        }
      }

      // If not at the end of the array
      if (x < WarehouseMap.map.length) {

        col(0)
        row(x + (1))
      }
    }

    // Copy the value into a variable
    var nextVertex = (nVertex)

    // If not at the end of the list
    if (z < vertexList.length) {
      row(0)
      if (nextVertex.name.equals(vertexList(z).name))

        // Set the next vertex to the vertex
        nextVertex = vertexList(z)

      // Repeat
      nextVertex = setTiles(z + (1), vertexList, vMap, nextVertex)
    }

    // Return the result
    nextVertex
  }
}