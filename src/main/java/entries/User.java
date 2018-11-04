package entries;

import java.util.Comparator;

public class User implements Comparable<User>{

    private String number;
    private String user;
    private String description;

    public String getNumber() {
        return number;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "number='" + number + '\'' +
                ", user='" + user + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public User(String number, String user, String description) {
        this.number = number;
        this.user = user;
        this.description = description;

    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(User user) {
        return Comparator.comparing(User::getNumber)
                .thenComparing(User::getUser)
                .thenComparing(User::getDescription)
                .compare(this, user);
    }
}

