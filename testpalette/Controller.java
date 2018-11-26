/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpalette;

/**
 *
 * @author marlon.rosendahl
 */
public class Controller {
    
    Home home;
    LoginLogout loginLogout;
    Warenkorb warenkorb;
    Produktänderung produktänderung;
    Login login;
    VielenDank vielendank;
    
    public Controller() {
        home = new Home(this);
        loginLogout = new LoginLogout(this);
        warenkorb = new Warenkorb(this);
        produktänderung = new Produktänderung(this);
        login = new Login(this);
        vielendank = new VielenDank(this);
        
        login.setVisible(true);
    }
    
    public void showFrame(String name) {
        if(name.equals("warenkorb")){
            warenkorb.setVisible(true);
        }
        if(name.equals("loginLogout")){
            loginLogout.setVisible(true);
        }
        if(name.equals("home")){
            home.setVisible(true);
        }
        
        if(name.equals("produktänderung")){
            produktänderung.setVisible(true);
        }
        if(name.equals("vielendank")){
            vielendank.setVisible(true);
    }
}
}

