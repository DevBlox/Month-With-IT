package com.tieto.it2014.ui;

import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Start {

    private Start() {
    }

    public static void main(String[] args) throws Exception {
        startJettyServer(8080, "/");
    }

    private static void startJettyServer(int port, String contextPath) {
        int timeout = (int) Duration.ONE_HOUR.getMilliseconds();
        Server server = new Server();
        SocketConnector connector = new SocketConnector();
        // Set some timeout options to make debugging easier.
        connector.setMaxIdleTime(timeout);
        connector.setSoLingerTime(-1);
        connector.setPort(port);
        server.addConnector(connector);
        WebAppContext bb = new WebAppContext();
        bb.setServer(server);
        bb.setContextPath(contextPath);
        bb.setWar("src/main/webapp");
        server.setHandler(bb);
        try {
            System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
            server.start();
            System.out.println();
            System.out.println("    http://localhost:" + port + contextPath);
            System.out.println();
            System.out.println("Press ENTER to stop Jetty ...");
            System.in.read();
            System.out.println(">>> STOPPING EMBEDDED JETTY SERVER");
            server.stop();
            server.join();
        } catch (Exception e) {
            System.exit(1);
        }
    }
}
