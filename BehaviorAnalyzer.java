import java.util.Date;
import java.sql.Timestamp;
/**
 * Beschreiben Sie hier die Klasse BehaiviorAnalyzer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class BehaviorAnalyzer
{
    public Product lastViewedProduct;
    public Product lastSoldProducts;

    /**
     * Konstruktor für Objekte der Klasse BehaiviorAnalyzer
     */
    public BehaviorAnalyzer()
    {
        // Instanzvariable initialisieren

    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    /*
    public long startTimer(Account account){
    Date date= new Date();
    long starttime= date.getTime();
    return starttime;    
    }*/

    /*public long stopTimer(Account account){

    Date date= new Date();
    long dat= date.getTime();
    long time= dat;
    return time;
    }*/
    public void startTimer(Account account){
        //Datenbank.logLogin(account);
    }

    public void stopTimer(Account account){  
        //Datenbank.logLogout(account);
    }

    public long timespend(Account account){
        Date[] loginDates = DatabaseAccess.getLoginDates(account);
        Date[] logoutDates = DatabaseAccess.getLogoutDates(account);
        long time= 0;
        for(int i=0; i<loginDates.length; i++){
            long a = loginDates[i].getTime();
            long b = logoutDates[i].getTime();
            time = time + (b-a); 
        }
        if(loginDates.length!=logoutDates.length){
            long a = loginDates[loginDates.length-1].getTime();
            long b = date.getTime();
            time = time + (b-a); 
        }
        return time;
    }
}
