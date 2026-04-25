package com.codingNinjas.Bank.Account.Registration;

import java.util.List;

public interface User {

    void setUserDetails(String name);

    void addAccount(Account account);

    List<Account> getAllAccounts();

    String getName();
}