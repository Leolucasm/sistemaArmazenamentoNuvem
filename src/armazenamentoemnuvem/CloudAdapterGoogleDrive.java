package armazenamentoemnuvem;

import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import control.CloudAdapter;
import java.util.ArrayList;

public class CloudAdapterGoogleDrive implements CloudAdapter{
    
    
    @Override
    public void autenticacao() {        
    }

    @Override
    public boolean uploadArquivo(String arquivo, String pathDestino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean downloadArquivo(String pathArquivo, String caminhoDestino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ArquivoNuvem> exibirArquivos(String directorio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
