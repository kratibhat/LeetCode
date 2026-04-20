package guessOutput;

public class GO1 {
    public static void main(String[] args) {
     int a=5;
     int x=30;
     int y=20;
     boolean res=(x<y) && (y>15) || (a==5);
     System.out.println(res); // Output: true
     System.out.println(x>y ? x++ : ++y);
     // z=20
     int b=a++ + ++a +a;
     System.out.println(b);
     System.out.println(x +""+y); // Output: 30
        System.out.println(a++ + ++a); // Output: 1
    }


}
