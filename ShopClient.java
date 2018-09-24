public class ShopClient extends Client {
    
    public ShopClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
    }

    public void processMessage(String pMessage){
        System.out.println(pMessage);
    }
    
    public void groesseFarbeWaehlen(String pGroesse, String pFarbe) {
        send("TSHIRT:" + pGroesse +":" + pFarbe);
    }

    public void bestaetigen(String pAntwort) {
        send("BESTAETIGUNG:" + pAntwort);
    }

    public void abmelden() {
        send("ABMELDEN");
    }
}
