# Data Model: ContactHub

## Entities

### Contact
Represents an individual person with contact information.

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | UUID | Primary Key, Auto-generated | Unique identifier |
| name | String | Not Null, Max 255 chars | Full name of contact |
| phone | String | Optional, Max 50 chars | Phone number |
| email | String | Optional, Max 255 chars, Valid email format | Email address |
| notes | Text | Optional | Additional notes |
| createdAt | Timestamp | Not Null, Auto-generated | Creation timestamp |
| updatedAt | Timestamp | Not Null, Auto-generated | Last update timestamp |

### Category
A user-defined grouping for organizing contacts.

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | UUID | Primary Key, Auto-generated | Unique identifier |
| name | String | Not Null, Max 100 chars | Category name |
| color | String | Optional, Max 20 chars | Hex color code |
| createdAt | Timestamp | Not Null, Auto-generated | Creation timestamp |

### Tag
A flexible, user-defined label for contacts.

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | UUID | Primary Key, Auto-generated | Unique identifier |
| name | String | Not Null, Max 50 chars | Tag name |
| createdAt | Timestamp | Not Null, Auto-generated | Creation timestamp |

## Relationships

### Contact-Category (Many-to-Many)
- A contact can belong to multiple categories
- A category can have multiple contacts
- Implemented via join table `contact_categories`

### Contact-Tag (Many-to-Many)
- A contact can have multiple tags
- A tag can be applied to multiple contacts
- Implemented via join table `contact_tags`

## Validation Rules

### Contact
- Name is required and must be 1-255 characters
- Phone must match regex pattern for valid phone numbers
- Email must be valid format if provided
- Notes can be any text up to 65535 characters

### Category
- Name is required and must be 1-100 characters
- Color must be valid hex format (#RRGGBB) if provided

### Tag
- Name is required and must be 1-50 characters

## State Transitions

### Contact Lifecycle
```
Created -> Active -> Deleted
```
- Contacts are created in Active state
- Deletion moves contact to Deleted state (soft delete)
- Can be restored from Deleted state

## Database Schema (SQL)

```sql
-- Contacts table
CREATE TABLE contacts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(50),
    email VARCHAR(255),
    notes TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Categories table
CREATE TABLE categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    color VARCHAR(20),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tags table
CREATE TABLE tags (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Contact-Category join table
CREATE TABLE contact_categories (
    contact_id UUID REFERENCES contacts(id) ON DELETE CASCADE,
    category_id UUID REFERENCES categories(id) ON DELETE CASCADE,
    PRIMARY KEY (contact_id, category_id)
);

-- Contact-Tag join table
CREATE TABLE contact_tags (
    contact_id UUID REFERENCES contacts(id) ON DELETE CASCADE,
    tag_id UUID REFERENCES tags(id) ON DELETE CASCADE,
    PRIMARY KEY (contact_id, tag_id)
);

-- Indexes for performance
CREATE INDEX idx_contacts_name ON contacts(name);
CREATE INDEX idx_contacts_email ON contacts(email);
CREATE INDEX idx_contacts_phone ON contacts(phone);
CREATE INDEX idx_contacts_deleted_at ON contacts(deleted_at);
CREATE INDEX idx_categories_name ON categories(name);
CREATE INDEX idx_tags_name ON tags(name);
```

## Scala 3 Domain Models

The Scala 3 implementation uses case classes and enums for domain modeling:

```scala
// Domain models in src/main/scala/com/contacthub/domain/

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
```

### Tagless Final Algebras (Interfaces)

```scala
// Repository algebras (type classes)

trait ContactRepository[F[_]]:
  def findAll: F[List[Contact]]
  def findById(id: UUID): F[Option[Contact]]
  def search(query: String): F[List[Contact]]
  def create(contact: Contact): F[Contact]
  def update(contact: Contact): F[Contact]
  def delete(id: UUID): F[Unit]

trait CategoryRepository[F[_]]:
  def findAll: F[List[Category]]
  def findById(id: UUID): F[Option[Category]]
  def create(category: Category): F[Category]
  def delete(id: UUID): F[Unit]

trait TagRepository[F[_]]:
  def findAll: F[List[Tag]]
  def findById(id: UUID): F[Option[Tag]]
  def create(tag: Tag): F[Tag]
  def delete(id: UUID): F[Unit]
```
