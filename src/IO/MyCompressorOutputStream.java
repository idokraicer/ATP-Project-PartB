package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    /**
     * compresses the byte arrays to a smaller byte array
     * @param b - byte array to write
     * @throws IOException - if there is a problem with the output stream
     */
    @Override
    public void write(byte[] b) throws IOException {

        // compress meta-data
        for (int i = 0; i < 12; i++) {
            write(b[i]);
        }

        int sum = 0;
        int j = 0;
        // compress maze
        for (int i = 12; i < b.length; i++) {
            if (j == 8) {
                write(sum);
                j = 0;
                sum = 0;
            }
            sum += Math.pow(2, j) * b[i];
            j++;
        }
    }
}


