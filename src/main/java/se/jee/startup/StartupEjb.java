package se.jee.startup;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.File;

@Startup
@Singleton
public class StartupEjb {

    private Logger logger = Logger.getLogger(getClass());

    @PostConstruct
    public void startingUp() {
        FileAppender fa = new FileAppender();
        fa.setName("FileLogger");
        String logFile = System.getProperty("user.dir") + File.separator + "demo.log";
        fa.setFile(logFile);
        fa.setLayout(new PatternLayout("%d{ABSOLUTE} %-5p [%t] [%c{1}] %m%n"));
        fa.setThreshold(Level.DEBUG);
        fa.setAppend(false); //truncate file
        fa.activateOptions();
        Logger.getRootLogger().addAppender(fa);
        System.out.println("Logging to file: " + logFile);
        logger.info("Application is starting");
    }

    @PreDestroy
    public void shuttingDown() {
        logger.info("Application is shutting down");
    }

}
