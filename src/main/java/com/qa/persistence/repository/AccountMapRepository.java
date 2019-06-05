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
		accountMap.put(a.getId(), a);
		return "Account has been created sucessfully";
	}

	public String deleteAccount(int accountNumber) {
		accountMap.remove(accountNumber);
		return "Account has been deleted sucessfully";
	}

	public String updateAccount(int accountNumber, String account) {
		Account a = json.getObjectForJSON(account, Account.class);
		accountMap.put(accountNumber, a);
		return "Account has been updated sucessfully";
	}

	public long getCountOfAccountsByFirstName(String firstName) {
		// long count = 0;
		// for(Account account : accountMap.values()) {
		// if(account.getFirstName().equals(firstName))
		// count++;
		// }

		long count = accountMap.values().stream().filter(x -> x.getFirstName().equals(firstName)).count();
		System.out.println(String.format("There are %s accounts with the first name '%s'.", count, firstName));
		return count;
	}

}
