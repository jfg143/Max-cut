/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxcut;

import java.util.Random;

public class Algoritmo implements Runnable{
    int vertices;
    double[][] C;
    Thread t;
    boolean Select[][];
    int[] contador;
    int NSoluciones;
    Random rand=new Random();
    double[] coste;
    Algoritmo(double[][]C,int vertices,int k,int NSoluciones){
        this.NSoluciones=NSoluciones;
        this.vertices=vertices;
        Select=new boolean[NSoluciones][vertices];
        coste=new double [NSoluciones];
        contador=new int[NSoluciones];
        this.C=C;
        for(int i=0;i<NSoluciones;i++){
            for(int j=0;j<vertices;j++){
                Select[i][j]=false;
            }
            contador[i]=0;
        }
        t=new Thread(this,Integer.toString(k));
    }

    private synchronized void Cutting(int cont,int vertex){
        if(!Select[cont][vertex]){
            contador[cont]++;
            Select[cont][vertex]=true;
        }
    }
    
    private synchronized void put_Cost(int cont,double Cost){
        coste[cont]=Cost;
    }
    
    
    @Override
    public void run() {
        int iter=1+rand.nextInt(vertices);
        int cont,i,j;
        double suma;
        for(cont=0;cont<NSoluciones;cont++){
            for(i=0;i<iter;i++){
                Cutting(cont,rand.nextInt(vertices));
            }
        
            suma=0;
            for(i=0;i<vertices;i++){
                if(Select[cont][i]){
                    for(j=0;j<vertices;j++){
                        if(!Select[cont][j]){
                            suma=suma+C[i][j];
                        }
                    }
                }
            }
            put_Cost(cont,suma);
        }    
        
    }
}
