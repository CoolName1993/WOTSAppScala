package com.qa.gui.pathfinder

import scala.collection.mutable.MutableList
import scala.collection.mutable.ListBuffer

/**
 * @author cboucher
 */
class Pathfinder {

  val vertexList = new ListBuffer[Vertex]()
  var vMap = vertexMap
  val map = Array(
    Array(2, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2),
    Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
    Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
    Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
    Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
    Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
    Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
    Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
    Array(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
    Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
    Array(2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2))

  // Fill the vertex map with new vertexes
  def vertexMap(): Array[Array[Vertex]] = {

    var vertexMap: Array[Array[Vertex]] = Array.ofDim[Vertex](map.length,map(0).length)

    def row(x: Int) {
      def column(y: Int) {
        if(y < map(0).length) {
          vertexMap(x)(y) = new Vertex("X: " + x + "Y: " + y)
          column(y +(1))
        }
      }
      if(x < map.length) {
        column(0)
        row(x +(1))
      }
    }
    
    row(0)
    vertexMap
  }

  // Add edges to all vertexes in the map
  def fillMap(): Unit = {
    def row(x : Int) {
      def column(y: Int) {
        if(y < map(0).length) {
          // Create a new list of edges
        var edgeList = new MutableList[Edge]

        // If the map tile is not a wall
        if (map(x)(y) != 1) {

          // If the map tile is not on the left edge
          if (x != 0) {

            // If the map tile to the left is not a wall
            if (map(x -(1))(y) != 1) {

              // Add a new edge to the edge list
              val outputEdge = new Edge(vMap(x - 1)(y), 1)
              edgeList.+=(outputEdge)
            }
          }

          // If the map tile is not on the top edge
          if (y != 0) {

            // If the map tile above is not a wall
            if (map(x)(y - 1) != 1) {

              // Add a new edge to the edge list
              val outputEdge = new Edge(vMap(x)(y - 1), 1)
              edgeList.+=(outputEdge)
            }
          }

          // If the map tile is not on the right edge
          if (x < 11) {

            // If the map tile to the right is not a wall
            if (map(x + 1)(y) != 1) {

              // Add a new edge to the edge list
              val outputEdge = new Edge(vMap(x + 1)(y), 1)
              edgeList.+=(outputEdge)
            }
          }

          // If the map tile is not on the bottom edge
          if (y < map(0).length) {

            // If the map tile below is not a wall
            if (map(x)(y + 1) != 1) {

              // Add a new edge to the edge list
              val outputEdge = new Edge(vMap(x)(y + 1), 1)
              edgeList.+=(outputEdge)
            }
          }
        }

        // If the edge list isn't empty
        if (edgeList != null) {

          // Convert the list to an array
          var edgeArray = new Array[Edge](edgeList.size)
                    
          // For each edge in the list
          def loop(z: Int) {
            if(z < edgeArray.size) {
              edgeArray(z) = edgeList(z)
              loop(z +(1))
            }
          }
          loop(0)

          // Add the edges to the vertex
          vMap(x)(y).adjacencies = edgeArray
        }
        column(y +(1))
        }
      }
      if(x < map.length) {
          column(0)
          row(x +(1))
      }
    }
    row(0)
  }

  // Find the nearest vertex and call recursively
  def pathFind(source: Vertex): Unit = {

    // Reset values
    // boolean complete = false;
    var minDistance = Double.PositiveInfinity
    source.previous = null
    var nextVertexName: String = null
    var nextVertex: Vertex = null

    // Find all paths from the current vertex
    computePaths(source)
    
    def loop(i: Int) {
      // If the vertex is not the current vertex
      if (!vertexList(i).equals(source)) {
        System.out.println("Distance to " + vertexList(i) + ": " + vertexList(i).minDistance)

        // Check the distance is closer than the closest
        if (vertexList(i).minDistance < minDistance) {

          // Set the vertex to be the closest
          minDistance = vertexList(i).minDistance
          nextVertex = vertexList(i)
          nextVertexName = vertexList(i).name
        }
      }
    }
    // For each vertex in the vertex list
    loop(0)

    // Create a path of coordinates from the path finder
    var path = getShortestPathTo(nextVertex)

    // Draw the path on the warehouse map
    // pathMap.addPath(path)

    // Remove the vertex from the list
    vertexList -= source

    // Reset the vertex map
    vMap = vertexMap
    fillMap

    def setTiles(z: Int) {
      def row(x: Int) {
        if(x < map.length) {
        def col(y: Int) {
          if(y < map(0).length) {
            if (vertexList(z).name.equals(vMap(x)(y).name)) {
              // Set it to the map tile
              vertexList(z) = vMap(x)(y)
            }
            col(y +(1))
          }
        }
        col(0)
        row(x +(1))
        }
      }
      if(z < vertexList.size) {
        row(0)
        if (nextVertexName.equals(vertexList(z).name))

        // Set the next vertex to the vertex
        nextVertex = vertexList(z)
        
        setTiles(z +(1))
      }
    }
    
    setTiles(0)
    
        // If there more than one vertex left
    if (vertexList.size > 1)

      // Do it again using the target vertex
      pathFind(nextVertex)    
  }

  // Perform the path finder and return the map
  public WarehouseMap pathMap() {
    pathFind(vertexList.get(0))
    return pathMap
  }

  // Path finder using dijkstra algorithm
  public static void computePaths(Vertex source) {

    // Set the minimum distance of the source to be 0
    source.minDistance = 0.

    // Add the source to a new queue
    PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
    vertexQueue.add(source)

    // While the vertex queue is not empty
    while (!vertexQueue.isEmpty()) {

      // Poll the vertex queue
      Vertex u = vertexQueue.poll()

      // For each edge of the polled vertex
      for (Edge e : u.adjacencies) {

        // Get the target vertex of the edge
        Vertex v = e.target

        // Get the weight of the edge
        double weight = e.weight

        // Calculate the distance
        double distanceThroughU = u.minDistance + weight

        // If the distance is less than the minimum
        if (distanceThroughU < v.minDistance) {

          // Remove the target from the queue
          vertexQueue.remove(v)

          // Set the minimum distance of v to be the distance
          v.minDistance = distanceThroughU

          // Set the previous vertex of v to be u
          v.previous = u

          // Add v to the queue
          vertexQueue.add(v)
        }
      }
    }
  }

  // Get the shortest path to the target vertex
  private ArrayList<int[]> getShortestPathTo(Vertex target) {

    // Initialise variables
    List<Vertex> path = new ArrayList<Vertex>()
    ArrayList<int[]> output = new ArrayList<int[]>()

    // Iterate the loop by navigating the previous vertexes of each vertex
    // in the path
    for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {

      // For each row in the map
      for (int i = 0; i < 11; i++) {

        // For each column in the map
        for (int j = 0; j < 10; j++) {

          // If the current vertex is the same as the map vertex
          if (vertexMap[i][j].name.equals(vertex.name)) {
            System.out.println(i + " " + j)

            // Get the coordinates of the vertex
            int[] coordinates = { i, j }

            // Add the coordinates to the path
            output.add(coordinates)
            vertex = vertexMap[i][j] // Temporary test
            path.add(vertex) // Temporary test
          }
        }
      }
    }

    // Reverse the path
    Collections.reverse(path) // Temporary test
    System.out.println(path) // Temporary test
    Collections.reverse(output)
    return output
  }

  // Add a location to the vertex list
  public void addLocation(int xPos, int yPos) {

    // Ad a vertex to the list
    vertexList.add(vertexMap[xPos][yPos])
  }
}