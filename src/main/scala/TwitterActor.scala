import akka.actor._

class TwitterActor extends Actor {

  var tweets: List[Tweet] = List()

  override def receive = {
    case tweet:Tweet => tweets = tweet :: tweets; sender ! tweets
    case _ => sender ! tweets
  }

}