package se.jee.logger;

import javax.transaction.Status;
import javax.transaction.TransactionSynchronizationRegistry;

public class Logger {

    private final TransactionSynchronizationRegistry txRegistry;
    private final org.apache.log4j.Logger logger;

    public Logger(org.apache.log4j.Logger logger, TransactionSynchronizationRegistry txRegistry) {
        this.logger = logger;
        this.txRegistry = txRegistry;
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
        info("Transaction status:" + status);

    }

    public void info(String message) {
        logger.info(message);
    }
}
