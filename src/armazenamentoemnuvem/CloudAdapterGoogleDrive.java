package armazenamentoemnuvem;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveRequestInitializer;
import com.google.api.services.drive.DriveScopes;
import control.CloudAdapter;
import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class CloudAdapterGoogleDrive implements CloudAdapter {

    private static String CLIENT_ID = "113529272903-vdjknsrtjhdq9nojb8sukgvnhhoknk7n.apps.googleusercontent.com"; //adicione aqui o CLIENT_SECRET que nós criamos 
    private static String CLIENT_SECRET = "um0ktL5voU7gJcyyOjfHDDbV"; //a REDIRECT_URI vai ser a mesma sempre (provavelmente) 
    private static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

    @Override
    public void autenticacao() {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        //Gera um código de autorização
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE)).setAccessType("online").setApprovalPrompt("auto").build();

        String urlAuthorization = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI(urlAuthorization);
//            desktop.browse(uri);
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//            String code = br.readLine();            
            GoogleTokenResponse response = new GoogleTokenResponse();
            response.setAccessToken("4/1d7n-hCMdcaiQqdAgkjFUlaRwckx5XiG-yH1Z9vkiY8");
//            GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
            GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
            Drive.Builder driveBuilder = new Drive.Builder(httpTransport, jsonFactory, credential);
            driveBuilder.setApplicationName("sistemaArmazenamentoNuvem");            
//            Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();
            OutputStream directorio  = new FileOutputStream("saida.txt");;
            Drive service = driveBuilder.build();                        
            Drive.Files.List request = service.files().list();
            Drive.Files.Get getFile = service.files().get("1");
            getFile.executeAndDownloadTo(directorio);
            request.get("Mecanica Deno.JPG");
            System.out.println(request.size());

        } catch (URISyntaxException | IOException ex) {
            ex.printStackTrace();
        }
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
