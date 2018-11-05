
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
    
    private String extractField(String fieldName, String[] colNames, String[] row){
        int index = -1;
        for(int i = 0; i < row.length; i++){
             if(colNames[i].equals(fieldName)){
                 index = i;
                 break;
             }
        }
        
        return row[index];
        
    }

    public Product[] getProducts(){
        this.dbConnector.executeStatement("SELECT s.id, p.name, color, size, type, price, description from stock s JOIN colors c ON s.color_id = c.id JOIN products p on p.id = s.product_id JOIN sizes si ON s.size_id = si.id JOIN product_types pt ON pt.id = p.product_type_id;");
        QueryResult res = this.dbConnector.getCurrentQueryResult();
      
        if (res != null) {
            String[] cols = res.getColumnNames();
            
            Product[] prods = new Product[res.getRowCount()];
            
            for (int i = 0; i < res.getRowCount(); i++) {
                
                String[] row = res.getData()[i];
                
                int id = Integer.parseInt(this.extractField("id", cols, row));
                double price = Double.parseDouble(this.extractField("price", cols, row));
                String name = this.extractField("name", cols, row), 
                       color = this.extractField("color", cols, row), 
                       size = this.extractField("size", cols, row), 
                       type = this.extractField("type", cols, row), 
                       description = this.extractField("description", cols, row);

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
