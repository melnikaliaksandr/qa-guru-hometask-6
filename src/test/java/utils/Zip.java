package utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static utils.Files.downloadFileFromPath;

public class Zip {

    public static String unzip(String path) throws ZipException, FileNotFoundException {
        File downloadedZipFile = downloadFileFromPath(path);
        String pathToFile = downloadedZipFile.getPath();
        ZipFile zipFile = new ZipFile(pathToFile);
        String pathToFolderFile = pathToFile.replace(downloadedZipFile.getName(), "");
        zipFile.extractAll(pathToFolderFile);
        File extractedFile = getFileByName(pathToFolderFile,".txt");
        return extractedFile.getPath();
    }

    public static File getFileByName(String path, String fileName) {
        File expectedFile = null;
        List<File> files = (List<File>) FileUtils.listFiles(new File(path), null, true);
        for (File file: files) {
            if (!file.toString().contains("._file.txt")) { // при распаковке архива на MaсOS создается дополнительный
                // временный файл с какой-то хитрой кодировкой, добавлен if чтобы его не использовать
                if (file.toString().contains(fileName)) {
                    expectedFile = file;
                    break;
                }
            }
        }
        return expectedFile;
    }

}