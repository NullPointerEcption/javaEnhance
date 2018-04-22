package d3ObserverPattern;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.Test;

import java.io.File;

/**
 * Author: wangyufei
 * CreateTime:2018/03/04
 * Companion:Champion Software
 */
public class App1 {

    public static void main(String[] args) {
        FileAlterationObserver observer=new FileAlterationObserver("D:/");
        observer.addListener(new FileAlterationListener() {
            @Override
            public void onStart(FileAlterationObserver observer) {
                System.out.println("observe start...");
            }

            @Override
            public void onDirectoryCreate(File directory) {
                System.out.println("create a directory : "+directory.getName());
            }

            @Override
            public void onDirectoryChange(File directory) {
                System.out.println("directory changed : "+directory.getName());
            }

            @Override
            public void onDirectoryDelete(File directory) {
                System.out.println("directory delete : "+directory.getName());
            }

            @Override
            public void onFileCreate(File file) {
                System.out.println("create a file : "+file.getName());
            }

            @Override
            public void onFileChange(File file) {
                System.out.println("file changed : "+file.getName());
            }

            @Override
            public void onFileDelete(File file) {
                System.out.println("delete a file : "+file.getName());
            }

            @Override
            public void onStop(FileAlterationObserver observer) {
                System.out.println("observe stop ...");
            }
        });

        FileAlterationMonitor monitor=new FileAlterationMonitor(2000,observer);
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
