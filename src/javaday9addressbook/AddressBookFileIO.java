package javaday9addressbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookFileIO {
    public static String FILE_NAME = "AddressBook.txt";
    public static void writeData(Map<String, List<Contacts>> addressBooks) {
        Path path = Paths.get(FILE_NAME);
        StringBuilder contactDataBuilder = new StringBuilder();
        addressBooks.keySet().forEach(keyOfBook -> {
            contactDataBuilder.append("\n").append(keyOfBook).append("->\n");
            addressBooks.get(keyOfBook).forEach(contacts -> {
                String string = contacts.toString();
                contactDataBuilder.append(string);
            });
        });
        try {
            Files.deleteIfExists(path);
            Files.write(path, contactDataBuilder.toString().getBytes());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static Map<String,  String> readData() {
        Map<String, String> mapFileContents = new HashMap<>();
        BufferedReader bufferedReader = null;
        try {
            File file = new File("AddressBook.txt");
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                String bookName = parts[0].trim();
                String fname = parts[1].trim();
                mapFileContents.put(bookName, fname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                }
            }
        }
        return mapFileContents;
    }
}
