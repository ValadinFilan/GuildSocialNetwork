package server;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Server {
    private Scanner sc;
    private LogManager logManager;
    public Server(){
        logManager = new LogManager(this);
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
            if(Objects.equals(data[0].toLowerCase(Locale.ROOT), "exit")) return -1;
            throw new Exception();
        }catch (Exception e){
            print("Wrong command", MessageType.ERROR);
        }
        return 0;
    }
}
