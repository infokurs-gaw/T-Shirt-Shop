
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

    public Product[] getLastSoldProduc(Account account){
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

    public Product[] getLastSoldProduct ( Account account) {
        Product[] orders = new Product[getOrders().length];
        int length = getOrders().length
        orders = getOrders();
        Product [] SoldProducts = new Product[5];
        int id = account.getId;
        int a = 0;
        int b = 0;
        while(SoldProducts[4]!= null){
            if(orders[length-a].getId == id) {
                SoldProducts[b] = orders[length-a];
                b++;
            }
            a++
        }
        return SoldProducts[];

    }

    public Product[] getLastViewedObject(Account account){
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
