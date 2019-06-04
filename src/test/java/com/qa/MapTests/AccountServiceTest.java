package com.qa.MapTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
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
		assertEquals(message, "{\"message\": \"account has been created sucessfully\"}");

	}

	@Test
	public void add2AccountsTest() {
		fail("TODO");
	}

	@Test
	public void removeAccountTest() {
		fail("TODO");
	}

	@Test
	public void remove2AccountsTest() {
		fail("TODO");
	}

	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		fail("TODO");
	}

	@Test
	public void jsonStringToAccountConversionTest() {
		// testing JSONUtil
		fail("TODO");
	}

	@Test
	public void accountConversionToJSONTest() {
		// testing JSONUtil
		fail("TODO");
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		// For a later piece of functionality
		fail("TODO");
	}

	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		// For a later piece of functionality
		fail("TODO");
	}

	@Test
	public void getCountForFirstNamesInAccountWhenTwo() {
		// For a later piece of functionality
		fail("TODO");
	}

}
