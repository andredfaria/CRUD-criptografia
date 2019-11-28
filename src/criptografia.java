
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

a criptografa  é feita de modo que cada caracter somando com a chave:
exemplo->   Palavra AED
            Chave   123

            soma o caracter A com a chave 123 => 65 + 123 = 188
            soma o caracter E com a chave 123 => 69 + 123 = 192
            soma o caracter D com a chave 123 => 68 + 123 = 191

*/
/**
 *
 * @author André
 */
public class criptografia extends JFrame implements ActionListener {
    private JButton criptografar, descriptografar;
    private JTextField nomeDoArquivo, chaveDoArquivo;
    private JLabel labelInf;
    private JLabel labelCha;
    BufferedReader in;
    private int c;
    private int i;
    private String Arquivo;
    private JTextField nomeDoArquivoN;
    private JLabel labelInfNovo;
    
    //função que controe todos os elementos visuais do programa como inputs, label e buttons
    public void construtor(){
        criptografar   = new JButton("criptografar");
        criptografar.setBounds(200,10,150,25);
        getContentPane().add(criptografar);
        criptografar.addActionListener(this);
        
        
        descriptografar   = new JButton("descriptografar"); 
        getContentPane().add(descriptografar); 
        descriptografar.setBounds(200,40,150,25);
        descriptografar.addActionListener(this);
        
        nomeDoArquivo = new JTextField();
        getContentPane().add(nomeDoArquivo);
        nomeDoArquivo.setBounds(20,40,125,25);
        
        nomeDoArquivoN = new JTextField();
        getContentPane().add(nomeDoArquivoN);
        nomeDoArquivoN.setBounds(180,100,125,25);
        
        chaveDoArquivo = new JTextField();
        getContentPane().add(chaveDoArquivo);
        chaveDoArquivo.setBounds(20,100,125,25);
        
        labelInf = new JLabel("nome do arquivo");
        getContentPane().add(labelInf);
        labelInf.setBounds(20,20,300,15);
        
        labelInfNovo = new JLabel("nome do NOVO arquivo");
        getContentPane().add(labelInfNovo);
        labelInfNovo.setBounds(180,80,300,15);
        
        labelCha = new JLabel("chave");
        getContentPane().add(labelCha);
        labelCha.setBounds(20,80,300,15);
        
    }  

public criptografia(){
    getContentPane().setLayout(null);
    setBounds(150,100,500,200);
    setTitle("Criptografia");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    construtor();
    
}   

public static void main(String arg[]){
    criptografia tela = new criptografia();
    tela.setVisible(true);
    
}  

@Override
public void actionPerformed(ActionEvent e) {
    //verifica qual botao foi clicado e chama respectiva função
    if(e.getSource() == criptografar){
        String Chave = new String();
        String Arquivo = new String();
        
        Chave   = chaveDoArquivo.getText();
        Arquivo = nomeDoArquivo.getText();
       
        

            try {
                criptografar(Chave, Arquivo);
            }catch (IOException ex) {
                Logger.getLogger(criptografia.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        System.out.println("criptografado");    
        
    }
    
    if(e.getSource() == descriptografar){
        String Chave = new String();
        String Arquivo = new String();
        
        Chave   = chaveDoArquivo.getText();
        Arquivo = nomeDoArquivo.getText();
        
        try {
            descriptografar(Chave, Arquivo);
        } catch (IOException ex) {
            Logger.getLogger(criptografia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
/*
Funçao que criptografa o texto passando a a chave e o texto que esta no arquivo
*/
private void criptografar(String Chave, String Arquivo) throws FileNotFoundException, IOException {
    int i, n,chave;
    
    chave = Integer.parseInt(Chave);

        BufferedReader fw = new BufferedReader(new FileReader(Arquivo));
        
        String str = "";
        String texto = "";
        while((str = fw.readLine()) != null){
         texto +=str;
        }
     
     n = texto.length();
     String aux = "";

     for (i=0; i<n; i++) {
        aux = aux + (char)(texto.charAt(i) + chave);
     }
    
     System.out.println(aux);
        //escrevendo
     
     String novo = nomeDoArquivoN.getText();
     
     FileWriter arquivo = new FileWriter(novo);
     arquivo.write(aux);
     arquivo.close();
}


private void descriptografar(String Chave, String Arquivo) throws FileNotFoundException, IOException {
    int i, n,chave;
    chave = Integer.parseInt(Chave);
    
    BufferedReader fw = new BufferedReader(new FileReader(Arquivo));
    String str = "";
    String texto = "";
    while((str = fw.readLine()) != null){
     texto +=str;
 }
 
 n = texto.length();
 String aux = "";

 for (i=0; i<n; i++) {
    aux = aux + (char)(texto.charAt(i) - chave);
}
 
 String novo = nomeDoArquivoN.getText();
 
FileWriter arquivo = new FileWriter(novo);
arquivo.write(aux);
arquivo.close();

}

    private boolean empty(String Chave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}