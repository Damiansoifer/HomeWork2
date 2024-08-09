package com.example.usercollectionapp.models;

public class User {
    private Name name;
    private Dob dob;
    private String email;
    private Location location;
    private Picture picture;
    private Id id;

    public Name getName() {
        return name;
    }

    public Dob getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public Location getLocation() {
        return location;
    }

    public Picture getPicture() {
        return picture;
    }

    public Id getId() {
        return id;
    }

    public class Name {
        private String first;
        private String last;

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }
    }

    public class Dob {
        private int age;

        public int getAge() {
            return age;
        }
    }

    public class Location {
        private String city;
        private String country;

        public String getCity() {
            return city;
        }

        public String getCountry() {
            return country;
        }
    }

    public class Picture {
        private String large;

        public String getLarge() {
            return large;
        }
    }

    public class Id {
        private String value;

        public String getValue() {
            return value;
        }
    }
}
