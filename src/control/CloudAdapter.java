package control;

import armazenamentoemnuvem.ArquivoNuvem;
import java.io.File;
import java.util.ArrayList;

public interface CloudAdapter {
    void autenticacao();
    boolean uploadArquivo(String arquivo, String pathDestino);
    boolean downloadArquivo(String pathArquivo, String caminhoDestino);
    ArrayList<ArquivoNuvem> exibirArquivos(String directorio);            
}
