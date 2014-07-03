import akka.actor.ActorSystem
import akka.testkit._
import org.scalatest._

class TwitterSpec extends FlatSpec with Matchers {

  implicit val system = ActorSystem("TestTwitterActorSystem")

  "Twitter" should "have n tweets after receiving n tweets" in {
    val twitterActor = TestActorRef[TwitterActor].underlyingActor
    twitterActor.receive(Tweet("Wouter", "My first tweet"))
    twitterActor.receive(Tweet("Wouter", "My second tweet"))
    twitterActor.tweets.size should be (2)
  }

}