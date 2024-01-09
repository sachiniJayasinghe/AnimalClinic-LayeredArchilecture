package lk.ijse.animal_clinic.entity;

public class Pet {
    private String pet_id;
    private String name;
    private String age;
    private String pet_type;
    private String cus_id;

    public Pet() {
    }

    public Pet(String pet_id, String name, String age, String pet_type, String cus_id) {
        this.pet_id = pet_id;
        this.name = name;
        this.age = age;
        this.pet_type = pet_type;
        this.cus_id = cus_id;
    }

    public String getPet_id() {
        return pet_id;
    }

    public void setPet_id(String pet_id) {
        this.pet_id = pet_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPet_type() {
        return pet_type;
    }

    public void setPet_type(String pet_type) {
        this.pet_type = pet_type;
    }

    public String getCus_id() {
        return cus_id;
    }

    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "pet_id='" + pet_id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", pet_type='" + pet_type + '\'' +
                ", cus_id='" + cus_id + '\'' +
                '}';
    }
}
