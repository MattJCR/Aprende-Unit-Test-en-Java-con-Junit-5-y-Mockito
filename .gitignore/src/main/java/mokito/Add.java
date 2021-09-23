package mokito;

public class Add {

    private ValidNumber validNumber;
    private Print print;

    public Add(ValidNumber validNumber, Print print){
        this.validNumber = validNumber;
        this.print = print;
    }
    public int add(Object a, Object b){
        if (validNumber.check(a) && validNumber.check(b)){
            return (Integer) a + (Integer) b;
        }
        return -1;
    }

    public int addInt(Object a, Object b){
        return validNumber.doubleToInt(a) + validNumber.doubleToInt(b);
    }

    public void addPrint(Object a, Object b){
        if (validNumber.check(a) && validNumber.check(b)){
            int result= (Integer) a + (Integer) b;
            print.soutMessage(result);
        }else {
            print.showError();
        }
    }

}
