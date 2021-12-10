package QueryManager;

import FileSystem.Message;
import com.google.gson.Gson;


public class RequestManager {

    private Gson gson = new Gson();

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
        /*
        *
        *
        * ПОТОКИ
        *
        * */
        //System.out.println(Request);
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