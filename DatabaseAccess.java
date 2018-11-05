
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
        // Instanzvariable initialisi	eren
        this.dbConnector = new DatabaseConnector("", 0, "datenbank.db", "", "");
    }

    public static void main(){
        DatabaseAccess d = new DatabaseAccess();
        d.getProducts();
    }

    public Product[] getProducts(){
        this.dbConnector.executeStatement("SELECT * from products;");
        QueryResult res = this.dbConnector.getCurrentQueryResult();

        if (res != null) {
            String[] cols = res.getColumnNames();
            for (int i = 0; i < res.getRowCount(); i++) {

                for(int j = 0; j < res.getData()[i].length; j++){
                    System.out.println(cols[j] + " " + res.getData()[i][j]);
                }

                System.out.println("-------");

            }
        } 
        else {
            System.out.println(dbConnector.getErrorMessage());
        }

        return new Product[]{};
    }
}
