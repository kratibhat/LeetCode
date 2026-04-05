package codesnippet.interview;

public class ExceptionChainingExample {
    public  static void main(String[] args) {
        try {
            methodA();
        }catch (IllegalArgumentException e){
            System.out.println("message "+e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Caught in main: " + e.getMessage());
            System.out.println("Cause: " + e.getCause());
        }
    }
    public static void methodA() throws Exception {
        String input = "abc";
        int number = Integer.parseInt(input); // This will throw NumberFormatException
        System.out.println("Method A: " + number);
    }
}
