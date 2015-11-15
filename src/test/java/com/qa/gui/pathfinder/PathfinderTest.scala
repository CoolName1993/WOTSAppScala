package com.qa.gui.pathfinder

import com.qa.TestBase

/**
 * @author cboucher
 */
class PathfinderTest extends TestBase {
  "execute" should "Find the optimal route and create a warehouse map with the path drawn on it" in {
    val result = (new TestValues().map)
    val locationArray = (new TestValues().locationArray)
    new Pathfinder().execute(locationArray, result)
    assert(result(8)(8) == 4)
    assert(result(0)(5) == 3)
    assert(result(6)(4) == 4)
    assert(result(10)(9) == 4)
  }
}