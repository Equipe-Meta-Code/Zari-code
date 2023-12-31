package view;

import model.Historico;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import controller.HistoricoController;

public class TabelaHistorico extends javax.swing.JFrame {

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    private RoundButton jBVoltar;
    
    private DefaultTableModel defalultTableModel;
    private Object[] colunas;
    private Object[] itens;
    
    private HistoricoController historicoController;
    
	private TabelaHistorico tabelaHistorico;
	
    String emailHistorico;
    private UploadArquivo uploadArquivo;
	public void setUploadArquivo(UploadArquivo uploadArquivo) {
		this.uploadArquivo = uploadArquivo;
	}
	public void setTabelaHistorico(TabelaHistorico tabelaHistorico) {
		this.tabelaHistorico = tabelaHistorico;
	}
    
    public void setHistoricoController(HistoricoController historicoController) {
    	this.historicoController = historicoController;
    }
    
    public List<Historico> dados = new ArrayList<>();
    public TabelaHistorico() {
        initComponents();
        init(); // !importante¡
    }

    private void initComponents() {
	 ImageIcon icon = new ImageIcon(getClass().getResource("zaribot.png"));
         setIconImage(icon.getImage());
         Image resizedIcon = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
         ImageIcon finalIcon = new ImageIcon(resizedIcon);
         setIconImage(finalIcon.getImage());
	    
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jBVoltar = new RoundButton("Voltar");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jScrollPane1.setViewportView(tabela);
        
        jBVoltar.setText("Voltar");
        jBVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadArquivo.setVisible(true);
         
				tabelaHistorico.dispose();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jBVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                        .addContainerGap())))
        		);
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
    
        );

        pack();
 
	    	}
	    
		public void gerarTabela(){
		     colunas = new Object[]{"Data e hora", "Email", "Documento"};
		     itens = new Object[colunas.length];

		     // modelo
		     defalultTableModel = new DefaultTableModel(null, colunas);

			for (Historico dado : dados) {
				
		     //data e hora na primeira coluna
	             itens[0] = dado.getDataEHora();
	            
	             // email na segunda coluna
	             itens[1] = dado.getEmailHistorico();

	             // documento na terceira coluna
	             itens[2] = dado.getDocumento();

	             // adiciona os itens no modelo
	             defalultTableModel.addRow(itens);
	        }

	        // adicionar modelo a la tabla
	        tabela.setModel(defalultTableModel);
	        
		}

        private void init() {
            setLocationRelativeTo(null);
            setTitle("Tabela Historico - JTable");
        }

        public static void main(String[] args) {
 
    } 

}
