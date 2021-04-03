package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.Files.readTextFromDocxFile;
import static utils.Files.getPdf;
import static utils.Files.getXls;
import static utils.Files.readTextFromPath;
import static utils.Zip.unzip;

public class FilesTest extends TestBase {

    private static final String
            DOCX_FILE_PATH = "/blob/master/src/test/resources/file.docx",
            DOCX_EXPECTED_DATA = "it is docx file",
            PDF_FILE_PATH = "/blob/master/src/test/resources/file.pdf",
            PDF_EXPECTED_DATA = "it is pdf file",
            XSL_FILE_PATH = "/blob/master/src/test/resources/file.xls",
            XSL_EXPECTED_DATA = "it is xls file",
            ZIP_FILE_PATH = "/blob/master/src/test/resources/file.zip",
            ZIP_EXPECTED_DATA = "it is txt file";

    @Test
    void docxTest() throws FileNotFoundException {
        String actualData = readTextFromDocxFile(DOCX_FILE_PATH);
        assertThat(actualData, equalTo(DOCX_EXPECTED_DATA));
    }

    @Test
    void pdfTest() throws IOException {
        PDF pdf = getPdf(PDF_FILE_PATH);
        assertThat(pdf, PDF.containsText(PDF_EXPECTED_DATA));
    }

    @Test
    void xlsTest() throws IOException {
        XLS xls = getXls(XSL_FILE_PATH);
        assertThat(xls, XLS.containsText(XSL_EXPECTED_DATA));
    }

    @Test
    void zipTest() throws ZipException, IOException {
        String unzipTxtFilePath = unzip(ZIP_FILE_PATH);
        String actualData = readTextFromPath(unzipTxtFilePath);
        assertThat(actualData, equalTo(ZIP_EXPECTED_DATA));
    }

}
