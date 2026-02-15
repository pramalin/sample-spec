# Tasks: ContactHub

**Input**: Design documents from `/specs/001-contacthub/`
**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Tests**: Not explicitly requested in feature specification - tests will be added as needed

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2, US3)
- Include exact file paths in descriptions

---

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Project initialization and Docker infrastructure

- [X] T001 Create project directory structure (backend/, frontend/, db/)
- [X] T002 Initialize Spring Boot 4 project in backend/ with Java 25
- [X] T003 Initialize Vite project in frontend/ with Vanilla JS
- [X] T004 [P] Create docker-compose.yml with all three tiers
- [X] T005 [P] Create Dockerfile for backend in backend/Dockerfile
- [X] T006 [P] Create Dockerfile for frontend in frontend/Dockerfile
- [X] T007 Create PostgreSQL init script in db/init.sql
- [X] T008 Configure connection between tiers in docker-compose.yml

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Core infrastructure that MUST be complete before ANY user story can be implemented

- [X] T009 Setup PostgreSQL schema (contacts, categories, tags tables) in db/init.sql
- [X] T010 [P] Configure Spring Boot database connection in backend/src/main/resources/application.yml
- [X] T011 [P] Setup JPA/Hibernate entities for Contact, Category, Tag
- [X] T012 Create base REST controller structure in backend/src/main/java/com/contacthub/api/
- [X] T013 Configure CORS for frontend-backend communication
- [X] T014 Setup error handling and global exception handler
- [X] T015 Configure logging infrastructure

**Checkpoint**: Foundation ready - user story implementation can now begin

---

## Phase 3: User Story 1 - Add and Manage Contacts (Priority: P1) 🎯 MVP

**Goal**: Users can add, view, edit, and delete contacts

**Independent Test**: Add a contact with name, phone, and email and verify it appears in the contact list

### Implementation for User Story 1

- [X] T016 [P] [US1] Create Contact record ADT in backend/src/main/java/com/contacthub/domain/Contact.java
- [X] T017 [P] [US1] Create ContactRepository in backend/src/main/java/com/contacthub/repository/ContactRepository.java
- [X] T018 [US1] Implement ContactService in backend/src/main/java/com/contacthub/service/ContactService.java
- [X] T019 [US1] Implement POST /api/contacts endpoint in backend/src/main/java/com/contacthub/api/ContactController.java
- [X] T020 [US1] Implement GET /api/contacts endpoint in backend/src/main/java/com/contacthub/api/ContactController.java
- [X] T021 [US1] Implement GET /api/contacts/{id} endpoint in backend/src/main/java/com/contacthub/api/ContactController.java
- [X] T022 [US1] Implement PUT /api/contacts/{id} endpoint in backend/src/main/java/com/contacthub/api/ContactController.java
- [X] T023 [US1] Implement DELETE /api/contacts/{id} endpoint in backend/src/main/java/com/contacthub/api/ContactController.java
- [X] T024 [P] [US1] Create contact list HTML page in frontend/src/pages/contacts.html
- [X] T025 [P] [US1] Create contact form HTML component in frontend/src/components/contact-form.html
- [X] T026 [US1] Create contacts API service in frontend/src/services/contacts.js
- [X] T027 [US1] Implement vanilla JS for contact list in frontend/src/components/contact-list.js
- [X] T028 [US1] Implement vanilla JS for contact form in frontend/src/components/contact-form.js
- [X] T029 [US1] Add CSS styling for contacts in frontend/src/styles/contacts.css

**Checkpoint**: User Story 1 should be fully functional and testable independently

---

## Phase 4: User Story 2 - Search and Filter Contacts (Priority: P2)

**Goal**: Users can search and filter contacts by name, phone, email

**Independent Test**: Search for a contact by name and verify the correct contact is returned

### Implementation for User Story 2

- [X] T030 [P] [US2] Add search query method to ContactRepository in backend/src/main/java/com/contacthub/repository/ContactRepository.java
- [X] T031 [US2] Extend ContactService with search functionality in backend/src/main/java/com/contacthub/service/ContactService.java
- [X] T032 [US2] Implement GET /api/contacts/search endpoint in backend/src/main/java/com/contacthub/api/ContactController.java
- [X] T033 [P] [US2] Add search input HTML in frontend/src/components/search-bar.html
- [X] T034 [US2] Implement search vanilla JS in frontend/src/components/search-bar.js
- [X] T035 [US2] Add category and tag filter support to GET /api/contacts in backend/src/main/java/com/contacthub/api/ContactController.java

