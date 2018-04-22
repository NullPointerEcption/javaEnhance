package d6ChainResponsibilityPattern;

import java.io.File;

/**
 * Author: wangyufei
 * CreateTime:2018/03/05
 * Companion:Champion Software
 *
 * 一个公共的接口，实现了该接口的类表示可以进行不同种文件的内容读取
 */
public interface ITextReader {
    /**
     * 是否能够读取toReadFile这个文件
     * @param toReadFile 待读取的文件
     * @return
     */
    boolean accept(File toReadFile);

    /**
     * 将文件的内容读取出来
     * @param file 待读取的文件
     * @return
     */
    String readAsString(File file);

}
