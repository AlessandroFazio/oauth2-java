package com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.response;

public class UserRest {
    private String email, firstName, lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    private UserRest(UserRestBuilder builder) {
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public static class UserRestBuilder {
        private String email;

        private String firstName;
        private String lastName;

        public UserRestBuilder() {
        }

        public UserRestBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserRestBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserRestBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserRest build() {
            return new UserRest(this);
        }
    }
}
