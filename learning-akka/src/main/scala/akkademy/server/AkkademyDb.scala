package akkademy.server

import akka.actor.Actor
import akka.event.Logging
import SetRequest

import scala.collection._

/**
  * Created by vitojeng on 9/23/16.
  */
class AkkademyDb extends Actor {

  val log = Logging(context.system, this)
  val map = new mutable.HashMap[String, String]

  override def receive: Receive = {
    case SetRequest(key, value) =>
      log.info("received SetRequest - key:{} value: {}", key, value)
      map.put(key, value)
    case o =>
      log.info("received unknow message: {}", o)
  }
}
