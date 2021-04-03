package utils;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Files {

    public static String readTextFromFile(File file) throws IOException {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    public static String readTextFromPath(String path) throws IOException {
        return readTextFromFile(getFile(path));
    }

    public static String readTextFromDocxFile(String path) throws FileNotFoundException {
        File docxFile = downloadFileFromPath(path);
        String pathToFile = docxFile.getPath();
        String result = "";
        try {
            FileInputStream inputStream = new FileInputStream(pathToFile);
            XWPFDocument file = new XWPFDocument(OPCPackage.open(inputStream));
            XWPFWordExtractor extractor = new XWPFWordExtractor(file);
            result = extractor.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static File downloadFileFromPath(String path) throws FileNotFoundException {
        open(path);
        return $("#raw-url").download();
    }

    public static PDF getPdf(String path) throws IOException {
        return new PDF(downloadFileFromPath(path));
    }

    public static XLS getXls(String path) throws IOException {
        return new XLS(downloadFileFromPath(path));
    }

    public static File getFile(String path) {
        return new File(path);
    }

}