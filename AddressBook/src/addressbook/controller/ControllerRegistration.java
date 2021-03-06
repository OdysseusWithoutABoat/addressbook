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

import addressbook.model.User;
import addressbook.persistence.UserDBOperations;

/**
 *
 * @author Damiano Simoni
 */
public class ControllerRegistration {
    
    UserDBOperations operationsDB;
    String error;

    public ControllerRegistration() {
        
        operationsDB = new UserDBOperations();
        this.error="";
        
    }
    
    public String doRegistration(String username, String password){
        User user = new User(username, password);
        
        if(!checkData(username, password))
            callDB(user);
             
        return this.error;
    }
    
    private boolean checkData(String username,String password){
        boolean result = false;
        if(username.length()<5 || password.length()<8){
            this.error = "Il nome e la password devono essere lunghi rispettivamente almeno 5 e 8 caratteri.";
            result = true;
        }       
        return  result;
    }
    
    private void callDB(User user){
        if(!operationsDB.containsUser(user)){
            operationsDB.createUser(user);             
        }
        else
            error = "L'utente è già registrato";       
    }
    
}
