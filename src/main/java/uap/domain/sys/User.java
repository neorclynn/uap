package uap.domain.sys;


import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import uap.domain.BaseDomain;

import javax.naming.Name;

@Entry(objectClasses = { "inetOrgPerson" }, base="ou=users")
public class User extends BaseDomain {
    @Id
    private Name id;

    @Attribute(name="cn")
    private String cn;

    @Attribute(name="sn")
    private String sn;

    @Attribute(name="userPassword")
    private String userPassword;
}