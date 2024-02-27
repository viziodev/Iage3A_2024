package cours;

public class CalculV1 implements ICalcul {
    public int somme(int a, int b) {
        if(a%2==0 && b%2==0  )return a+b;
        return 0;
    }
}
