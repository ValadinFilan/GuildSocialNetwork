package server;


import java.util.Date;

public class LogRow {
    private String date;
    private String message;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    private MessageType messageType;
    public LogRow(String date, String message, MessageType messageType){
        this.date = date;
        this.message = message;
        this.messageType = messageType;
    }
    public LogRow(String log) throws CreatingLogException {
        try {
            String[] data = log.split("[|]");
            date = data[0].trim();
            data = data[1].trim().split(" ");
            message = data[1];
            for (int i = 2; i < data.length; i++)
                message += " " + data[i];
            switch (data[0]){
                case "[ERROR]":
                    messageType = MessageType.ERROR;
                    break;
                case "[SUCCESS]":
                    messageType = MessageType.SUCCESS;
                    break;
                case "[MESSAGE]":
                    messageType = MessageType.MESSAGE;
                    break;
                case "[WARNING]":
                    messageType = MessageType.WARNING;
                    break;
                default:
                    throw new Exception();
            }
        }catch (Exception e){
            throw new CreatingLogException("Wrong string for parsing to log");
        }
    }
    @Override
    public String toString() {
        String type = "Message";
        switch (messageType){
            case MESSAGE:
                type = "MESSAGE";
                break;
            case ERROR:
                type = "ERROR";
                break;
            case SUCCESS:
                type = "SUCCESS";
                break;
            case WARNING:
                type = "WARNING";
                break;
        }
        return String.format("%s | [%s] %s", date.toString(), type, message);
    }
}
