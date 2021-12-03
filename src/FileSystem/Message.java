package FileSystem;

public class Message {
    public Message(String t, int u, String c){
        Time = t;
        UserID = u;
        Text = c;
    }
    String Time;
    int UserID;
    String Text;

    @Override
    public String toString() {
        return "FileSystem.Message{" +
                "Time='" + Time + '\'' +
                ", FileSystem.User='" + UserID + '\'' +
                ", Text='" + Text + '\'' +
                '}';
    }
}
