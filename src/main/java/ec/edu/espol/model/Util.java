
package ec.edu.espol.model;

import java.io.File;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Util {
    public static String[] readFile(String nomfile){
        String palabras[] = new String[10];
        try(Scanner sc = new Scanner(new File(nomfile))){
            int i = 0;
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                palabras[i++] = linea;     
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return palabras;
    }
}
