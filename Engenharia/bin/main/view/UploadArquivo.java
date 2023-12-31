
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.common.io.Files;

public class UploadArquivo extends JFrame implements ActionListener{
	
	JButton selector;
	String arquivo_path;
    
    public UploadArquivo(){
        
       //Titulo
       JLabel title = new JLabel("Upload de Arquivos");
       title.setHorizontalAlignment(JLabel.CENTER);
       title.setVerticalAlignment(JLabel.TOP);
       title.setFont(new Font("Arial", Font.BOLD, 30));
       
       
       //Botão para abrir selecionador de arquivos
       selector = new JButton();
       selector.setBounds((450/2)-90, 100, 180, 32);
       selector.setHorizontalTextPosition(JButton.CENTER);
       selector.setFocusable(false);
       selector.setText("Selecione um Arquivo...");
       selector.addActionListener(this);     //Propriedade que detecta se o botão esta sento clicado
        
       
       this.setSize(450,450);
       this.setBackground(Color.yellow);
       this.setResizable(false);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setLocationRelativeTo(null);
       this.setLayout(null);
       
       //adiciona o botão e o titulo para o frame(tela)
       this.add(selector);
       this.add(title);
       
       this.setVisible(true);
       
        
    }
    
    
    
    @Override
    //Metodo que sera chamado caso o botão seja apertado
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==selector){   //verifica se o botão foi apertado
            
        	FileNameExtensionFilter filtro = new FileNameExtensionFilter("Text Files", "txt");   //Cria filtro para permitir selecionar apenas certos tipos de arquivos
        	
            JFileChooser explorador_arq = new JFileChooser();     //Cria uma janela para selecionar o arquivo
            explorador_arq.setFileFilter(filtro);                 //Aplica o filtro ao selecionador de arquivos
            int resposta = explorador_arq.showOpenDialog(null);   //verifica se foi selecionado um arquivo (leva o valor para a variavel resposta)
            
            if(resposta == JFileChooser.APPROVE_OPTION){
             
                File arquivo = new File(explorador_arq.getSelectedFile().getAbsolutePath());  //pega o caminho do arquivo selecionado
                arquivo_path = arquivo.getAbsolutePath();     //Transforma o caminho em uma String
                
                String texto;
                texto = arquivo.getAbsolutePath();
                char[] xChars = arquivo_path.toCharArray();
                
                String limpo;
        		limpo = "";
        		
        	try {
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                StringBuffer sb = new StringBuffer();
                String str;             
                
                while ((str = reader.readLine()) != null) {
        			sb.append(str).append("\n");
        			char[] chars = str.toCharArray();
        			for(int i = 0; i < chars.length; i++) {
            			if(chars[i] != ',' & chars[i] != '.' & chars[i] != '`' & chars[i] != ';' & chars[i] != ':'  & chars[i] != '[' 
            				& chars[i] != ']' & chars[i] != '(' & chars[i] != ')' & chars[i] != '{' & chars[i] != '}' & chars[i] != '/' 
            				& chars[i] != '!') {
            				limpo = limpo + chars[i];
            				
            			}
            		
        			
        			}	
        		limpo = limpo + "\n";
                } 
                reader.close();

        		} catch (IOException a) {
        			a.printStackTrace();
        		}
        		
        		try (PrintWriter out = new PrintWriter("src/main/java/_Engenharia/template.txt")) { //Cria um arquivo .txt no pacote _Engenharia
        		    out.println(limpo);
        		} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
        		
        
                
            	new PerguntaResposta();         //Cria a tela de pergunta
              
                this.dispose();
                
            }
            
        }
        
    }
    
    public String getArquivo_path() {
    	return arquivo_path;
    }
    
}
