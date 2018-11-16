
import java.util.*;
import java.io.*;

public class ShopServer extends Server {

    Map<String, Account> accounts = new HashMap<String, Account>();

    String clientIP;

    public ShopServer(int pPortnummer) {
        super(pPortnummer);
    }

    public void processNewConnection(String pClientIP, int pClientPort) {
        clientIP = pClientIP;

        send(pClientIP, pClientPort, "Willkommen! Wählen Sie eine Größe und eine Farbe für Ihr T-Shirt.");
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        System.out.println(pClientIP + ": " + pMessage);

        String[] nachrichtTeil = pMessage.split(":");

        if(nachrichtTeil[0].equals("LOGIN")){
            logIn(pClientIP,nachrichtTeil[1],nachrichtTeil[2]);
        }

        else if(nachrichtTeil[0].equals("REGISTER")) {
            //register(nachrichtTeil[1],nachrichtTeil[2],nachrichtTeil[3],nachrichtTeil[4],nachrichtTeil[5],nachrichtTeil[6]);
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

    public void register(int id, String name, String address, String email, String creditCard, String pass){
        Account newAccount = new Account(id, name, address, email, creditCard, null);

        /*
         *  Hier Account zu Datenbank hinzuf�gen!
         *  
         *  
         */

        accounts.put(clientIP, newAccount);
    }

    public void logIn(String pClientIP, String email, String pass){
        /*
         * Account newAccount = db.getAccount(email);
         * 
         * accounts.put(clientIP, newAccount);
         * 
         * ba.startTimer(accounts.get(pClientIP));
         */
    }

    public void addNewProducts(String pClientIP, int pClientPort, String pMessage) {
        /*
         * String[] nachrichtTeil = pMessage.split(":");     *** Erwartete Form "id:amount" ***
         * 
         * Product newProduct = db.getProduct(nachrichtTeil[0]);
         * 
         * basket.addProduct(newProduct, nachrichtTeil[1], accounts.get(pClientIP), );
         * 
         */
    }

    public void buy(String pClientIP, int pClientPort){
        /* 
         * Order newOrder = new Order(-1, basket.getProducts(), accounts.get(pClientIP), "AUFGEGEBEN");
         * 
         * db.addOrder(newOrder);
         * 
         */
    }

    public void logOff(String pClientIP, int pClientPort){
        accounts.remove(pClientIP);

        /*
         * ba.stopTimer(accounts.get(pClientIP));
         * 
         */
    }
}
