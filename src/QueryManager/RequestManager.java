package QueryManager;

import FileSystem.Message;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class RequestManager {

    private Gson gson = new Gson();
    Socket socket;
    //BufferedReader br;
    DataOutputStream oos;
    DataInputStream ois;

    public RequestManager(){
        try{
            System.out.println("Start do");
            socket = new Socket("192.168.1.64", 0);
            System.out.println("Start connection");
            //br = new BufferedReader(new InputStreamReader(System.in));
            oos = new DataOutputStream(socket.getOutputStream());
            ois = new DataInputStream(socket.getInputStream());
            System.out.println("Client connected to socket.");
            System.out.println();
            System.out.println("Client writing channel = oos & reading channel = ois initialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String LOAD_MSG(String Username, int DialogID, String MessageTime) {
        String Request = "{\"Type\":\"LOAD_MSG\"," + "\"Username\":\"" + Username +"\"," +
                "\"DialogID\":\"" + DialogID +"\"," + "\"MessageTime\":\"" + MessageTime + "\"}";
        Message M = new Message("20:20", 1, "Hello"); // some message
        //System.out.println(Request);
        return Request; // return result
    }

    public Message LOAD_MSG(String Username, int DialogID) {
        String Request = "{\"Type\":\"LOAD_MSG\"," + "\"Username\":\"" + Username +"\"," +
                "\"DialogID\":\"" + DialogID +"\"," + "\"MessageTime\":\"" + "NULL" + "\"}";
        Message M = new Message("20:20", 1, "Hello"); // some message

        if(!socket.isInputShutdown()){
            try {
                oos.writeUTF(Request);
                oos.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("reading...");
                String data = ois.readUTF();
                System.out.println(data);
                M = gson.fromJson(data, Message.class);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return M; // return result
    }

    public String SEND_MSG(Message Message, int DialogID){
        String M = gson.toJson(Message);
        String Request = "{\"Type\":\"SEND_MSG\"," + "\"Message\":" + M + ",\"DialogID\":" + DialogID + "}";
        System.out.println(Request);
        return Request; // return result
    }

    public String REG_USER(String Login, String Password){
        String Request = "{\"Type\":\"REG_USER\"," + "\"Login\":\"" + Login +"\"," +
                "\"Password\":\"" + Password +"\"}";
        System.out.println(Request);
        return Request; // return result
    }

    public String AUTH_USER(String Login, String Password){
        String Request = "{\"Type\":\"AUTH_USER\"," + "\"Login\":\"" + Login +"\"," +
                "\"Password\":\"" + Password +"\"}";
        System.out.println(Request);
        return Request; // return result
    }

    public String UNAUTH_USER(String Login){
        String Request = "{\"Type\":\"UNAUTH_USER\"," + "\"Login\":\"" + Login +"\"}";
        System.out.println(Request);
        return Request; // return result
    } // не помню зачем он нужен

    public String GET_DIAL(String Username){
        String Request = "{\"Type\":\"GET_DIAL\"," + "\"Username\":\"" + Username +"\"}";
        return Request; // return result
    }

    public String NEW_DIAL(String Username, String TargetUser){
        String Request = "{\"Type\":\"NEW_DIAL\"," + "\"Username\":\"" + Username +"\"," +
                "\"TargetUser\":\"" + TargetUser +"\"}";
        return Request;
    }
}