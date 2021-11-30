
package ec.edu.espol.util;

import java.io.File;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Util {
    public static Queue<String> readFile(String nomfile){
        Queue<String> palabras =  new PriorityQueue();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                palabras.offer(linea);      
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return palabras;
    }
}
