package mainpack;

import components.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class main_bank 
{
    public static void main(String[] args) 
    {

        System.out.println("Bienvenue Nicolas :");
        
        List<Client> clients = generateClients(3);
        
        // Affichage des clients
        System.out.println("Affichage des clients :");
        
        displayClients(clients);

        List<Account> accounts = createAccounts(clients);

        // Afficher les comptes
        System.out.println("Affichage des comptes :");
        
        displayAccounts(accounts);

        Hashtable<Integer, Account> accountHashtable = createAccountHashtable(accounts);

        // Afficher la Hashtable d'accounts
        System.out.println("Affichage des comptes via la Hashmap :");
        
        displayAccountHashtable(accountHashtable);

        List<Flow> flows = createFlows(accountHashtable);

        // Afficher les flows
        System.out.println("Affichage des flows :");
        
        displayFlows(flows);

        String filePathXml = "accounts.xml"; // Chemin vers le fichier XML
        
        List<Account> accountsXml = loadAccountsFromXML(filePathXml);

        // Afficher les comptes chargés depuis le fichier XML
        System.out.println("Affichage des comptes à partir du fichier XML :");
        
        displayAccounts(accountsXml);

        String filePathJson = "flows.json"; // Chemin vers le fichier JSON
        
        Flow[] flowsJson = loadFlowsFromJSON(filePathJson);
        
        if (flowsJson != null) 
        {
            System.out.println("Affichage des flows à partir du fichier JSON :");
            
            for (Flow flow : flowsJson) 
            {
                System.out.println(flow);
            }
        }
    }

    // Méthode pour générer un tableau de clients
    public static List<Client> generateClients(int numberOfClients) 
    {
        List<Client> clients = new ArrayList<>();
        
        for (int i = 1; i <= numberOfClients; i++) 
        {
            clients.add(new Client("name" + i, "firstname" + i));
        }
        return clients;
    }

    // Méthode pour afficher les clients
    public static void displayClients(List<Client> clients)
    {
        clients.forEach(System.out::println);
    }

    // Méthode pour créer un tableau de comptes à partir d'un tableau de clients
    public static List<Account> createAccounts(List<Client> clients) 
    {
        List<Account> accounts = new ArrayList<>();
        
        for (Client client : clients) 
        {
            accounts.add(new CurrentAccount("Current", client)); // Exemple de création de compte courant
            accounts.add(new SavingsAccount("Savings", client)); // Exemple de création de compte épargne
        }
        
        return accounts;
    }

    // Méthode pour afficher les comptes
    public static void displayAccounts(List<Account> accounts) 
    {
        accounts.forEach(System.out::println);
    }

    // Méthode pour créer une Hashtable d'accounts à partir d'une liste d'accounts
    public static Hashtable<Integer, Account> createAccountHashtable(List<Account> accounts) 
    {
        Hashtable<Integer, Account> accountHashtable = new Hashtable<>();
        
        for (Account account : accounts) 
        {
            accountHashtable.put(account.getAccountNumber(), account);
        }
        
        return accountHashtable;
    }

    // Méthode pour afficher la Hashtable d'accounts
    public static void displayAccountHashtable(Hashtable<Integer, Account> accountHashtable) 
    {
        accountHashtable.values().forEach(System.out::println);
    }

    // Méthode pour créer un tableau de flows
    public static List<Flow> createFlows(Hashtable<Integer, Account> accountHashtable) 
    {
        List<Flow> flows = new ArrayList<>();

        // Exemple de création de flows
        flows.add(new Debit("Debit of 50€ from account n°1", 1, 50.0, 1, true));
        flows.add(new Credit("Credit of 100.50€ on all current accounts", 2, 100.50, -1, true)); // Modifier le -1 avec l'account number correct
        flows.add(new Credit("Credit of 1500€ on all savings accounts", 3, 1500.0, -1, true)); // Modifier le -1 avec l'account number correct
        flows.add(new Transfer("Transfer of 50€ from account n°1 to account n°2", 4, 50.0, 2, true, 1));

        // Modifier la date des flows (2 jours après la date actuelle)
        LocalDate dateOfFlows = LocalDate.now().plusDays(2);
        flows.forEach(flow -> flow.setDateOfFlow(dateOfFlows));

        return flows;
    }

    // Méthode pour afficher les flows
    public static void displayFlows(List<Flow> flows) 
    {
        flows.forEach(System.out::println);
    }

    // Méthode pour charger les comptes depuis un fichier XML
    public static List<Account> loadAccountsFromXML(String filePath) 
    {
        List<Account> accounts = new ArrayList<>();

        try {
        	
            File file = new File(filePath);
            
            JAXBContext jaxbContext = JAXBContext.newInstance(AccountList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            AccountList accountList = (AccountList) jaxbUnmarshaller.unmarshal(file);
            List<ConcreteAccount> concreteAccounts = accountList.getAccounts();

            // Convertir les ConcreteAccount en Account et les ajouter à la liste des comptes
            for (ConcreteAccount concreteAccount : concreteAccounts) 
            {
                Client client = concreteAccount.getClient();
                
                // Création d'un nouveau client avec les informations récupérées du XML
                Client newClient = new Client(client.getFirstName(), client.getLastName(), client.getClientNumber());

                // Création du compte correspondant
                Account account = new ConcreteAccount(
                        concreteAccount.getAccountNumber(),
                        concreteAccount.getLabel(),
                        concreteAccount.getBalance(),
                        newClient);

                accounts.add(account);
            }

        } 
        catch (JAXBException e) 
        {
            e.printStackTrace(); // Gérer l'exception selon les besoins
        }

        return accounts;
    }

    // Méthode pour charger les flows depuis un fichier JSON
    public static Flow[] loadFlowsFromJSON(String filePath) 
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Enregistrer les types pour les classes Flow, Debit, Credit, Transfer
            objectMapper.registerSubtypes(Debit.class, Credit.class, Transfer.class);
            
            // Enregistrer le module Jackson JSR-310 pour les dates
            objectMapper.registerModule(new JavaTimeModule());

            // Utilisation de java.nio.file.Path et try-with-resources pour charger le fichier JSON
            Path path = Paths.get(filePath);
            
            // Désérialisation du fichier JSON en tableau de Flow
            return objectMapper.readValue(Files.newBufferedReader(path), Flow[].class);
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
}
