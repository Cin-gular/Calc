import java.util.*;
    
public class Calc{
    static boolean addStack(char check, Stack stack){
        boolean decision=false;
        if(stack.isEmpty()){
            decision=true;
        }
        else{

            char elementPresent=stack.peek().toString().charAt(0);
            switch(check){
                case '+':
                    if(elementPresent == '*' || elementPresent == '/' || elementPresent == '-'){
                        decision = false;
                    }
                    else{
                        decision=true;
                    }
                break;

                case '-':
                    if(elementPresent == '*' || elementPresent == '/' || elementPresent == '+'){
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

    static int RPM(String output,Stack<Character> stack){
        int result=0;
        Stack<Integer> resultStack = new Stack<>();
        int index=0;
        while(index < output.length()){
            char token=output.charAt(index);
            if (Character.isDigit(token)) {  
                resultStack.push(Character.getNumericValue(token));
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
            }
            index++;
        }
        result=(int)resultStack.pop();
        return result;
    }

    

    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        System.out.println("Please enter the math equation: ");
        String equation = sc.nextLine();
        Stack<Character> stack = new Stack<Character>();
        String output="";
        //5+4-3*2
        boolean flag=false;
        for(int i=0;i<equation.length();i++){
            if(Character.isDigit(equation.charAt(i))){
                output+=equation.charAt(i);
            }
            else if(equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '*' || equation.charAt(i) == '/'){
                flag=false;
                while(flag==false){
                    if(addStack(equation.charAt(i), stack)){
                        stack.push(equation.charAt(i));
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
            }    
        }
        //adding the rest of the stack
        while(!stack.empty()){
            char element=stack.pop().toString().charAt(0);
            output+=element;
        }
        Stack finaly=new Stack();
        for(int i=0;i<output.length();i++){
            finaly.push(output.charAt((i)));
        }
        System.out.println(stack);
        System.out.println(output);  
        int ans=RPM(output,finaly);
        System.out.println(ans);  
    }
}