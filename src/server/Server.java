package server;

import FileSystem.*;
import QueryManager.*;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.awt.windows.ThemeReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server {
    private Scanner sc;
    private LogManager logManager;
    private Server_FileSystem serverFileSystem;
    private AnswerManager ansManager;
    public Server(){
        logManager = new LogManager(this);
        try {
            serverFileSystem = new Server_FileSystem();
        } catch (IOException e) {
            e.printStackTrace();
            print("Filesystem crashed", MessageType.WARNING);
        }
        ansManager = new AnswerManager(serverFileSystem);
        sc = new Scanner(System.in);
        Start();
    }
    public void Start(){
        print("Server successfully started", MessageType.SUCCESS);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (ListenCommand() != -1){
                }
            };
        });
        thread.start();
        Listen();
    }
    public void Listen(){
        while(true){
            print("Listening new connection...", MessageType.SUCCESS);
            try (ServerSocket server= new ServerSocket(0)){
                print("Port has been opened: " + server.getLocalPort(), MessageType.WARNING);
                Socket client = server.accept();
                print("Connection accepted." + client.getInetAddress().getHostAddress(), MessageType.SUCCESS);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            DataOutputStream out = new DataOutputStream(client.getOutputStream());
                            DataInputStream in = new DataInputStream(client.getInputStream());
                            boolean flag = true;
                            try {
                                while (flag) {
                                    String entry = in.readUTF();
                                    String data = ansManager.Handle(entry);
                                    if (data.equals("quit")) flag = false;
                                    while (data != null) {
                                        out.writeUTF(data);
                                        out.flush();
                                        System.out.println(data);
                                        entry = in.readUTF();
                                        data = ansManager.Handle(entry);
                                    }
                                    out.writeUTF("NULL");
                                    out.flush();
                                }
                            }catch (Exception e){
                                print(e.getMessage(), MessageType.WARNING);
                            }
                            out.writeUTF("STOP");
                            out.flush();
                            in.close();
                            out.close();
                            client.close();
                        }catch (Exception e){
                            print("Error with running" + e.getMessage(), MessageType.ERROR);
                        }
                    }
                });
                thread.start();
            }catch (Exception e){
                print("Error with listening" + e.getMessage(), MessageType.ERROR);
            }
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
                serverFileSystem.AddUser("Igor", "hiinomaru", "1234567890"); // add user in list
                logManager.sendLogToConsole(logManager.createNewLog("User added", MessageType.SUCCESS));
                return 0;
            }
            else if(Objects.equals(data[0], "FS_CreateDialog")){
                serverFileSystem.CreateDialog("User1", "User2");// add dialog in list
                logManager.sendLogToConsole(logManager.createNewLog("Dialog added", MessageType.SUCCESS));
                return 0;
            }
            else if(Objects.equals(data[0], "FS_WriteDialog")){
                Message M = new Message("23:50", 1, "4 Tuza");
                serverFileSystem.WriteDialog(M, 1);// add dialog in list
                return 0;
            }
            else if(Objects.equals(data[0], "FS_Check_LOAD_MSG")){
                logManager.sendLogToConsole(logManager.createNewLog("Input Username, LastMsgTime, DialogID", MessageType.SUCCESS));
                String U = sc.nextLine();
                String T = sc.nextLine();
                int ID = sc.nextInt();
                //ansManager.Handle(reqManager.LOAD_MSG(U, ID, T));
                return 0;
            }
            else if(Objects.equals(data[0], "FS_Check_SEND_MSG")){
                logManager.sendLogToConsole(logManager.createNewLog("SEND_MSG", MessageType.SUCCESS));
                Message m = new Message((new SimpleDateFormat("hh:mm:ss")).format(new Date()), 2, command.substring(command.indexOf(" ") + 1, command.length() - 1));
                String M = (new Gson()).toJson(m);
                String Request = "{\"Type\":\"SEND_MSG\"," + "\"Message\":" + M + ",\"DialogID\":" + 1 + "}";
                ansManager.Handle(Request);
                return 0;
            }
            else if(Objects.equals(data[0], "FS_Check_REG_USER")){
                logManager.sendLogToConsole(logManager.createNewLog("Check RegUser", MessageType.SUCCESS));
                //ansManager.Handle(reqManager.REG_USER("Valera", "11111111"));
                return 0;
            }
            else if(Objects.equals(data[0], "FS_Check_AUTH_USER")){
                logManager.sendLogToConsole(logManager.createNewLog("Check RegUser", MessageType.SUCCESS));
                //ansManager.Handle(reqManager.AUTH_USER("Valera", "11111111"));
                return 0;
            }
            else if(Objects.equals(data[0], "FS_Check_NEW_DIAl")){
                logManager.sendLogToConsole(logManager.createNewLog("Make new dialog", MessageType.SUCCESS));
                //ansManager.Handle(reqManager.NEW_DIAL("Valera", "Igor"));
                return 0;
            }
            else if(Objects.equals(data[0], "FS_Check_GET_DIAl")){
                logManager.sendLogToConsole(logManager.createNewLog("Get dialogs", MessageType.SUCCESS));
                //ansManager.Handle(reqManager.GET_DIAL("Igor"));
                return 0;
            }
            if(Objects.equals(data[0].toLowerCase(Locale.ROOT), "exit")) return -1;
            throw new Exception();
        }catch (Exception e){
            print("Wrong command", MessageType.ERROR);
        }
        return 0;
    }
}
