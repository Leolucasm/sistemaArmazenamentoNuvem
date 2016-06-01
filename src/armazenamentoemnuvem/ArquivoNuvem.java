package armazenamentoemnuvem;

public class ArquivoNuvem {
    private String nomeArquivo;
    private String pathArquivo;

    public ArquivoNuvem(String nomeArquivo, String pathArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.pathArquivo = pathArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getPathArquivo() {
        return pathArquivo;
    }
        
}
