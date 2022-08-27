package com.techelevator.tenmo;

import com.techelevator.tenmo.model.Account;
import io.cucumber.java.en_old.Ac;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    private Account test1;
    private Account test2;

    @Before
    public void setup() {
        test1 = new Account(1,9001,1000);
        test2 = new Account(2,9002,1000);
    }

    @Test
    public void testWithdrawMethod() {
        double expected = 900;
        test1.withdraw(100);
        Assert.assertEquals(expected,test1.getBalance(),0);
    }

    @Test
    public void testDepositMethod() {
        double expected = 1100;
        test1.deposit(100);
        Assert.assertEquals(expected,test1.getBalance(),0);
    }

    @Test
    public void testTransferMethod() {
        double expectedTest1Balance = 900;
        double expectedTest2Balance = 1100;
        test1.transferTo(test2,100);
        Assert.assertEquals(expectedTest1Balance,test1.getBalance(),0);
        Assert.assertEquals(expectedTest2Balance,test2.getBalance(),0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenTransferAmountIs0() {
        test1.transferTo(test2,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenTransferAmountIsNegative() {
        test1.transferTo(test2,-100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenBothAccountsAreTheSame() {
        test1.transferTo(test1,100);
    }




}
