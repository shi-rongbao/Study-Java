import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Test9 {
    public static void main(String[] args) throws FileNotFoundException {
        OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream("..\\myfilescharset\\fos3.txt"));
    }
}
