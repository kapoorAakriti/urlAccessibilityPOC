import java.io.IOException;

public class callingScript {
    public static void main(String[] args) throws IOException, InterruptedException {

        urlAccessibility readXlsx = new urlAccessibility();
        readXlsx.readXLSXFile("C://Users//aakkapoo//Desktop//URLAccessibilitysheet.xlsx");
    }
}
