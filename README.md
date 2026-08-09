# EASJavaJSFTemplate

Enterprise Application Stack JSF Template (EASJavaJSFTemplate) is a Java 21 web application built on Spring Boot 4.1.0, JSF/PrimeFaces via JoinFaces, and Maven. The project is packaged as a JAR and uses Spring Security, Spring Data JPA, Quartz, and CAS-based authentication.

## Current project facts

- Java 21 and Spring Boot 4.1.0
- JoinFaces 6.1.0 with PrimeFaces integration
- HSUCommon 5.1.0 and EASSCore 4.1.0 as internal dependencies
- Database profiles: `tomcat-db2`, `tomcat-mysql`, `tomcat-oracle`, `tomcat-postgresql`, and `tomcat-sqlserver`
- The default Maven profile is `tomcat-mysql`
- The build includes native2ascii resource conversion, AspectJ weaving, and profile-based property filtering
- Runtime configuration uses SSL on port `10443` and CAS-based SSO settings

## Repository structure

- `pom.xml` — Maven build, dependency versions, profiles, and plugin configuration
- `src/main/java` — application source code
- `src/main/resources` — runtime configuration, security policies, and web resources
- `src/main/native2ascii` — localization resource sources for native2ascii processing
- `src/main/resources/META-INF/resources` — JSF/PrimeFaces web assets
- `src/test/java` — test support code and tests
- `DB Schema SQL/` — database schema scripts for supported databases
- `DB Init Data SQL/` — database seed scripts and initial data

## Build and run

### Typical commands

```bash
mvn clean package
mvn -DskipTests package
mvn test
```

### Notes

- The current `pom.xml` configures the Surefire plugin with `skip=true`, so a plain `mvn test` run may not execute tests unless that configuration is changed.
- The build also runs native2ascii conversion and AspectJ weaving, so full builds can take longer than a basic Java project.
- Application settings are profile-driven and use Maven resource filtering for values such as the application root, SSO URL, and system URL.

## Authentication and seed data

- The repository includes SQL files under `DB Init Data SQL/` that create initial portal data and an administrative account.
- The seed scripts in this repository do not include a documented default password, so initial credentials depend on your environment and initialization steps.
- The default runtime properties are configured for CAS-based authentication.

## Important notes

- Edit localization sources under `src/main/native2ascii` rather than generated resources under `src/main/resources`.
- Avoid editing generated files under `target/`.
- Preserve the existing Spring Boot / JSF / PrimeFaces patterns unless a change clearly requires otherwise.

## Planning artifacts

- No root-level planning artifacts such as `spec.md` or `plan.md` are currently present in the repository root.