package d6ChainResponsibilityPattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * Author: wangyufei
 * CreateTime:2018/03/05
 * Companion:Champion Software
 */
public class HtmlReader extends BaseTextReader{
    @Override
    public String[] acceptFileExts() {
        return new String[]{"htm","html"};
    }

    @Override
    public String readAsString(File file) {
        String content = null;
        try {
            Document parse = Jsoup.parse(file, "UTF-8");//编码格式传null会去自动匹配html的编码格式
            return parse.text();
        } catch (IOException e) {
            throw new RuntimeException("读取文件失败",e);
        }
    }
}
