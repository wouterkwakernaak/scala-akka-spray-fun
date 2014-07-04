import spray.routing._

class TwitterServiceActor extends HttpServiceActor with Routes {

  def receive = runRoute(tweetsRoute)

}
