package se.jee.filter;

import se.jee.logger.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.net.URI;

@Provider
@PreMatching
@ApplicationScoped
public class RestPreMatcher implements ContainerRequestFilter {

    @Inject
    Logger logger;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext reqContext) throws IOException {

        String requestUriString = reqContext.getUriInfo().getRequestUri().getRawPath();
        String APA = "APA";
        if(requestUriString.contains(APA)) {
            reqContext.setRequestUri(URI.create(requestUriString.replaceAll(APA, "")));
            logger.info("Modified reqContextUri: " + reqContext.getUriInfo().getRequestUri());
        }
    }

}