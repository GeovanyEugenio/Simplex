package model;

import javax.swing.JOptionPane;

public class Simplex 
{
	     public void troca(Celula matriz[][], int linhaPermitida, int colunaPermitida) {
	        matriz[linhaPermitida][colunaPermitida].setBaixo(String.valueOf((1 / (Double.parseDouble(matriz[linhaPermitida][colunaPermitida].getCima()))))); // setando a celula parte de baixo com o inverso 

	        for (int k = linhaPermitida; k < matriz.length; k++) {
	            for (int l = 1; l < matriz[k].length; l++) {

	                if (l != colunaPermitida) {
	                    matriz[k][l].setBaixo(String.valueOf((Double.parseDouble(matriz[k][l].getCima()) * Double.parseDouble(matriz[linhaPermitida][colunaPermitida].getBaixo()))));
	                }
	            }

	            break;  // parte de baixo da linha permissiva preenchia

	        }

	        for (int j = 1; j < matriz.length; j++) {
	            if (j != linhaPermitida) {
	                matriz[j][colunaPermitida].setBaixo(String.valueOf((Double.parseDouble(matriz[j][colunaPermitida].getCima()) * -(Double.parseDouble(matriz[linhaPermitida][colunaPermitida].getBaixo())))));
	                // deixando o valor 0 negativo tratar
	            }

	        } // parte de baixo da coluna permissiva preenchida

	        for (int q = 1; q < matriz.length; q++) {
	            for (int j = 1; j < matriz[q].length; j++) {
	                if (j == colunaPermitida || q == linhaPermitida) {

	                } else {
	                    matriz[q][j].setBaixo(String.valueOf(Double.parseDouble(matriz[linhaPermitida][j].getCima()) * Double.parseDouble(matriz[q][colunaPermitida].getBaixo())));

	                }

	            }

	        }

	        String aux = matriz[0][colunaPermitida].getCima();
	        matriz[0][colunaPermitida].setCima(matriz[linhaPermitida][0].getCima());
	        matriz[linhaPermitida][0].setCima(aux);

	      
	        for (int j = 1; j < matriz.length; j++)// modifica a matriz troca passo 9 
	        {
	            for (int k = 1; k < matriz[j].length; k++) {
	                if (k == colunaPermitida || j == linhaPermitida) {
	                    matriz[j][k].setCima(matriz[j][k].getBaixo());
	                    matriz[j][k].setBaixo(String.valueOf(0));
	                } else {
	                    matriz[j][k].setCima(String.valueOf(Double.parseDouble(matriz[j][k].getCima()) + (Double.parseDouble(matriz[j][k].getBaixo()))));
	                  //  matriz[j][k].setBaixo(String.valueOf(0));
	                }

	            }

	        }

	    }
	     public void imprimir(Celula matriz[][]) {
	        
	        for (int i = 0; i < matriz.length; i++) {
	            for (int j = 0; j < matriz[i].length; j++) {
	                System.out.print(matriz[i][j].getCima() + "/" + matriz[i][j].getBaixo() + "         ");
	            }
	            System.out.println();
	            System.out.println();
	        }

	    }
	     public Celula[][] passo1(Celula matriz[][]) {
	    	 
	        int linhaComMembroLivreNegativo = 0;
	        int linhaPermissiva = 0;
	        int colunaPermissiva = 0;
	        double valor = 0;
	        boolean todosPositivos = true;

	        for (int i = 2; i < matriz.length; i++) // roda as linha da matriz  
	        {

	            valor = Double.parseDouble(matriz[i][1].getCima()); // pega os elementos da coluna 1

	            if (valor < 0) // se o elemento for menor que zero 
	            {
	                linhaComMembroLivreNegativo = i; // armazen a linha que contem o mebro negativo

	                for (int j = i; j < matriz.length; j++)// roda as linhas da matriz
	                {
	                    for (int k = 2; k < matriz[j].length; k++) // roda a partir da coluna 2 pois nela que contem os elementos que procuramos 
	                    {
	                        valor = Double.parseDouble(matriz[i][k].getCima()); // pega o primeiro valor negativo

	                        if (valor < 0) // mesmo podendo ter mais valores negativos vamos pegar o primeiro
	                        {
	                            colunaPermissiva = k;
	                            todosPositivos = false; // se nenhum e negativo intao 
	                            break;
	                        }

	                    }
	                    break;

	                }
	                if (todosPositivos == true) {
	                    matriz[1][0].setBaixo("solucao permissivel não existe");
	                    break;
	                } else {
	                    double menor = 999999999;

	                    for (int j = 2; j < matriz.length; j++) {
	                        double x = Double.parseDouble(matriz[j][1].getCima());
	                        double y = Double.parseDouble(matriz[j][colunaPermissiva].getCima());

	                        if (y != 0 && (x / y) > 0) {
	                            double quociente = x / y;
	                            if (menor > quociente) {
	                                menor = quociente;
	                                linhaPermissiva = j; // pega a linha permissiva
	                            }
	                        }
	                    }

	                    troca(matriz, linhaPermissiva, colunaPermissiva);

	                    break;
	                }

	            }
	            

	        }

	        return matriz;
	    }
	     public Celula[][] passo2(Celula[][] matrizNova) 
	    {
	        boolean todosNegativos = true; // todos os elementos sao "positivos" 
	        int colunaPermitida = 0;
	        int linhaPermitida = 0;

	            for (int j = 2; j < matrizNova[1].length; j++)
	            {
	                if (Double.parseDouble(matrizNova[1][j].getCima()) > 0) // se tiver algum elemento 
	                {                                                      //´positivo 
	                    todosNegativos = false;
	                    colunaPermitida = j;
	                    break;
	                    
	                }

	            }


	        if (todosNegativos == true)
	        {
	              matrizNova[1][0].setBaixo("solução Ótima obtida");// solução Ótima obtida
	        } 
	        else
	        {
	            boolean existePositivo = false;

	            for (int i = 2; i < matrizNova.length; i++)
	            {
	                if (Double.parseDouble(matrizNova[i][colunaPermitida].getCima()) > 0) 
	                {
	                    existePositivo = true;
	                    break;
	                }

	            }

	            if (existePositivo == true) // entao passamos para a operação 3  
	            {

	                double menor = 999999999;

	                for (int j = 2; j < matrizNova.length; j++) 
	                {
	                    double x = Double.parseDouble(matrizNova[j][1].getCima());
	                    double y = Double.parseDouble(matrizNova[j][colunaPermitida].getCima());

	                    if (y > 0)
	                    {
	                        double quociente = x / y;
	                        if (menor > quociente) 
	                        {
	                            menor = quociente;
	                            linhaPermitida = j; // pega a linha permissiva
	                        }
	                    }
	                }

	                troca(matrizNova, linhaPermitida, colunaPermitida); //até aqui tudo funcionando
                            
	                   
	            } 
	            else 
	            {
	                matrizNova[1][0].setBaixo("Solução ilimitada"); // Solucao ilimitada

	            }

	        }
	        return matrizNova;

	    }

}
