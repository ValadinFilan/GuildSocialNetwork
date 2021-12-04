package FileSystem;

import Authorization.Authorization;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileSystem.Client_FileSystem C = new FileSystem.Client_FileSystem();
        FileSystem.UserInfo[] U = {new FileSystem.UserInfo("IGOR", 1), new FileSystem.UserInfo("VALERA", 2)};
        FileSystem.Dialog D = new FileSystem.Dialog("Messages", 001, U);
        C.CreateDialog(D);
        System.out.println(C.ReadDialog("002"));
        FileSystem.Message M = new FileSystem.Message("23:50", 1, "444");
        C.WriteDialog(M, "002");
        //new Authorization(new JFrame());
        /*FileSystem.Server_FileSystem Server = new FileSystem.Server_FileSystem();
        ArrayList<FileSystem.Dialog> dialogs = new ArrayList<FileSystem.Dialog>();
        FileSystem.UserInfo[] U = {new FileSystem.UserInfo("IGOR", 1), new FileSystem.UserInfo("VALERA", 2)};
        for(int i = 0; i < 10; i++){
            FileSystem.Dialog D = new FileSystem.Dialog("dialog"+i, i, U);
            dialogs.add(D);
        }
        FileSystem.User USER = new FileSystem.User("Igor", 1, "hiinomaru", "1234567890", dialogs);
        Server.AddUser(USER);*/
    }
}
