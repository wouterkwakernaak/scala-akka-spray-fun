import spray.routing._

class TwitterServiceActor extends HttpServiceActor {

  def receive = runRoute {
    path("tweets") {
      get {
        complete("test tweet")
      }
    }
  }

}
