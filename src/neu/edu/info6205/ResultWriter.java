package neu.edu.info6205;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ResultWriter {

    private BufferedWriter writer;

    public ResultWriter() throws IOException {
       this.writer = new BufferedWriter(new FileWriter("C:\\Users\\Abhishek Dongare\\Desktop\\PSORES\\pso_result"+Thread.currentThread().getName()+".txt", false));
    }


    public void writeLine(String line) throws IOException {
        writer.write(line);

    }

    public void closeStream() throws IOException {
        writer.close();
    }
}
