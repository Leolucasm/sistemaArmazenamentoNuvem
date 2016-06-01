package control;

import armazenamentoemnuvem.ArquivoNuvem;
import java.io.File;
import java.util.ArrayList;
import com.dropbox.core.*;
import java.io.*;
import java.util.Locale;

public class CloudAdapterDropbox implements CloudAdapter {

    DbxClient DropboxClient;

    /*
     @Override
     public void autenticacao() {
     try {
     final String APP_KEY = "p3yzykdtidsobcy";
     final String APP_SECRET = "wzsfz666y0jedt2";            
     DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

     DbxRequestConfig config = new DbxRequestConfig(
     "JavaTutorial/1.0", Locale.getDefault().toString());
     DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);                        
     String authorizeUrl = webAuth.start();
     System.out.println("1. Go to: " + authorizeUrl);
     System.out.println("2. Click \"Allow\" (you might have to log in first)");
     System.out.println("3. Copy the authorization code.");
     String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
     DbxAuthFinish authFinish = webAuth.finish(code);
     String accessToken = authFinish.accessToken;
     DropboxClient = new DbxClient(config, accessToken);
     System.out.println("Linked account: " + DropboxClient.getAccountInfo().displayName);
     } catch (IOException ex) {
     Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
     } catch (DbxException ex) {
     Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
     }

     }*/
    @Override
    public void autenticacao() {
        DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
        try {
            DropboxClient = new DbxClient(config, "t8u1onC-qYAAAAAAAAAAIDs2XSXpBAOBTQMhL-a7ONxA1jnE1dWGRGD1YtfAywp3");
        } catch (Exception e) {
            System.out.println("Houve um problema ao realizar a autenticação, verifique. \nMensagem: " + e.getMessage());
        }
    }

    @Override
    public boolean uploadArquivo(String arquivo, String pathDestino) {
        try {
            File inputFile = new File(arquivo);
            FileInputStream inputStream = new FileInputStream(inputFile);

            DropboxClient.uploadFile(pathDestino,
                    DbxWriteMode.add(), inputFile.length(), inputStream);

        } catch (DbxException | IOException ex) {
            System.out.println("Houve um problema ao realizar o upload, verifique. \nMensagem: " + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean downloadArquivo(String pathArquivo, String caminhoDestino) {
        try {
            FileOutputStream outputStream = new FileOutputStream(caminhoDestino);
            DropboxClient.getFile(pathArquivo, null,
                    outputStream);
        } catch (DbxException | IOException ex) {
            System.out.println("Houve um problema ao realizar o download, verifique. \nMensagem: " + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<ArquivoNuvem> exibirArquivos(String directorio) {
        ArrayList<ArquivoNuvem> listaArquivos = new ArrayList<>();
        try {
            DbxEntry.WithChildren listing = DropboxClient.getMetadataWithChildren(directorio);
            System.out.println("Arquivos disponíveis: \n");
            int contador = 1;
            for (DbxEntry child : listing.children) {
                ArquivoNuvem arquivo = new ArquivoNuvem(child.name, child.path);
                listaArquivos.add(arquivo);
                System.out.println(contador + "°) " + child.path);
                contador++;
            }
        } catch (DbxException ex) {
            System.out.println("Houve um problema ao exibir os arquivos, verifique. \nMensagem: " + ex.getMessage());
            return null;
        }
        return listaArquivos;
    }
}
