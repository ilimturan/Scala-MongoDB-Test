package com.ilim.db

/**
 * Created by ilimturan on 23/11/14.
 */
import com.mongodb.casbah.MongoConnection

object MongoFactory{

  private val SERVER = "localhost"
  private val PORT = 27017
  private val DATABASE = "scala_test"

  val connection = MongoConnection(SERVER)
  val bookCollection = connection(DATABASE)("books")
  // others collection here
}
