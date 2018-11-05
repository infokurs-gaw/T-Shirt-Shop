
/**
 * Beschreiben Sie hier die Klasse DatabaseAccess.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class DatabaseAccess
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int x;

    /**
     * Konstruktor für Objekte der Klasse DatabaseAccess
     */
    public DatabaseAccess()
    {
        // Instanzvariable initialisieren
        x = 0;
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
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
