package pt.ismai.a029187.xmlparser;

public class Cliente {
    protected int clienteID;
    protected String firstName;
    protected String lastName;
    protected String street;
    protected String city;

    public Cliente(int id) {
        clienteID = id;
    }

    public Cliente() {
        clienteID = -1;
    }

    public int getId() {
        return clienteID;
    }

    public void setId(int id) {
        clienteID = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String s) {
        firstName = s;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String s) {
        lastName = s;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String s) {
        street = s;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String s) {
        city = s;
    }

    @Override
    public String toString() {
        return clienteID + ": " + firstName + lastName;
    }

    public String getMorada() {
        return street + ", " + city;
    }
}