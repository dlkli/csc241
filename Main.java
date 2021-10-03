import java.io.File;
import javax.xml.parsers.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("What is the file name?");
        File inputFile = new File(sc.next());
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        MyHandler handler = new MyHandler();
        parser.parse(inputFile, handler);
        handler.readPC.play();
 
    }
}