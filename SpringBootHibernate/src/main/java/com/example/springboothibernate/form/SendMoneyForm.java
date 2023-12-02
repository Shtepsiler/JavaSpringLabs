package com.example.springboothibernate.form;

import com.example.springboothibernate.exception.BankTransactionException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SendMoneyForm {
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;

    public SendMoneyForm() {
    }
    public SendMoneyForm(Long fromAccountId, Long toAccountId, Double amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }
    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
    public Long getToAccountId() {
        return toAccountId;
    }
    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}


