package result;

import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 返回图片的流给浏览器
 */
public class FileResult implements BaseResult {

    private String filePath;//服务器的文件路径
    private String  downFileName;//浏览器下载的文件名称

    public FileResult(String filePath) {
        this.filePath = filePath;
        this.downFileName= UUID.randomUUID().toString().replace("-","").substring(0,16);
    }

    public FileResult(String filePath, String downFileName) {
        this.filePath = filePath;
        this.downFileName = downFileName;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        File file2Down = new File(filePath);//服务器上的文件
        String ext = FilenameUtils.getExtension(file2Down.getAbsolutePath());//文件拓展名
        response.setContentType("application/octet-stream");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition", "attachment;fileName="+downFileName+"."+ext);

        try (FileInputStream fis = new FileInputStream(file2Down);
             ServletOutputStream outputStream = response.getOutputStream();) {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
