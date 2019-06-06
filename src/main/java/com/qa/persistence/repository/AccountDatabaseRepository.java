package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class AccountDatabaseRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	private JSONUtil util;

	public String getAllAccounts() {
		TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a", Account.class);
		Collection<Account> accounts = query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account acc = util.getObjectForJSON(account, Account.class);
		entityManager.persist(acc);
		return "{\"message\": \"Account has been created sucessfully\"}";
	}

	@Transactional(REQUIRED)
	public String deleteAccount(int accountNumber) {
		entityManager.createQuery(String.format("DELETE FROM Account WHERE accountNumber = %s", accountNumber));
		return "{\"message\": \"Account sucessfully deleted\"}";
	}

	@Transactional(REQUIRED)
	public String updateAccount(int accountNumber, String account) {
		Account updateAcc = util.getObjectForJSON(account, Account.class);
		Account accFromDb = entityManager.find(Account.class, accountNumber);

		entityManager.getTransaction().begin();
		accFromDb.setAccountNumber(updateAcc.getAccountNumber());
		accFromDb.setFirstName(updateAcc.getFirstName());
		accFromDb.setLastName(updateAcc.getLastName());
		entityManager.getTransaction().commit();

		return "{\"message\": \"Account has been updated sucessfully\"}";

	}

	public long getCountOfAccountsByFirstName(String firstName) {
		TypedQuery<Account> query = entityManager
				.createQuery(String.format("SELECT a FROM ACCOUNT a WHERE firstName = ", firstName), Account.class);
		return query.getResultList().size();
	}

	public String findAccount(String firstName) {
		return util.getJSONForObject(entityManager.find(Account.class, firstName));
	}

}
