package d7visitorPattern;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class App2 {


    @Test
    public void test() throws Exception {
        Document parse = Jsoup.parse(new URL("http://www.rupeng.com"), 10000);
        MyNodeVisitor nodeVisitor = new MyNodeVisitor();
        parse.traverse(nodeVisitor);

        Stream.of(nodeVisitor.getUrls()).forEach(
                (url)->{
                    try(
                            InputStream inputStream = new URL(url).openStream();
                            FileOutputStream fos=new FileOutputStream("D://imgs//"+UUID.randomUUID().toString().substring(0,5)+".png");
                            ){
                        IOUtils.copy(inputStream,fos);
                        System.out.println("ok");
                    }catch (Exception e){

                    }

                }
        );


    }


}

class MyNodeVisitor implements NodeVisitor{

    private List<String> urls =new ArrayList<>();

    @Override
    public void head(Node node, int i) {
       if (node.nodeName().equals("img")){
           String url = node.attr("src");
            urls.add(url);
       }
    }

    @Override
    public void tail(Node node, int i) {
    }

    public String[] getUrls() {
        return urls.toArray(new String[urls.size()]);
    }
}
