package core;

public class ShopClient extends Client {

    public ShopClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
    }

    public void processMessage(String pMessage){
        System.out.println(pMessage);
    }

    //     public void groesseFarbeWaehlen(String pGroesse, String pFarbe) {
    //         send("TSHIRT:" + pGroesse +":" + pFarbe);
    //     }
    // 
    //     public void bestaetigen(String pAntwort) {
    //         send("BESTAETIGUNG:" + pAntwort);
    //     }
    // 
    //     public void abmelden() {
    //         send("ABMELDEN");
    //     }
    
    public void logIn(String email, String pass) {
             send("LOGIN:" + email +":" + pass);
         }
    public void register(int id, String name, String address, String email, String creditCard, String pass) {
             send("REGISTER:" + id +":" + name +":" + address +":" + email +":" + creditCard +":" + pass);
         }
    public void addNewProducts(int id, int amount) {
             send("ADD:" + id +":" + amount);
         }
    public void buy() {
             send("BUY");
         }
    public void logOff() {
             send("LOGOFF");
         }
}
