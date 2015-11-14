package com.qa.gui.pathfinder

/**
 * Creates a new vertex map and fills it with all adjacencies.
 * @author cboucher
 */
class VertexMap {

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
        new FillMapColumn().column(0, x, vMap)
        row(x + (1))
      }
    }
    row(0)
  }

  /**
   *  Create a new vertex map with new vertexes.
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
          vertexMap(x)(y) = new Vertex("X: " + x + "Y: " + y)
          mapColumn(y + (1))
        }
      }
      if (x < WarehouseMap.map.length) {
        mapColumn(0)
        mapRow(x + (1))
      }
    }

    mapRow(0)
    fillMap(vertexMap)
    vertexMap
  }
}