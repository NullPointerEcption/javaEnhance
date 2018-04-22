package d7visitorPattern;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class App {
    @Test
    public void test() throws DocumentException {
        SAXReader saxReader =new SAXReader();
        Document doc =saxReader.read("D:\\MyIdeaWorkspace\\javaEnhance\\DesignPattern\\pom.xml");
        MyPOMVisitor visitor = new MyPOMVisitor();
        doc.accept(visitor);
        System.out.println(visitor.getAllText());
    }
}

class MyPOMVisitor implements Visitor{
    private StringBuilder allText=new StringBuilder();
    @Override
    public void visit(Attribute attribute) {

    }

    @Override
    public void visit(CDATA cdata) {

    }

    @Override
    public void visit(Comment comment) {

    }

    @Override
    public void visit(Document document) {

    }

    @Override
    public void visit(DocumentType documentType) {

    }

    @Override
    public void visit(Element element) {

    }

    @Override
    public void visit(Entity entity) {

    }

    @Override
    public void visit(Namespace namespace) {

    }

    @Override
    public void visit(ProcessingInstruction processingInstruction) {

    }

    @Override
    public void visit(Text text) {
        allText.append(text.getText()).append(System.lineSeparator());
    }

    public StringBuilder getAllText() {
        return allText;
    }
}
