package databaseconnection;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Database {
    public static void main(String[] args) {
        // Specify the URL of the PDF file
        String fileURL = "https://example.com/sample.pdf";
        // Specify the local file path to save the downloaded PDF
        String saveFilePath = "C:/Downloads/sample.pdf";
        
        // Call the method to download the PDF
        try {
            downloadPDF(fileURL, saveFilePath);
            System.out.println("PDF downloaded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while downloading the PDF.");
            e.printStackTrace();
        }
    }

    public static void downloadPDF(String fileURL, String saveFilePath) throws IOException {
        // Open a connection to the URL
        URL url = new URL(fileURL);
        InputStream inputStream = new BufferedInputStream(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(saveFilePath);

        // Define a buffer to hold bytes while reading/writing
        byte[] buffer = new byte[1024];
        int bytesRead;

        // Read bytes from the input stream and write them to the output stream
        while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        // Close streams
        fileOutputStream.close();
        inputStream.close();
    }
}
