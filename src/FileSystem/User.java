package FileSystem;

import java.util.ArrayList;

public class User {
    int ID;
    String Name;
    String Login;
    String Password;
    ArrayList<Dialog> Dialogs;

    public User(String name, int id, String login, String password, ArrayList<Dialog> dialogs){
        ID = id;
        Name = name;
        Login = login;
        Password = password;
        Dialogs = dialogs;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void AddDialog(Dialog D){
        Dialogs.add(D);
    }
}
