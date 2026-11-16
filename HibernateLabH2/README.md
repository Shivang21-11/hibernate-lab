# Hibernate + JPA Lab - H2 version (no MySQL needed)

1. Open IntelliJ IDEA -> File -> Open -> select this HibernateLabH2 folder -> Trust Project.
2. Wait for Maven to finish loading (bottom bar).
3. File -> Project Structure -> Project -> SDK -> Add SDK -> Download JDK -> version 17 -> Download -> OK.
4. Open src/main/java/App.java and click the green Run button next to main.
5. Screenshot the Run panel at the bottom. It shows the Hibernate SQL, the
   messages, and the final student table.

## No IntelliJ? Run on a Mac from Terminal
    cd HibernateLabH2
    bash run-mac.sh
(Downloads Java 17 and Maven into ~/hibernate-lab-tools the first time, then runs the program.)
