/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxcut;

public class GenericMatrix<T> {
    T[][] array;
    
    GenericMatrix(T a[][]){
        array=a;
        System.out.println(a.getClass()+"Hola");
    }
    
    T get_ij(int i,int j){
        return array[i][j];
    }
    
    void put_ij(T value,int i,int j){
        array[i][j]=value;
    }
    
}
