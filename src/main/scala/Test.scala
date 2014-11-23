package com.ilim.mongodbtest
/**
 * Created by ilimturan on 23/11/14.
 */
import org.joda.time.DateTime
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.conversions.scala.{RegisterConversionHelpers, RegisterJodaTimeConversionHelpers}

object Test extends App {

  // fix datetime conversion :(
  RegisterConversionHelpers()
  RegisterJodaTimeConversionHelpers()

  //insert some book
  testInsert
  // get all book
  testFindAll
  //get a book
  testFindOne
  //update one
  testUpdateOne
  //remove one
  testRemoveOne


  def testInsert = {
    val book1 = Book(123, "Scala Cookbook", DateTime.now())
    val book2 = Book(34534534, "Scala In Action", DateTime.now())
    val book3 = Book(79855332, "Scala for Play", DateTime.now())

    BookBuilder.insertBook(book1)
    BookBuilder.insertBook(book2)
    BookBuilder.insertBook(book3)
    println("*****insert query*******")
    println("Books are saved")
  }

  def testFindOne = {
    val q = MongoDBObject("name" -> "Scala Cookbook")
    val book = BookBuilder.findOne(q)
    println("******Find one query*******")
    println(book)
  }

  def testFindAll = {
    val books = BookBuilder.findAll
    println("*******Find all query********")
    books.foreach(println)
  }

  def testUpdateOne = {
    val query = MongoDBObject("isbn" -> 123)
    val update = MongoDBObject("name" -> "Scala Cookbook (Updated)")
    val uBook = BookBuilder.updateOne(query, update)
    println("******Update query*******")
    println(uBook)
  }

  def testRemoveOne = {
    val q = MongoDBObject("name" -> "Scala for Play")
    val result = BookBuilder.removeOne(q)
    println("******remove one query*******")
    println(result)
  }

}
