package akkademy.server

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._

class AkkademyDbSpec extends FunSpecLike with Matchers {

  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)

  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value into map") {
        val actorRef: TestActorRef[AkkademyDb] = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key", "value")

        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
      }
    }
    describe("given two SetRequest") {
      it("should place two key/value into map") {
        val actorRef: TestActorRef[AkkademyDb] = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key", "value")
        actorRef ! SetRequest("name", "vito")

        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
        akkademyDb.map.get("name") should equal(Some("vito"))
      }
    }
  }
}
