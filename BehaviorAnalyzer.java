
/**
 * Beschreiben Sie hier die Klasse BehaviorAnalyzer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class BehaviorAnalyzer
{   
    public Product[] Orders = new Product [8]; // Das muss entfernt sein
    public String[] Typ = new String[5]; // Das muss entfernt sein

    public Product lastViewedProduct;
    public Product lastSoldProducts;
    public int datetime;
    // anzahl an suzugeben produkten

    public BehaviorAnalyzer() {
       Orders[1] = new Product(1,"Shirt","Gelb","1",15.22,"Gelb","XL");// Das folgende muss entfernt werden
        Orders[2] = new Product(1,"Shirt2","Gelb","2",15.22,"Gelb","XL");
        Orders[3] = new Product(1,"Shirt2","Gelb","3",15.22,"Gelb","XL");
        Orders[4] = new Product(1,"Shirt4","Gelb","4",15.22,"Gelb","XL");
        Orders[0] = new Product(1,"Shirt4","Gelb","0",15.22,"Gelb","XL");
        Orders[5] = new Product(1,"Shirt4","Gelb","0",15.22,"Gelb","XL");
        Orders[6] = new Product(1,"Shirt2","Gelb","3",15.22,"Gelb","XL");
        Orders[7] = new Product(1,"Shirt2","Gelb","3",15.22,"Gelb","XL");
        Typ[0]= "Shirt";
        Typ[1]= "Shirt1";
        Typ[2]= "Shirt2";
        Typ[3]= "Shirt3";
        Typ[4]= "Shirt4";

    }

    /**
     * Liefert die 10 meist gekauften Produkte sortiert nach Typen(Tshirt,Pullover etc.)
     * Zur�ck gegeben werden soll ein array aus den besten 10 Produkten sortiert das [0] das beste 
     * Produkt ist
     */
    public String[] getTop10Products () {        
        //Die ben�tigten Arrays der Datenbank werden gespeichert
             
        //Product[] Orders = new Product [DatabaseAccess.getOrders.length];// Muss rein
        // Orders[] = databaseAccess.getOrders[];// Muss rein
     
        //String[] Typ = new String [DatabaseAccess.getProducts.length];// Muss rein
        // Typ[] = DatabaseAccess.getProducts; // Muss rein
        
        // Variablen die z�hlen sollen
        
        int a = 0;

        int[] Counter = new int[Typ.length]; // Das zweite Array das die selbe l�nge wie die Menge an Typen hat um parralel zu speichern wie oft
        // ein Produkt gekauft wurde
        
        // Er geht durch das Array durch und f�r jedes gekaufte Produkt setzt er den Z�hler h�her

        while(a<Orders.length){
            String typtmp = Orders[a].getType();
            int b = 0;
            while(typtmp != Typ[b]){
                b++;   
                
            }            
            Counter[b] ++; 
            a++;
        }
        // Jetzt muss das Array nur nach der Anzahl geordnet werden hier mit einem Bubblesort aber zuvor wird ein Temp�r�res Array erstellt da
        // das normale Array noch gebraucht wird
        String[] Typtmp = new String[Typ.length];
        Typtmp = Typ;

        for (int j=Typtmp.length; j>0; j--) {
            for (int i=0; i<j-1; i++) {
                if (Counter[i] > Counter[i+1]) {
                    int temp = Counter[i];
                    Counter[i] = Counter[i+1];
                    Counter[i+1] = temp;
                    String tmp2 = Typtmp[i];
                    Typtmp[i] = Typtmp[i+1];
                    Typtmp[i+1] = tmp2;
                }
            }
        }
        // Das Array muss nur noch umgedreht werden damit das an erster Stelle das meistgekaufte Produkt ist, da der Bubbleso
        
        String[] Typarray = new String [Typtmp.length];
        int d = Typtmp.length;
        for(int c = 0; c<Typtmp.length; c++){            
            Typarray[c] = Typtmp[d-c-1];
        }
        // Das sortierte Array wird zur�ck gegeben
        return Typarray;
    }
    
    public String[] getType() {
        return Typ;
    }


    public Product[] getLastSoldProduc(Account account){
        int id ;
        int val = 5;
        id = account.getId();
        Product[] order= new Product[5];
        order= giveOrders(id);
        new array (giveOrders) = array;
        //datenbank abfragen und array bekommen 
        //array muss order hei�en
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
