package FileSystem;

public class Dialog {
    String Dialog_name = "";
    int DialogID;

    public int getDialogID() {
        return DialogID;
    }

    public void setDialogID(int dialogID) {
        DialogID = dialogID;
    }

    UserInfo[] Dialog_members;
    public Dialog(String DName, int DID, UserInfo[] Users){
        Dialog_name = DName;
        DialogID = DID;
        Dialog_members = Users;
    }
}
