package com.example.RF;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Bisection extends RootFinder{
    private double lowerLimit;
    private double upperLimit;
    private double result;
    private double iterations;
    private FunctionExpression function;
    public Bisection(boolean applyPrecision1, int precision1, int lowerLimit, int upperLimit, int iterations, FunctionExpression function) {
        super(applyPrecision1, precision1);
        this.lowerLimit=lowerLimit;
        this.upperLimit=upperLimit;
        this.iterations = iterations;
        this.function = function;
    }
    PrintWriter writer;//clears the text file before write

    {
        try {
            writer = new PrintWriter("Bisection.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeFile() {//write steps function
        try {
            FileWriter writer = new FileWriter("Bisection.txt", true);
            writer.write("Lower limit: "+this.lowerLimit+","+"Upper limit: "+this.upperLimit+"\n");
            writer.write("Result: "+this.result+"\n");
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public double getRoot() {
        if(this.function.evaluate(this.upperLimit)*this.function.evaluate(this.lowerLimit)>=0){
            throw new ArithmeticException ("No roots found between entered limits");
        }
        while (this.iterations>0){
            writeFile();
            this.result=round((this.lowerLimit+this.upperLimit)/2.0);
            if(round(this.function.evaluate(this.result)*this.function.evaluate(this.lowerLimit))>0){
                this.lowerLimit=this.result;
            }
            else if(round(this.function.evaluate(this.result)*this.function.evaluate(this.lowerLimit))<0){
                this.upperLimit=this.result;
            }
            else {
                writeFile();
                return this.result;
            }
            this.iterations--;
        }
        writeFile();
        return this.result;
    }

    @Override
    public void setPrecision(int pre) {

    }

    @Override
    public void setFunc(FunctionExpression func) {

    }

    public static void main(String[] args) throws IOException {//for test
        String s="x^3-25";
        FunctionExpression function=new FunctionExpression(s);
        Bisection B=new Bisection(true,5,0,4,50,function);
        System.out.println(B.getRoot());
    }
}