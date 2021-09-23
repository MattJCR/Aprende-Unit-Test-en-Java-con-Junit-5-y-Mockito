package mokito;

public class ValidNumber {

    public ValidNumber(){

    }

    public boolean check(Object o){
        if (o instanceof Integer){
            if((Integer) o < 10 && (Integer) o >=0){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public boolean checkZero(Object o){
        if (o instanceof Integer){
            if((Integer) o ==0){
                throw new ArithmeticException("No podemos aceptar 0");
            }else{
                return true;
            }
        }
        return false;
    }

    public int doubleToInt(Object o){
        if (o instanceof Double){
            return ((Double) o).intValue();
        }
        return 0;
    }

}
