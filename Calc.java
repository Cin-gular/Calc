import java.util.*;
    
public class Calc{
    static boolean addStack(char check, Stack<Character> stack){
        boolean decision=false;
        if(stack.isEmpty()){
            decision=true;
        }
        else{

            char elementPresent=stack.peek().toString().charAt(0);
            switch(check){
                case '+':
                    if(elementPresent == '*' || elementPresent == '/' || elementPresent == '-' || elementPresent == '+'){
                        decision = false;
                    }
                    else{
                        decision=true;
                    }
                break;

                case '-':
                    if(elementPresent == '*' || elementPresent == '/' || elementPresent == '+' || elementPresent == '-'){
                        decision = false;
                    }
                    else{
                        decision=true;
                    }
                break;
                
                case '*':
                    if(elementPresent == '+' || elementPresent == '-'){
                        decision = true;
                    }
                    else{
                        decision=false;
                    }
                break;

                case '/':
                    if(elementPresent == '+' || elementPresent == '-'){
                        decision = true;
                    }
                    else{
                        decision=false;
                    }
                break;
            }
        }
        return decision;
    }

    static int RPM(String output){
        int result=0;
        Stack<Integer> resultStack = new Stack<>();
        String temp="";
        int index=0;
        while(index < output.length()){
            char token=output.charAt(index);
            if (Character.isDigit(token)) {
                temp="";
                while(index<output.length() && Character.isDigit(token)){  
                    System.out.println("Token "+token);
                    temp+=Character.getNumericValue(token);
                    index++;
                    token=output.charAt(index);
                }
                resultStack.push(Integer.parseInt(temp));
                System.out.println("Result Stack: "+resultStack);
            }
            else if(Character.isWhitespace(token)){
                index++;
            }
            else{
                if (resultStack.size() < 2) {
                    throw new IllegalStateException("Not enough operands for operation");
                }
                int b=Integer.parseInt(resultStack.pop().toString());
                int a=Integer.parseInt(resultStack.pop().toString());
            switch(token){
                case '+':
                    resultStack.push(a+b); 
                break;
                case '-':
                    resultStack.push(a-b); 
                break;
                case '*':
                    resultStack.push(a*b); 
                break;
                case '/':
                    resultStack.push(a/b); 
                break;
                }    
                index++;
            }
        }
        result=(int)resultStack.pop();
        return result;
    }

    // static int examineNumber(String inputString){

    //     return 0;
    // }

    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        System.out.println("Please enter the math equation: ");
        String equation = sc.nextLine();
        Stack<Character> stack = new Stack<Character>();
        String output="";
        boolean flag=false;
        //5+4-3*2
        int j=0;
        while(equation.length()>j){
            if(Character.isDigit(equation.charAt(j))){
                //545 / 2 - 33
                while(equation.length()>j && Character.isDigit(equation.charAt(j))){
                    output+=equation.charAt(j);
                    j+=1;
                }
                output+=" ";
            }
            else if(equation.charAt(j) == '+' || equation.charAt(j) == '-' || equation.charAt(j) == '*' || equation.charAt(j) == '/'){
                flag=false;
                while(flag==false){
                    if(addStack(equation.charAt(j), stack)){
                        stack.push(equation.charAt(j));
                        flag=true;    
                        System.out.println(stack);
                    }    
                    else{
                        char element=stack.pop().toString().charAt(0);
                        output+=element;    
                        System.out.println(stack);
                        flag=false;
                    }
                }
                j+=1;
            }    
        }
        //adding the rest of the stack
        System.err.println("output: "+output);
        while(!stack.empty()){
            char element=stack.pop().toString().charAt(0);
            output+=element;
        }
        System.out.println(stack);
        System.out.println(output);  
        int ans=RPM(output);
        System.out.println(ans);  
    }
}