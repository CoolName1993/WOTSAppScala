package com.qa

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.OptionValues
import org.scalatest.Inside
import org.scalatest.Inspectors

/**
 * Base test class to be inherited by all other test classes.
 * @author cboucher
 */
abstract class TestBase extends FlatSpec
with Matchers with OptionValues
with Inside with Inspectors