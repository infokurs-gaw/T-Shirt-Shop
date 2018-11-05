import java.util.*;
public class Basket
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    
    ArrayList<Product> basket = new ArrayList<>();
    public String[] arraylocal;
    double totalprice = 0;// Gesamtpreis 
    
    String benutzer;// Benutzernahme
    double amount; // Anzahl der Waren
    /**
     * Konstruktor für Objekte der Klasse Basket
     */
    public Basket()
    {

    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public void addProduct(Product product,int anzahl )
    {
        for(int i = 0; i < anzahl; i++){
        basket.add(product);
        double p = 0;
        p = product.getPrice();
        totalprice= p + totalprice;
        amount++;
    }
    }

    public void deleteBasket()
    {
        totalprice = 0;
        basket.clear();    }

    public void displayBasket()
    {
        if(basket.isEmpty())
        {
            System.out.println("--------START-------");
            System.out.println("Es befinden sich keine Artikel im Warenkorb");
            System.out.println("--------END-------");
        }
        else{
            System.out.println("--------START-------");
            for (int i = 0;i<basket.size();i++){
                System.out.println("Sie bestellen:");
                System.out.println(basket.get(i).getType());
                System.out.println(basket.get(i).getName());
                System.out.println(basket.get(i).getColor());
                System.out.println(basket.get(i).getSize());
                System.out.println(basket.get(i).getDescription());
                System.out.println("Das Produkt kostet:");
                System.out.println(basket.get(i).getPrice());
                System.out.println("-------");
            }
            System.out.println("Der Gesamtpreis beträgt:");
            System.out.println(totalprice); 
            System.out.println("--------END-------");
        }

    }

    public void deleteProduct(Product product)
    {
        //for
        double p = 0;
        p = product.getPrice();
        totalprice= Math.abs(p - totalprice);
        amount--;
        basket.remove(product);
    }

    public double getTotalPrice()
    {
        return totalprice;
    }
    public void basketSaven()
    {
     
     this.arraylokal = basket;
    
    
    
    
    }
    
    public void purchase()
    {
    
    }
}
















