package Client;

import java.io.InputStream;
import java.io.OutputStream;

public interface IClientStrategy {
    /**
     * client strategy
     * @param inFromServer inFromServer
     * @param outToServer outToServer
     */
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);

}
