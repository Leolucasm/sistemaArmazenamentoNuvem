package control;

import java.io.File;
import java.util.ArrayList;

public interface CloudAdapter {
    boolean uploadArquivo(String file);
    File downloadArquivo(String file);
    ArrayList<String> exibirArquivos();            
}
