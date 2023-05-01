package search.domain;

public class Person {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String profession;
    private String city;
    private String details;

    public Person() {
    }

    private Person(final String id, final String firstname, final String lastname,
                   final String email, final String profession, final String city, final String details) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.profession = profession;
        this.city = city;
        this.details = details;
    }

    public static class Builder {
        private String id;
        private String firstname;
        private String lastname;
        private String email;
        private String profession;
        private String city;
        private String details;

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder firstname(final String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder lastname(final String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder email(final String email) {
            this.email = email;
            return this;
        }

        public Builder profession(final String profession) {
            this.profession = profession;
            return this;
        }

        public Builder city(final String city) {
            this.city = city;
            return this;
        }

        public Builder details(final String details) {
            this.details = details;
            return this;
        }

        public Person build() {
            return new Person(id, firstname, lastname, email, profession, city, details);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getProfession() {
        return profession;
    }

    public String getCity() {
        return city;
    }

    public String getDetails() {
        return details;
    }
}
