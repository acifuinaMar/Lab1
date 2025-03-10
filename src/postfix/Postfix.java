/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package postfix;

/**
 *
 * @author maryori
 */
import java.util.*;

public class Postfix {
    public static boolean isOperator(char ch){
        return ch=='+' || ch=='-' || ch=='*' || ch=='/' || ch=='^';
    }
    
    public static int assign(char operator) {
        switch (operator) {
            case '+': case '-':
                return 1;
                
            case '*': case '/':
                return 2;
                
            case '^':
                return 3;
            default:
                return 0;
        }
    }
    
    public static boolean validateHierarchy(char op1, char op2){
        return assign(op1) <= assign(op2);
    }
    
    public static String reverseString(String expression){
        String reversed = "";
        for(int i = expression.length() -1; i>=0; i--){
            reversed += expression.charAt(i);
        }
        return reversed;
    }
    
    public static String toPostfix(String expression){
       List<Character> lista = new ArrayList<>();
       Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (isOperator(c)) {
                while (!pila.isEmpty() && validateHierarchy(c, pila.peek())) {
                    lista.add(pila.pop());
                }
                pila.push(c);
            }else if (c == '(') {
                pila.push(c);
            }else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    lista.add(pila.pop());
                }
                pila.pop();
            } else {
                lista.add(c);
            }
        }

        while (!pila.isEmpty()) {
            lista.add(pila.pop());
        }

        StringBuilder resultado = new StringBuilder();
        for (char ch : lista) {
            resultado.append(ch);
        }

        return resultado.toString();
    }
    
    public static String toPrefix(String expression){
        String reversed = reverseString(expression);
    
        String corrected = "";
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            switch (c) {
                case '(':
                    corrected += ')';
                break;
                
                case ')':
                    corrected += '(';
                break;
                default:
                    corrected += c;
                break;
            }
        }

        return reverseString(toPostfix(corrected));
    }
    
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        String expression = "";
        int option = 0;
        
        while(option != 3){
            System.out.println("Convertir de infija a");
            System.out.println("1. Postfija");
            System.out.println("2. Prefija");
            System.out.println("3. Salir");
            option = entry.nextInt();
            entry.nextLine();
            
            switch(option){
                case 1:
                   System.out.println("Ingrese la expresion:");
                   expression = entry.nextLine();
                   System.out.println("Postfija: " + toPostfix(expression));
                break;
                
                case 2:
                    System.out.println("Ingrese la expresion:");
                    expression = entry.nextLine();
                    System.out.println("Prefija: " + toPrefix(expression));
                break;
                
                case 3:
                    System.out.println("Saliendo...");
                break;
                
                default:
                    System.out.println("Opcion invalida");
                break;
            } //End of switch
        } //End of while
    } 
}