**Checkpoint**: User Stories 1 AND 2 should both work independently

---

## Phase 5: User Story 3 - Organize Contacts with Categories and Tags (Priority: P3)

**Goal**: Users can create categories and tags, assign them to contacts

**Independent Test**: Create categories and tags, assign to contacts, verify they appear in correct groups

### Implementation for User Story 3

- [X] T036 [P] [US3] Create Category record ADT in backend/src/main/java/com/contacthub/domain/Category.java
- [X] T037 [P] [US3] Create Tag record ADT in backend/src/main/java/com/contacthub/domain/Tag.java
- [X] T038 [P] [US3] Create CategoryRepository in backend/src/main/java/com/contacthub/repository/CategoryRepository.java
- [X] T039 [P] [US3] Create TagRepository in backend/src/main/java/com/contacthub/repository/TagRepository.java
- [X] T040 [US3] Implement CategoryService in backend/src/main/java/com/contacthub/service/CategoryService.java
- [X] T041 [US3] Implement TagService in backend/src/main/java/com/contacthub/service/TagService.java
- [X] T042 [US3] Implement Category CRUD endpoints in backend/src/main/java/com/contacthub/api/CategoryController.java
- [X] T043 [US3] Implement Tag CRUD endpoints in backend/src/main/java/com/contacthub/api/TagController.java
- [X] T044 [P] [US3] Create category management HTML in frontend/src/pages/categories.html
- [X] T045 [P] [US3] Create tag management HTML in frontend/src/pages/tags.html
- [X] T046 [US3] Create categories API service in frontend/src/services/categories.js
- [X] T047 [US3] Create tags API service in frontend/src/services/tags.js
- [X] T048 [US3] Implement category/tag selector in contact form in frontend/src/components/contact-form.js

**Checkpoint**: All user stories should now be independently functional

---

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Improvements that affect multiple user stories

- [ ] T049 [P] Add input validation for phone number format
- [ ] T050 [P] Add input validation for email format
- [ ] T051 Add loading states and error messages to UI
- [ ] T052 Optimize database queries with proper indexes
- [ ] T053 Add responsive CSS for mobile devices
- [ ] T054 Run docker-compose validation (quickstart.md)
- [ ] T055 Verify all endpoints work with curl tests

---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: No dependencies - can start immediately
- **Foundational (Phase 2)**: Depends on Setup completion - BLOCKS all user stories
- **User Stories (Phase 3+)**: All depend on Foundational phase completion
  - User stories can then proceed in parallel (if staffed)
  - Or sequentially in priority order (P1 → P2 → P3)
- **Polish (Final Phase)**: Depends on all desired user stories being complete

### User Story Dependencies

- **User Story 1 (P1)**: Can start after Foundational (Phase 2) - No dependencies on other stories
- **User Story 2 (P2)**: Can start after Foundational (Phase 2) - May integrate with US1 but should be independently testable
- **User Story 3 (P3)**: Can start after Foundational (Phase 2) - May integrate with US1/US2 but should be independently testable

### Within Each User Story

- Models before services
- Services before endpoints
- Core implementation before integration
- Story complete before moving to next priority

### Parallel Opportunities

- All Setup tasks marked [P] can run in parallel
- All Foundational tasks marked [P] can run in parallel (within Phase 2)
- Once Foundational phase completes, all user stories can start in parallel (if team capacity allows)
- Models within a story marked [P] can run in parallel
- Different user stories can be worked on in parallel by different team members

---

## Parallel Example: User Story 1

```bash
# Launch all backend models for User Story 1 together:
Task: "Create Contact record ADT in backend/src/main/java/com/contacthub/domain/Contact.java"
Task: "Create ContactRepository in backend/src/main/java/com/contacthub/repository/ContactRepository.java"

# Launch all frontend components for User Story 1 together:
Task: "Create contact list HTML page in frontend/src/pages/contacts.html"
Task: "Create contact form HTML component in frontend/src/components/contact-form.html"
```

---

## Implementation Strategy

### MVP First (User Story 1 Only)

1. Complete Phase 1: Setup
2. Complete Phase 2: Foundational (CRITICAL - blocks all stories)
3. Complete Phase 3: User Story 1
4. **STOP and VALIDATE**: Test User Story 1 independently
5. Deploy/demo if ready

