
/**
 * Beschreiben Sie hier die Klasse DatabaseAccess.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class DatabaseAccess
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private DatabaseConnector dbConnector;

    /**
     * Konstruktor für Objekte der Klasse DatabaseAccess
     */
    public DatabaseAccess()
    {
        // Instanzvariable initialisi   eren
        //https://bitbucket.org/xerial/sqlite-jdbc/downloads/ Driver must be installed!!!
        this.dbConnector = new DatabaseConnector("", 0, "datenbank.db", "", "");
    }

    public static void main(){
        DatabaseAccess d = new DatabaseAccess();
        d.getProducts();
    }

    public Product[] getProducts(){
        this.dbConnector.executeStatement("SELECT s.id, p.name, color, size, type, price, description from stock s JOIN colors c ON s.color_id = c.id JOIN products p on p.id = s.product_id JOIN sizes si ON s.size_id = si.id JOIN product_types pt ON pt.id = p.product_type_id;");
        QueryResult res = this.dbConnector.getCurrentQueryResult();
      
        if (res != null) {
            String[] cols = res.getColumnNames();
            
            Product[] prods = new Product[res.getRowCount()];
            
            for (int i = 0; i < res.getRowCount(); i++) {
                
                int id = -1;
                double price = 0;
                String name = "", color = "", size = "", type = "", description = "";

                for(int j = 0; j < res.getData()[i].length; j++){
                    String data = res.getData()[i][j];
                    switch (cols[j]){
                        case "id":
                           id = Integer.parseInt(data);
                           break;
                        case "name":
                            name = data;
                            break;
                        case "color":
                            color = data;
                            break;
                        case "size":
                            size = data;
                            break;
                        case "type":
                            type = data;
                            break;
                        case "description":
                            description = data;
                            break;
                        case "price":
                            price = Double.parseDouble(data);
                            break;
                    }
                        
                }

                prods[i] = new Product(id, type, name, description, price, color, size);

            }
            
            return prods;
        } 
        else {
            System.out.println(dbConnector.getErrorMessage());
            
            return null;
        }


    }
}
