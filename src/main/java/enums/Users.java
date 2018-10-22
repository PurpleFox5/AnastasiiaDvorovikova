package enums;

public enum Users {

    PITER_SHALOVSKII("epam","1234");

    String login;
    String pasword;

    Users(String login, String pasword){

        this.login = login;
        this.pasword = pasword;

    }
}
