package enums;

import com.epam.commons.DataClass;

public class User extends DataClass {
    public String fullName;
    public String name;
    public String password;

    public User(String fullName, String name, String password) {
        this.fullName = fullName;
        this.name = name;
        this.password = password;
    }
}

