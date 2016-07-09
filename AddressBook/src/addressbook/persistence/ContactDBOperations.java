/*
 * Copyright (C) 2016 Damiano Simoni
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package addressbook.persistence;

import addressbook.model.Contact;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Damiano Simoni
 */
public class ContactDBOperations {
    
    private DataSource source;
    private Statement stmt;
    private Connection c;
    private String sqlquery;
    private static final String nameTable="contacts"; 
    private static final String nameColumn="name";
    private static final String surnameColumn="surname";
    private static final String numberColumn="number";
    private static final String addressColumn="address";
    private static final String ageColumn="age";

    public ContactDBOperations() {
        
        this.source = new DataSource();
        
    }
    
    public void addContact(Contact contact){
        
        String name = contact.getName();
        String surname = contact.getSurname();
        String number = contact.getNumber();
        String address = contact.getAddress();
        int age = contact.getAge();
        
        try {   
            this.c = this.source.getConnection();
            this.stmt = c.createStatement();
            this.sqlquery = "INSERT INTO "+nameTable+"("+nameColumn+", "+ surnameColumn+", "+
                   numberColumn+", "+ addressColumn+", "+ ageColumn+") "+
                "VALUES ('"+name+"', '"+surname+"', '"+number+"', '"+address+
                    "', '"+age+"');";
        
            this.stmt.executeUpdate(sqlquery);
            this.stmt.close();
            this.c.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
    }
    
    public void updateContact(Contact contact, String nameOld,
            String surnameOld, String numberOld){
        
        String name = contact.getName();
        String surname = contact.getSurname();
        String number = contact.getNumber();
        String address = contact.getAddress();
        int age = contact.getAge();
        
        try {   
            this.c = this.source.getConnection();
            this.stmt = c.createStatement();
            this.sqlquery = "UPDATE "+nameTable+" SET "+nameColumn+"='"+name+"', "+
                    surnameColumn+"='"+surname+"', "+numberColumn+"='"+number+"', "+
                    addressColumn+"='"+address+"', "+ageColumn+"='"+age+"' "+
                "WHERE "+nameColumn+"='"+nameOld+"' AND "+surnameColumn+"='"+surnameOld+"' AND "+
                    numberColumn+"='"+numberOld+"';";
        
            this.stmt.executeUpdate(sqlquery);
            this.stmt.close();
            this.c.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
    }
    
    public void removeContact(Contact contact){
        
        String name = contact.getName();
        String surname = contact.getSurname();
        String number = contact.getNumber();
        
        try {   
            this.c = this.source.getConnection();
            this.stmt = c.createStatement();
            this.sqlquery = "DELETE FROM "+nameTable+" WHERE "+
                "name='"+name+"' AND surname='"+surname+"' AND number='"+number+"';";
        
            this.stmt.executeUpdate(sqlquery);
            this.stmt.close();
            this.c.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
    
    public List<Contact> getAllContacts(){
        
        List<Contact> result = new ArrayList<>();
        
        try {   
            this.c = this.source.getConnection();
            this.stmt = c.createStatement();
            this.sqlquery = "SELECT * FROM "+nameTable+";";
        
            ResultSet rs = this.stmt.executeQuery(sqlquery);
            Contact contact;
            while(rs.next()){
                contact = new Contact();
                contact.setName(rs.getString(nameColumn));
                contact.setSurname(rs.getString(surnameColumn));
                contact.setNumber(rs.getString(numberColumn));
                contact.setAddress(rs.getString(addressColumn));
                contact.setAge(rs.getInt(ageColumn));                
                result.add(contact);
            }
            
            rs.close();
            this.stmt.close();
            this.c.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
