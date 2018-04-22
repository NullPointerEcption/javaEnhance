package d9DerectorPattern;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.lang.String;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App3 {
    @Test
    public void test() throws Exception {

//        List<String> strLimitList = new StringLengthLimitList(5,new LinkedList<String>());
//        strLimitList.add("hi");
//        strLimitList.add("hello");
//        strLimitList.add("howareu");


        List<String> strLimitList1 = new SizeLimitList<>(3, new StringLengthLimitList(5, new LinkedList<String>()));
        strLimitList1.add("hi");
        strLimitList1.add("hello");
        strLimitList1.add("howar");
        strLimitList1.add("howar");

    }

    @Test
    public void test1() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("dddd");

        List<String> unmodifiableList = Collections.unmodifiableList(list);
        unmodifiableList.add("a");

        unmodifiableList.stream().forEach(System.out::println);
    }

    @Test
    public void renameFiles() throws Exception {
        File rootDirectory = new File("D:\\MyGraduateProject\\MinDOCHTMLS\\views");

        String[] extensions = {"tpl"};
        Collection<File> tpls = FileUtils.listFiles(rootDirectory, extensions, true);
        tpls.forEach(f -> {
            try {
                reNameExtToHtml(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        try {
            new App3().modifyFileContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyFileContent() throws Exception {
        File rootDirectory = new File("D:\\MyGraduateProject\\MinDOCHTMLS\\views");
        String[] extensions = {"html"};
        Collection<File> tpls = FileUtils.listFiles(rootDirectory, extensions, true);

        tpls.forEach(f -> {
                    try {
                        modifyContent(f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    public void modifyContent(File f) throws IOException {
        List<String> lines = FileUtils.readLines(f, "UTF-8");
        List<Object> modifyLines = lines.stream()
                .map(new Function<String, Object>() {
                    @Override
                    public Object apply(String s) {
                        if (s.contains(" <link href=\"{{cdncss \"/static")) {
                            return replaceContent(s);
                        }
                        return s;
                    }
                })
                .collect(Collectors.toList());
        FileUtils.writeLines(f, modifyLines);
        System.out.println("文件" + f.getAbsolutePath() + "替换url完成");
    }

    private String replaceContent(String line) {
        //<link href="{{cdncss "/static/bootstrap/css/bootstrap.min.css"}}" rel="stylesheet">

        int startIndex = line.indexOf("\"/static") + 2;
        int endIndex = line.lastIndexOf("\"}}");

        String innerContent = line.substring(startIndex, endIndex);
        //href="../../static/bootstrap/css/bootstrap.min.css"
        String newContent = "\"../../" + innerContent + "\"";

        String replacedLine = line.replaceAll("href=.+ ", "href=" + newContent + " ");
        return replacedLine;
    }

    /**
     * 把文件扩展名变成.html
     *
     * @param srcFile
     */
    private void reNameExtToHtml(File srcFile) throws IOException {
        String dirName = srcFile.getParent();
        String newFileName = srcFile.getName().substring(0, srcFile.getName().lastIndexOf(".")) + ".html";
        String newFullPath = dirName.concat("\\").concat(newFileName);

        FileUtils.moveFile(srcFile, new File(newFullPath));
        System.out.println("完成重命名从:" + srcFile.getAbsolutePath() + "->" + newFullPath);
    }

}

class StringConsumer implements Consumer<String> {
    public StringConsumer() {
    }

    @Override
    public void accept(String s) {
        if (s.contains(" <link href=\"{{cdncss \"/static")) {
            System.out.println(s);
        }
    }
}


class StringLengthLimitList implements List<String> {


    private int maxLength;
    private List<String> target;

    public StringLengthLimitList(int maxLength, List<String> target) {
        this.maxLength = maxLength;
        this.target = target;
    }

    @Override
    public int size() {
        return target.size();
    }

    @Override
    public boolean isEmpty() {
        return target.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return target.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return target.iterator();//TODO 自实现一个StringLengthLimitIterator
    }

    @Override
    public Object[] toArray() {
        return target.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return target.toArray(a);
    }

    @Override
    public boolean add(String string) {
        if (string.isEmpty() || string.length() > maxLength) {
            throw new RuntimeException("要添加的字符串" + string + ",超出string大小限制，字符串最大长度为：" + maxLength);
        }
        return target.add(string);
    }

    @Override
    public boolean remove(Object o) {
        return target.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return target.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends java.lang.String> c) {
        for (String s : c) {
            if (s.isEmpty() || s.length() > maxLength) {
                throw new RuntimeException("超出string大小限制，字符串最大长度为：" + maxLength);
            }
        }
        return target.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends java.lang.String> c) {
        for (String s : c) {
            if (s.isEmpty() || s.length() > maxLength) {
                throw new RuntimeException("超出string大小限制，字符串最大长度为：" + maxLength);
            }
        }
        return target.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return target.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return target.retainAll(c);
    }

    @Override
    public void clear() {
        target.clear();
    }

    @Override
    public java.lang.String get(int index) {
        return target.get(index);
    }

    @Override
    public java.lang.String set(int index, java.lang.String s) {
        if (s.isEmpty() || s.length() > maxLength) {
            throw new RuntimeException("超出string大小限制，字符串最大长度为：" + maxLength);
        }

        return target.set(index, s);
    }

    @Override
    public void add(int index, java.lang.String s) {
        if (s.isEmpty() || s.length() > maxLength) {
            throw new RuntimeException("超出string大小限制，字符串最大长度为：" + maxLength);
        }
        target.add(index, s);
    }

    @Override
    public java.lang.String remove(int index) {
        return target.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return target.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return target.lastIndexOf(o);
    }

    @Override
    public ListIterator<java.lang.String> listIterator() {
        return target.listIterator();//TODO 自实现一个StringLengthLimitIterator
    }

    @Override
    public ListIterator<java.lang.String> listIterator(int index) {
        return target.listIterator(index);//TODO 自实现一个StringLengthLimitIterator
    }

    @Override
    public List<java.lang.String> subList(int fromIndex, int toIndex) {
        return target.subList(fromIndex, toIndex);
    }
}
