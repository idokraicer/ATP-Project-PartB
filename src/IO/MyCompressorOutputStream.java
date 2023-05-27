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


    //
//    @Override
//    public void write(byte[] b) throws IOException {
//        // write the first 12 bytes
//        // Then writes every 8 numbers as a single byte
//        // Then writes the last byte remaining
//        byte[] compressed = new byte[12 + (b.length - 12) / 8];
//        for (int i = 0; i < 12; i++) {
//            compressed[i] = b[i];
//        }
//        int index = 12;
//        for (int i = 12; i+8 < b.length; i += 8) {
//            int num = 0;
//            for (int j = 0; j < 8; j++) {
//                if (b[i + j] == 1) {
//                    num += Math.pow(2, j);
//                }
//            }
//            compressed[index] = (byte) num;
//            index++;
//        }
//        int num = 0;
//        int power = 0;
//        for (int i = 12 + (index - 12) * 8; i < b.length; i++) {
//            num += Math.pow(2, power++) * b[i];
//        }
//        compressed[index] = (byte) num;
//        out.write(compressed);
//    }
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


