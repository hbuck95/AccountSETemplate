package com.qa.business.service;

public interface AccountService {

	String getAllAccounts();

	String createAccount(String account);

	String deleteAccount(int accountNumber);

	String updateAccount(int accountNumber, String account);

	String findAccount(int accountNumber);

	int cycleAccount(String firstName);

}
