package enums;

public enum Users {

    PITER_SHAILOVSKII("PITER CHAILOVSKII", "epam","1234");

    public String name;
    public String login;
    public String password;

    Users(String name, String login, String password){
        this.name = name;
        this.login = login;
        this.password = password;
    }

}
