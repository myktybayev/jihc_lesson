package kz.jihc.registration.university;

public class University {
    String photo;
    String name;
    String city;
    String phoneNumber;

    public University(String photo, String name, String city, String phoneNumber){
        this.photo = photo;
        this.name = name;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

