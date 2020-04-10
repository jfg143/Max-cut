/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxcut;

public class MaxCut {

    public static void main(String[] args) throws InterruptedException {
        
        int Nsol=4;
        double[]x={0.0,1.0,2.0,3.0,4.0,5.0};
        double[]y={0.0,0.0,0.0,0.0,0.0,0.0};
        int vertices=x.length;
        Instancias<Double> a=new Instancias<Double>(x,y);
        a.MostrarMatrix(a.C);
        LocalSearch b=new LocalSearch(a.C,vertices,4,Nsol,0.2);
        for(int i=0;i<b.Select_Tot[0].length;i++){
            for(int j=0;j<b.Select_Tot[0][i].length;j++){
                System.out.print(b.Select_Tot[0][i][j]+" ");
            }
             System.out.println();
        }
        b.run2(a.C, b.Select_Tot[0][0], vertices);
        /*Instancias<Double> a=new Instancias<Double>(vertices);
        a.MostrarMatrix(a.C);
        LocalSearch b=new LocalSearch(a.C,vertices,4,Nsol,0.2);
        for(int i=0;i<b.Select_Tot[0].length;i++){
            for(int j=0;j<b.Select_Tot[0][i].length;j++){
                System.out.print(b.Select_Tot[0][i][j]+" ");
            }
             System.out.println();
        }
        b.run2(a.C, b.Select_Tot[0][0], vertices);*/
        
    }
    
}
