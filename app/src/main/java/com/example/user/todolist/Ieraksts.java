package com.example.user.todolist;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user on 2017.02.07..
 */
public class Ieraksts implements Serializable{
    private String ierakstaTeksts;
    private Date pievienosanasDatums;
    private int svarigums;
public Ieraksts(String ierakstaTeksts,int svarigums){
    this.ierakstaTeksts = ierakstaTeksts;
    this.pievienosanasDatums = new Date();
    this.svarigums = svarigums;
}
    @Override
   public String toString(){

        return ierakstaTeksts;
    }
    public Date getDate(){
        return pievienosanasDatums;
    }
    public void setText(String text){
        ierakstaTeksts = text;

    }
}
