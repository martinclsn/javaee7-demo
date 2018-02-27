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

    private org.apache.log4j.Logger logger = LogManager.getLogger(getClass().getName());

    @Resource
    private TransactionSynchronizationRegistry txsRegistry;

    @Produces
    public Logger createLogger(InjectionPoint injectionPoint) {
        String loggerName = injectionPoint.getMember().getDeclaringClass().getName();
        org.apache.log4j.Logger logger = LogManager.getLogger(loggerName);
        this.logger.info("Created logger for " + loggerName);
        return new Logger(logger, txsRegistry);
    }

    @PostConstruct
    public void configureLogging() {
        FileAppender fa = new FileAppender();
        fa.setName("FileLogger");
        String logFile = System.getProperty("user.dir") + File.separator + "demo.log";
        fa.setFile(logFile);
        fa.setLayout(new PatternLayout("%d %-5p [%t] [%c{1}] %m%n"));
        fa.setThreshold(Level.DEBUG);
        fa.setAppend(true);
        fa.activateOptions();
        //add appender to any Logger (here is root)
        org.apache.log4j.Logger.getRootLogger().addAppender(fa);
        System.out.println("Logging to file: " + logFile);
        logger.info("Created application scoped instance");
    }

}
