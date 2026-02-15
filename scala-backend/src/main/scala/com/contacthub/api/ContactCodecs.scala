package com.contacthub.api

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.*
import com.contacthub.domain.{Contact, Category, Tag}
import java.util.UUID
import java.time.Instant

given Encoder[Contact] = deriveEncoder
given Decoder[Contact] = deriveDecoder

given Encoder[Category] = deriveEncoder
given Decoder[Category] = deriveDecoder

given Encoder[Tag] = deriveEncoder
given Decoder[Tag] = deriveDecoder

given Encoder[UUID] = Encoder.encodeString.contramap(_.toString)
given Decoder[UUID] = Decoder.decodeString.map(UUID.fromString)

given Encoder[Instant] = Encoder.encodeString.contramap(_.toString)
given Decoder[Instant] = Decoder.decodeString.map(Instant.parse)

case class CreateContactRequest(
  name: String,
  phone: Option[String] = None,
  email: Option[String] = None,
  notes: Option[String] = None,
  categoryIds: List[UUID] = List.empty,
  tagIds: List[UUID] = List.empty
)

case class UpdateContactRequest(
  name: Option[String] = None,
  phone: Option[String] = None,
  email: Option[String] = None,
  notes: Option[String] = None,
  categoryIds: Option[List[UUID]] = None,
  tagIds: Option[List[UUID]] = None
)

case class CreateCategoryRequest(
  name: String,
  color: Option[String] = None
)

case class UpdateCategoryRequest(
  name: Option[String] = None,
  color: Option[String] = None
)

case class CreateTagRequest(
  name: String
)

case class UpdateTagRequest(
  name: Option[String] = None
)

given Encoder[CreateContactRequest] = deriveEncoder
given Decoder[CreateContactRequest] = deriveDecoder

given Encoder[UpdateContactRequest] = deriveEncoder
given Decoder[UpdateContactRequest] = deriveDecoder

given Encoder[CreateCategoryRequest] = deriveEncoder
given Decoder[CreateCategoryRequest] = deriveDecoder

given Encoder[UpdateCategoryRequest] = deriveEncoder
given Decoder[UpdateCategoryRequest] = deriveDecoder

given Encoder[CreateTagRequest] = deriveEncoder
given Decoder[CreateTagRequest] = deriveDecoder

given Encoder[UpdateTagRequest] = deriveEncoder
given Decoder[UpdateTagRequest] = deriveDecoder
