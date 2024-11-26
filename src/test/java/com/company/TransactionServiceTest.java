package com.company;

import com.company.unitTesting.dto.AccountDto;
import com.company.unitTesting.exception.AccountNotFoundException;
import com.company.unitTesting.exception.InvalidAmountException;
import com.company.unitTesting.exception.NotEnoughBalanceException;
import com.company.unitTesting.service.TransactionService;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("transaction service tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TransactionServiceTest {
    public static final BigDecimal RANDOM_MONEY_AMOUNT = new BigDecimal(100);
    public static final BigDecimal MORE_THAN_RANDOM_BALANCE = new BigDecimal(200);
    public static final BigDecimal ZERO_MONEY_AMOUNT = BigDecimal.ZERO;
    public static final BigDecimal NEGATIVE_MONEY_AMOUNT = new BigDecimal(-1);
    TransactionService instanceService;

    /*@before*/
    @BeforeEach
    void setup() {
        instanceService = new TransactionService();
    }

    /*@After*/
    @AfterEach
    void teardown() {
//        System.out.println("this method execute after each test method");
    }

    /*@BeforeClass*/
    @BeforeAll
    public static void beforeAll() {
//        System.out.println("this method execute before all test method");
    }

    /*@AfterClass*/
    @AfterAll
    public static void afterAll() {
//        System.out.println("this method execute after all test method");
    }

    @Test
    @DisplayName("transfer amount in normal way")
    void transfer() throws InvalidAmountException, AccountNotFoundException, NotEnoughBalanceException {
        /*GIVEN*/
        AccountDto sourceAccount = AccountDto.builder()
                .balance(RANDOM_MONEY_AMOUNT)
                .build();
        AccountDto destinationAccount = AccountDto.builder()
                .balance(ZERO_MONEY_AMOUNT)
                .build();

        /*WHEN*/
        instanceService.transfer(sourceAccount, destinationAccount, RANDOM_MONEY_AMOUNT);

        /*THEN*/
        assertEquals(ZERO_MONEY_AMOUNT, sourceAccount.getBalance());
        assertEquals(RANDOM_MONEY_AMOUNT, destinationAccount.getBalance());

//        assertAll("transfer transaction checking",
//                () -> {
//                    /*WHEN*/
//                    boolean transactionState = instanceService.transfer(sourceAccount, destinationAccount, RANDOM_MONEY_AMOUNT);
//                    assertTrue(transactionState);
//
//                    /*executed only if the previous assertion is valid*/
//                    assertAll("check account balance",
//                            () -> assertEquals(ZERO_MONEY_AMOUNT, sourceAccount.getBalance()),
//                            () -> assertEquals(RANDOM_MONEY_AMOUNT, destinationAccount.getBalance())
//                    );
//                }
//        );
    }

    @Test
    void transferFromNullAccount() {
        /*GIVEN*/
        AccountDto destinationAccount = AccountDto.builder()
                .balance(ZERO_MONEY_AMOUNT)
                .build();

        /*WHEN & THEN*/
        assertThrows(AccountNotFoundException.class, () -> instanceService.transfer(null, destinationAccount, RANDOM_MONEY_AMOUNT));
    }

    @Test
    void transferNegativeAmount() {
        /*GIVEN*/
        AccountDto sourceAccount = AccountDto.builder()
                .balance(RANDOM_MONEY_AMOUNT)
                .build();
        AccountDto destinationAccount = AccountDto.builder()
                .balance(ZERO_MONEY_AMOUNT)
                .build();

        /*WHEN & THEN*/
        assertThrows(InvalidAmountException.class, () -> instanceService.transfer(sourceAccount, destinationAccount, NEGATIVE_MONEY_AMOUNT));
    }

    @Test
    void transferFromEmptyAccount() {
        /*GIVEN*/
        AccountDto sourceAccount = AccountDto.builder()
                .balance(ZERO_MONEY_AMOUNT)
                .build();
        AccountDto destinationAccount = AccountDto.builder()
                .balance(ZERO_MONEY_AMOUNT)
                .build();

        /*WHEN & THEN*/
        assertThrows(NotEnoughBalanceException.class, () -> instanceService.transfer(sourceAccount, destinationAccount, RANDOM_MONEY_AMOUNT));
    }
}
