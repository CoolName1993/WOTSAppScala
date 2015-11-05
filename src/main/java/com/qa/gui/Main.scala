package com.qa.gui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{ TableCell, TableColumn, TableView }
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.beans.property.StringProperty
import scalafx.beans.property.ObjectProperty
import com.qa.sql.SQLConnector
import com.qa.mongo.MongoConnector
/**
 * @author cboucher
 */
object Main extends JFXApp {

  //TEST//
  SQLConnector.connect
  SQLConnector.disconnect
  MongoConnector.connect
  val fields = Array("IsPorousware","idSupplier")
  val entity = Array(true,2)
  val results = MongoConnector.read("Item", fields, entity)
  for(result <- results) {
    println(result.toString())
  }
  MongoConnector.disconnect
  //TEST//

  val characters = ObservableBuffer[Person](
    new Person("Peggy", "Sue", Color.Violet),
    new Person("Rocky", "Raccoon", Color.GreenYellow),
    new Person("Bungalow ", "Bill", Color.DarkSalmon))

  stage = new PrimaryStage {
    title = "TableView with custom color cell"
    scene = new Scene {
      content = new TableView[Person](characters) {
        columns ++= List(
          new TableColumn[Person, String] {
            text = "First Name"
            cellValueFactory = { _.value.firstName }
            prefWidth = 300
          },
          new TableColumn[Person, String]() {
            text = "Last Name"
            cellValueFactory = { _.value.lastName }
            prefWidth = 300
          },
          new TableColumn[Person, Color] {
            text = "Favorite Color"
            cellValueFactory = { _.value.favoriteColor }
            // Render the property value when it changes, 
            // including initial assignment
            cellFactory = { _ =>
              new TableCell[Person, Color] {
                item.onChange { (_, _, newColor) =>
                  graphic = new Circle { fill = newColor; radius = 8 }
                }
              }
            }
            prefWidth = 300
          })
      }
    }
  }
}

class Person(firstName_ : String,
             lastName_ : String,
             favoriteColor_ : Color) {
  val firstName = new StringProperty(this, "firstName", firstName_)
  val lastName = new StringProperty(this, "lastName", lastName_)
  val favoriteColor = new ObjectProperty(this, "favoriteColor",
    favoriteColor_)
}