package control;

import java.io.File;
import java.util.ArrayList;
import com.dropbox.core.*;
import java.io.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloudAdapterDropbox implements CloudAdapter {

    DbxClient DropboxClient;

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

    }

    @Override
    public boolean uploadArquivo(String file) {
        FileInputStream inputStream = null;
        try {
            File inputFile = new File("working-draft.txt");
            inputStream = new FileInputStream(inputFile);
            try {
                DbxEntry.File uploadedFile = DropboxClient.uploadFile("/magnum-opus.txt",
                        DbxWriteMode.add(), inputFile.length(), inputStream);
                System.out.println("Uploaded: " + uploadedFile.toString());
            } catch (DbxException ex) {
                Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public File downloadArquivo(String file) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("magnum-opus.txt");
            try {
                DbxEntry.File downloadedFile = DropboxClient.getFile("/magnum-opus.txt", null,
                        outputStream);
                System.out.println("Metadata: " + downloadedFile.toString());
            } catch (DbxException ex) {
                Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public ArrayList<String> exibirArquivos() {
        ArrayList<String> listaArquivos = new ArrayList<>();
        try {
            DbxEntry.WithChildren listing = DropboxClient.getMetadataWithChildren("/");
            System.out.println("Files in the root path:");
            for (DbxEntry child : listing.children) {
                listaArquivos.add(child.name);
                System.out.println("	" + child.name + ": " + child.toString());
            }
        } catch (DbxException ex) {
            Logger.getLogger(CloudAdapterDropbox.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaArquivos;
    }
}
