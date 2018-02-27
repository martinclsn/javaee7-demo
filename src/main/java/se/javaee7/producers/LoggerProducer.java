package se.javaee7.producers;

import org.apache.log4j.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class LoggerProducer {

    private Logger logger = LogManager.getLogger(getClass().getName());

    @Produces
    public Logger createLogger(InjectionPoint injectionPoint) {
        String loggerName = injectionPoint.getMember().getDeclaringClass().getName();
        Logger logger = LogManager.getLogger(loggerName);
        logger.info("Created logger for " + loggerName);
        return logger;
    }

    @PostConstruct
    public void configureLogging() {
        FileAppender fa = new FileAppender();
        fa.setName("FileLogger");
        fa.setFile("C:\\development\\skv\\wlsdomains\\ik\\demo.log");
        fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
        fa.setThreshold(Level.DEBUG);
        fa.setAppend(true);
        fa.activateOptions();
        //add appender to any Logger (here is root)
        Logger.getRootLogger().addAppender(fa);

        LogManager.getLogger("hej").info("vladsldlafl");
        logger.info("Created application scoped instance");
    }

}
