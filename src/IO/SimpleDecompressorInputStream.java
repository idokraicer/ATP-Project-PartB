package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    /**
     * decompresses the data from consecutive 0's and 1's to a byte array
     *
     * @param b - byte array to read
     * @return 0 if successful
     * @throws IOException - if there is a problem with the input stream
     */
    @Override
    public int read(byte[] b) throws IOException {
        byte[] inputBytes = in.readAllBytes();

        ArrayList<Byte> bytes = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            bytes.add((byte) inputBytes[i]);
        }
        int index = 12;
        int num;
        boolean isZero = true;
        for (int i = 12; i < b.length; i += 1) {
            num = inputBytes[i];
            if (num == 0) continue;
            for (int j = 0; j < num; j++) {
                bytes.add((byte) (isZero ? 0 : 1));
            }
            isZero = !isZero;
        }
        byte[] toReturn = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            toReturn[i] = bytes.get(i);
        }

        return 0;
    }
}
