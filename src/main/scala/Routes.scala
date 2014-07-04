import akka.actor._
import akka.pattern.AskSupport
import akka.util.Timeout
import scala.concurrent.duration._
import spray.routing._
import spray.httpx.marshalling._
import spray.http._

trait Routes extends HttpService with AskSupport {

  val twitterActor = actorRefFactory.actorOf((Props[TwitterActor]))
  implicit val timeout = Timeout(1 seconds)
  implicit val executionContext = actorRefFactory.dispatcher

  val tweetsRoute: Route =
    path("tweets") {
      get {
        onSuccess((twitterActor ? None).mapTo[List[Tweet]]) {
          tweets => complete(tweets)
        }
      } ~
        post {
          val tweet = Tweet("Wouter", "Dummy tweet")
          onSuccess((twitterActor ? tweet).mapTo[List[Tweet]]) {
            tweets => complete(tweets)
          }
        }
    }

  implicit val TweetsMarshaller = Marshaller.of[List[Tweet]](ContentTypes.`text/plain`) { (tweets, contentType, ctx) =>
    val string = tweets.foldLeft("")((cum, tweet) => cum + tweet.author + ": " + tweet.text + ", ")
    ctx.marshalTo(HttpEntity(contentType, string))
  }

}
