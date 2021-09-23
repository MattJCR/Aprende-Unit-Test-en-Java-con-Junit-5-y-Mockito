package junit5;

public class Calculator {

    private int result;

    public int add(int num1, int num2){
        result = num1 + num2;
        return result;
    }

    public int subtract (int num1, int num2){
        result = num1 - num2;
        return result;
    }

    public int divide(int num1, int num2){
        if (num2 == 0){
            throw new ArithmeticException("Can't divide by Zero.");
        }
        result = num1 / num2;
        return result;
    }

    public int multiply(int num1, int num2){
        result = num1 * num2;
        return result;
    }

    public void longTaskOperation(){
        try{
            Thread.sleep(1000);
        }catch (Exception e){

        }
    }

    public float toFahrenheit(float celsius){
        return (celsius * 9/5) + 32;
    }
}
