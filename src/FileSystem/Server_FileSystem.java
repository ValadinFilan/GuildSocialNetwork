package FileSystem;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class Server_FileSystem {
    private class Stream_info{
        Stream_info(int id, ReverseLineInputStream str){
            ID = id;
            FIS = str;
        }
        int ID;
        ReverseLineInputStream FIS;
    }
    Stream_info[] Streams; // 4 streams for dialogs
    int OldStream;
    int lastRegUserID = 1;
    int lastDialID = 1;
    Gson gson = new Gson();

    public void AddUser(String name, String login, String password) throws IOException {
        ArrayList<Dialog> dialogs = new ArrayList<Dialog>();
        lastRegUserID += 1;
        User user = new User(name, lastRegUserID, login, password, dialogs);
        FileWriter Users_info = new FileWriter("sources_server/Users_info.txt", true);
        gson.toJson(user, Users_info);
        Users_info.write("\n");
        Users_info.close();
    }

    public User FindUser(String login) throws IOException {
        FileInputStream FIS = new FileInputStream(new File("sources_server/Users_info.txt"));
        String User = "";
        int c;
        while(true){
            while(((c=FIS.read())!= -1) && (c != 125)){
                User += (char)c;
            }
            User += '}';
            User U = gson.fromJson(User, User.class);
            if((U.getLogin()).equals(login)){
                return U;
            }
            User = "";
            if (c == -1) break;
        }
        FIS.close();
        return null;
    }

    public Server_FileSystem() throws IOException {
        Streams = new Stream_info[3]; // 4 streams for dialogs
        OldStream = 0;
        FileWriter out = new FileWriter("sources_server/Dialogs_info.txt", true);
        out.close();
        out = new FileWriter("sources_server/Users_info.txt", true);
        out.close();
        FileInputStream FIS = new FileInputStream(new File("sources_server/Users_info.txt"));
        int c = 0;
        String User = "";
        int max = 0;
        while(true){ // set lastRegUserID
            while(((c=FIS.read())!= -1) && (c != 125)){
                User += (char)c;
            }
            try{
                max = Integer.valueOf(User.substring(User.indexOf(':') +1 , User.indexOf(',')));
            }
            catch (Exception e){}
            if (lastRegUserID < max) {
                lastRegUserID = max;
            }
            User = "";
            if (c == -1) break;
        }
        FIS.close();
        FIS = new FileInputStream(new File("sources_server/Dialogs_info.txt"));
        String Dialog = "";
        while(true){ // set lastDialID
            while(((c=FIS.read())!= -1) && (c != 10)){
                Dialog += (char)c;
            }
            Dialog D = gson.fromJson(Dialog, Dialog.class);
            if((D != null) && (lastDialID < D.getDialogID()))
                lastDialID = D.getDialogID();
            Dialog = "";
            if (c == -1) break;
        }
    }

    public Message ReadDialog(int ID) throws IOException {
        int c;
        String Result = "";
        Stream_info Stream = null;
        for (int i = 0; i < 4; i++) {
            if (Streams[i] == null) {
                Streams[i] = new Stream_info(ID, new ReverseLineInputStream(new File("sources_server/" + ID + ".txt")));
                Stream = Streams[i];
                break;
            }
            else if (Streams[i].ID == ID) {
                Stream = Streams[i];
                break;
            }
        }
        if(Stream == null) {
            Streams[OldStream].FIS.close();
            Streams[OldStream] = new Stream_info(ID, new ReverseLineInputStream(new File("sources_server/" + ID + ".txt")));
            OldStream = (OldStream + 1) % 4;
        }

        while(((c=Stream.FIS.read())!= -1)&&(c != 125)){
            Result += (char)c;
            //System.out.print((char)c);
        }
        if(c == -1){
            Stream.FIS.close();
            Stream.ID = 0;
            return null;
        }
        else {
            Result += '}';
            //System.out.print(Result);
            return gson.fromJson(Result, Message.class);
        }
    } //read last message from FileSystem.Dialog ID

    public Message ReadDialog(int ID, String Last_msg_time) throws IOException {
        int c;
        String Result = "";
        Stream_info Stream = null;
        for (int i = 0; i < 4; i++) {
            if (Streams[i] == null) {
                Streams[i] = new Stream_info(ID, new ReverseLineInputStream(new File("sources_server/" + ID + ".txt")));
                Stream = Streams[i];
                break;
            }
            else if (Streams[i].ID == ID) {
                Stream = Streams[i];
                break;
            }
        }
        if(Stream == null) {
            Streams[OldStream].FIS.close();
            Streams[OldStream] = new Stream_info(ID, new ReverseLineInputStream(new File("sources_server/" + ID + ".txt")));
            OldStream = (OldStream + 1) % 4;
        }
        Last_msg_time = "{\"Time\":\"" + Last_msg_time;
        while(!Result.equals(Last_msg_time)){
            while(((c=Stream.FIS.read())!= -1)&&(c != 123)){}
            Result = "{";
            for (int i = 0; i < 13; i++){
                Result += (char)Stream.FIS.read();
            }
        }
        Result ="{";
        while(((c=Stream.FIS.read())!= -1)&&(c != 123)){}
        while(((c=Stream.FIS.read())!= -1)&&(c != 125)){
            Result += (char)c;
        }
        if(c == -1){
            Stream.FIS.close();
            Stream.ID = 0;
            return null;
        }
        else {
            Result += '}';
            //System.out.print(Result);
            return gson.fromJson(Result, Message.class);
        }
    }  //read message before time = Last_msg_time from FileSystem.Dialog ID

    public Message ReadDialogByTime(int ID, String Msg_time) throws IOException {
        int c;
        String Result = "";
        Stream_info Stream = null;
        for (int i = 0; i < 4; i++) {
            if (Streams[i] == null) {
                Streams[i] = new Stream_info(ID, new ReverseLineInputStream(new File("sources_server/" + ID + ".txt")));
                Stream = Streams[i];
                break;
            }
            else if (Streams[i].ID == ID) {
                Stream = Streams[i];
                break;
            }
        }
        if(Stream == null) {
            Streams[OldStream].FIS.close();
            Streams[OldStream] = new Stream_info(ID, new ReverseLineInputStream(new File("sources_server/" + ID + ".txt")));
            OldStream = (OldStream + 1) % 4;
        }
        Msg_time = "{\"Time\":\"" + Msg_time;
        while(!Result.equals(Msg_time)){
            while(((c=Stream.FIS.read())!= -1)&&(c != 123)){}
            Result = "{";
            for (int i = 0; i < 13; i++){
                Result += (char)Stream.FIS.read();
            }
        }

        while(((c=Stream.FIS.read())!= -1)&&(c != 125)){
            Result += (char)c;
        }
        if(c == -1){
            Stream.FIS.close();
            Stream.ID = 0;
            return null;
        }
        else {
            Result += '}';
            System.out.print(Result);
            return gson.fromJson(Result, Message.class);
        }
    } //read message, time = FileSystem.Message from FileSystem.Dialog ID

    public Dialog CreateDialog(String User1, String User2) throws IOException {
        lastDialID += 1;
        FileWriter out = new FileWriter("sources_server/" + String.valueOf(lastDialID) + ".txt", true);
        out.close();
        FileWriter Dialogs_info = new FileWriter("sources_server/Dialogs_info.txt", true);
        String[] members = {User1, User2};
        Dialog D = new Dialog("0", lastDialID, members);
        gson.toJson(D, Dialogs_info);
        Dialogs_info.write("\n");
        Dialogs_info.close();
        return D;
    } // ..

    public String FindDialog(String Username) throws IOException {
        String Dialogs = "";
        FileInputStream FIS = new FileInputStream(new File("sources_server/Dialogs_info.txt"));
        String Dialog = "";
        int c;
        while(true){
            while(((c=FIS.read())!= -1) && (c != 10)){
                Dialog += (char)c;
            }
            Dialog D = gson.fromJson(Dialog, Dialog.class);
            if((D != null) && (D.IsInMembers(Username))){
                Dialogs += Dialog;
                Dialogs += "\n";
            }
            Dialog = "";
            if (c == -1) break;
        }
        FIS.close();
        //System.out.println(Dialogs);
        if(Dialogs.equals(""))
            return null;
        else
            return Dialogs;
    } // ..

    public void WriteDialog(Message M, int ID) throws IOException {
        FileWriter out = new FileWriter("sources_server/" + ID + ".txt", true);
        gson.toJson(M, out);
        out.write("\n");
        out.close();
    } // write M in FileSystem.Dialog ID
}