### Incremental Delivery

1. Complete Setup + Foundational → Foundation ready
2. Add User Story 1 → Test independently → Deploy/Demo (MVP!)
3. Add User Story 2 → Test independently → Deploy/Demo
4. Add User Story 3 → Test independently → Deploy/Demo
5. Each story adds value without breaking previous stories

### Parallel Team Strategy

With multiple developers:

1. Team completes Setup + Foundational together
2. Once Foundational is done:
   - Developer A: User Story 1
   - Developer B: User Story 2
   - Developer C: User Story 3
3. Stories complete and integrate independently

---

## Notes

- [P] tasks = different files, no dependencies
- [Story] label maps task to specific user story for traceability
- Each user story should be independently completable and testable
- Commit after each task or logical group
- Stop at any checkpoint to validate story independently
- Avoid: vague tasks, same file conflicts, cross-story dependencies that break independence

---

# Tasks: Scala 3 Tagless Final Backend (NEW Implementation)

**Input**: Design documents from `/specs/001-contacthub/` for Scala 3 backend
**Purpose**: New Scala 3 backend implementation with tagless final pattern and property-based tests

**Tests**: Property-based tests with ScalaCheck are REQUIRED per plan.md

**Organization**: Tasks are grouped by user story to enable independent implementation and testing

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2, US3)
- Include exact file paths in descriptions

---

## Phase S1: Scala Setup (Shared Infrastructure)

**Purpose**: Initialize Scala 3 project with sbt and dependencies

- [X] S001 Create Scala 3 project structure in src/main/scala/com/contacthub/
- [X] S002 Initialize sbt project with build.sbt in scala-backend/
- [X] S003 [P] Add Cats Effect, Doobie, Http4s, Circe, ScalaCheck dependencies to build.sbt
- [X] S004 [P] Create project directory structure (domain/, service/, repository/, api/, config/)
- [X] S005 Configure Scala 3 compiler options in build.sbt

---

## Phase S2: Scala Foundational (Blocking Prerequisites)

**Purpose**: Core infrastructure for Scala backend

- [X] S006 Create domain models (Contact, Category, Tag case classes) in src/main/scala/com/contacthub/domain/
- [X] S007 [P] Define repository algebras (traits) in src/main/scala/com/contacthub/domain/
- [X] S008 [P] Create service algebras in src/main/scala/com/contacthub/domain/
- [X] S009 Setup Doobie database connection in src/main/scala/com/contacthub/repository/
- [X] S010 Configure PostgreSQL connection in src/main/scala/com/contacthub/config/
- [X] S011 Create HTTP server with Http4s in src/main/scala/com/contacthub/api/
- [X] S012 Setup JSON serialization with Circe in src/main/scala/com/contacthub/api/
- [X] S013 Configure error handling and logging in scala-backend/

**Checkpoint**: Scala foundation ready - user story implementation can now begin

---

## Phase S3: User Story 1 - Add and Manage Contacts (Priority: P1) 🎯 MVP

**Goal**: Users can add, view, edit, and delete contacts via Scala backend

**Independent Test**: Add a contact with name, phone, and email via Scala API and verify it appears

### Tests for User Story 1 (REQUIRED - Property-Based)

- [X] S014 [P] [US1] Write property-based tests for Contact model in src/test/scala/com/contacthub/property/ContactPropertySpec.scala
- [X] S015 [P] [US1] Write unit tests for Contact validation in src/test/scala/com/contacthub/unit/ContactValidationSpec.scala

### Implementation for User Story 1

- [ ] S016 [P] [US1] Implement ContactRepository algebra in src/main/scala/com/contacthub/domain/ContactRepository.scala
- [ ] S017 [P] [US1] Implement DoobieContactRepository in src/main/scala/com/contacthub/repository/DoobieContactRepository.scala
- [ ] S018 [US1] Implement ContactService in src/main/scala/com/contacthub/service/ContactService.scala
- [ ] S019 [US1] Implement POST /api/contacts endpoint in src/main/scala/com/contacthub/api/ContactRoutes.scala
- [ ] S020 [US1] Implement GET /api/contacts endpoint in src/main/scala/com/contacthub/api/ContactRoutes.scala
- [ ] S021 [US1] Implement GET /api/contacts/{id} endpoint in src/main/scala/com/contacthub/api/ContactRoutes.scala
- [ ] S022 [US1] Implement PUT /api/contacts/{id} endpoint in src/main/scala/com/contacthub/api/ContactRoutes.scala
- [ ] S023 [US1] Implement DELETE /api/contacts/{id} endpoint in src/main/scala/com/contacthub/api/ContactRoutes.scala

