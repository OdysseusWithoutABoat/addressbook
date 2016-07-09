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
package addressbook.controller;

import addressbook.model.Contact;
import addressbook.persistence.ContactDBOperations;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Damiano Simoni
 */
public class ControllerContacts {

    ContactDBOperations operations;
    Contact oldContact;
    
    public ControllerContacts() {
        this.operations = new ContactDBOperations();
    }
    
    public void fillTable(DefaultTableModel model){
        
        List<Contact> contacts;        
        contacts = (ArrayList<Contact>) operations.getAllContacts();
        
        model.setRowCount(0);
        String name, surname, number, address;
        int age;
        
        for(Contact contact: contacts){          
            name = contact.getName();
            surname = contact.getSurname();
            number = contact.getNumber();
            address = contact.getAddress();
            age = contact.getAge();
            model.addRow(new Object[]{name, surname, number, address, age});
        }
        
    }
    
    public void updateContacts(String name, String surname, String number,
            String address, String age, int mode){
        
        Contact contact = new Contact(name, surname, number,
                address, convertAge(age));
        if(mode == 1){
            operations.addContact(contact);
        }
        else if(mode == 2)
            operations.updateContact(contact, oldContact.getName(),
                 oldContact.getSurname(), oldContact.getNumber());
    }
    
    public int removeContact(int row, DefaultTableModel model){
        Vector vector = (Vector) model.getDataVector().elementAt(row);
        Contact contact = new Contact();
        contact.setName((String) vector.get(0));
        contact.setSurname((String) vector.get(1));
        contact.setNumber((String) vector.get(2));
        int reply = JOptionPane.showConfirmDialog(null, "Eliminare il contatto '"+contact.getName()+" "+
            contact.getSurname()+"'?", "Conferma", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION)           
            operations.removeContact(contact);
        return reply;
    }
    
    private int convertAge(String age){
        int ageN;
        try{
            ageN = Integer.parseInt(age);
        }
        catch(NumberFormatException e){
            ageN = 0;
        }
        return ageN;
    }
    
    public void setOldContact(int row, DefaultTableModel model){
        Vector vector = (Vector) model.getDataVector().elementAt(row);
        Contact oldContact = new Contact();
        oldContact.setName((String) vector.get(0));
        oldContact.setSurname((String) vector.get(1));
        oldContact.setNumber((String) vector.get(2));
        oldContact.setAddress((String) vector.get(3));
        oldContact.setAge((Integer) vector.get(4));
        this.oldContact = oldContact;
    }

    public Contact getOldContact() {
        return oldContact;
    }
    
    public boolean checkFields(String name, String surname, String number){
        return (name.isEmpty() || surname.isEmpty() || number.isEmpty());
    }
    
}
