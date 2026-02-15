package com.contacthub.domain

import java.util.UUID
import java.time.Instant

case class Contact(
  id: UUID,
  name: String,
  phone: Option[String],
  email: Option[String],
  notes: Option[String],
  categoryIds: List[UUID],
  tagIds: List[UUID],
  createdAt: Instant,
  updatedAt: Instant,
  deletedAt: Option[Instant]
)

case class Category(
  id: UUID,
  name: String,
  color: Option[String],
  createdAt: Instant
)

case class Tag(
  id: UUID,
  name: String,
  createdAt: Instant
)
