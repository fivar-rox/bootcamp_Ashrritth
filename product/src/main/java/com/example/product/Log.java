package com.example.product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log{
    private String fileName = "/Users/pashrritth/Documents/formalities/bootcamp/product/log.txt";
    private FileWriter fw = null;
    private BufferedWriter bw = null;
    private PrintWriter pw = null;

    Log() throws IOException{
        fw = new FileWriter(fileName);
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw);
    }

    void Finalize(){
        pw.flush();
        pw.close();
    }

    public String print(String msg){
        pw.println(msg);
        return msg;
    }
}
