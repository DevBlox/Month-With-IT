package com.tieto.it2014.ui;

import org.apache.log4j.Logger;
import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Start {

    private static final Logger LOGGER = Logger.getLogger(Start.class);

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
            server.start();
            System.out.println("<<< Started Jetty server, press any key to stop. >>>");
            System.in.read();
            server.stop();
            server.join();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            System.exit(1);
        }
    }
}
