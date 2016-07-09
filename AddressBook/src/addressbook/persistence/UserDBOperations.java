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

import addressbook.model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Damiano Simoni
 */
public class UserDBOperations {
    
    private DataSource source;
    private Statement stmt;
    private Connection c;
    private String sqlquery;
    private static final String nameTable="users"; 
    private static final String usernameColumn="username";
    private static final String passwordColumn="password";

    public UserDBOperations() {
        
        this.source = new DataSource();
        
    }
    
    public void createUser(User user){
        
        String username = user.getUsername();
        String password = user.getPassword();
        
        try {   
            this.c = this.source.getConnection();
            this.stmt = c.createStatement();
            this.sqlquery = "INSERT INTO "+nameTable+"("+usernameColumn+", "+ passwordColumn+") "+
                "VALUES ('"+username+"', '"+password+"');";
        
            this.stmt.executeUpdate(sqlquery);
            this.stmt.close();
            this.c.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
    }
    
    public boolean containsUser(User user){
        
        String username = user.getUsername();
        String password = user.getPassword();
        boolean result = false;
        
        try {   
            this.c = this.source.getConnection();
            this.stmt = c.createStatement();
            this.sqlquery = "SELECT * FROM "+nameTable+" WHERE "+
                "username='"+username+"' AND password='"+password+"';";
        
            ResultSet rs = this.stmt.executeQuery(sqlquery);
            if(rs.isBeforeFirst())  //If there are no results
                result = true;
            
            rs.close();
            this.stmt.close();
            this.c.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
