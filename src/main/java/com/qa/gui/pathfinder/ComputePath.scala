package com.qa.gui.pathfinder

import scala.collection.mutable.PriorityQueue

/**
 * Finds all paths from the source vertex.
 * @author cboucher
 */
class ComputePath {
  /**
   *  Path finder using dijkstra algorithm.
   *  @param source The vertex to calculate paths from.
   */
  def computePaths(source: Vertex): Unit = {

    // Set the minimum distance of the source to be 0
    source.minDistance = 0

    // Add the source to a new queue
    var vertexQueue = new PriorityQueue[Vertex]()
    vertexQueue.+=(source)

    /**
     *  While the vertex queue is not empty
     */
    def whileLoop() {
      if (!vertexQueue.isEmpty) {

        // Poll the vertex queue
        var u = vertexQueue.dequeue

        /**
         *  For each edge of the polled vertex
         */
        def compare(i: Int) {
          if (i < u.adjacencies.length) {
            // Get the target vertex of the edge
            var v = u.adjacencies(i).target

            // Calculate the distance
            val distanceThroughU = u.minDistance + u.adjacencies(i).weight

            // If the distance is less than the minimum
            if (distanceThroughU < v.minDistance) {

              // Remove the target from the queue
              var tempQueue = vertexQueue.clone
              vertexQueue.dequeueAll

              def removeElement() {
                if (!tempQueue.isEmpty) {
                  var next = tempQueue.dequeue
                  if (next != v) {
                    vertexQueue.+=(next)
                  }
                  removeElement
                }
              }

              removeElement
              // Set the minimum distance of v to be the distance
              v.minDistance = distanceThroughU

              // Set the previous vertex of v to be u
              v.previous = u

              // Add v to the queue
              vertexQueue.+=(v)
            }
            compare(i + (1))
          }
        }
        compare(0)
        whileLoop
      }
    }
    whileLoop
  }
}