package FileSystem;

import java.util.ArrayList;

public class User {
    public User(String name, int id, String login, String password, ArrayList<Dialog> dialogs){
        Name = name;
        ID = id;
        Login = login;
        Password = password;
        Dialogs = dialogs;
    }
    String Name;
    int ID;
    String Login;
    String Password;
    ArrayList<Dialog> Dialogs;

    void AddDialog(Dialog D){
        Dialogs.add(D);
    }
}
