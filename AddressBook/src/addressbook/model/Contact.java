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
package addressbook.model;

import java.util.Objects;

/**
 *
 * @author Damiano Simoni
 */
public class Contact {
    
    private String name;
    private String surname;
    private String number;
    private String address;
    private int age;

    /* ----- Constructors ----- */
    
    public Contact() {
    }

    public Contact(String name, String surname, String number) {
        this.name = name;
        this.surname = surname;
        this.number = number;
    }

    public Contact(String name, String surname, String number, String address) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.address = address;
    }

    public Contact(String name, String surname, String number, int age) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.age = age;
    }
    
    

    public Contact(String name, String surname, String number, String address, int age) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.address = address;
        this.age = age;
    }

    /* ----- Get/Set methods ----- */
    
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.number);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact other = (Contact) obj;
        return Objects.equals(this.number, other.number);
    } 
    
}
