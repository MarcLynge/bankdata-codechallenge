package org.acme.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.acme.model.Account;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountService {
    private List<Account> mockAccounts = createList();
    
    public List<Account> getAccounts() {
        return (mockAccounts != null) ? mockAccounts : new ArrayList<Account>();
    }

    public Account createAccount(Account acc) {
        // Check if username already exists
        if (checkifUserAlreadyExists(acc)) { return null; }

        // Generating ID
        UUID uuid = UUID.randomUUID();
        acc.setId(uuid);

        // Generate password
        String hashedPassword = hashingPassword(acc.getPassword());

        acc.setPassword(hashedPassword.toString());
        mockAccounts.add(acc);
        return acc;
    }

    public void updateAccount(Account acc) {
        // Get index of user
        int index = this.mockAccounts.indexOf(acc);
        this.mockAccounts.set(index, acc);
    }

    public Account getAccountByUsername(String username) {
        Account acc_match = null;
        if (mockAccounts.size() == 0) {return null;}
        
        for (Account acc : mockAccounts) {
            if (acc.getUsername().equals(username)) {
                acc_match = acc;
            }
        }
        return acc_match;
    }

    private String hashingPassword(String password) {
        // Generate salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        // Hash password
        MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return hashedPassword.toString();
    }

    public Boolean checkifUserAlreadyExists(Account acc) {
        for (Account acc_iter : mockAccounts) {
            if (acc_iter.getUsername().toLowerCase().equals(acc.getUsername().toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    private List<Account> createList() {
	    List<Account> mockAccounts= new ArrayList<>();
	    Account ac1 = new Account();
        UUID generate_ac1 = UUID.randomUUID();
        ac1.setId(generate_ac1);
        ac1.setUsername("Thomas");
        ac1.setBalance(2000.00);
		Account ac2 = new Account();
        UUID generate_ac2 = UUID.randomUUID();
        ac2.setId(generate_ac2);
        ac2.setBalance(10000.00);
        ac2.setUsername("Mads");



		mockAccounts.add(ac1);
		mockAccounts.add(ac2);
		return mockAccounts;
	}
}
