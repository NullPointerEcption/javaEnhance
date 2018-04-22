package d6ChainResponsibilityPattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Author: wangyufei
 * CreateTime:2018/03/05
 * Companion:Champion Software
 */
public class DocXReader extends BaseTextReader {

    @Override
    public String[] acceptFileExts() {
        return new String[]{"docx"};
    }

    @Override
    public String readAsString(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument xdoc = new XWPFDocument(fis);
             XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);)
        {
            String text = extractor.getText();
            return text;
        }catch (Exception e){
            throw new RuntimeException("读取docx文件异常",e);
        }
    }
}
