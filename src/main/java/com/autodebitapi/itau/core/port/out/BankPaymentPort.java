package com.autodebitapi.itau.core.port.out;

import com.autodebitapi.itau.core.domain.DebitResult;
import com.autodebitapi.itau.core.domain.ReversalResult;
import java.math.BigDecimal;

public interface BankPaymentPort {
    DebitResult debit(String accountId, BigDecimal amount, String description);
    ReversalResult reverse(String accountId, BigDecimal amount, String debitTransactionId, String reason);
}
