package server;

import java.util.ArrayList;
import java.util.Date;

public class LogManager {
    ArrayList<LogRow> logRowSessionArrayList = new ArrayList<>();
    Server server = null;
    public LogManager(){
        test();
    }
    public LogManager(Server server){
        this.server = server;
        test();
    }
    public void test(){
        sendLogToConsole(createNewLog("Fri Nov 12 20:25:58 MSK 2021 | [MESSAGE] This is test string log"));
        sendLogToConsole(createNewLog("This is test error log", MessageType.ERROR));
        sendLogToConsole(createNewLog("This is test warning log", MessageType.WARNING));
        sendLogToConsole(createNewLog("This is test success log", MessageType.SUCCESS));
    }
    public LogRow createNewLog(String message, MessageType type){
        LogRow result = new LogRow((new Date()).toString(), message, type);
        logRowSessionArrayList.add(result);
        return result;
    }
    public LogRow createNewLog(String log){
        LogRow result;
        try{
            result = new LogRow(log);
            logRowSessionArrayList.add(result);
            return result;
        }catch (CreatingLogException e){
            sendLogToConsole(createNewLog(e.getMessage(), MessageType.ERROR));
            return null;
        }
    }
    public void sendLogToConsole(LogRow log){
        if(log != null)
            server.print(log.toString(), log.getMessageType());
    }
    public void showDayLog(String month, String day){

    }
    public void showDayLog(){

    }
    public void showSessionLog(){
        for (LogRow log : logRowSessionArrayList)
            sendLogToConsole(log);
    }
}
