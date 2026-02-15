package com.contacthub

import cats.effect.{IO, IOApp}
import com.contacthub.config.{DatabaseConfig, ServerConfig}
import com.contacthub.api.ContactRoutes
import org.http4s.ember.server.EmberServerBuilder
import com.comcast.ip4s.{Host, Port}

object Main extends IOApp.Simple:
  val run: IO[Unit] =
    for
      dbConfig <- IO(DatabaseConfig.default)
      serverConfig <- IO(ServerConfig.default)
      routes = ContactRoutes()
      host <- IO(Host.fromString(serverConfig.host).get)
      port <- IO(Port.fromInt(serverConfig.port).get)
      server <- EmberServerBuilder
        .default[IO]
        .withHost(host)
        .withPort(port)
        .withHttpApp(routes.httpApp)
        .build
        .useForever
    yield ()
