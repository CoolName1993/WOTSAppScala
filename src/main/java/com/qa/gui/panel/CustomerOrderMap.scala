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
    var colour = White
    map(x)(y) match {
      case 0 => colour = White
      case 1 => colour = Color.rgb(107, 120, 115)
      case 2 => colour = Color.rgb(116, 97, 96)
      case 3 => colour = Blue
      case 4 => colour = Yellow
      case _ => colour = White
    }
    var tile = new Rectangle() {
      width = 61
      height = 61
      fill = colour
      onMouseClicked = (me: MouseEvent) => {
        colour = Blue
        fill = colour
      }
      onMouseEntered = (me: MouseEvent) => {
        fill = Grey
      }
      onMouseExited = (me: MouseEvent) => {
        fill = colour
      }
    }
    tile
  }

  def createMap() {
    var tileMap: Array[Array[Rectangle]] = Array.ofDim[Rectangle](map.length, map(0).length)
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