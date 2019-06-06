package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.AccountService;

@Path("/account")
public class AccountController {

	@Inject
	private AccountService accountService;

	@Path("/getAllAccounts")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return accountService.getAllAccounts();
	}

	@Path("/createAccount")
	@POST
	@Produces({ "application/json" })
	public String createAccount(String account) {
		return accountService.createAccount(account);
	}

	@Path("/deleteAccount/{accountId}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("accountId") int accountId) {
		return accountService.deleteAccount(accountId);
	}

	@Path("/updateAccount/{accountId}")
	@PUT
	@Produces({ "application/json" })
	public String updateAccount(@PathParam("accountId") int accountId, String account) {
		return accountService.updateAccount(accountId, account);
	}

	@Path("/findAccount/{accountId}")
	@GET
	@Produces({ "application/json" })
	public String findAccount(@PathParam("accountId") int accountId) {
		return accountService.findAccount(accountId);
	}

	@Path("/cycleAccount/{firstName}")
	@GET
	@Produces({ "application/json" })
	public int cycleAccount(@PathParam("firstName") String firstName) {
		return accountService.cycleAccount(firstName);
	}

	public void setService(AccountService service) {
		this.accountService = service;
	}

}
