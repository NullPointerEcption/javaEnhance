package d6ChainResponsibilityPattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;

/**
 * Author: wangyufei
 * CreateTime:2018/03/05
 * Companion:Champion Software
 */
public class TextReader extends BaseTextReader {
    @Override
    public String[] acceptFileExts() {
        return new String[]{"txt"};
    }

    @Override
    public String readAsString(File file) {
        String content = null;
        try {
            content = FileUtils.readFileToString(file, "UTF-8");
            return content;
        } catch (IOException e) {
            throw new RuntimeException("读取文件失败",e);
        }
    }
}
