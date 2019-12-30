package com.app.dev;

import com.app.dev.services.LibraryRestService;
import com.app.dev.services.MessageRestService;
import com.app.dev.services.MovieCrudService;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Application extends javax.ws.rs.core.Application {

    private Set<Object> singletons = new HashSet<>();

    public Application() {
        singletons.add(new MessageRestService());
        singletons.add(new LibraryRestService());
        singletons.add(new MovieCrudService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}