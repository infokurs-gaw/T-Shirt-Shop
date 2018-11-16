
/**
 * Beschreiben Sie hier die Klasse BehaviorAnalyzer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class BehaviorAnalyzer
{   
    public Product[] Testprodukte = new Product [8];
    public String[] Typ = new String[5];

    public BehaviorAnalyzer() {
        Testprodukte[1] = new Product(1,"Shirt","Gelb","1",15.22,"Gelb","XL");
        Testprodukte[2] = new Product(1,"Shirt2","Gelb","2",15.22,"Gelb","XL");
        Testprodukte[3] = new Product(1,"Shirt2","Gelb","3",15.22,"Gelb","XL");
        Testprodukte[4] = new Product(1,"Shirt4","Gelb","4",15.22,"Gelb","XL");
        Testprodukte[0] = new Product(1,"Shirt4","Gelb","0",15.22,"Gelb","XL");
        Testprodukte[5] = new Product(1,"Shirt4","Gelb","0",15.22,"Gelb","XL");
        Testprodukte[6] = new Product(1,"Shirt2","Gelb","3",15.22,"Gelb","XL");
        Testprodukte[7] = new Product(1,"Shirt2","Gelb","3",15.22,"Gelb","XL");
        Typ[0]= "Shirt";
        Typ[1]= "Shirt1";
        Typ[2]= "Shirt2";
        Typ[3]= "Shirt3";
        Typ[4]= "Shirt4";

    }

    /**
     * Liefert die 10 meist gekauften Produkte sortiert nach Typen(Tshirt,Pullover etc.)
     * Zurück gegeben werden soll ein array aus den besten 10 Produkten sortiert das [0] das beste 
     * Produkt ist
     */
    public String[] getTop10Products () {
        int length = 0;
        int a = 0;
        length = Testprodukte.length;  
        // Zwei Arrays Das erste Arry soll nur die Produkttypen enthalten 
        // Das zweite Array soll dann weiter die Anzahl an gekauften Objekten darstellen
        String[] ProductToCount = new String [Typ.length];
        int[] Counter = new int[Typ.length];
        ProductToCount = Typ;

        while(a<length){
            String typ = Testprodukte[a].getType();
            int b = 0;
            while(typ != Typ[b]){
                b++;   
                
            }            
            Counter[b] ++; 
            a++;
        }
        // Jetzt müssen die Array nur nach der Anzahl geordnet werden
        String[] Typtmp = new String[Typ.length];
        Typtmp = Typ;

        for (int j=Typ.length; j>0; j--) {
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
        // Das Array muss nur noch umgedreht werden damit das an erster Stelle das meistgekaufte Produkt ist
        String[] Typarray = new String [Typtmp.length];
        for(int c = 0; c<Typtmp.length; c++){
            int d = Typtmp.length;
            Typarray[c] = Typtmp[d-c-1];
        }
        
        return Typarray;
    }
    
    public String[] getType() {
        return Typ;
    }
}



















