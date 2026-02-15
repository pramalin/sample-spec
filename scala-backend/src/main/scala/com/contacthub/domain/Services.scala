package com.contacthub.domain

import java.util.UUID

trait ContactService[F[_]]:
  def getAllContacts: F[List[Contact]]
  def getContactById(id: UUID): F[Option[Contact]]
  def searchContacts(query: String): F[List[Contact]]
  def createContact(name: String, phone: Option[String], email: Option[String], notes: Option[String]): F[Contact]
  def updateContact(id: UUID, name: String, phone: Option[String], email: Option[String], notes: Option[String]): F[Option[Contact]]
  def deleteContact(id: UUID): F[Unit]
  def getContactsByCategory(categoryId: UUID): F[List[Contact]]
  def getContactsByTag(tagId: UUID): F[List[Contact]]

trait CategoryService[F[_]]:
  def getAllCategories: F[List[Category]]
  def getCategoryById(id: UUID): F[Option[Category]]
  def createCategory(name: String, color: Option[String]): F[Category]
  def updateCategory(id: UUID, name: String, color: Option[String]): F[Option[Category]]
  def deleteCategory(id: UUID): F[Unit]

trait TagService[F[_]]:
  def getAllTags: F[List[Tag]]
  def getTagById(id: UUID): F[Option[Tag]]
  def createTag(name: String): F[Tag]
  def updateTag(id: UUID, name: String): F[Option[Tag]]
  def deleteTag(id: UUID): F[Unit]
