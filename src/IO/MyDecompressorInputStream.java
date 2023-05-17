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


    @Override
    public int read(byte[] b) throws IOException {
        byte[] inputBytes = in.readAllBytes();
        int index = 12;
        for (int i = 0; i < 12; i++) {
            b[i] = inputBytes[i];
        }
        for (int i = 12; i < inputBytes.length; i++) {
            int num = inputBytes[index];
            for (int j = 0; j < 8; j++) {
                if (num % 2 == 1) {
                    b[i + j] = (byte) 1;
                } else {
                    b[i + j] = (byte) 0;
                }
                num /= 2;
            }
            index++;
        }
        return 0;
    }
}
