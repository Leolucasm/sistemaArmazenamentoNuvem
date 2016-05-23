package armazenamentoemnuvem;

import control.CloudAdapter;
import control.CloudAdapterDropbox;
import java.util.ArrayList;

public class ArmazenamentoEmNuvem {
    
    public static void main(String[] args) {
        ArrayList<String> listaDeArquivos = new ArrayList<>();
        CloudAdapter cloud = new CloudAdapterDropbox();        
        cloud.autenticacao();                
        listaDeArquivos = cloud.exibirArquivos();
    }
    
}
