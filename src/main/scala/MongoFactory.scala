package com.ilim.mongodbtest
/**
 * Created by ilimturan on 23/11/14.
 */
import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.Imports._

object MongoFactory{

  private val SERVER = "localhost"
  private val PORT = 27017
  private val DATABASE = "scala_test"

  val connection = MongoConnection(SERVER)
  val bookCollection = connection(DATABASE)("books")
  // others collection here
}
