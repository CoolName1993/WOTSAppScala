package com.qa.gui.panel

import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color._
import scalafx.scene.input.MouseEvent
import scalafx.Includes._
import scalafx.scene.layout.GridPane

/**
 * @author cboucher
 */
case class CustomerOrderMap(map: Array[Array[Int]]) extends GridPane {

  def createTile(x: Int, y: Int): Rectangle = {
    var colour = ""
    var highlight = ""
    map(x)(y) match {
      case 0 => {
        colour = "map-floor"
        highlight = "map-floor-highlight"
      }
      case 1 => {
        colour = "map-wall"
        highlight = "map-wall-highlight"
      }
      case 2 => {
        colour = "map-wall-highlight"
        highlight = "map-wall-highlight"
      }
      case 3 => {
        colour = "map-path-floor"
        highlight = "map-path-floor-highlight"
      }
      case 4 => {
        colour = "map-path-wall-open"
        highlight = "map-path-wall-open-highlight"
      }
      case 5 => {
        colour = "map-path-wall-closed"
        highlight = "map-path-wall-closed-highlight"
      }
      case _ => {
        colour = "map-floor"
        highlight = "map-floor-highlight"
      }
    }
    val tile = new Rectangle() {
      width = 61
      height = 61
      id = colour
      onMouseClicked = (me: MouseEvent) => {
        // do stuff
      }
      onMouseEntered = (me: MouseEvent) => {
        id = highlight
      }
      onMouseExited = (me: MouseEvent) => {
        id = colour
      }
    }
    tile
  }

  def createMap() {
    val tileMap: Array[Array[Rectangle]] = Array.ofDim[Rectangle](map.length, map(0).length)
    def addTiles(x: Int) {
      def singleTile(y: Int) {
        if (y < map(x).length) {
          this.add(createTile(x, y), y, x)
          singleTile(y + (1))
        }
      }
      if (x < map.length) {
        singleTile(0)
        addTiles(x + (1))
      }
    }
    addTiles(0)
  }
  createMap
}