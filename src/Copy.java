import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Copy {
    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("Usage java copy < source file> <destination file");
            return;
        }

        String sourceFilePath = args[0];
        String destinationFilePath = args[1];



        try{
            copyFile(sourceFilePath, destinationFilePath);
            System.out.println("Source file is copied to destination file successfully");
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void copyFile(String sourceFilePath, String destinationFilePath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        File destinationFile = new File(destinationFilePath);

        if (!sourceFile.exists()) {
            throw new IOException("Source file not found: " + sourceFilePath);
        }

        if (destinationFile.exists() && !destinationFile.canWrite()) {
            throw new IOException("Permission denied: Cannot write to destination file.");
        }

        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int length;


            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        } catch (SecurityException e) {

            throw new IOException("Access denied: " + e.getMessage());
        }
    }
}
