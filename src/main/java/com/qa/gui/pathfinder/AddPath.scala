package com.qa.gui.pathfinder

import scala.collection.mutable.MutableList

/**
 * Adds a path to the final path map
 * @author cboucher
 */
class AddPath {

  /**
   *  Add a path to the final path map.
   *  @param i The index in the list.
   *  @param path The calculated path between vertexes.
   *  @param outputMap The final map to contain the paths.
   */
  def addPath(i: Int, path: MutableList[Array[Int]], outputMap: Array[Array[Int]]): Unit = {

    // For coordinate set in the array list
    if (i < path.length) {

      // Change the number on the final path map to reflect the tile type
      outputMap(path(i)(0))(path(i)(1)) match {
        // If it is on the floor add a floor path tile
        case 0 => outputMap(path(i)(0))(path(i)(1)) = 3

        // If it is on a wall add a wall path tile
        case 1 => outputMap(path(i)(0))(path(i)(1)) = 4

        // If it is not reachable, keep it the same
        case 2 => outputMap(path(i)(0))(path(i)(1)) = 2

        // If it is a floor path tile, keep it the same
        case 3 => outputMap(path(i)(0))(path(i)(1)) = 3

        // If it is a wall path tile, keep it the same
        case 4 => outputMap(path(i)(0))(path(i)(1)) = 4

        // Default to floor tile
        case _ => outputMap(path(i)(0))(path(i)(1)) = 0
      }

      // Repeat
      addPath(i + (1), path, outputMap)
    }
  }
}