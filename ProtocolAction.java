import java.util.HashMap;

public interface ProtocolAction {
    void performAction(HashMap<String, String> fields, String ip, int port);
}