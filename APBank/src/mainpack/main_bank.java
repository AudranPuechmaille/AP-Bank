package mainpack;

import components.Account;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.SavingsAccount;
import components.Transfer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class main_bank {
    public static void main(String[] args) {
    	
    	List<Client> clients = generateClients(3);
    	
    	// Affichage des clients
        displayClients(clients);
        
        List<Account> accounts = createAccounts(clients);
        
        // Afficher les comptes
        displayAccounts(accounts);

        Hashtable<Integer, Account> accountHashtable = createAccountHashtable(accounts);

        // Afficher la Hashtable d'accounts
        displayAccountHashtable(accountHashtable);
        
        List<Flow> flows = createFlows(accountHashtable);

        // Afficher les flows
        displayFlows(flows);
    }

    // Méthode pour générer un tableau de clients
    public static List<Client> generateClients(int numberOfClients) {
        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= numberOfClients; i++) {
            clients.add(new Client("name" + i, "firstname" + i));
        }
        return clients;
    }

    // Méthode pour afficher les clients
    public static void displayClients(List<Client> clients) {
        clients.stream()
                .map(Client::toString)
                .forEach(System.out::println);
    }
    
 // Méthode pour créer un tableau de comptes à partir d'un tableau de clients
    public static List<Account> createAccounts(List<Client> clients) {
        List<Account> accounts = new ArrayList<>();
        for (Client client : clients) {
            accounts.add(new CurrentAccount("Current", client)); // Exemple de création de compte courant
            accounts.add(new SavingsAccount("Savings", client)); // Exemple de création de compte épargne
        }
        return accounts;
    }

    // Méthode pour afficher les comptes
    public static void displayAccounts(List<Account> accounts) {
        accounts.stream()
                .map(Account::toString)
                .forEach(System.out::println);
    }
    
 // Méthode pour créer une Hashtable d'accounts à partir d'une liste d'accounts
    public static Hashtable<Integer, Account> createAccountHashtable(List<Account> accounts) {
        Hashtable<Integer, Account> accountHashtable = new Hashtable<>();
        for (Account account : accounts) {
            accountHashtable.put(account.getAccountNumber(), account);
        }
        return accountHashtable;
    }

    // Méthode pour afficher la Hashtable d'accounts
    public static void displayAccountHashtable(Hashtable<Integer, Account> accountHashtable) {
        accountHashtable.values().stream()
                .map(Account::toString)
                .forEach(System.out::println);
    }
    
 // Méthode pour créer un tableau de flows
    public static List<Flow> createFlows(Hashtable<Integer, Account> accountHashtable) {
        List<Flow> flows = new ArrayList<>();

        // Exemple de création de flows
        flows.add(new Debit("Debit of 50€ from account n°1", 1, 50.0, 1, true));
        flows.add(new Credit("Credit of 100.50€ on all current accounts", 2, 100.50, -1, true)); // Modifier le -1 avec l'account number correct
        flows.add(new Credit("Credit of 1500€ on all savings accounts", 3, 1500.0, -1, true)); // Modifier le -1 avec l'account number correct
        flows.add(new Transfer("Transfer of 50€ from account n°1 to account n°2", 4, 50.0, 2, true, 1));

        // Modifier la date des flows (2 jours après la date actuelle)
        LocalDate dateOfFlows = LocalDate.now().plusDays(2);
        flows.forEach(flow -> ((Flow) flow).setDateOfFlow(dateOfFlows));

        return flows;
    }

    // Méthode pour afficher les flows
    public static void displayFlows(List<Flow> flows) {
        flows.forEach(flow -> System.out.println(flow.toString()));
    }
}
