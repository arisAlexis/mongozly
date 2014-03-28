package org.mongozly.server.rest.nio;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;


public class App {

    private static final URI BASE_URI = URI.create("http://localhost:8080/rest/");


    public static void main(String[] args) {
        try {
            System.out.println("grizzly rest server app and running..");

            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, createApp());

            System.out.println(String.format("Application started.\nTry out %s\nHit enter to stop it...",BASE_URI));
            System.in.read();
            server.shutdownNow();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ResourceConfig createApp() {
        return new ResourceConfig().packages("org.mongozly.server.rest.nio").register(createMoxyJsonResolver());
    }

    public static ContextResolver<MoxyJsonConfig> createMoxyJsonResolver() {
        final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();
        Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
        namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        moxyJsonConfig.setNamespacePrefixMapper(namespacePrefixMapper).setNamespaceSeparator(':');
        return moxyJsonConfig.resolver();
    }
    
}