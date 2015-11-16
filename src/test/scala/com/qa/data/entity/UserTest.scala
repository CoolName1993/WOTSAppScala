package com.qa.data.entity

import com.qa.TestBase

/**
 * @author cboucher
 */
class UserTest extends TestBase {

  "A user" should "be initialised with the correct values" in {
    val result = new User(0,"Test","Test1","Test2","Testmail",0)
    assert(result.idUser.getValue.equals(0))
    assert(result.password.getValue.equals("Test"))
    assert(result.forename.getValue.equals("Test1"))
    assert(result.surname.getValue.equals("Test2"))
    assert(result.email.getValue.equals("Testmail"))
    assert(result.isEmployee.getValue.equals(0))
  }
}