import akka.actor._
import akka.io.IO
import spray.can.Http

object Main extends App {

  implicit val system = ActorSystem("TwitterActorSystem")
  val twitterService = system.actorOf((Props[TwitterServiceActor]))
  IO(Http) ! Http.Bind(twitterService, interface = "localhost", port = 8080)

}
