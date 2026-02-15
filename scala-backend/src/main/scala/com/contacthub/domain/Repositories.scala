package com.contacthub.domain

import java.util.UUID

trait ContactRepository[F[_]]:
  def findAll: F[List[Contact]]
  def findById(id: UUID): F[Option[Contact]]
  def search(query: String): F[List[Contact]]
  def create(contact: Contact): F[Contact]
  def update(contact: Contact): F[Contact]
  def delete(id: UUID): F[Unit]
  def findByCategoryId(categoryId: UUID): F[List[Contact]]
  def findByTagId(tagId: UUID): F[List[Contact]]

trait CategoryRepository[F[_]]:
  def findAll: F[List[Category]]
  def findById(id: UUID): F[Option[Category]]
  def create(category: Category): F[Category]
  def update(category: Category): F[Category]
  def delete(id: UUID): F[Unit]

trait TagRepository[F[_]]:
  def findAll: F[List[Tag]]
  def findById(id: UUID): F[Option[Tag]]
  def create(tag: Tag): F[Tag]
  def update(tag: Tag): F[Tag]
  def delete(id: UUID): F[Unit]
