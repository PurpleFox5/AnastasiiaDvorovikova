package enums;

public enum Users{

    PITER_CHAILOVSKII("PITER CHAILOVSKII", "epam","1234");

    public String name;
    public String login;
    public String password;

    Users(String name, String login, String password){
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public static Users getUser(String name){
        for (Users user : Users.values()) {
            if (user.name.equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
