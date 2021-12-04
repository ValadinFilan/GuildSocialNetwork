package server;

import FileSystem.*;
import RequestManager.*;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Server {
    private Scanner sc;
    private LogManager logManager;
    private Server_FileSystem serverFileSystem;
    private RequestManager reqManager;
    private AnswerManager ansManager;
    public Server(){
        logManager = new LogManager(this);
        try {
            serverFileSystem = new Server_FileSystem();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: Filesystem crashed");
        }
        reqManager = new RequestManager();
        ansManager = new AnswerManager(serverFileSystem);
        sc = new Scanner(System.in);

        print("Server successfully started", MessageType.SUCCESS);

        while (ListenCommand() != -1){
        }
    }
    public void print(String message, MessageType type){
        String textColorMarker = "\u001B[30m";
        switch (type){
            case MESSAGE:
                textColorMarker = "\u001B[0m";
                break;
            case ERROR:
                textColorMarker = "\u001B[31m";
                break;
            case SUCCESS:
                textColorMarker = "\u001B[32m";
                break;
            case WARNING:
                textColorMarker = "\u001B[33m";
                break;
        }
        String endColorTextMarker = "\u001B[0m";
        System.out.println(textColorMarker + message + endColorTextMarker);
    }
    public int ListenCommand(){
        String command = sc.nextLine() + " ";
        try{
            String[] data = command.split("[ ]");
            if(Objects.equals(data[0], "showLog")){
                if(data.length == 1) {
                    logManager.showSessionLog();
                }else {
                    if(Objects.equals(data[1], "session"))
                        logManager.showSessionLog();
                    else if(Objects.equals(data[1], "day"))
                        if(data.length == 2)
                            logManager.showSessionLog();
                        else if(data.length == 4){
                            logManager.showDayLog(data[2], data[3]);
                        }
                }
                return 0;
            }
            if(Objects.equals(data[0], "FS_AddUser")){
                ArrayList<Dialog> dialogs = new ArrayList<Dialog>();
                User USER = new User("Igor", 1, "hiinomaru", "1234567890", dialogs); //create user
                serverFileSystem.AddUser(USER); // add user in list
                logManager.sendLogToConsole(logManager.createNewLog("User added", MessageType.SUCCESS));
                return 0;
            }
            else if(Objects.equals(data[0], "FS_CreateDialog")){
                UserInfo[] U = {new UserInfo("IGOR", 1), new UserInfo("VALERA", 2)};
                Dialog D = new Dialog("Messages", 001, U);
                serverFileSystem.CreateDialog(D);// add dialog in list
                logManager.sendLogToConsole(logManager.createNewLog("Dialog added", MessageType.SUCCESS));
                return 0;
            }
            else if(Objects.equals(data[0], "FS_WriteDialog")){
                Message M = new Message("23:50", 1, "4 Tuza");
                serverFileSystem.WriteDialog(M, 1);// add dialog in list
                return 0;
            }
            else if(Objects.equals(data[0], "FS_Check")){
                reqManager.LOAD_MSG("Igor", 1, "22:01");
                ansManager.Handle("{\"Type\":\"LOAD_MSG\",\"Username\":\"Igor\",\"DialogID\":\"1\",\"MessageTime\":\"22:01\"}");
                reqManager.SEND_MSG(new Message("23:50", 1, "22:01"), 1);
                ansManager.Handle("{\"Type\":\"SEND_MSG\",\"Message\":{\"Time\":\"23:50\",\"UserID\":1,\"Text\":\"22:01\"},\"DialogID\":1}");
                return 0;
            }
            // недописаный кусок
            if(Objects.equals(data[0].toLowerCase(Locale.ROOT), "exit")) return -1;
            throw new Exception();
        }catch (Exception e){
            print("Wrong command", MessageType.ERROR);
        }
        return 0;
    }
}
