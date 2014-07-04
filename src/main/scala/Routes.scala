import spray.routing._

trait Routes extends HttpService {

  val tweetsRoute: Route =
    path("tweets") {
      get {
        complete("test tweet")
      } ~
        post {
          complete("hey")
        }
    }

}
