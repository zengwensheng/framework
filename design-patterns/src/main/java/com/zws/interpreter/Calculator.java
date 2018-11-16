package com.zws.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.zws.interpreter.cls.RPN;

public class Calculator {

    public Calculator() {


        try {
            System.out.println("Input a expression:");
            BufferedReader is = new BufferedReader(new InputStreamReader(
                    System.in));

            String input = new String();
            input = is.readLine().trim();


            HashMap<String, Float> var;
            var = new HashMap<String, Float>();
            var.put("a", 10F);
            var.put("b", 20F);
            var.put("c", 30F);
            var.put("d", 40F);
            var.put("e", 50F);
            var.put("f", 60F);


            RPN boya = new RPN(input);
            boya.getResult(var);


            is.close();
        } catch (IOException e) {
            System.out.println("Wrong input!!!");
        }

    }

}
