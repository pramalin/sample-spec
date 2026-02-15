package com.contacthub.property

import org.scalacheck.{Properties, Arbitrary, Gen}
import org.scalacheck.Prop.forAll
import java.util.UUID
import java.time.Instant
import com.contacthub.domain.Tag

object TagPropertySpec extends Properties("Tag"):
  private val nonEmptyString = for
    len <- Gen.choose(1, 30)
    chars <- Gen.listOfN(len, Gen.alphaChar)
  yield chars.mkString

  private val timestamp = for
    millis <- Gen.choose(0L, System.currentTimeMillis())
  yield Instant.ofEpochMilli(millis)

  private val tagGen = for
    id <- Gen.uuid
    name <- nonEmptyString
    createdAt <- timestamp
  yield Tag(id, name, createdAt)

  given Arbitrary[Tag] = Arbitrary(tagGen)

  property("name must not be empty") = forAll { (tag: Tag) =>
    tag.name.nonEmpty
  }

  property("name length is reasonable") = forAll { (tag: Tag) =>
    tag.name.length >= 1 && tag.name.length <= 50
  }

  property("id must be valid UUID") = forAll { (tag: Tag) =>
    tag.id.toString.nonEmpty
  }
