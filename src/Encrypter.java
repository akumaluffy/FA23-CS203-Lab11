import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
        String message = readFile(inputFilePath);
        char[] array = message.toCharArray();
        for (int i=0; i < array.length; i++) {
            int ascii = array[i];
            if (ascii >= 97 && ascii <= 122) {
                ascii += shift;
                if (ascii > 122) {
                    ascii -= 26;
                }
                array[i] = (char)ascii;
            }
            else if (ascii >= 65 && ascii <= 90) {
                ascii += shift;
                if (ascii > 90) {
                    ascii -= 26;
                }
                array[i] = (char)ascii;
            }
        }
        writeFile(String.valueOf(array), encryptedFilePath);
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
        String message = readFile(messageFilePath);
        char[] array = message.toCharArray();
        int temp = 4;
        for (int i=0; i < array.length; i++) {
            int ascii = array[i];
            if (ascii >= 65 && ascii <= 90) {
                ascii -= temp;
                if (ascii < 65) {
                    ascii += 26;
                }
                array[i] = (char)ascii;
            }
            else if (ascii >= 97 && ascii <= 122) {
                ascii -= temp;
                if (ascii < 97) {
                    ascii += 26;
                }
                array[i] = (char)ascii;
            }
        }
        writeFile(String.valueOf(array), decryptedFilePath);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        String message = "";
        //TODO: Read file from filePath
        Scanner fileScanner = new Scanner(Paths.get(filePath));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            message += line;
        }
        fileScanner.close();
        return message;
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
        try {
            FileWriter fw = new FileWriter(new File(filePath), false);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