**Checkpoint**: User Story 1 should be fully functional and testable independently

---

## Phase S4: User Story 2 - Search and Filter Contacts (Priority: P2)

**Goal**: Users can search and filter contacts via Scala backend

**Independent Test**: Search for a contact by name via Scala API and verify results

### Tests for User Story 2 (REQUIRED - Property-Based)

- [ ] S024 [P] [US2] Write property-based tests for search functionality in src/test/scala/com/contacthub/property/SearchPropertySpec.scala

### Implementation for User Story 2

- [ ] S025 [P] [US2] Add search method to ContactRepository in src/main/scala/com/contacthub/domain/ContactRepository.scala
- [ ] S026 [US2] Implement search in DoobieContactRepository in src/main/scala/com/contacthub/repository/DoobieContactRepository.scala
- [ ] S027 [US2] Extend ContactService with search in src/main/scala/com/contacthub/service/ContactService.scala
- [ ] S028 [US2] Implement GET /api/contacts/search endpoint in src/main/scala/com/contacthub/api/ContactRoutes.scala
- [ ] S029 [US2] Add category/tag filter support to GET /api/contacts in src/main/scala/com/contacthub/api/ContactRoutes.scala

**Checkpoint**: User Stories 1 AND 2 should both work independently

---

## Phase S5: User Story 3 - Organize Contacts with Categories and Tags (Priority: P3)

**Goal**: Users can create and manage categories and tags via Scala backend

**Independent Test**: Create categories and tags, assign to contacts, verify via API

### Tests for User Story 3 (REQUIRED - Property-Based)

- [X] S030 [P] [US3] Write property-based tests for Category model in src/test/scala/com/contacthub/property/CategoryPropertySpec.scala
- [X] S031 [P] [US3] Write property-based tests for Tag model in src/test/scala/com/contacthub/property/TagPropertySpec.scala

### Implementation for User Story 3

- [ ] S032 [P] [US3] Implement CategoryRepository algebra in src/main/scala/com/contacthub/domain/CategoryRepository.scala
- [ ] S033 [P] [US3] Implement TagRepository algebra in src/main/scala/com/contacthub/domain/TagRepository.scala
- [ ] S034 [P] [US3] Implement DoobieCategoryRepository in src/main/scala/com/contacthub/repository/DoobieCategoryRepository.scala
- [ ] S035 [P] [US3] Implement DoobieTagRepository in src/main/scala/com/contacthub/repository/DoobieTagRepository.scala
- [ ] S036 [US3] Implement CategoryService in src/main/scala/com/contacthub/service/CategoryService.scala
- [ ] S037 [US3] Implement TagService in src/main/scala/com/contacthub/service/TagService.scala
- [ ] S038 [US3] Implement Category CRUD endpoints in src/main/scala/com/contacthub/api/CategoryRoutes.scala
- [ ] S039 [US3] Implement Tag CRUD endpoints in src/main/scala/com/contacthub/api/TagRoutes.scala

**Checkpoint**: All user stories should now be independently functional

---

## Phase S6: Polish & Integration

**Purpose**: Complete integration between Scala and Java backends

- [ ] S040 Run Scala backend on port 8081 (separate from Java backend on 8080)
- [ ] S041 Verify both backends work with shared PostgreSQL database
- [ ] S042 Compare API response times between Java and Scala backends
- [ ] S043 Run all property-based tests and verify coverage
- [ ] S044 Update quickstart.md with Scala backend instructions

---

## Dependencies & Execution Order

### Phase Dependencies

- **Scala Setup (Phase S1)**: No dependencies - can start immediately
- **Scala Foundational (Phase S2)**: Depends on Setup - BLOCKS all user stories
- **User Stories (Phase S3+)**: All depend on Foundational completion

### Parallel Opportunities

- All Setup tasks marked [P] can run in parallel
- All Foundational tasks marked [P] can run in parallel
- Property-based tests can run independently

### Comparison Strategy

The Scala backend runs on port 8081 alongside Java backend on 8080:
- Both use same PostgreSQL database
- Compare functionality and performance
- Property-based tests provide additional coverage
