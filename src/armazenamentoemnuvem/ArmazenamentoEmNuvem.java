package armazenamentoemnuvem;

import control.CloudAdapter;
import control.CloudAdapterDropbox;
import java.util.ArrayList;

public class ArmazenamentoEmNuvem {

    public static void main(String[] args) {
        ArrayList<ArquivoNuvem> listaDeArquivos = new ArrayList<>();
        CloudAdapter cloud = new CloudAdapterDropbox();
        cloud.autenticacao();
        listaDeArquivos = cloud.exibirArquivos("/Teste");
       // ArquivoNuvem arquivo = listaDeArquivos.get(1);
        //cloud.downloadArquivo(arquivo.getPathArquivo(), arquivo.getNomeArquivo());
       // cloud.uploadArquivo("C:/Users/5936420/Documents/Nova pasta/sistemaArmazenamentoNuvem/README.MD", "/README.MD");
       // cloud.exibirArquivos("/");
    }

}
