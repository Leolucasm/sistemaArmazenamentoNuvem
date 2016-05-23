package armazenamentoemnuvem;

import com.google.api.client.auth.oauth2.Credential;
import control.CloudAdapter;
import java.io.File;
import java.util.ArrayList;


public class GoogleCloudAdapter implements CloudAdapter{
    
    @Override
    public void autenticacao() {
        
    }

    @Override
    public boolean uploadArquivo(String file) {
        return false;
    }

    @Override
    public File downloadArquivo(String file) {
        return null;
    }

    @Override
    public ArrayList<String> exibirArquivos() {
        return null;
    }
    
    private static Credential authorize() throws Exception{
        
    }
}
