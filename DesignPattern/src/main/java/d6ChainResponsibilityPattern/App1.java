package d6ChainResponsibilityPattern;

import com.google.common.io.Resources;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Author: wangyufei
 * CreateTime:2018/03/05
 * Companion:Champion Software
 */
public class App1 {

    private static LinkedList<ITextReader> textReaderLinkedList=new LinkedList<>();
    static {

    }
    //根据配置文件加载所有的ITextReader的实现类
    static {
        try (InputStream in = Resources.getResource("ClassForReaders.properties").openStream()){
            Properties properties = new Properties();
            properties.load(in);

            String[] textReaderNameArr=properties.get("TextReaders").toString().split(",");//加载配置文件
            for (int i = 0; i < textReaderNameArr.length; i++) {
                Class clz = null;
                try {
                    clz = Class.forName(textReaderNameArr[i]);
                    ITextReader instance = (ITextReader)clz.newInstance();
                    textReaderLinkedList.add(instance);
                } catch (Exception e){
                    throw new RuntimeException("加载类异常",e);
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    //便利每一个实现类 找到可以解析传入File的实现类
    public static String parseFile(File file){
        for (ITextReader iTextReader : textReaderLinkedList) {
            if(iTextReader.accept(file)){
                return iTextReader.readAsString(file);
            }
        }
        throw new RuntimeException("尚未存在可以解析"+file.getName()+"的对应解析类");
    }

    @Test
    public void method() {
        String dirName="D:/";
        String[] files = {"a.txt","1.htm","b.docx","7.docx"};

        textReaderLinkedList.forEach(System.out::println);

        String content1 = parseFile(new File(dirName + files[0]));
        System.out.println(content1);

        String content2 = parseFile(new File(dirName + files[1]));
        System.out.println(content2);

        String content = parseFile(new File(dirName + files[3]));
        System.out.println(content);

    }

}
