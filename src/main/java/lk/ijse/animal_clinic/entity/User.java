package lk.ijse.animal_clinic.entity;

public class User {
    private String id;
    private String password;
    private String name;

    private String address;
    private String email;

    private byte[] image;
    public User(){}

    public User(String id, String password, String name, String address, String email, byte[] image) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
