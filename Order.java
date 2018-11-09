import java.util.Date;
import java.util.*;
/**
 * Beschreiben Sie hier die Klasse Bestellung.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Order
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int id;
    private Product[] products;
    private Account account;
    private Date date;
    private String status;
    ArrayList<Product> orderList = new ArrayList<>();//Obejekt beeinhaltet Liste von bestellten Produkten und den zugehoerigen Account

    public Order(int id, Product[] products, Account account, Date date, String status)
    {
        this.id = id;
        this.products = products;
        this.account = account;
        this.date = date;
        this.status = status;
    }
    
    public int getId(){
        return id;
    }
    
    public Product[] getProducts(){
        return products;
    }
    
    public Account getAccount(){
        return account;
    }
    
    public Date getDate(){
        return date;
    }
    
    public String getStatus(){
        return status;
    }

  
}
