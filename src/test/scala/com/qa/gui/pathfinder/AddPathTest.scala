package com.qa.gui.pathfinder

import com.qa.TestBase

/**
 * @author cboucher
 */
class AddPathTest extends TestBase{
  
  "addPath" should "draw the specified path on the map" in {
    val result = (new TestValues().map)
    new AddPath().addPath(0, new TestValues().path, result)
    
    def loop(i: Int) {
      if(i < new TestValues().path.length)
      {
        assert(result(new TestValues().path(i)(0))(new TestValues().path(i)(1)) == 3)
      }
    }
    
    loop(0)
  }
}