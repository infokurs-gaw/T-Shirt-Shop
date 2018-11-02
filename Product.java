
/**
 * Beschreiben Sie hier die Klasse Produkt.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Product
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int id;
    private String type;
    private String name;
    private String description;
    private double price;
    private String color;
    private String size;

    /**
     * Konstruktor für Objekte der Klasse Produkt
     */
    public Product(int pId,String pType,String pName,String pDescription,double pPrice,String pColor,String pSize)
    {
        id = pId;
        type = pType;
        name = pName;
        description = pDescription;
        price= pPrice;
        color = pColor;
        size = pSize;
    }

    public String getType(){
        return type;
    }

    public String getColor(){
        return color;
    }

    public String getSize(){
        return size;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }
    
    public double getPrice(){
        return price;
    }

}
