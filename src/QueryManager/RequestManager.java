package QueryManager;
import FileSystem.Message;
import client.Dialogs.DialogPanel;
import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;
public class RequestManager {
    private Gson gson = new Gson();
    Socket socket;
    DataOutputStream oos;
    DataInputStream ois;
    DialogPanel dialogPanel;

    public RequestManager(DialogPanel panel) {
        try {
            socket = new Socket("25.62.187.132", 61944);
            oos = new DataOutputStream(socket.getOutputStream());
            ois = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String LOAD_MSG(String Username,
                           int DialogID,
                           String MessageTime) {
        String Request = "{\"Type\":\"LOAD_MSG\"," + "\"Username\":\"" + Username + "\"," + "\"DialogID\":\"" + DialogID + "\"," + "\"MessageTime\":\"" + MessageTime + "\"}";
        Message M = new Message("20:20", 1, "Hello");
        /*some message*/ /*System.out.println(Request)*/
        ;
        return Request; /* return result*/
    }

    public Message LOAD_MSG(String Username, int DialogID) {
        String Request = "{\"Type\":\"LOAD_MSG\"," + "\"Username\":\"" + Username + "\"," + "\"DialogID\":\"" + DialogID + "\"," + "\"MessageTime\":\"" + "NULL" + "\"}";
        Message M = null;
        if (!socket.isInputShutdown()) {
            try {
                oos.writeUTF(Request);
                oos.flush();
                String data = ois.readUTF();
                if (!data.equals("NULL")) M = gson.fromJson(data, Message.class);
                else M = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return M;
    }

    public String RENEW_MSG(String Username, int DialogID, String MessageTime) {
        String Request = "{\"Type\":\"RENEW_MSG\"," + "\"Username\":\"" +
                Username + "\"," + "\"DialogID\":\"" + DialogID + "\"," + "\"MessageTime\":\"" + MessageTime + "\"}";
        //System.out.println(Request);
        Message M = null;
        String data = null;
        if (!socket.isInputShutdown()) {
            try {
                oos.writeUTF(Request);
                oos.flush();
                data = ois.readUTF();
                System.out.println(data.equals("NULL") ? "SUCCESS" : "ERROR");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public String SEND_MSG(Message Message, int DialogID) {
        String M = gson.toJson(Message);
        String Request = "{\"Type\":\"SEND_MSG\"," + "\"Message\":" + M + ",\"DialogID\":" + DialogID + "}";
        if (!socket.isInputShutdown()) {
            try {
                oos.writeUTF(Request);
                oos.flush();
                String data = ois.readUTF();
                System.out.println(data == "NULL" ? "SUCCESS" : "ERROR");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Request;
    }

    public String REG_USER(String Login, String Password) {
        String Request = "{\"Type\":\"REG_USER\"," + "\"Login\":\"" + Login + "\"," + "\"Password\":\"" + Password + "\"}";
        System.out.println(Request);
        return Request;
    }

    public String AUTH_USER(String Login, String Password) {
        String Request = "{\"Type\":\"AUTH_USER\"," + "\"Login\":\"" + Login + "\"," + "\"Password\":\"" + Password + "\"}";
        System.out.println(Request);
        return Request;
    }

    public String UNAUTH_USER(String Login) {
        String Request = "{\"Type\":\"UNAUTH_USER\"," + "\"Login\":\"" + Login + "\"}";
        System.out.println(Request);
        return Request;
    } // не помню зачем он нужен public

    String GET_DIAL(String Username) {
        String Request = "{\"Type\":\"GET_DIAL\"," + "\"Username\":\"" + Username + "\"}";
        return Request;
    }

    public String NEW_DIAL(String Username, String TargetUser) {

        String Request = "{\"Type\":\"NEW_DIAL\"," + "\"Username\":\"" + Username + "\"," + "\"TargetUser\":\"" + TargetUser + "\"}";
        return Request;
    }
}