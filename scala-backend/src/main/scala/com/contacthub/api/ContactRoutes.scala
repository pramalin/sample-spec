package com.contacthub.api

import cats.effect.IO
import org.http4s.{HttpRoutes, HttpApp}
import org.http4s.dsl.io.*
import org.http4s.circe.*

class ContactRoutes[F[_]]:
  def routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "health" => Ok("{\"status\":\"ok\"}")
  }

  def httpApp: HttpApp[IO] = routes.orNotFound
