package com.company.unitTesting.service;

import com.company.unitTesting.dto.AccountDto;
import com.company.unitTesting.exception.AccountNotFoundException;
import com.company.unitTesting.exception.InvalidAmountException;
import com.company.unitTesting.exception.NotEnoughBalanceException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public class TransactionService {

    public boolean transfer(AccountDto sourceAccount, AccountDto destinationAccount, BigDecimal amount) throws AccountNotFoundException, InvalidAmountException, NotEnoughBalanceException {
        if(Objects.isNull(sourceAccount) || Objects.isNull(destinationAccount))
            throw new AccountNotFoundException();

        if(amount.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidAmountException();

        if(sourceAccount.getBalance().compareTo(amount) < 0)
            throw new NotEnoughBalanceException();

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        destinationAccount.setBalance(sourceAccount.getBalance().add(amount, MathContext.DECIMAL128));

        return true;
    }
}
