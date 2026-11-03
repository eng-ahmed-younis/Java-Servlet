package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Server {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws LifecycleException {
        int port = 8080;
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector(); // Trigger connector initialization

        // Working directory for embedded Tomcat
        File workDir = new File("build/tomcat");
        if (!workDir.exists()) {
            workDir.mkdirs();
        }
        tomcat.setBaseDir(workDir.getAbsolutePath());

        // Mount webapp directory
        String docBase = new File("src/main/webapp").getAbsolutePath();
        Context ctx = tomcat.addWebapp("", docBase);

        // Register HelloServlet as default and at /hello
        Tomcat.addServlet(ctx, "HelloServlet", new HelloServlet());
        ctx.addServletMappingDecoded("/", "HelloServlet");
        ctx.addServletMappingDecoded("/hello", "HelloServlet");
        ctx.addServletMappingDecoded("/hello-servlet", "HelloServlet");

        System.out.println("Starting Tomcat server on port " + port + "...");
        tomcat.start();
        System.out.println("==========================================================");
        System.out.println(" Server is RUNNING at: http://localhost:" + port + "/");
        System.out.println(" HelloServlet URL:     http://localhost:" + port + "/hello");
        System.out.println(" Default URL:          http://localhost:" + port + "/");
        System.out.println("==========================================================");

        tomcat.getServer().await();
    }
}
