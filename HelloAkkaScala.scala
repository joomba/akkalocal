import akka.actor.{ ActorRef, ActorSystem, Props, Actor, Inbox }
import scala.concurrent.duration._
import scala.io.Source


case object Greet
case object Send
case class Time(message: Long)



//Класс-отправитель сообщений
class Sender extends Actor {
    def receive = {
        case Send => {
           
        }
    }
}


//Класс-получатель сообщений
class Receiver extends Actor {
    def receive = {
        case _ => {
            val time = System.currentTimeMillis
            //println (message)
            sender ! Time(time)
        }
        
    }
}

object HelloAkkaScala extends App {
    
  // Создание акторной системы
  val system = ActorSystem("helloakka")
  
  //Создание актора Receiver
  val receiver = system.actorOf(Props[Receiver], "receiver")

  // Создание inbox
  val inbox = Inbox.create(system)
  
  
  
  //Засечка времени отправки
  val sendtime=System.currentTimeMillis
  println (sendtime)
  //Отправка короткого JSON-файла
  //Открытие файла
  val filename = "/Users/joomba/Downloads/diplom/short.json"
  val fileContents = Source.fromFile(filename).getLines.mkString
  
  inbox.send(receiver, fileContents)

  // Получение и вывод ответного сообщения со временем получения файла
  val Time(message1) = inbox.receive(1.seconds)
  println(s"Time: $message1")
  
  //Время передачи сообщения
  val timeOfSend = message1-sendtime
  println (s"Время передачи сообщения (303 байта) составило: $timeOfSend мс")
  
  
  
  
  //Отправка среднего файла DOCX (64 кб)
  //Засечка времени отправки
  val sendtime2=System.currentTimeMillis
  println (sendtime2)
  //Открытие файла
  val filename2 = "/Users/joomba/Downloads/diplom/middle.docx"
  val fileContents2 = Source.fromFile(filename2).getLines.mkString
  
  inbox.send(receiver, fileContents2)

  // Получение и вывод ответного сообщения со временем получения файла
  val Time(message2) = inbox.receive(1.seconds)
  println(s"Time: $message2")
  
  //Время передачи сообщения
  val timeOfSend2 = message2-sendtime2
  println (s"Время передачи сообщения (64 кб) составило: $timeOfSend2 мс")
  
  
  
  //Отправка большого файла JPEG (1.4 мб)
  //Засечка времени отправки
  val sendtime3=System.currentTimeMillis
  println (sendtime3)
  //Открытие файла
  val filename3 = "/Users/joomba/Downloads/diplom/big.jpeg"
  val fileContents3 = Source.fromFile(filename3).getLines.mkString
  
  inbox.send(receiver, fileContents3)

  // Получение и вывод ответного сообщения со временем получения файла
  val Time(message3) = inbox.receive(1.seconds)
  println(s"Time: $message3")
  
  //Время передачи сообщения
  val timeOfSend3 = message3-sendtime3
  println (s"Время передачи сообщения (1.4 мб) составило: $timeOfSend3 мс")
  
  
  
  
  
  
   //Отправка большого файла PDF (16.5 мб)
  //Засечка времени отправки
  val sendtime4=System.currentTimeMillis
  println (sendtime4)
  //Открытие файла
  val filename4 = "/Users/joomba/Downloads/diplom/verybig.pdf"
  val fileContents4 = Source.fromFile(filename4).getLines.mkString
  
  inbox.send(receiver, fileContents4)

  // Получение и вывод ответного сообщения со временем получения файла
  val Time(message4) = inbox.receive(1.seconds)
  println(s"Time: $message4")
  
  //Время передачи сообщения
  val timeOfSend4 = message4-sendtime4
  println (s"Время передачи сообщения (16.5 мб) составило: $timeOfSend4 мс")
  
}

