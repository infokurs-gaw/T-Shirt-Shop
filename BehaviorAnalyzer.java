
/**
 * Beschreiben Sie hier die Klasse BehaviorAnalyzer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class BehaviorAnalyzer
{
    public Product lastViewedProduct;
    public Product lastSoldProducts;
    public int datetime;
    // anzahl an suzugeben produkten

    /**
     * Konstruktor für Objekte der Klasse BehaviorAnalyzer
     */

    public BehaviorAnalyzer()
    {

    }

    public Product[] getLastSoldProduct(Account account){
        int id ;
        int val = 5;
        id = account.getId();
        Product[] order= new Product[5];
        order= giveOrders(id);
        new array (giveOrders) = array;
        //datenbank abfragen und array bekommen 
        //array muss order heißen
        if(order.length=!0){
            return Product(); 
            int a = array.length;
            Product[] tmp = new Product[val];
            for(int a = 0;a>5;a++) {
                tmp[a] = array[a];
            }
            return tmp;
        }
        else{
            return null;
        }
    }

    public Product getLastViewedObject(Account account){
        Product obj = new Product[1];
        obj = getAccount.lastViewedProduct();
        if(obj.length =0){
            return null;
        }
        else{
            return obj;
        }
    }
}
