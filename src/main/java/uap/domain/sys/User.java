package uap.domain.sys;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(objectClasses = {"inetOrgPerson", "organizationalPerson", "person", "top"}, base = "ou=users")
public class User {
    @Id
    private Name uid;

    @Attribute(name = "cn")
    @DnAttribute("cn")
    private String firstName;

    @Attribute(name = "sn")
    @DnAttribute("sn")
    private String lastName;

    public Name getUid() {
        return uid;
    }

    public void setUid(Name uid) {
        this.uid = uid;
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
}