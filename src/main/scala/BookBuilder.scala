package com.ilim.mongodbtest
/**
 * Created by ilimturan on 23/11/14.
 */
import org.joda.time.DateTime
import com.mongodb.casbah.Imports._

case class Book(isbn: Long, name: String, date: DateTime)

object BookBuilder {

  // scala object to mongodb object
  def buildDbBook(book: Book): MongoDBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "isbn" -> book.isbn
    builder += "name" -> book.name
    builder += "date" -> book.date
    builder.result
  }

  // mongodb object to scala object
  def convertDbBookToBook(obj: MongoDBObject): Book = {
    Book(obj.getAs[Long]("isbn").get, obj.getAs[String]("name").get, obj.getAs[DateTime]("date").get )
  }

  /**
   * insert one book
   * @param book Book obj
   */
  def insertBook(book: Book): Unit = {
    val mObj = buildDbBook(book)
    MongoFactory.bookCollection.save(mObj)
  }

  /**
   *
   * @param query is mongo query
   * @return book object
   */
  def findOne(query: MongoDBObject):Book = {
    val bookObj = MongoFactory.bookCollection.findOne(query)
    //mongo cursor to scala object
    convertDbBookToBook(bookObj.get)
  }

  /**
   *
   * @return mutable book list
   */
  def findAll = List {
    val booksObjs = MongoFactory.bookCollection.find
    //mongo cursor to scala mutable list (Book)
    val booksList = scala.collection.mutable.MutableList[Book]()
    for(b <- booksObjs) {
      booksList += convertDbBookToBook(b)
    }
    booksList
  }

  /**
   *
   * @param q is mongo query
   * @param u is mongo query
   * @return true or false
   */

  def updateOne(q: MongoDBObject, u: MongoDBObject):Boolean = {
    val result = MongoFactory.bookCollection.update(q, u)

    if (result.getN == 1) true else false
  }

  /**
   *
   * @param query is mongo query
   * @return true or false
   */
  def removeOne(query: MongoDBObject):Boolean = {
    val result = MongoFactory.bookCollection.remove(query)

    if (result.getN == 1) true else false
  }
}


