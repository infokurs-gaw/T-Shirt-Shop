import java.util.Date;
import java.text.SimpleDateFormat;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * Beschreiben Sie hier die Klasse DatabaseAccess.
 * 
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class DatabaseAccess {
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private DatabaseConnector dbConnector;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * Konstruktor für Objekte der Klasse DatabaseAccess
     */
    public DatabaseAccess() {
        // Instanzvariable initialisi eren
        // https://bitbucket.org/xerial/sqlite-jdbc/downloads/ Driver must be
        // installed!!!
        this.init();
    }

    public void init() {
        try {

            String dbName = "datenbank2.db";
            Path dbPath = Paths.get(dbName);

            if (!Files.exists(dbPath)) {
                this.dbConnector = new DatabaseConnector("", 0, dbName, "", "");
                String sql = new String(Files.readAllBytes(Paths.get("create-queries.sql")));
                String[] queries = sql.split(";");

                for (int i = 0; i < queries.length; i++) {
                    this.dbConnector.executeStatement(queries[i]);
                }

                System.out.println("SQLite Database " + dbName
                        + " should now be successfully initialized and populated. Continuing...");

            } else {
                System.out.println("Skipping init... db file already exists.");
                this.dbConnector = new DatabaseConnector("", 0, dbName, "", "");

                String m = this.dbConnector.getErrorMessage();

                System.out.println(m);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main() {
        DatabaseAccess d = new DatabaseAccess();
        d.getProducts();
    }

    private String extractField(String fieldName, String[] colNames, String[] row) {
        int index = -1;
        for (int i = 0; i < row.length; i++) {
            if (colNames[i].equals(fieldName)) {
                index = i;
                break;
            }
        }

        return row[index];

    }

    public void logLogin(Account a) {
        this.dbConnector
                .executeStatement("INSERT INTO event_log(account_id, event) VALUES (" + a.getId() + ", 'LOGIN');");
    }

    public void logLogout(Account a) {
        this.dbConnector
                .executeStatement("INSERT INTO event_log(account_id, event) VALUES (" + a.getId() + ", 'LOGOUT');");
    }

    public Date[] getLoginDates(Account a) {
        this.dbConnector
                .executeStatement("SELECT * from event_log el WHERE event = 'LOGIN' AND account_id = " + a.getId());
        QueryResult res = this.dbConnector.getCurrentQueryResult();

        if (res != null) {
            String[] cols = res.getColumnNames();

            Date[] dates = new Date[res.getRowCount()];

            for (int i = 0; i < res.getRowCount(); i++) {

                String[] row = res.getData()[i];

                String timestamp = this.extractField("timestamp", cols, row);
                try {
                    dates[i] = this.df.parse(timestamp);
                } catch (Exception e) {
                    dates[i] = null;
                }
            }

            return dates;
        } else {
            System.out.println(dbConnector.getErrorMessage());

            return null;
        }
    }

    public Date[] getLogoutDates(Account a) {
        this.dbConnector
                .executeStatement("SELECT * from event_log el WHERE event = 'LOGOUT' AND account_id = " + a.getId());
        QueryResult res = this.dbConnector.getCurrentQueryResult();

        if (res != null) {
            String[] cols = res.getColumnNames();

            Date[] dates = new Date[res.getRowCount()];

            for (int i = 0; i < res.getRowCount(); i++) {

                String[] row = res.getData()[i];

                String timestamp = this.extractField("timestamp", cols, row);
                try {
                    dates[i] = this.df.parse(timestamp);
                } catch (Exception e) {
                    dates[i] = null;
                }
            }

            return dates;
        } else {
            System.out.println(dbConnector.getErrorMessage());

            return null;
        }
    }

    public DatabaseConnector getConnector() {
        return this.dbConnector;
    }

    public Product getProductById(int pId) {
        this.dbConnector.executeStatement(
                "SELECT s.id, p.name, color, size, type, price, description from stock s JOIN colors c ON s.color_id = c.id JOIN products p on p.id = s.product_id JOIN sizes si ON s.size_id = si.id JOIN product_types pt ON pt.id = p.product_type_id WHERE s.id = "
                        + pId + " LIMIT 1;");
        QueryResult res = this.dbConnector.getCurrentQueryResult();

        if (res != null) {
            String[] cols = res.getColumnNames();

            Product[] prod = new Product[res.getRowCount()];

            if (res.getRowCount() > 0) {
                String[] row = res.getData()[0];

                int id = Integer.parseInt(this.extractField("id", cols, row));
                double price = Double.parseDouble(this.extractField("price", cols, row));
                String name = this.extractField("name", cols, row), color = this.extractField("color", cols, row),
                        size = this.extractField("size", cols, row), type = this.extractField("type", cols, row),
                        description = this.extractField("description", cols, row);

                return new Product(id, type, name, description, price, color, size);
            } else {
                return null;
            }

        } else {
            System.out.println(dbConnector.getErrorMessage());

            return null;
        }

    }

    public int getAvailableAmountForProductInStock(Product p) {
        this.dbConnector.executeStatement("SELECT s.id, amount FROM stock s WHERE s.id = " + p.getId() + " LIMIT 1;");
        QueryResult res = this.dbConnector.getCurrentQueryResult();

        if (res != null) {
            String[] cols = res.getColumnNames();

            Product[] prod = new Product[res.getRowCount()];

            if (res.getRowCount() > 0) {
                String[] row = res.getData()[0];
                int amount = Integer.parseInt(this.extractField("amount", cols, row));

                return amount;
            } else {
                return -1;
            }

        } else {
            System.out.println(dbConnector.getErrorMessage());

            return -1;
        }
    }

    public Product[] getProducts() {
        this.dbConnector.executeStatement(
                "SELECT s.id, p.name, color, size, type, price, description from stock s JOIN colors c ON s.color_id = c.id JOIN products p on p.id = s.product_id JOIN sizes si ON s.size_id = si.id JOIN product_types pt ON pt.id = p.product_type_id;");
        QueryResult res = this.dbConnector.getCurrentQueryResult();

        if (res != null) {
            String[] cols = res.getColumnNames();

            Product[] prods = new Product[res.getRowCount()];

            for (int i = 0; i < res.getRowCount(); i++) {

                String[] row = res.getData()[i];

                int id = Integer.parseInt(this.extractField("id", cols, row));
                double price = Double.parseDouble(this.extractField("price", cols, row));
                String name = this.extractField("name", cols, row), color = this.extractField("color", cols, row),
                        size = this.extractField("size", cols, row), type = this.extractField("type", cols, row),
                        description = this.extractField("description", cols, row);

                prods[i] = new Product(id, type, name, description, price, color, size);

            }

            return prods;
        } else {
            System.out.println(dbConnector.getErrorMessage());

            return null;
        }

    }
}
