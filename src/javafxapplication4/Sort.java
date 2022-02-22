/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

/**
 *
 * @author acer
 */
public class Sort {
    String name;
     String  nos ;
     String type;
     String a;
     
     
     public Sort() {
        this.name = "";
        this.nos = "";
        this.type= "";
        this.a="";
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
     
    public Sort(String name,String nos){
    this.name=name;
    this.nos=nos;
    
    }
    public Sort(String name,String nos,String a){
    this.name=name;
    this.nos=nos;
    this.a=a;
    }
    public Sort(String name,String nos,String type,String a){
    this.name=name;
    this.nos=nos;
    this.type=type;
    this.a=a;
    }
        public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getNos() {
        return nos;
    }
 
    public void setNos(String nos) {
        this.nos = nos;
    }
 
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
}
