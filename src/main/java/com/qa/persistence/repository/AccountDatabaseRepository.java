package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDatabaseRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	private JSONUtil util;

	@Override
	public String getAllAccounts() {
		TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a", Account.class);
		Collection<Account> accounts = query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account acc = util.getObjectForJSON(account, Account.class);
		entityManager.persist(acc);
		return "{\"message\": \"Account has been created sucessfully\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(int accountNumber) {
		entityManager.createQuery(String.format("DELETE FROM Account WHERE accountNumber = %s", accountNumber));
		return "{\"message\": \"Account successfully deleted\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccount(int accountNumber, String account) {
		Account updateAcc = util.getObjectForJSON(account, Account.class);
		Account accFromDb = entityManager.find(Account.class, accountNumber);

		entityManager.getTransaction().begin();
		accFromDb.setAccountNumber(updateAcc.getAccountNumber());
		accFromDb.setFirstName(updateAcc.getFirstName());
		accFromDb.setLastName(updateAcc.getLastName());
		entityManager.getTransaction().commit();

		return "{\"message\": \"Account has been updated successfully\"}";

	}

	@Override
	public String findAccount(int accountNumber) {
		return util.getJSONForObject((Account) entityManager.find(Account.class, accountNumber));
	}

	@Override
	public int cycleAccount(String firstName) {
		return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList().size();
	}

}
