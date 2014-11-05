package DBCreater;

import java.io.File;
import java.util.ArrayList;

public class FileList {
    ArrayList<File> file;
    public FileList(String path){
        file = new ArrayList();
        file = this.addFile(path);
    }
    
    public ArrayList<File> addFile(String path) {
        ArrayList<File> files = new ArrayList();
        File fs[] = new File(path).listFiles();
        for (File f1 : fs) {
            if (f1.isFile()) {
                if (f1.getName().endsWith(".mdb")) {
                    files.add(f1);
                }
            } else {
                ArrayList<File> dirFiles = addFile(f1.getParent() + "/" + f1.getName());
                for(File f : dirFiles){
                    files.add(f);
                }
            }
        }
        
        return files;
    }

    public ArrayList<File> getFiles() {
        return new ArrayList(file);
    }
}
