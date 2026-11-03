# Servlet Demo

A small Java web application demonstrating Jakarta Servlet 6.0 and HTTP session
handling. It runs on an embedded Apache Tomcat 11 server and displays session
details, including the session ID, creation time, last access time, user ID, and
visit count.

## Requirements

- JDK 17 or later

## Run the application

Start the embedded Tomcat server from the project root:

```shell
./gradlew runServer
```

On Windows, use:

```powershell
.\gradlew.bat runServer
```

Open [http://localhost:8080/](http://localhost:8080/) or
[http://localhost:8080/hello](http://localhost:8080/hello). The root page
redirects to the servlet. The servlet is also available at `/hello-servlet`.

## Build the WAR

```shell
./gradlew war
```

On Windows:

```powershell
.\gradlew.bat war
```

The deployable archive is created at `build/libs/servlet-demo.war`.

## Project layout

- `src/main/java/org/example/HelloServlet.java` — serves the session information
  page and tracks visits in an HTTP session.
- `src/main/java/org/example/Server.java` — starts the embedded Tomcat server
  on port 8080.
- `src/main/webapp/` — web application files and servlet deployment descriptor.
