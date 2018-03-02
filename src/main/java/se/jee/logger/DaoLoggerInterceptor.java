package se.jee.logger;

import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class DaoLoggerInterceptor {

    @Inject
    Logger logger;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext invocationContext) throws Exception {
        Object result = invocationContext.proceed();
        String daoName = invocationContext.getTarget().getClass().getSuperclass().getSimpleName();
        logger.info(daoName + " result: " + result);
        return result;
    }
}
