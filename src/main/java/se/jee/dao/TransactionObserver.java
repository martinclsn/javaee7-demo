package se.jee.dao;

import org.apache.log4j.Logger;
import se.jee.entity.TimeEntity;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Status;
import javax.transaction.TransactionSynchronizationRegistry;

import static javax.enterprise.event.TransactionPhase.AFTER_SUCCESS;
import static javax.enterprise.event.TransactionPhase.IN_PROGRESS;

@ApplicationScoped
public class TransactionObserver {

    @Resource
    private TransactionSynchronizationRegistry txRegistry;

    @Inject
    Logger logger;

    public void afterSuccess(@Observes(during = AFTER_SUCCESS) TimeEntity timeEntity) throws Exception {
        logTxStatus();
    }

    public void slowTransaction(@Observes(during = IN_PROGRESS) TimeEntity timeEntity) throws Exception {
        logTxStatus();
        Thread.sleep(3000);
    }

    public void logTxStatus() {
        String status;
        switch (txRegistry.getTransactionStatus()) {
            case Status.STATUS_NO_TRANSACTION: {
                status = "NO_TRANSACTION";
                break;
            }
            case Status.STATUS_ACTIVE: {
                status = "ACTIVE";
                break;
            }
            default:
                status = String.valueOf(txRegistry.getTransactionStatus());
        }
        logger.info("Transaction status:" + status);

    }

}
