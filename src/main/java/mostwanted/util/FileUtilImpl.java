package mostwanted.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtilImpl implements FileUtil{
    @Override
    public String readFile(String filePath) throws IOException {
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        StringBuilder stringBuilder= new StringBuilder();
        String line;

        while ((line= bufferedReader.readLine())!= null) {
            stringBuilder.append(line).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
