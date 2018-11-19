
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

        // Create object of account that should be added

        Account newAccount = new Account(-1, "Hans Müller", "Musterstraße 13", "hans@müller.de", "0000-1111-222", null);

        // If everything works correctly, this should output "true"
        System.out.println(d.addAccount(newAccount, "123456789"));
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
    }

    public Account getAccount(String user, String password)
    {
        this.dbConnector.executeStatement("select * from accounts where email = '" + user + "' AND password = '" + password + "';");
        QueryResult res = this.dbConnector.getCurrentQueryResult();
        if (res != null) {            
            String[] cols = res.getColumnNames();
            if(res.getRowCount() > 0){
                String[] row = res.getData()[0];
                int id = Integer.parseInt(this.extractField("id", cols, row));
                String name = this.extractField("name", cols, row);
                String address = this.extractField("address", cols, row);
                String email = this.extractField("email", cols, row);
                String creditCard = this.extractField("credit_card", cols, row);
                int last_viewed_product_id = Integer.parseInt(this.extractField("last_viewed_product_id", cols, row));
                
                return new Account(id, name, address, email, creditCard, getProductById(last_viewed_product_id));
            }else{
                return null;
            }
        } 
        else {
            System.out.println(this.dbConnector.getErrorMessage());
            return null;
        }

    }

    public boolean  addAccount(Account newAccount, String password)
    {

        // Switched to use of formatted string because it is easier to read and write.
        this.dbConnector.executeStatement(String.format("INSERT INTO accounts(name, address, email, password, credit_card) VALUES ('%s', '%s', '%s', '%s', '%s');", newAccount.getName(), newAccount.getAddress(), newAccount.getEmail(), password, newAccount.getCreditCard()));

        // If there is no error, the method will return true.
        return this.dbConnector.getErrorMessage() == null;        
    }
}
