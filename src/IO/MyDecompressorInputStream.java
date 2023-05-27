package IO;

import javax.lang.model.element.NestingKind;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }


    /**
     * decompresses the byte arrays to a bigger byte array
     *
     * @param b - byte array to read
     * @return 0 if successful
     * @throws IOException - if there is a problem with the input stream
     */
    @Override
    public int read(byte[] b) throws IOException {
        int index = 12;
        for (int i = 0; i < 12; i++) {
            b[i] = (byte) read();
        }
        int i = 12;
        while (i < b.length) {
            int num = read();
            num = num < 0 ? num + 256 : num;
            for (int j = 0; j < 8 && i < b.length; j++) {
                b[i++] = (byte) (num % 2);
                num = num / 2;
            }
        }
        return 0;
    }
}
