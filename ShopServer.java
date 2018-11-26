
import java.util.*;
import java.io.*;

public class ShopServer extends Server {
    DatabaseAccess db = new DatabaseAccess();
    Map<String, Account> accounts = new HashMap<String, Account>();

    String clientIP;

    public ShopServer(int pPortnummer) {
        super(pPortnummer);
    }

    public void processNewConnection(String pClientIP, int pClientPort) {
        clientIP = pClientIP;

        send(pClientIP, pClientPort, "Willkommen in unserem T-Shirt Shop!!!");
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        System.out.println(pClientIP + ": " + pMessage);

        String[] nachrichtTeil = pMessage.split(":");

        if(nachrichtTeil[0].equals("LOGIN")){
            logIn(pClientIP,pClientPort,nachrichtTeil[1],nachrichtTeil[2]);
        }

        else if(nachrichtTeil[0].equals("REGISTER")) {
            register(pClientIP, pClientPort, Integer.valueOf(nachrichtTeil[1]),nachrichtTeil[2],nachrichtTeil[3],nachrichtTeil[4],nachrichtTeil[5],nachrichtTeil[6]);
        }

        else if(nachrichtTeil[0].equals("ADD")) {

            addNewProducts(pClientIP, pClientPort, Integer.valueOf(nachrichtTeil[1]), Integer.valueOf(nachrichtTeil[2]));

        }

        else if(nachrichtTeil[0].equals("BUY")) {

            buy(pClientIP, pClientPort);

        }

        else if(nachrichtTeil[0].equals("LOGOFF")) {

            logOff(pClientIP, pClientPort);

        }

        //         if(nachrichtTeil[0].equals("TSHIRT")) {
        //             send(pClientIP, pClientPort, "Die Groesse ist " + nachrichtTeil[1] + 
        //                                          ", die Farbe ist " + nachrichtTeil[2] + 
        //                                          " und es kostet 19,99 Euro! Bitte bestätigen Sie die Bestellung.");
        //         }
        // 
        //         else if(nachrichtTeil[0].equals("BESTAETIGUNG")) {
        //             if(nachrichtTeil [1] .equals("ja")) {  
        //                 send (pClientIP, pClientPort, "Vielen Dank für Ihre Bestellung."); 
        //                 closeConnection(pClientIP, pClientPort);  
        //             }
        //             else if (nachrichtTeil[1].equals("nein")) {
        //                 closeConnection(pClientIP, pClientPort);  
        //             } 
        //             else {  
        //                 send(pClientIP, pClientPort, "Bitte geben Sie ja oder nein ein.");
        //             }
        //         }
        //         
        //         else if(nachrichtTeil[0].equals("ABMELDEN")) {
        //             closeConnection(pClientIP, pClientPort);
        //         }
        //         
        //         else {
        //             this.send(pClientIP, pClientPort, "Bitte korrigieren Sie Ihre Eingabe.");
        //         }
    }

    public void processClosingConnection(String pClientIP, int pClientPort) {
        this.send(pClientIP, pClientPort, "Auf Wiedersehen!");
    }

    public void register(String pClientIP, int pClientPort, int id, String name, String address, String email, String creditCard, String pass){
        Account newAccount = new Account(id, name, address, email, creditCard, null);

        db.addAccount(newAccount,pass);

        accounts.put(clientIP, newAccount);
    }

    public void logIn(String pClientIP, int pClientPort, String email, String pass){
        if(db.getAccount(email,pass)!=null){
            Account newAccount = db.getAccount(email,pass);

            accounts.put(clientIP, newAccount);

        }
        else{
            send(pClientIP, pClientPort, "Nutzername oder Passwort falsch! Bitte überprüfen Sie Ihre Eingabe");
        }

        //ba.startTimer(accounts.get(pClientIP));
    }

    public void addNewProducts(String pClientIP, int pClientPort, int id, int amount) {
        int realAmount=-1;
        if(db.getProductById(id)!=null){
            Product newProduct = db.getProductById(id);

            i