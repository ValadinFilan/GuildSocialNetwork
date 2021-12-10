package RequestManager;

import FileSystem.Dialog;
import FileSystem.Message;
import FileSystem.Server_FileSystem;
import com.google.gson.Gson;

import java.io.IOException;

public class AnswerManager {
    Server_FileSystem FS;
    private Gson gson;

    public AnswerManager (Server_FileSystem FileSystem){
        FS = FileSystem;
        gson  = new Gson();
    }

    public void Handle(String Request) throws IOException {
        int i = Request.indexOf(',');
        String Type = Request.substring(9, i - 1);
        Request = Request.substring(i + 1);
        System.out.println(Type);
        switch (Type){
            case ("LOAD_MSG"):// parse Json and made FS to read Message
            {
                i = Request.indexOf(',');
                String Username = Request.substring(12, i - 1);
                Request = Request.substring(i + 1);
                i = Request.indexOf(',');
                int DialogID = 0;
                try {
                    DialogID = Integer.valueOf(Request.substring(12, i - 1));
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
                Request = Request.substring(i + 1);
                String MessageTime = Request.substring(15, Request.length()-2);
                if(MessageTime.equals("NULL")){
                    System.out.println(FS.ReadDialog(DialogID));
                }
                else{
                    System.out.println(FS.ReadDialog(DialogID, MessageTime));
                }
                break;
            }
            case ("SEND_MSG"): // parse Json and made FS to write Message
            {
                int DialogID = 0;
                i = Request.lastIndexOf(':') + 1;
                try{
                    DialogID = Integer.parseInt(Request.substring(i , Request.length() - 1));
                    System.out.println(DialogID);
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
                Request = Request.substring(10, i - 12);
                Message M = gson.fromJson(Request, Message.class);
                FS.WriteDialog(M, DialogID);
                System.out.println(M);
                break;
            }
            case ("REG_USER"): // FS makes new record in Users_info
            {
                i = Request.indexOf(',');
                String Login = Request.substring(9, i - 1);
                Request = Request.substring(i + 1);
                String Password = Request.substring(12, Request.length() - 2);
                Request = Request.substring(i + 1);
                FS.AddUser("0", Login, Password);
            }
            case ("AUTH_USER"): // print "authorized" if found user
            {
                i = Request.indexOf(',');
                String Login = Request.substring(9, i - 1);
                Request = Request.substring(i + 1);
                String Password = Request.substring(12, Request.length() - 2);
                if (Password.equals((FS.FindUser(Login)).getPassword())){
                    System.out.println("authorized");
                }
                else
                    System.out.println("unauthorized");
            }
            case ("GET_DIAL"):
            {
                String Username = Request.substring(12, Request.length() - 2);
                System.out.println(FS.FindDialog(Username));
            }
            case ("NEW_DIAL"): // нет добавления в список диалогов юзера (только в список юзеров диалога)
            {
                i = Request.indexOf(',');
                String Username = Request.substring(12, i - 1);
                Request = Request.substring(i + 1);
                String TargetUser = Request.substring(14, Request.length() - 2);
                Dialog D = FS.CreateDialog(Username, TargetUser);
                //(FS.FindUser(Username)).AddDialog(D);
                //(FS.FindUser(Username)).AddDialog(D);
            }
        }
    }
}