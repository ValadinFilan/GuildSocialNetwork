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

    public boolean IsInMembers(String S){
        for(String x : Dialog_members){
            if(x.equals(S)){
                return true;
            }
        }
        return false;
    }

    String[] Dialog_members;
    public Dialog(String DName, int DID, String[] Users){
        Dialog_name = DName;
        DialogID = DID;
        Dialog_members = Users;
    }
}