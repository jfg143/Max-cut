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
    Random rand;
    double[] coste;
    Algoritmo(double[][]C,int vertices,int Procesadores){
        Select=new boolean[3*Procesadores][vertices];
        coste=new double [3*Procesadores];
        contador=new int[3*Procesadores];
        this.C=C;
        for(int i=0;i<3*Procesadores;i++){
            for(int j=0;j<vertices;j++){
                Select[i][j]=false;
            }
            contador[i]=0;
        }
        
        for(int i=0;i<Procesadores;i++){
            t=new Thread(this,Integer.toString(i));
        }
    }

    private synchronized void Cutting(int hilo,int cont,int vertex){
        if(!Select[hilo+4*cont][vertex]){
            contador[hilo+4*cont]++;
            Select[hilo+4*cont][vertex]=true;
        }
    }
    
    private synchronized void put_Cost(int hilo,int cont,double Cost){
        coste[hilo+4*cont]=Cost;
    }
    
    
    @Override
    public void run() {
        int iter=1+rand.nextInt(vertices);
        int cont,i,j;
        double suma;
        int Nombre=Integer.parseInt(t.getName());
        for(cont=0;cont<3;cont++){
            for(i=0;i<iter;i++){
                Cutting(Nombre,cont,rand.nextInt(vertices));
            }
        
            suma=0;
            for(i=0;i<vertices;i++){
                if(Select[Nombre+4*cont][i]){
                    for(j=0;j<vertices;j++){
                        if(!Select[Nombre+4*cont][i]){
                            suma=suma+C[i][j];
                        }
                    }
                }
            }
            
            put_Cost(Nombre,cont,suma);
        }
    }
}
