/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxcut;

public class Class_BL {
    int id;
    double get;
    boolean Meter;
    boolean[] Sol;
    Class_BL(int id,double get){
        this.id=id;
        this.get=get;
    }
    
    Class_BL(int id,double get,boolean Meter){
        this.id=id;
        this.get=get;
        this.Meter=Meter;
    }
    
    Class_BL(int id,double get,boolean[] Sol){
        this.id=id;
        this.get=get;
        this.Sol=Sol;
    }
    
    public void Aniadir(boolean Meter){
        this.Meter=Meter;
    }
}
