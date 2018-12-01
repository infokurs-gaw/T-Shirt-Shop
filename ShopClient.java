import java.util.HashMap;
import java.util.Date;

public class ShopClient extends Client {

    private String lastMessage = "";

    public ShopClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
    }

    public void processMessage(String pMessage) {
        lastMessage = pMessage;
        System.out.println(pMessage);
    }

    public void logIn(String email, String pass) {
        send("LOGIN:" + email + ":" + pass);
    }

    public void register(int id, String name, String address, String email, String creditCard, String pass) {
        send("REGISTER:" + name + ":" + address + ":" + email + ":" + creditCard + ":" + pass);
    }

    public void addNewProducts(int id, int amount) {
        send("ADD:" + id + ":" + amount);
    }

    public void buy() {
        send("BUY");
    }

    public void logOff() {
        send("LOGOFF");
    }

    public void closeConnection() {
        send("CLOSE_CONNECTION");
    }

    public Product getProductInfoById(int id) {
        send("GET_PRODUCT_INFO_BY_ID:" + id);

        ProtocolDefinition def = new ProtocolDefinition("PRODUCT_INFO:<id>:<type>:<name>:<desc>:<price>:<color>:<size>",
                null);

        Date startDate = new Date();

        while (!def.matchesDefinition(lastMessage) && (new Date().getTime()-startDate.getTime())/1000 < 10) {
            //System.out.println("WAITING...");
        }


        if((new Date().getTime()-startDate.getTime())/1000 >= 10){
            System.out.println("Could not get Product, timeout of 10 seconds expired.");
            return null;
        }else{
            HashMap<String, String> map = def.extractInformation(lastMessage);

            return new Product(Integer.parseInt(map.get("id")), map.get("type"), map.get("name"), map.get("desc"),
                    Double.parseDouble(map.get("price")), map.get("color"), map.get("size"));
        }

       
    }
}
