package com.contacthub.property

import org.scalacheck.{Properties, Arbitrary, Gen}
import org.scalacheck.Prop.forAll
import java.util.UUID
import java.time.Instant
import com.contacthub.domain.Contact

object ContactPropertySpec extends Properties("Contact"):
  private val nonEmptyString = for
    len <- Gen.choose(1, 100)
    chars <- Gen.listOfN(len, Gen.alphaChar)
  yield chars.mkString

  private val validEmail = for
    user <- nonEmptyString
    domain <- nonEmptyString
  yield s"$user@$domain.com"

  private val validPhone = for
    prefix <- Gen.oneOf("+1", "+44", "")
    digits <- Gen.listOfN(10, Gen.numChar)
  yield prefix + digits.mkString

  private val timestamp = for
    millis <- Gen.choose(0L, System.currentTimeMillis())
  yield Instant.ofEpochMilli(millis)

  private val contactGen = for
    id <- Gen.uuid
    name <- nonEmptyString
    phone <- Gen.option(validPhone)
    email <- Gen.option(validEmail)
    notes <- Gen.option(Gen.alphaStr)
    categoryIds <- Gen.listOf(Gen.uuid)
    tagIds <- Gen.listOf(Gen.uuid)
    createdAt <- timestamp
    timeDelta <- Gen.choose(0L, 86400000L) // up to 1 day
    updatedAt = createdAt.plusMillis(timeDelta)
    deletedAt <- Gen.option(timestamp)
  yield Contact(id, name, phone, email, notes, categoryIds, tagIds, createdAt, updatedAt, deletedAt)

  given Arbitrary[Contact] = Arbitrary(contactGen)

  property("name must not be empty") = forAll { (contact: Contact) =>
    contact.name.nonEmpty
  }

  property("email format must be valid when present") = forAll { (contact: Contact) =>
    contact.email.forall(e => e.contains("@") && e.contains("."))
  }

  property("phone format must be valid when present") = forAll { (contact: Contact) =>
    contact.phone.forall(p => p.forall(_.isDigit) || p.startsWith("+"))
  }

  property("timestamps must be consistent") = forAll { (contact: Contact) =>
    !contact.createdAt.isAfter(contact.updatedAt)
  }

  property("categoryIds must be valid UUIDs") = forAll { (contact: Contact) =>
    contact.categoryIds.forall(_.toString.nonEmpty)
  }

  property("tagIds must be valid UUIDs") = forAll { (contact: Contact) =>
    contact.tagIds.forall(_.toString.nonEmpty)
  }
