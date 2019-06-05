package com.qa.MapTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepository;
import com.qa.util.JSONUtil;

public class AccountServiceTest {
	private JSONUtil json;
	private AccountMapRepository amr;
	private Account acc1;
	private Account acc2;
	private Account acc3;
	private String acc1Json;
	private String acc2Json;

	@Before
	public void setup() {
		json = new JSONUtil();
		amr = new AccountMapRepository();
		acc1 = new Account(1, "1", "John", "Smith");
		acc2 = new Account(2, "2", "Bobson", "Dugnutt");
		acc3 = new Account(3, "3", "Mike", "Truk");
		acc1Json = "{\"id\":1,\"accountNumber\":\"1\",\"firstName\":\"John\",\"lastName\":\"Smith\"}";
		acc2Json = "{\"id\":2,\"accountNumber\":\"2\",\"firstName\":\"Bobson\",\"lastName\":\"Dugnutt\"}";
	}

	@Test
	public void addAccountTest() {
		amr.createAccount(acc1Json);
		assertEquals(1, amr.getAccountMap().size());
		assertEquals("John", amr.getAccountMap().get(1).getFirstName());
	}

	@Test
	public void add2AccountsTest() {
		amr.createAccount(acc1Json);
		amr.createAccount(acc2Json);
		assertEquals(2, amr.getAccountMap().size());
		assertEquals("John", amr.getAccountMap().get(1).getFirstName());
		assertEquals("Bobson", amr.getAccountMap().get(2).getFirstName());

	}

	@Test
	public void removeAccountTest() {
		amr.getAccountMap().put(1, acc1);
		amr.deleteAccount(1);
		assertEquals(0, amr.getAccountMap().size());
		assertEquals("{}", amr.getAccountMap().toString());

	}

	@Test
	public void remove2AccountsTest() {
		amr.getAccountMap().put(1, acc2);
		amr.getAccountMap().put(2, acc2);
		amr.deleteAccount(1);
		amr.deleteAccount(2);
		assertEquals(0, amr.getAccountMap().size());
		assertEquals("{}", amr.getAccountMap().toString());
	}

	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		amr.getAccountMap().put(1, acc2);
		amr.getAccountMap().put(2, acc2);
		amr.getAccountMap().put(3, acc3);
		amr.deleteAccount(1);
		amr.deleteAccount(2);
		amr.deleteAccount(4);
		assertEquals(1, amr.getAccountMap().size());
	}

	@Test
	public void jsonStringToAccountConversionTest() {
		Account account = json.getObjectForJSON(acc1Json, Account.class);
		assertEquals("John", account.getFirstName());
		assertEquals(1, account.getId());
	}

	@Test
	public void accountConversionToJSONTest() {
		assertEquals(acc1Json, json.getJSONForObject(acc1));
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
