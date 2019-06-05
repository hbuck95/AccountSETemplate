package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

public class AccountMapRepository implements AccountRepository {

	Map<Integer, Account> accountMap = new HashMap<Integer, Account>();
	private JSONUtil json = new JSONUtil();

	// You must provide concrete implementation for each of these methods
	// do not change the method signature
	// THINK - if the parameter is a String, or the return type is a String
	// How can I convert to a String from an Object?
	// What utility methods do I have available?

	// You must complete this section using TDD
	// You can use the suggested tests or build your own.

	public Map<Integer, Account> getAccountMap() {
		return accountMap;
	}

	public String getAllAccounts() {
		return json.getJSONForObject(accountMap.values());
	}

	public String createAccount(String account) {
		Account a = json.getObjectForJSON(account, Account.class);
		if (accountMap.containsKey(a.getAccountNumber())) {
			return "{\"message\": \"account with this number already exists!\"}";
		}
		accountMap.put(a.getAccountNumber(), a);

		return "{\"message\": \"account has been created sucessfully\"}";
	}

	public String deleteAccount(int accountNumber) {
		if (accountMap.containsKey(accountNumber)) {
			accountMap.remove(accountNumber);
			return "{\"message\": \"account has been deleted sucessfully\"}";

		}

		return "{\"message\": \"specified account number does not match an account!\"}";

	}

	public String updateAccount(int accountNumber, String account) {
		Account a = json.getObjectForJSON(account, Account.class);
		accountMap.put(accountNumber, a);
		return "{\"message\": \"account has been updated sucessfully\"}";
	}

}
