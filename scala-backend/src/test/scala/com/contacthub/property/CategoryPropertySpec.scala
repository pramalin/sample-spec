package com.contacthub.property

import org.scalacheck.{Properties, Arbitrary, Gen}
import org.scalacheck.Prop.forAll
import java.util.UUID
import java.time.Instant
import com.contacthub.domain.Category

object CategoryPropertySpec extends Properties("Category"):
  private val nonEmptyString = for
    len <- Gen.choose(1, 50)
    chars <- Gen.listOfN(len, Gen.alphaChar)
  yield chars.mkString

  private val validHexColor = for
    prefix <- Gen.const("#")
    r <- Gen.hexChar
    g <- Gen.hexChar
    b <- Gen.hexChar
    rr <- Gen.hexChar
    gg <- Gen.hexChar
    bb <- Gen.hexChar
  yield s"$prefix$r$g$b$rr$gg$bb"

  private val timestamp = for
    millis <- Gen.choose(0L, System.currentTimeMillis())
  yield Instant.ofEpochMilli(millis)

  private val categoryGen = for
    id <- Gen.uuid
    name <- nonEmptyString
    color <- Gen.option(validHexColor)
    createdAt <- timestamp
  yield Category(id, name, color, createdAt)

  given Arbitrary[Category] = Arbitrary(categoryGen)

  property("name must not be empty") = forAll { (category: Category) =>
    category.name.nonEmpty
  }

  property("color must be valid hex format when present") = forAll { (category: Category) =>
    category.color.forall(c => c.startsWith("#") && c.length == 7)
  }

  property("color can be None") = forAll { (category: Category) =>
    category.color.isEmpty || category.color.get.startsWith("#")
  }
