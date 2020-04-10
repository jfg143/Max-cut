
package maxcut;

import java.util.Random;


public class LocalSearch implements Runnable {
    
    int vertices;
    double alpha;
    double[][] C;
    Thread t;
    Random rand=new Random();
    boolean Select_Tot[][][];
    int[] contador;
    double[][] coste_Tot;
    boolean Select[][];
    double[] coste;
    Algoritmo[] alg;
    Thread t2;
    LocalSearch(double[][]C,int vertices,int Procesadores,int NSoluciones,double alpha) throws InterruptedException{
        alg=new Algoritmo[Procesadores];
        this.alpha=alpha;
        Select_Tot=new boolean [Procesadores][NSoluciones][vertices];
        coste_Tot=new double[Procesadores][NSoluciones];
        for(int i=0;i<Procesadores;i++){
            alg[i]=new Algoritmo(C,vertices,i,NSoluciones);
            alg[i].t.run();
        }
        for(int i=0;i<Procesadores;i++){
            alg[i].t.join();
        }
        for(int i=0;i<Procesadores;i++){
            this.coste_Tot[i]=alg[i].coste;
            this.Select_Tot[i]=alg[i].Select;
        }
    }
    
    LocalSearch(double alpha,double[]coste,boolean[][]Select,String Name,double[][] C){
        this.alpha=alpha;
        this.coste=coste;
        this.Select=Select;
        this.C=C;
        t2=new Thread(this,Name);
    }
    
    
    public double Max(Class_BL[] Value){
        double Max=Value[0].get;
        int i;
        for(i=1;i<Value.length;i++){
            if(Value[i].get>Max  && Value[i].get>0){
                Max=Value[i].get;
            }
        }
        return Max;
    }
    
    public double Min(Class_BL[] Value){
        double Min=Value[0].get;
        int i;
        for(i=1;i<Value.length;i++){
            if(Value[i].get<Min && Value[i].get>0){
                Min=Value[i].get;
            }
        }
        return Min;
    }
    
    
    //1º We added all the nodes that we can and then we get off all that we can
    public Class_BL In_Out(Class_BL[][] Imp,int vertices,double alpha){
        int i,j,con=0;
        int n=Imp[0].length;
        Double Value1,Value2;
        boolean No_Meter=false;
        for(i=0;i<n;i++){
            if(Imp[0][i].get<0){con++;}
        }
        if(con==n){No_Meter=true;}
        if(!No_Meter){
            boolean[] Meter=new boolean[n];
            for(i=0;i<n;i++){Meter[i]=false;}
            int contador=0;
            Value1=Max(Imp[0]);
            Value2=Min(Imp[0]);
            double Value=(double)(Value1-alpha*(Value1-Value2));
            for(i=0;i<n;i++){
                if(Imp[0][i].get>=Value){
                    Meter[i]=true;
                    contador++;
                }
            }
            int seleccionar=rand.nextInt(contador);
            Class_BL sel=null;
            System.out.println(seleccionar);
            j=0;
            i=0;
            while(j<n){
                if(Meter[j]){
                    if(i==seleccionar){
                       sel=Imp[0][j]; 
                       sel.Aniadir(true);
                    }
                    i++;
                }
                j++;
            }
            return sel;
        }
        else{
            boolean[] Meter=new boolean[vertices-n];
            for(i=0;i<vertices-n;i++){Meter[i]=false;}
            int contador=0,cont=0;
            Value1=Max(Imp[1]);
            if(Value1<=0){return null;}//If the maximum value is't possitive we improve all the methods.
            Value2=Min(Imp[1]);
            double Value=(double)Value1-alpha*(Value1-Value2);
            for(i=0;i<vertices-n;i++){
                if(Imp[1][i].get>=Value){
                    Meter[i]=true;
                    contador++;
                }
            }
            int seleccionar=rand.nextInt(contador);
            Class_BL sel=null;
            j=0;
            i=0;
            while(j<vertices-n){
                if(Meter[j]){
                    if(i==seleccionar){
                       sel=Imp[1][j]; 
                       sel.Aniadir(false);
                    }
                    i++;
                }
                j++;
            }
            return sel;
        }
    }
    
    public Class_BL[][] Value(double [][]C,boolean[] Select,int vertices){
        int i,j,k2=0,k1=0,suma=0;
        
        
        for(i=0;i<vertices;i++){
            if(Select[i]){suma++;}
        }
        Class_BL[] get_out=new Class_BL[suma];
        Class_BL[] get_in=new Class_BL[vertices-suma];
        
        double cost;
        
        for(i=0;i<vertices;i++){
            cost=0;
            if(Select[i]){
                for(j=0;j<vertices;j++){
                    if(!Select[j]){
                        cost-=C[i][j];
                    }
                    if(Select[j]){
                        cost+=C[i][j];
                    }
                }
                get_out[k1]=new Class_BL(i,cost);
                k1++;
            }
            else{
                for(j=0;j<vertices;j++){
                    if(Select[j]){
                        cost-=C[i][j];
                    }
                    if(!Select[j]){
                        cost+=C[i][j];
                    }
                }
                get_in[k2]=new Class_BL(i,cost);
                k2++;
            }
        }
        
        Class_BL[][] get=new Class_BL[2][];
        get[0]=get_in;
        get[1]=get_out;
        
        return get;
    }
    
    public void run2(double [][]C,boolean[] Select,int vertices){
        Class_BL[][] aux1;
        aux1 = Value(C,Select,vertices);
        int i,j,k,l;
        for(i=0;i<2;i++){
             System.out.println("Estamos en: "+i);
            for(j=0;j<aux1[i].length;j++){
                System.out.println(aux1[i][j].get);
            }
        }
        Class_BL aux2;
        aux2=In_Out(aux1,vertices,alpha);
        while(aux2 != null){
        System.out.println("El id es: "+aux2.id);
        System.out.println("El id es: "+aux2.get);
            if(aux2.Meter){
                Select[aux2.id]=true;
            }
            else{
                Select[aux2.id]=false;
            }
        aux1 = Value(C,Select,vertices);
        aux2=In_Out(aux1,vertices,alpha);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("La solución es:");
        for(i=0;i<Select.length;i++){
            System.out.print(Select[i]+" ");
        }
        
    }
    
    @Override
    public void run() {
       /*Integer.parseInt();*/
    }
    
    
}
