import java.util.*;
public class Basket
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen

    ArrayList<Product> products = new ArrayList<>();

    double totalPrice = 0;// Gesamtpreis 
    double amount; // Anzahl der Waren
    Account owner;
    
    //public String bestaetigen;
    /**
     * Konstruktor für Objekte der Klasse Basket
     */
    public Basket(Account owner)
    {
        this.owner = owner;
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
            products.add(product);
            double p = 0;
            p = product.getPrice();
            totalPrice= p + totalPrice;
            amount++;
        }
    }

    public void AttentionDeleteBasket()
    {
        totalPrice = 0;
        amount = 0;
        products.clear();    
    }

    public void displayBasket()
    {
        if(products.isEmpty())
        {
            System.out.println("--------START-------");
            System.out.println("Es befinden sich keine Artikel im Warenkorb");
            System.out.println("--------END-------");
        }
        else{
            System.out.println("--------START-------");
            for (int i = 0;i<products.size();i++){
                System.out.println("Sie bestellen:");
                System.out.println("Artikelart: " +products.get(i).getType());
                System.out.println("Artikelname: "+products.get(i).getName());
                System.out.println("Produktfarbe: "+products.get(i).getColor());
                System.out.println("Größe: " +products.get(i).getSize());
                System.out.println("Produktbeschreibung: "+products.get(i).getDescription());
                System.out.println("Das Produkt kostet: "+products.get(i).getPrice());

                System.out.println("-------");
            }

            System.out.println("Der Gesamtpreis beträgt: "+ totalPrice);

            System.out.println("für "+amount+" Artikel");
            System.out.println("--------END-------");
        }

    }

    

    public void deleteProduct(int id)
    {
        //for(Product p: products) {

        //p.
        //}

        for(int p = 0;p<amount ;p++)
        {
            if(id==products.get(p).getId())
            {
                System.out.println("Sie loeschen:");
                System.out.println("Artikelart: " +products.get(p).getType());
                System.out.println("Artikelname: "+products.get(p).getName());
                System.out.println("Produktfarbe: "+products.get(p).getColor());
                System.out.println("Größe: " +products.get(p).getSize());
                System.out.println("Produktbeschreibung: "+products.get(p).getDescription());
                System.out.println("Das Produkt kostet: "+products.get(p).getPrice());
                

                
                
                
                    double o = 0;
                    o = products.get(p).getPrice();
                    totalPrice -= o; //Math.abs(o - totalPrice);
                    amount--;
                    products.remove(p);
                    p--;  
                    
                

            }
        } 
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public double getProductCount(){
        return amount;
    }

    public void purchase(Order x)
    {
        Product[] pArray = new Product[products.size()];
        pArray = products.toArray(pArray);
       x.setProducts(pArray);
    }

    public Account getOwner ()
    {
        return owner;
    }

   
}

