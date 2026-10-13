# Library Management System 

A BTech major-project starter built with Java 17, Spring Boot, Spring Security, Spring Data JPA, Thymeleaf, HTML/CSS and a database.

## Version  features
- Secure login with BCrypt password hashing
- Role-based authorization: Admin and Student role
- Admin dashboard with inventory statistics
- Book add, edit, delete and search (title, author, ISBN)
- Student/member records
- Issue and return workflow
- Automatic 14-day due date
- Fine calculation at ₹5/day after the due date
- Transaction history and responsive UI
- H2 database for easy local run
- Optional MySQL profile
- Sample data and demo accounts

## Requirements
- JDK 17 or newer
- IntelliJ IDEA (Community is fine)
- Internet connection for Maven to download dependencies

## Run in IntelliJ
1. Extract the ZIP.
2. Open the extracted `library-management-system-v2` folder in IntelliJ.
3. Allow IntelliJ to import the Maven project and download dependencies.
4. Open `src/main/java/com/example/library/LibraryManagementApplication.java`.
5. Run `LibraryManagementApplication`.
6. Visit http://localhost:8080 and sign in.

## Demo credentials
- Admin: `admin` / `Admin@123`
- Student role: `student` / `Student@123`

Important: the Student account has role-based access but this version's operational pages are primarily admin-facing. Do not use demo passwords in a public deployment; change them before any real use.

## Default database
By default, the app uses a file-based H2 database. It creates `./data/librarydb` automatically and persists data between restarts. H2 console is at http://localhost:8080/h2-console with JDBC URL `jdbc:h2:file:./data/librarydb`, username `sa`, and blank password.

## Use MySQL instead
1. Install MySQL and create a database named `library_management` (or let the configured URL create it if your MySQL user has permission).
2. Set environment variables `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`, or edit `src/main/resources/application-mysql.properties`.
3. Run the app with the `mysql` profile, e.g. from a terminal:
   `mvn spring-boot:run -Dspring-boot.run.profiles=mysql`
4. Keep database credentials out of Git.

## Project structure
- `controller/` — web routes and form handling
- `service/` — issue/return business rules and fine calculation
- `repository/` — Spring Data database operations
- `model/` — JPA entities
- `config/` — security and demo data initialization
- `templates/` — Thymeleaf pages
- `static/css/` — responsive styles

## Borrowing rules
- Default loan period: 14 days
- Fine: ₹5 for each day returned after the due date
- A book cannot be issued when no copy is available
- A book with an outstanding issued copy cannot be deleted

## Academic note / next improvements
This is a substantial starter, not a production-hardened public library system. Before final submission, compile and test it on your machine; add full student self-service borrowing history and profile management, more integration tests, configurable policy settings, and a deployment guide. Adapt the report and feature list to the version you actually demonstrate.

## UI Refresh

The latest build includes a more interactive visual design with a warm library/book background, glass-style login panel, responsive two-column login screen, password visibility toggle, animated cards/buttons, and subtle hover effects. The background asset is stored at `src/main/resources/static/images/library-bg.jpg`.


## Enhancements
- 37 seeded book titles with generated cover images.
- Books page now includes interactive visual book cards.
- Dashboard includes featured book cards and library background.
- Book issue period can be selected as 7 days or 30 days.
- Overdue fine is ₹5 per day and accrues automatically after the due date; the live accrued fine is visible for active transactions and the final fine is stored when returned.
- Existing data is preserved; seed data is added only when a matching ISBN is missing.
