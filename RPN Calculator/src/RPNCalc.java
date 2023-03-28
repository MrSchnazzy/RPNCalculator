
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/* Eli Tisor
 * RPNCalc.java
 * CSCI-310 Data Structures
 * HW 11 - RPN Calculator
 */

/**
 *
 * @author Eli
 */
public class RPNCalc {
    static Scanner in = new Scanner(System.in);
    static boolean finished = false;
    
    public static void main(String[] args) {
        ArrayList<String> tokenList = new ArrayList<>();
        DLinked_StackGeneric<Integer> stack = new DLinked_StackGeneric();
        
        System.out.println("RPN Calculator");
        while(!finished){
            tokenList = readInput();
            
            for(String token : tokenList){
                if(isInt(token))
                    stack.push(Integer.parseInt(token));
                else if (isOperator(token))
                    operation(token, stack);
                else if (isInstruction(token))
                    instruction(token, stack);
                else
                    System.err.println("# Invalid Input: " + token + " not recognized");    
            }
        }
    }
    
    private static ArrayList<String> readInput(){
        String input = in.nextLine();
        ArrayList<String> result = new ArrayList<>(Arrays.asList(input.split("\\s+")));
        return result;
    }
    
    private static void operation(String token,DLinked_StackGeneric<Integer> stack){
        int x;
        int y;
        int z = 0;
        if (stack.size >= 2){
            x = stack.pop();
            y = stack.pop();
            
            if(null != token)
                switch (token) {
                case "+" -> z = y + x;
                case "-" -> z = y - x;
                case "*" -> z = y * x;
                case "/" -> {
                    if(x == 0){
                        System.err.println("# Operation Failed: Cannot Divide By Zero");
                        stack.push(y);
                        stack.push(x);
                        return;
                    }
                    z = y / x;
                }
                case "%" -> {
                    if(x == 0){
                        System.err.println("# Operation Failed: Cannot Divide By Zero");
                        stack.push(y);
                        stack.push(x);
                        return;
                    }
                    z = y % x;
                }
                default -> {
                }
            }
            
            stack.push(z);
            System.out.println(z);
        } else {
            System.err.println("# Operation Failed: Two Numbers Needed");
        }
    }
    
    private static void instruction(String token,DLinked_StackGeneric<Integer> stack){
        if(null != token)
            switch (token) {
            case "?" -> System.out.println(stack.toString());
            case "^" -> System.out.println(stack.pop());
            case "$" -> stack.clear();
            case "!" -> {
                System.out.println("Goodbye :)");
                finished = true;
            }
            default -> {
            }
        }
    }
    
    private static boolean isInt(String token){
        if (token == null)
            return false;
        try{
            Integer i = Integer.parseInt(token);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    
    private static boolean isOperator(String token){
        String operators = "-+*/%";
        for(int i = 0; i < operators.length(); i++){
            if(token.contains(Character.toString(operators.charAt(i))))
                return true;
        }
        return false;
    }
    
    private static boolean isInstruction(String token){
        String operators = "^$?!";
        for(int i = 0; i < operators.length(); i++){
            if(token.contains(Character.toString(operators.charAt(i))))
                return true;
        }
        return false;
    }
}
