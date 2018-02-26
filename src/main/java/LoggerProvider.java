import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

@ApplicationScoped
public class LoggerProvider {

    private Logger logger = Logger.getLogger(getClass().getName());

    @PostConstruct
    public void postConstruct() {
        logger.info("Created application scoped instance");
    }

    @Produces
    public Logger createLogger(/*InjectionPoint injectionPoint*/) {
        //String loggerName = injectionPoint.getMember().getDeclaringClass().getName();
        String loggerName = "XXXXX";
        Logger logger = Logger.getLogger(loggerName);
        logger.info("Created logger for " + loggerName);
        return logger;
    }


}
