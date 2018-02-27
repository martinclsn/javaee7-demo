package se.jee.logger;

import org.apache.log4j.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.transaction.TransactionSynchronizationRegistry;
import java.io.File;

@ApplicationScoped
public class LoggerProducer {

    @Resource
    private TransactionSynchronizationRegistry txsRegistry;

    @Produces
    public Logger createLogger(InjectionPoint injectionPoint) {
        String loggerName = injectionPoint.getMember().getDeclaringClass().getName();
        org.apache.log4j.Logger logger = LogManager.getLogger(loggerName);
        return new Logger(logger, txsRegistry);
    }

}
