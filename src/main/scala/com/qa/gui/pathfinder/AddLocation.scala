package com.qa.gui.pathfinder

import com.qa.data.entity.Location
import scala.collection.mutable.ListBuffer

/**
 * Adds locations to the vertex list.
 * @author cboucher
 */
class AddLocation {

  /**
   * Add a location to the vertex list. Loops over the entire array.
   */
  def addLocationX(x: Int, locationArray: Array[Array[Location]], vMap: Array[Array[Vertex]], vertexList: ListBuffer[Vertex]) {

    /**
     * Loop over Y times.
     */
    def addLocationY(y: Int) {

      // If not at the end of the array
      if (y < locationArray(x).length) {

        if (locationArray(x)(y) != null) {

          // Add a vertex to the list
          vertexList += (vMap(locationArray(x)(y).row.getValue.asInstanceOf[Int])(locationArray(x)(y).col.getValue.asInstanceOf[Int]))
        }
        // Repeat
        addLocationY(x + (1))
      }
    }

    // If not at the end of the array
    if (x < locationArray.length) {
      addLocationY(0)

      // Repeat
      addLocationX(x + (1), locationArray, vMap, vertexList)
    }
  }
}