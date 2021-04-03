package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void setup() {
        Configuration.startMaximized = true;
        Configuration.downloadsFolder = "downloads";
        Configuration.baseUrl = "https://github.com/melnikaliaksandr/qa-guru-hometask-6";
    }

}
