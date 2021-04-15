package daycare.entity;

public class Person {
    private int schoolID;
    private int personID;
    private String name;
    private int ageMonths;
    private String address;
    private String phone;

    public Person() {

    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public int getPersonID() {
        return personID;
    }


    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgeMonths() {
        return ageMonths;
    }

    public void setAgeMonths(int ageMonths) {
        this.ageMonths = ageMonths;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personID +
                ", schoolID='" + schoolID + '\'' +
                ", name='" + name + '\'' +
                ", ageMonths=" + ageMonths +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
