package world.test.ajcom.helloworld;

public class User {
    String userName;
    String password;
    String userID;
    String vinNumber;

    public User ()
    {

    }

    public User(String userName, String password, String userID, String vinNumber) {
        this.userName = userName;
        this.password = password;
        this.userID = userID;
        this.vinNumber = vinNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserID() {
        return userID;
    }

    public String getVinNumber() {
        return vinNumber;
    }
}
