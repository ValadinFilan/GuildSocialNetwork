package FileSystem;

public class Dialog {
    String Dialog_name = "";
    int DialogID;
    UserInfo[] Dialog_members;
    public Dialog(String DName, int DID, UserInfo[] Users){
        Dialog_name = DName;
        DialogID = DID;
        Dialog_members = Users;
    }
}
