
/**
 * Beschreiben Sie hier die Klasse Account.
 * 
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Account {

    private int id;
    private String name;
    private String address;
    private String email;
    private String creditCard;
    private Produkt lastViewedProduct;

    /**
     * Konstruktor für Objekte der Klasse Account
     */
    public Account(int id, String name, String address, String email, String creditCard, Produkt lastViewedProduct) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.creditCard = creditCard;
        this.lastViewedProduct = lastViewedProduct;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getEmail(){
        return email;
    }

    public String getCreditCard(){
        return creditCard;
    }

    public Produkt getLastViewedProduct(){
        return lastViewedProduct;
    }
}
