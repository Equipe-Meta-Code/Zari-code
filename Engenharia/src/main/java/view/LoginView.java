package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JCheckBox;

import controller.LoginController;
import dao.UsuarioDao;
import model.Usuario;
import javax.swing.JPanel;


public class LoginView extends JFrame implements ActionListener{
    
    JLabel title;
    JLabel jLabelEmail;
    JLabel jLabelSenha;
    JTextField loginEmail;
    JPasswordField loginSenha;
    
    RoundButton cadastrar;
    RoundButton entrar;
    JCheckBox buttonShow;
    
	private LoginController controller;
    
  
    
    public LoginView() {
    	
    	controller = new LoginController(this);
        
		JFrame JFrame = new JFrame("Login");
		
	
        setSize( 550,450);
        setDefaultCloseOperation(LoginView.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

	ImageIcon icon = new ImageIcon(getClass().getResource("zaribot.png"));
        setIconImage(icon.getImage());
        Image resizedIcon = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon finalIcon = new ImageIcon(resizedIcon);
        setIconImage(finalIcon.getImage());
	    
        title = new JLabel("Login");
        title.setFont(new Font("Yu Gothic", Font.BOLD, 30));
        title.setBounds(226, 11, 82, 100);
                
        getContentPane().add(title);
        
        jLabelEmail = new JLabel("Email:");
        jLabelEmail.setBounds(106, 81, 46, 30);
        jLabelEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
  
                 
        getContentPane().add(jLabelEmail);
        
        loginEmail = new JTextField();
        loginEmail.setBounds(106, 122, 300, 40);
        loginEmail.setFont(new Font("Arial", Font.ITALIC, 15));
        
        getContentPane().add(loginEmail);
        
        
        jLabelSenha = new JLabel("Senha:");
        jLabelSenha.setBounds(106, 168, 56, 23);
        jLabelSenha.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
  
                 
        getContentPane().add(jLabelSenha);
        
        loginSenha = new JPasswordField();
        loginSenha.setBounds(106, 202, 300, 40);
        loginSenha.setFont(new Font("Arial", Font.ITALIC, 15));
                
        getContentPane().add(loginSenha);
        
        //botao para mostrar senha 
        buttonShow = new JCheckBox("Mostrar Senha");
        buttonShow.setBounds(321, 249, 125, 40);
        buttonShow.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
        
        getContentPane().add(buttonShow);
        
        buttonShow.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(buttonShow.isSelected())
        			loginSenha.setEchoChar((char)0);
        		else
        			loginSenha.setEchoChar('*');
        	} 
        });
        
        entrar = new RoundButton("Entrar");
        entrar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
        entrar.setBounds(200, 280, 100, 30);
        getContentPane().add(entrar);
        
        entrar.addActionListener(this);
     
        
        cadastrar = new RoundButton("Cadastrar");
        cadastrar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
        cadastrar.setBounds(200, 321, 100, 30);
        getContentPane().add(cadastrar);
        
        cadastrar.addActionListener(this);
        
        
        
        setVisible(true);
		
}
   
	public JTextField getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(JTextField loginEmail) {
		this.loginEmail = loginEmail;
	}

	public JTextField getLoginSenha() {
		return loginSenha;
	}

	public void setLoginSenha(JPasswordField loginSenha) {
		this.loginSenha = loginSenha;
	}

	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == entrar){
			try {
				if (controller.autenticar()) {
					this.dispose();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if (event.getSource() == cadastrar){
			this.dispose();
			new CadastrarView();
			
		}
		 
		
	}
}
