# Implementation Plan: ContactHub (Scala 3 Backend)

**Branch**: `001-contacthub` | **Date**: 2026-02-15 | **Spec**: /specs/001-contacthub/spec.md
**Input**: Feature specification from `/specs/001-contacthub/spec.md`

**Note**: This plan covers a new Scala 3 backend implementation in tagless final style with property-based tests for comparison against the existing Java/Spring Boot implementation.

## Summary

Reimplement the ContactHub backend using Scala 3 with tagless final pattern and property-based testing. This new implementation will serve as a comparison point to evaluate the pros and cons of Scala 3 functional programming approach versus the existing Java 25/Spring Boot implementation.

## Technical Context

**Language/Version**: Scala 3.5
**Primary Dependencies**: 
- Cats Effect (IO monad for tagless final)
- Doobie (pure functional JDBC)
- Http4s (typeful HTTP server)
- Circe (JSON serialization)
- ScalaCheck (property-based testing)
- ScalaTest + Cats Effect Testing (testing)
**Storage**: PostgreSQL 15 (existing)
**Testing**: ScalaTest, ScalaCheck, cats-effect-testing
**Target Platform**: Linux server
**Project Type**: Backend API (new project for comparison)
**Performance Goals**: API response times under 200ms p99 (matching existing)
**Constraints**: Pure functional programming, tagless final pattern, type-safe error handling
**Scale/Scope**: Single backend service, ~10k contacts expected

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

- [x] All code must meet quality standards (Code Quality Standards principle)
- [x] All features must include comprehensive testing (Testing Standards principle)
- [x] All user-facing components must follow design consistency guidelines (User Experience Consistency principle)
- [x] All features must meet defined performance benchmarks (Performance Requirements principle)

**Constitution Compliance**: This Scala 3 implementation will use:
- Scala 3's strict type system and enums for code quality
- Property-based testing (ScalaCheck) for comprehensive test coverage
- Tagless final pattern for pure functional, testable code
- Cats Effect for async/concurrent performance

## Project Structure

### Documentation (this feature)

```text
specs/[###-feature]/
├── plan.md              # This file (/speckit.plan command output)
├── research.md          # Phase 0 output (/speckit.plan command)
├── data-model.md        # Phase 1 output (/speckit.plan command)
├── quickstart.md        # Phase 1 output (/speckit.plan command)
├── contracts/           # Phase 1 output (/speckit.plan command)
└── tasks.md             # Phase 2 output (/speckit.tasks command - NOT created by /speckit.plan)
```

### Source Code (repository root)

```text
# Scala 3 Backend (new project for comparison)
src/
├── main/
│   └── scala/
│       └── com/
│           └── contacthub/
│               ├── domain/           # Domain models and algebras
│               ├── service/          # Business logic (tagless final)
│               ├── repository/       # Data access layer
│               ├── api/              # HTTP routes
│               └── config/           # Configuration
└── test/
    └── scala/
        └── com/
            └── contacthub/
                ├── unit/             # Unit tests
                ├── property/         # Property-based tests (ScalaCheck)
                └── integration/     # Integration tests

# Existing Java/Spring Boot backend (unchanged)
backend/
├── src/
│   └── [existing structure]
```

## Complexity Tracking

> **Fill ONLY if Constitution Check has violations that must be justified**

No violations. The Scala 3 tagless final implementation maintains all Constitution principles:
- Code quality through Scala 3's type system and cats-effect
- Testing through property-based testing (ScalaCheck) + unit tests
- Backend-only (no user-facing components, so UX principles apply to existing frontend)
- Performance through Cats Effect's async runtime
