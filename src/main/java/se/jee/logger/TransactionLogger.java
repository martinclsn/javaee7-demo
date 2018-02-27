package se.jee.logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class TransactionLogger {

    @Inject
    Logger logger;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext invocationContext) throws Exception {
        Object result = invocationContext.proceed();
        Thread.sleep(1000);
        logger.logTxStatus();
        return result;
    }
}
