package me

import com.github.jknack.handlebars.Handlebars
import io.undertow.server.{HttpServerExchange, HttpHandler}
import io.undertow.server.handlers.resource.{ClassPathResourceManager, ResourceHandler}
import io.undertow.{Handlers, Undertow}

import scala.beans.BeanProperty

class HttpServer(port: Int = 7070) {
  val server = Undertow.builder()
    .addHttpListener(port, "0.0.0.0")
    .setHandler(
      Handlers.path()
        .addPrefixPath("/public",
          new ResourceHandler(new ClassPathResourceManager(Thread.currentThread().getContextClassLoader, "public"))
        )
        .addPrefixPath("/foo", new SomeHandler())
    )
    .build()

  def start() = server.start()
  def stop() = server.stop()
}

case class SomeResponse(@BeanProperty var title: String, @BeanProperty var headline: String)

class SomeHandler() extends HttpHandler {
  override def handleRequest(exchange: HttpServerExchange) = {
    val handlebars = new Handlebars()
    val template = handlebars.compile("template")
    val resp = SomeResponse("Some nice title", "It works!")
    val out = template.apply(resp)
    exchange.getResponseSender.send(out)
  }
}

object Start extends App {
  val server = new HttpServer()
  server.start()
}
