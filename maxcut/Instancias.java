/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package maxcut;
import java.lang.reflect.Array;
import java.util.Random;
public class Instancias <T extends Number>{
    int vertices;
    double[][] C;
    
    Instancias(int vertices){
        this.vertices=vertices;
        Random rand = new Random();
        C=new double[vertices][vertices];
        for(int i=0;i<vertices;i++){
            for(int j=i;j<vertices;j++){
                if(i==j){
                    C[i][j]=0.0;
                }
                else{
                    C[i][j]=rand.nextDouble()*10;
                    C[j][i]=C[i][j];
                }
            }
        }
    }
    
    
    Instancias(double[]x,double[]y){
        this.vertices=x.length;
        for(int i=0;i<vertices;i++){
            for(int j=i;j<vertices;j++){
                if(i==j){
                    C[i][j]=0.0;
                }
                else{
                    C[i][j]=Math.sqrt(Math.pow((x[i]-x[j]),2)+Math.pow((y[i]-y[j]),2));
                    C[j][i]=C[i][j];
                }
            }
        }
    }
    
    
    void MostrarMatrix(double [][]C){
        for(int i=0;i<C.length;i++){
            for(int j=0;j<C[i].length;j++){
                System.out.print(C[i][j]+" ");
            }
            System.out.println();
        }
    }
        
}
