public class ShopClient extends Client {

    public ShopClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
    }

    public void processMessage(String pMessage) {
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
}
