package org.acme.controller;
import org.acme.model.Account;
import org.acme.model.Transfer;
import org.acme.service.AccountService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountService acc_service;

    // Exercise - List all accounts
    @GET
    @Path("/getAccounts")
    public Response getAccounts()  {
        return Response.status(200).entity(acc_service.getAccounts()).build();
    }

    // Exercise - Create an account
    @POST
    @Path("/createAccount")
    public Response createAccount(@Valid Account acc) {
        Account created_acc = acc_service.createAccount(acc);
        if (created_acc == null) {return Response.status(400).entity("Username already exists or balance is below 0").build(); }
        return Response.status(201).entity("User created: " + created_acc.getUsername()).build();
    }

    // Exercise - Transfer amount between accounts
    @PUT
    @Path("/transfer")
    public Response transferAmount(@Valid Transfer transfer) {
        Account accountFrom = acc_service.getAccountByUsername(transfer.getFromUsername());
        Account accountTo = acc_service.getAccountByUsername(transfer.getToUsername());

        if(accountFrom == null) {return Response.status(404).entity("From user not found").build();}
        if(accountTo == null) {return Response.status(404).entity("To user not found").build();}
        if (accountFrom.getBalance() - transfer.getAmount() < 0) return Response.status(400).entity("The amount can not be bigger than your balance").build(); 

        Double currentBalanceFromUser = accountFrom.getBalance() - transfer.getAmount();
        accountFrom.setBalance(currentBalanceFromUser);

        Double currentBalanceToUser = accountTo.getBalance() + transfer.getAmount();
        accountTo.setBalance(currentBalanceToUser);

        acc_service.updateAccount(accountFrom);
        acc_service.updateAccount(accountTo);

        return Response.status(200).entity("Transferred " + transfer.getAmount() + " from user: " + accountFrom.getUsername() + " to user: " + accountTo.getUsername()).build();
    }
}
