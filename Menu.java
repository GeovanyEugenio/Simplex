package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Celula;
import model.Simplex;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField tb_funcaoZ;
	private JTextField tb_restricoes;
	private JTextField tb_mostraZ;
	private JTextField tb_mostraVariavel;
	String tipo ="";
	ArrayList<String> listaRestricoes = new ArrayList<String>();
	String funcaoZ ="";
	String restricoes="";
	int cont = 1;
	String [] vetorMaxZ;
	private JTable tbl_matriz;
	 boolean controleEtapa = false;
	 private JTextField tb_resultado;
	 boolean otimo = false;
	

	/**
	 * Launch the application.
	 */
	 
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1018, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 1002, 165);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSimplex = new JLabel("Simplex");
		lblSimplex.setFont(new Font("Tahoma", Font.PLAIN, 74));
		lblSimplex.setBounds(384, 30, 269, 107);
		panel.add(lblSimplex);
		
		JComboBox cb_tipo = new JComboBox();
		cb_tipo.setModel(new DefaultComboBoxModel(new String[] {"Maximiza\u00E7\u00E3o", "Minimiza\u00E7\u00E3o"}));
		cb_tipo.setBounds(145, 282, 116, 20);
		contentPane.add(cb_tipo);
		
		JLabel lblNewLabel = new JLabel("Tipo de Fun\u00E7\u00E3o");
		lblNewLabel.setBounds(32, 285, 121, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fun\u00E7\u00E3o Objetiva");
		lblNewLabel_1.setBounds(32, 330, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		tb_funcaoZ = new JTextField();
		tb_funcaoZ.setBounds(144, 327, 167, 20);
		contentPane.add(tb_funcaoZ);
		tb_funcaoZ.setColumns(10);
		
		JButton bt_ok = new JButton("Ok");
		bt_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(cb_tipo.getSelectedItem().equals("Minimização"))
						tipo ="-1";
				else
					tipo = "1";
				
				
			funcaoZ = tb_funcaoZ.getText().replace(',', '.');
							
			     cont = 1;

		        for (int i = 0; i < funcaoZ.length(); i++) // verifica quantos elementos tem na funcaoZ
		        {
		            char c = funcaoZ.charAt(i);  
		            if (c == '+') 
		            {
		                cont++;
		            }
		        }	
			
		        
		        
		        String z ="";            // trata a função objetiva
		        String parcial="";
		        String s = "+";
		        for (int i = 0; i < funcaoZ.length(); i++)
		        {
		            char x = funcaoZ.charAt(i);
		            
		            if(x!= '+')
		                parcial+= x;
		            if( i == funcaoZ.length()-1)
		                s = "";
		            if(x == '+' || i == funcaoZ.length()-1)
		            {
		                z+= concertaString(parcial)+ s;
		                parcial ="";
		            }
		            
		        }
		        
		        
		        

		        int index =1;    
		        boolean controle =false;
		        vetorMaxZ = new String [cont+1]; 
		        
		        for (int i = 0; i <z.length(); i++)
		        {
		            char x = z.charAt(i);
		            if(controle == false && x!= 'x')
		            {
		                if(vetorMaxZ[index]== null)
		                    vetorMaxZ[index] = "";
		            
		                 vetorMaxZ[index]+= x;
		            }
		            if(x =='x')
		                controle = true;
		            
		            if(x =='+'){
		                controle = false;
		            index++;}
		            
		            
		        }
		        
		        JOptionPane.showMessageDialog(null,"Função objetiva setada com sucesso !");
		        
		        
		        
		        
		        
		        
		        
		        
		        
			
				
				
				
				
				
				
			}
		});
		bt_ok.setBounds(321, 326, 89, 23);
		contentPane.add(bt_ok);
		
		JLabel lblNewLabel_2 = new JLabel("Restri\u00E7\u00F5es");
		lblNewLabel_2.setBounds(32, 378, 89, 14);
		contentPane.add(lblNewLabel_2);
		
		tb_restricoes = new JTextField();
		tb_restricoes.setBounds(144, 375, 167, 20);
		contentPane.add(tb_restricoes);
		tb_restricoes.setColumns(10);
		
		JButton bt_adicionar = new JButton("Adicionar");
		bt_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tb_restricoes.getText().replace(',', '.') == "")
					JOptionPane.showMessageDialog(null,"Campo vazio ou restrição invalida !");
				else					
				{
					restricoes = tb_restricoes.getText().replace(',','.');
					
					listaRestricoes.add(restricoes); // salva um lista de restricoes em string 
					
					tb_restricoes.setText("");
									
				}
				
				
			
				
			}
		});
		bt_adicionar.setBounds(321, 374, 89, 23);
		contentPane.add(bt_adicionar);
		
		JButton bt_Novo = new JButton("Novo");
		bt_Novo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				tb_mostraZ.setText("");
				tb_mostraVariavel.setText("");
				tb_funcaoZ.setText("");
			    tipo ="";
				listaRestricoes.clear();
				String funcaoZ ="";
				String restricoes="";
			    cont = 1;	
			    tb_resultado.setText("");
				
				for(int i = 0; i<vetorMaxZ.length; i ++ )
					vetorMaxZ[i] = "";
			
				controleEtapa = false;
				otimo = false;
			}
		});
		bt_Novo.setBounds(321, 281, 89, 23);
		contentPane.add(bt_Novo);
		
		JLabel lblNewLabel_3 = new JLabel("Z =");
		lblNewLabel_3.setBounds(572, 285, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblVariveis = new JLabel("Vari\u00E1veis =");
		lblVariveis.setBounds(572, 330, 66, 14);
		contentPane.add(lblVariveis);
		
		tb_mostraZ = new JTextField();
		tb_mostraZ.setBounds(678, 279, 127, 20);
		contentPane.add(tb_mostraZ);
		tb_mostraZ.setColumns(10);
		
		tb_mostraVariavel = new JTextField();
		tb_mostraVariavel.setBounds(678, 327, 314, 20);
		contentPane.add(tb_mostraVariavel);
		tb_mostraVariavel.setColumns(10);
		
		JButton bt_otimizar = new JButton("Otimizar");
		bt_otimizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tb_funcaoZ.getText() == "" )
					JOptionPane.showMessageDialog(null,"Verifique se tudo está preenchido !");
				else
				{
					
					vetorMaxZ[0] = String.valueOf(0);
					
					 Celula[][] matriz = new Celula[listaRestricoes.size() + 2][vetorMaxZ.length + 1];
				        //cria a matriz a ser preeenchida usando o vetor contendo o numero de elementos da funcaoZ como tamanho +1

				        for (int q = 0; q < matriz.length; q++) {
				            int g = 1;
				            for (int w = 2; w < matriz[q].length; w++) {
				                Celula nova = new Celula();
				                nova.setCima("X" + g);        // cria o identificador de cada coluna exemplo x1, x2, x3 
				                matriz[0][w] = nova;
				                g++;

				                if (g == vetorMaxZ.length) {
				                    break;
				                }
				            }
				            break;

				        }
				        
				        
				        for (int i = 1; i < matriz.length; i++) // linha
				        {
				            for (int j = 1; j < matriz[i].length; j++) // coluna
				            {
				                Celula nova = new Celula();
				                nova.setCima(String.valueOf( Double.parseDouble(vetorMaxZ[j - 1])*Double.parseDouble((tipo))));
				                matriz[1][j] = nova;
				            }
				            break;
				        }
				        Celula nova1 = new Celula();
				        nova1.setCima("F(X)");
				        matriz[1][0] = nova1;  // colocando o F(X) 
				        Celula nova2 = new Celula();
				        nova2.setCima("ML");  // membro livre 
				        matriz[0][1] = nova2; // coloca o nome da coluna como membro livre
				        Celula nova3 = new Celula();
				        nova3.setCima("VB/VNB");
				        matriz[0][0] = nova3;

				      			        
				        // matriz preenchida com o os elementos do vetor
				        // retornando uma string com a restricao no formato correto exemplo x3;-24;-16;-10 so colocar na matriz
				        // cada posicao do vetor separaString tera um elemento da restricao exemplo [0] x3 ; [1] -24 ; [2] -16
				        for (int u = 2; u < matriz.length; u++)// linha
				        {
				            String separaString[] = Final(listaRestricoes.get(u - 2), ("X" + ((u - 2) + vetorMaxZ.length)),cont).split(";");
				            for (int j = 0; j < matriz[u - 2].length; j++) // coluna
				            {
				                Celula nova = new Celula();
				                nova.setCima(separaString[j]);
				                matriz[u][j] = nova;
				            }

				        }
				        
				        Simplex simplex = new Simplex ();
				        simplex.passo1(matriz);
				       
				        if(matriz[1][0].getBaixo() != "solucao permissivel não existe")
				        {
				        
				        		for (int i = 2; i < matriz.length; i++)
				        			{
				        				if(Double.parseDouble(matriz[i][1].getCima()) <0)
				        				{
				        					simplex.passo1(matriz);
				        					i = 1;
				              
				        				}       
				        				if(matriz[1][0].getBaixo() == "solucao permissivel não existe" && matriz[1][0].getBaixo() != null){
				        					controleEtapa = true;	  
				        					break;}
				                   }
				        }
				        else
				        	controleEtapa = true;
				        
				        
				    	
				        
				        if(controleEtapa == false);
				        {
				        
				        	simplex.passo2(matriz); 
				        
				        	    
				        
				            	for (int j = 2; j <matriz[1].length; j++)
				            	{
				            		if(matriz[1][0].getBaixo() == "Solução ilimitada")
				            			break;
				                
				            		if (Double.parseDouble(matriz[1][j].getCima()) > 0)
				            		{
				            			simplex.passo2(matriz);
				            			j = 1;
	            			            otimo = false;
	            			            matriz[1][0].setBaixo("");
	            			            
				            		}
				            		else
				            			matriz[1][0].setBaixo("Solução Ótima");
				            		
				            	
				            		
				                }
				         
				        }
				        
				            if(Double.parseDouble(matriz[1][1].getCima()) < 0)
				            	tb_mostraZ.setText(String.valueOf(Double.parseDouble(matriz[1][1].getCima()) *-1));
				            else
				            	tb_mostraZ.setText(matriz[1][1].getCima());
				            
				         
				               
				               tb_resultado.setText(matriz[1][0].getBaixo());
					           
				               matriz[1][0].setBaixo("");
				            				            
					String ML ="";
					
					
					DecimalFormat df = new DecimalFormat("###,##0.00");
					
					
					matriz[1][0].setBaixo("");
					for(int i = 2 ; i < matriz.length; i++)
					{						  
						    String  num2 = df.format(Double.parseDouble(matriz[i][1].getCima()));
						    
						    ML += matriz[i][0].getCima()+" = "+ num2  +"    " ;				     
		
					}
					tb_mostraVariavel.setText(ML);
				    
					
					for(int k = 1 ; k <matriz.length; k ++)
						for(int p = 1; p< matriz[k].length; p ++)						
							 matriz[k][p].setCima(df.format(Double.parseDouble(matriz[k][p].getCima())));
							  
							
							
					DefaultTableModel model = (DefaultTableModel) tbl_matriz.getModel();
					model.setRowCount(listaRestricoes.size()+2);
					model.setColumnCount(vetorMaxZ.length + 1);
					 
					for(int q = 0; q < matriz.length; q++)
					{ 
						for(int  u = 0; u< matriz[q].length; u ++)
						{							
							tbl_matriz.setValueAt(matriz[q][u].getCima()+"/"+ matriz[q][u].getBaixo(),q ,u );
						}
						
					}
					
					
					
					
					
				}
				
							
				
			}
		});
		bt_otimizar.setBounds(321, 429, 89, 23);
		contentPane.add(bt_otimizar);
		
		tbl_matriz = new JTable();
		tbl_matriz.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		tbl_matriz.setBounds(572, 378, 420, 232);
		contentPane.add(tbl_matriz);
		
		JLabel lblNewLabel_4 = new JLabel("Resultado = ");
		lblNewLabel_4.setBounds(572, 305, 66, 14);
		contentPane.add(lblNewLabel_4);
		
		tb_resultado = new JTextField();
		tb_resultado.setBounds(678, 302, 314, 20);
		contentPane.add(tb_resultado);
		tb_resultado.setColumns(10);
	}
	
	private String concertaString(String parcial)
    {
        String retorno="";
           
            char elemento = parcial.charAt(0);
            
            if(elemento == 'x')
                retorno = "1"+parcial;
            else
                retorno = parcial;
        
        return retorno;
    
    }
	private String Final(String restricao, String contadorDeRestricao, int numElementosFuncaoZ)
    {
         String restricaoFinal = ""; 
         int tipo = 1;
         int cont = 1;
         String igualdade="";
         double igual = 999999999;
       
         

       for (int i = 0; i < restricao.length(); i++) // le caracter por caracter da string funcaoZ
       {
           char c = restricao.charAt(i);  // adiciona caracter i em c

           if (c == '+') 
           {
               cont++;
           }
       }
    
       String z ="";
       String parcial="";
       String s = "+";
       
       for (int i = 0; i < restricao.length(); i++)
       {
           char x = restricao.charAt(i);
           
           if(x!= '+')
               parcial+= x;
           if( i == restricao.length()-1)
               s = "";
           if(x == '+' || i == restricao.length()-1)
           {
               z+= concertaString(parcial)+ s;
               parcial ="";
           }
           
       }  
       
       
       
       
       if(cont > 1)
       {
           
           for (int i = 0; i <restricao.length(); i++)
           {
               char x = restricao.charAt(i);
               
               if(x == '>')
                   tipo = -1;
               if(x == '=')
                   igual = i;
               if(i > igual)
                   igualdade+=x;
               
           }
           
           restricaoFinal = contadorDeRestricao +";"+(Double.parseDouble(igualdade)*tipo);
           
           boolean controle = false;
           String valor="";
           
           for (int i = 0; i <z.length(); i++)
           {
               char x = z.charAt(i);
               
               if(x == '<' || x == '>')
                   break;
               if(controle == false && x!='x')
                   valor+=x;
               if(x == 'x')
               {
                   controle = true;
                   restricaoFinal+=";"+(Double.parseDouble(valor)*tipo);
                   valor="";
               }
               if(x == '+')
                   controle = false;
                   
               
           }
       
       }
       else
       {
           
           String variavelLivre = "1";
           
           for (int i = 0; i < z.length(); i++)
           {
               char x = z.charAt(i);
               
               if(x == '>')
                   tipo = -1;
               if(x == '=')
                   igual = i;
                   
               if(i>igual)    
                   igualdade+=x;
                            
               
           }
           
           double posicao = 999999999;
           String elemento="";
           
           for (int i = 0; i < z.length(); i++)
           {
               char x = z.charAt(i);
               
               if(x == 'x')
                   posicao = i;
               
                if(x == '<' || x == '>')
                   break;
               
               if(i>posicao)
                   elemento+=x;
           }                
           
           for (int i = 0; i < z.length(); i++)
           {
               char x = z.charAt(i);
                       if(x != 'x')
                       {
                           variavelLivre = "";
                           variavelLivre+=x;
                       }
                       if(x == 'x')
                           break;
               
           }
           
             restricaoFinal = contadorDeRestricao +";"+(Double.parseDouble(igualdade)*tipo);
             // exmplo : x3;100
       
             for (int i = 1; i <= numElementosFuncaoZ; i++) 
             {
                 if(i == Integer.parseInt(elemento))
                     restricaoFinal+=";"+(Double.parseDouble(variavelLivre)*tipo);
                 else
                     restricaoFinal+=";"+(0);
             }
       
       }
       return restricaoFinal;
    
    
    }    
}
