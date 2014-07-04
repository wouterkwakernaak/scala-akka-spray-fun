import akka.actor.Props
import org.scalatest.{Matchers, FlatSpec}
import spray.testkit._

class TwitterServiceSpec extends FlatSpec with Matchers with ScalatestRouteTest with Routes {

  def actorRefFactory = system
  val tweet1 = Tweet("Wouter", "Test tweet 1")
  val tweet2 = Tweet("Wouter", "Test tweet 2")
  twitterActor ! tweet1
  twitterActor ! tweet2

  "The Twitter service" should "return tweets after a POST request" in {
    Post("/tweets") ~> tweetsRoute ~> check {
      responseAs[String] should include ("tweet")
    }
  }

}
