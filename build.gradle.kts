plugins {
    id("java")
    id("war") // Apply the WAR plugin for web application packaging
    
    id("idea")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // ⬇️ ADD THIS LINE FOR JAKARTA EE 10 SERVLETS ⬇️
    providedCompile("jakarta.servlet:jakarta.servlet-api:6.0.0")

    // Embedded Tomcat to run directly with F5 / launch.json without installing external Tomcat
    implementation("org.apache.tomcat.embed:tomcat-embed-core:11.0.27")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<JavaExec>("runServer") {
    group = "application"
    description = "Runs the embedded Tomcat server"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("org.example.Server")
}

tasks.war {
    // For root path http://localhost:8080/
    archiveFileName.set("servlet-demo.war")
}