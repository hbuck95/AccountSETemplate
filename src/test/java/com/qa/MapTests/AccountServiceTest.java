package com.qa.MapTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepository;
import com.qa.util.JSONUtil;

public class AccountServiceTest {
	JSONUtil json;
	AccountMapRepository amr;

	@Before
	public void setup() {
		json = new JSONUtil();
		amr = new AccountMapRepository();

	}

	@Test
	public void addAccountTest() {
		Account a = new Account(1, 123456, "John", "Smith");
		String jsonString = json.getJSONForObject(a);
		String message = amr.createAccount(jsonString);

		if (message.equals("{\"message\": \"account with this number already exists!\"}")) {
			fail(String.format("Account not added as an account with the account number %s already exists!",
					a.getAccountNumber()));
		}

		assertTrue(amr.getAccountMap().containsKey(a.getAccountNumber()));
	}

	@Test
	public void add2AccountsTest() {
		Account[] accounts = new Account[] { new Account(2, 789014, "Bobson", "Dugnutt"),
				new Account(3, 554056, "Mike", "Truk") };

		int count = 0;
		for (Account a : accounts) {
			String message = amr.createAccount(json.getJSONForObject(a));
			System.out.println(message);
			count += message.equals("{\"message\": \"account has been created sucessfully\"}") ? 1 : 0;
		}

		assertEquals(2, count);
	}

	@Test
	public void removeAccountTest() {
		Account a = new Account(4, 777777, "Sarah", "Smith");
		amr.createAccount(json.getJSONForObject(a));

		String message = amr.deleteAccount(777777);
		assertEquals("{\"message\": \"account has been deleted sucessfully\"}", message);
	}

	@Test
	public void remove2AccountsTest() {
		Account[] accounts = new Account[] { new Account(4, 777777, "Sarah", "Smith"),
				new Account(5, 888888, "Jeff", "Dell") };

		for (Account a : accounts) {
			amr.createAccount(json.getJSONForObject(a));
		}

		int count = 0;
		for (Account a : accounts) {
			String message = amr.deleteAccount(a.getAccountNumber());
			count += message.equals("{\"message\": \"account has been deleted sucessfully\"}") ? 1 : 0;
		}

		assertEquals(2, count);
	}

	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		Account[] accounts = new Account[] { new Account(4, 777777, "Sarah", "Smith"),
				new Account(5, 888888, "Jeff", "Dell") };

		int[] accountNumbers = new int[] { accounts[0].getAccountNumber(), // Valid
				accounts[1].getAccountNumber(), // Valid
				123456789 // Invalid
		};

		for (Account a : accounts) {
			amr.createAccount(json.getJSONForObject(a));
		}

		int count = 0;
		for (int accountNumber : accountNumbers) {
			String message = amr.deleteAccount(accountNumber);
			count += message.equals("{\"message\": \"account has been deleted sucessfully\"}") ? 1 : 0;
		}

		assertEquals(2, count);
	}

	@Test
	public void jsonStringToAccountConversionTest() {
		Account account = new Account(4, 777777, "Sarah", "Smith");
		String accountJson = "{\"id\":4,\"accountNumber\":777777,\"firstName\":\"Sarah\",\"lastName\":\"Smith\"}";
		assertEquals(accountJson, json.getJSONForObject(account));
	}

	@Test
	public void accountConversionToJSONTest() {
		Account account = new Account(4, 777777, "Sarah", "Smith");
		String accountJson = "{\"id\":4,\"accountNumber\":777777,\"firstName\":\"Sarah\",\"lastName\":\"Smith\"}";
		Account accountFromJson = json.getObjectForJSON(accountJson, Account.class);

		int count = 0;
		if (accountFromJson.getAccountNumber() == account.getAccountNumber()) {
			count++;
		}

		if (accountFromJson.getFirstName().equals(account.getFirstName())) {
			count++;
		}

		if (accountFromJson.getLastName().equals(account.getLastName())) {
			count++;
		}

		if (accountFromJson.getId() == account.getId()) {
			count++;
		}

		assertEquals(4, count);

	}

	@Test
	@Ignore
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		// For a later piece of functionality
		fail("TODO");
	}

	@Test
	@Ignore
	public void getCountForFirstNamesInAccountWhenOne() {
		// For a later piece of functionality
		fail("TODO");
	}

	@Test
	@Ignore
	public void getCountForFirstNamesInAccountWhenTwo() {
		// For a later piece of functionality
		fail("TODO");
	}

}
