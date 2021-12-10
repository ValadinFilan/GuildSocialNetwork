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

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
