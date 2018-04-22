package d6ChainResponsibilityPattern;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.Arrays;

/**
 * Author: wangyufei
 * CreateTime:2018/03/05
 * Companion:Champion Software
 */
public abstract class BaseTextReader implements ITextReader {
    @Override
    public boolean accept(File toReadFile) {
        String extension = FilenameUtils.getExtension(toReadFile.getName());
        return Arrays.stream(acceptFileExts()).anyMatch(n -> n.equalsIgnoreCase(extension));
    }

    /**
     * 返回支持的文件的后缀名
     *
     * @return
     */
    public abstract String[] acceptFileExts();
}
