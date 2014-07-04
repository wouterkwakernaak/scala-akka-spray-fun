import org.scalatest.{Matchers, FlatSpec}
import spray.testkit._

class TwitterServiceSpec extends FlatSpec with Matchers with ScalatestRouteTest with Routes {

  def actorRefFactory = system

  "The Twitter service" should "return tweets after a GET request" in {
      Get("/tweets") ~> tweetsRoute ~> check {
        responseAs[String] should include ("tweet")
      }
    }

  it should "return all tweets including the posted one after a POST request" in {
    Post("/tweets") ~> tweetsRoute ~> check {
      responseAs[String] should include ("hey")
    }
  }

}
