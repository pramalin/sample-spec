package com.contacthub.config

import cats.effect.IO
import org.http4s.server.Server
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.defaults
import com.comcast.ip4s.*

case class ServerConfig(
  host: String,
  port: Int
)

object ServerConfig:
  def default: ServerConfig =
    ServerConfig(
      host = sys.env.getOrElse("HTTP_HOST", "0.0.0.0"),
      port = sys.env.getOrElse("HTTP_PORT", "8081").toInt
    )
