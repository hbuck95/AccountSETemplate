package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Alternative
public class AccountMapRepository implements AccountRepository {

	Map<Integer, Account> accountMap = new HashMap<Integer, Account>();

	@Inject
	private JSONUtil json;

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

	@Override
	public String getAllAccounts() {
		return json.getJSONForObject(accountMap.values());
	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account a = json.getObjectForJSON(account, Account.class);
		accountMap.put(a.getId(), a);
		return "Account has been created sucessfully";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(int accountNumber) {
		accountMap.remove(accountNumber);
		return "Account has been deleted sucessfully";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccount(int accountNumber, String account) {
		Account a = json.getObjectForJSON(account, Account.class);
		accountMap.put(accountNumber, a);
		return "Account has been updated sucessfully";
	}

	@Override
	public String findAccount(int accountNumber) {
		Account a = accountMap.values().stream().filter(x -> x.getAccountNumber().equals(String.valueOf(accountNumber)))
				.findFirst().orElse(null);
		return json.getJSONForObject(a);
	}

	@Override
	public int cycleAccount(String firstName) {
		long count = accountMap.values().stream().filter(x -> x.getFirstName().equals(firstName)).count();
		System.out.println(String.format("There are %s accounts with the first name '%s'.", count, firstName));
		return (int) count;
	}

}
