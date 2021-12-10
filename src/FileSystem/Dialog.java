package FileSystem;

public class Dialog {
    private String Dialog_name = "";
    private int DialogID;

    public String getDialog_name() {
        return Dialog_name;
    }

    public void setDialog_name(String dialog_name) {
        Dialog_name = dialog_name;
    }

    public int getDialogID() {
        return DialogID;
    }

    public void setDialogID(int dialogID) {
        DialogID = dialogID;
    }

    public UserInfo[] getDialog_members() {
        return Dialog_members;
    }

    public void setDialog_members(UserInfo[] dialog_members) {
        Dialog_members = dialog_members;
    }

    private UserInfo[] Dialog_members;
    public Dialog(String DName, int DID, UserInfo[] Users){
        Dialog_name = DName;
        DialogID = DID;
        Dialog_members = Users;
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
