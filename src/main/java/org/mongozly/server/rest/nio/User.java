package org.mongozly.server.rest.nio;

import java.io.Serializable;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;


/**
 *
 * @author aris
 */
@XmlRootElement
@Entity(value="users", noClassnameStored=true)
public class User implements Serializable {
    
    @Id
    private ObjectId _id;

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Indexed(unique=true)
    @NotNull
    @Size(min = 3, max = 25)
    @Pattern(regexp = "[A-Za-z0-9]*", message = "must contain only letters and digits")
    private String userName;
    private String city;
    @Pattern(regexp = "[A-Za-z]*", message = "must contain only letters")
    private String firstName;
    @Pattern(regexp = "[A-Za-z]*", message = "must contain only letters")
    private String lastName;
    @Pattern(regexp = "[A-Za-z]*", message = "must contain only letters")
    private String address;
    @NotNull
    @Email
    private String email;
    //private image TODO with gridfs?
    
    @Embedded
    private Set<SocialAccount> socialAccounts;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Set<SocialAccount> getSocialAccounts() {
        return socialAccounts;
    }

    public void setSocialAccounts(Set<SocialAccount> socialAccounts) {
        this.socialAccounts = socialAccounts;
    }
    public String getUserName() {
        return userName;
    }

    public ObjectId getId() {
        return _id;
    }

    public User() {
    }
    
    @Override
    /**
     * This function helps differentiate users based on data we deem necessary
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User u = (User) o;

        if (!userName.equalsIgnoreCase(u.userName)) return false;
        if (!firstName.equalsIgnoreCase(u.firstName)) return false;
        if (!lastName.equalsIgnoreCase(u.lastName)) return false;
        if (!email.equalsIgnoreCase(u.email)) return false;

        return true;
    }

    @Override
    /**
     * This function produces a unique hash for every user for quick differentiation
     */
    public int hashCode() {
        int hash = 7;

        hash = 31 * hash + (this.userName != null ? this.userName.hashCode() : 0);
        hash = 31 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 31 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 31 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

   
}
