# AI in Programming Course Repo

Course workspace for 5-day training.

## Structure
- backend/ - Spring Boot starter app (seeded from Silky master)
- frontend/ - UI app for course demo
- exercises/ - isolated exercise code samples
- prompts/ - copy/paste prompts used during sessions
- materials/ - scripts, handouts, references
- materials/scripts/ - helper scripts for setup/demo

## Run local package
- Requirements: Docker Desktop running and Java 21 available in `PATH`
- Build backend jar: `build-ai-course.cmd`
- Start app + PostgreSQL: `run-ai-course.cmd`
- Stop app + PostgreSQL: `stop-ai-course.cmd`
- App URL: `http://localhost:8080`
