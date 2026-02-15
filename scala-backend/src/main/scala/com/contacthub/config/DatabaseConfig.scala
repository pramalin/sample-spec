package com.contacthub.config

import cats.effect.IO
import doobie.*
import doobie.implicits.*

case class DatabaseConfig(
  host: String,
  port: Int,
  database: String,
  user: String,
  password: String
)

object DatabaseConfig:
  def default: DatabaseConfig =
    DatabaseConfig(
      host = sys.env.getOrElse("DB_HOST", "localhost"),
      port = sys.env.getOrElse("DB_PORT", "5432").toInt,
      database = sys.env.getOrElse("DB_NAME", "contacthub"),
      user = sys.env.getOrElse("DB_USER", "contacthub"),
      password = sys.env.getOrElse("DB_PASSWORD", "contacthub")
    )

  def transactor(config: DatabaseConfig): Transactor.Aux[IO, Unit] =
    Transactor.fromDriverManager[IO](
      driver = "org.postgresql.Driver",
      url = s"jdbc:postgresql://${config.host}:${config.port}/${config.database}",
      user = config.user,
      pass = config.password
    )
