package se.jee.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class LoggerProducer {

    @Produces
    public Logger createLogger(InjectionPoint injectionPoint) {
        String loggerName = injectionPoint.getMember().getDeclaringClass().getName();
        return LogManager.getLogger(loggerName);
    }

}
