package Client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;

public class Client implements IClientStrategy{
    public Client(InetAddress localHost, int i, IClientStrategy iClientStrategy) {
        
    }

    @Override
    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
        
    }

    public void communicateWithServer() {
    }
}
