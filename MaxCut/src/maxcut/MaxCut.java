/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxcut;

public class MaxCut {

    public static void main(String[] args) throws InterruptedException {
        
        int Nsol=20,i,procesadores=8;
        double alpha=0.2;
        /*
        double[]x={0.0,1.0,2.0,3.0,4.0,5.0};
        double[]y={0.0,0.0,0.0,0.0,0.0,0.0};
        int vertices=x.length;
        Instancias<Double> a=new Instancias<Double>(x,y);
        */
        int vertices=200;
        Instancias<Double> a=new Instancias<Double>(vertices);
        //a.MostrarMatrix(a.C);
        LocalSearch b=new LocalSearch(a.C,vertices,procesadores,Nsol,alpha);

        LocalSearch[] aux= new LocalSearch[procesadores];
        Class_BL[] Final=new Class_BL[procesadores];
        for(i=0;i<procesadores;i++) {
        	aux[i]= new LocalSearch(alpha,b.alg[i].coste,b.alg[i].Select,a.C,Nsol,vertices);
        	aux[i].t2.run();
        }
        
        for(i=0;i<procesadores;i++){
            aux[i].t2.join();
        }
        
        for(i=0;i<procesadores;i++) {
        	Final[i]=aux[i].Final;
        }
        Class_BL SOL=Max(Final);
        System.out.println("El ganador ha sido "+Max(Final).id+" ;con un coste de "+Max(Final).get+" y la solución es: ");
        
        for (i=0;i<vertices;i++) {
        	System.out.print(SOL.Sol[i]+" ");
        }
    }
    
    public static Class_BL Max(Class_BL[] Value){
        double Max=Value[0].get;
        int index=Value[0].id;
        int procesador=0;
        int i;
        for(i=1;i<Value.length;i++){
            if(Value[i].get>Max ){
                Max=Value[i].get;
                index=Value[i].id;
                procesador=i;
            }
        }
        
        return new Class_BL(index,Max,Value[procesador].Sol);
    }
    
}
