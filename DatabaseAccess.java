
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
        Product p = d.getProductById(1);
        System.out.println(d.getAvailableAmountForProductInStock(p));
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

    public DatabaseConnector getConnector(){
        return this.dbConnector;
    }

    public Product getProductById(int pId){
        this.dbConnector.executeStatement("SELECT s.id, p.name, color, size, type, price, description from stock s JOIN colors c ON s.color_id = c.id JOIN products p on p.id = s.product_id JOIN sizes si ON s.size_id = si.id JOIN product_types pt ON pt.id = p.product_type_id WHERE s.id = " + pId + " LIMIT 1;");
        QueryResult res = this.dbConnector.getCurrentQueryResult();

        if (res != null) {
            String[] cols = res.getColumnNames();

            Product[] prod = new Product[res.getRowCount()];

            if(res.getRowCount() > 0){
                String[] row = res.getData()[0];

                int id = Integer.parseInt(this.extractField("id", cols, row));
                double price = Double.parseDouble(this.extractField("price", cols, row));
                String name = this.extractField("name", cols, row), 
                color = this.extractField("color", cols, row), 
                size = this.extractField("size", cols, row), 
                type = this.extractField("type", cols, row), 
                description = this.extractField("description", cols, row);

                return new Product(id, type, name, description, price, color, size);
            }else{
                return null;
            }

        } 
        else {
            System.out.println(dbConnector.getErrorMessage());

            return null;
        }

    }
    public int getAvailableAmountForProductInStock(Product p){
        this.dbConnector.executeStatement("SELECT s.id, amount FROM stock s WHERE s.id = " + p.getId() + " LIMIT 1;");
        QueryResult res = this.dbConnector.getCurrentQueryResult();

        if (res != null) {
            String[] cols = res.getColumnNames();

            Product[] prod = new Product[res.getRowCount()];

            if(res.getRowCount() > 0){
                String[] row = res.getData()[0];
                int amount = Integer.parseInt(this.extractField("amount", cols, row));

                return amount;
            }else{
                return -1;
            }

        } 
        else {
            System.out.println(dbConnector.getErrorMessage());

            return -1;
        }
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


    public void getAccount(String user, String password)
    {
        // tragen Sie hier den Code ein

        DatabaseConnector dbConnector = new DatabaseConnector("",0,"DatabaseAccess.db","","");
        dbConnector.executeStatement("select * from accounts where email = '" + user + "'AND password = '" + password + "';");
        QueryResult res = dbConnector.getCurrentQueryResult();
        if (res != null) {
            for (int i = 0; i < res.getRowCount(); i++) {
                System.out.println(res.getData()[i][0]);
            }
        } 
        else {
            System.out.println(dbConnector.getErrorMessage());
        }
        dbConnector.close();
    }
}
