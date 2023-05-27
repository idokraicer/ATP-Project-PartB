package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {
    OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write((byte) b);
    }

    /**
     * compresses the byte arrays to sequences of consecutive 1's and 0's
     * @param b - byte array to write
     * @throws IOException - if there is a problem with the output stream
     */
    @Override
    public void write(byte[] b) throws IOException {

        ArrayList<Byte> bytes = new ArrayList<>();
        boolean isZero = false;
        int count = 0;
        for (int i = 12; i < b.length; i++) {
            if (isZero) {
                if (b[i] == 0) {
                    count++;
                } else {
                    writeToByteArray(count, bytes);
                    isZero = false;
                    count = 1;
                }
            } else {
                if (b[i] == 1) {
                    count++;
                } else {
                    writeToByteArray(count, bytes);
                    isZero = true;
                    count = 1;
                }
            }
        }
        writeToByteArray(count, bytes);
        byte[] toReturn = new byte[bytes.size()+12];
        for (int i = 0; i < 12; i++) {
            toReturn[i] = b[i];
        }
        for (int i = 0; i < bytes.size(); i++) {
            toReturn[i+12] = bytes.get(i);
        }
        out.write(toReturn);
    }

    private void writeToByteArray(int num, ArrayList<Byte> bytes) {
        int count = num;
        if (count == 0) {
            bytes.add((byte) 0);
            return;
        }
        while (count > 0) {
            if (count > 255) {
                bytes.add((byte) 255);
                bytes.add((byte) 0);
            } else {
                bytes.add((byte) count);
            }
            count -= 255;
        }

    }

}
