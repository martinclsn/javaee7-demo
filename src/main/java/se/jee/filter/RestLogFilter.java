package se.jee.filter;

import org.apache.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@ApplicationScoped
public class RestLogFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    Logger logger;

    @Override
    public void filter(ContainerRequestContext reqContext) throws IOException {
        logger.info("Request matched resources: " + reqContext.getUriInfo().getMatchedResources());
    }

    @Override
    public void filter(ContainerRequestContext reqContext,
                       ContainerResponseContext resContext) throws IOException {
        logger.info("Returning: " + resContext.getEntity());

    }

}