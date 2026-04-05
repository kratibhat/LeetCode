package codesnippet.interview;

public class CamelCaseConversion {
    public static void main(String[] args) {
        String input = "heLlo worRld this is java";
        String camelCase = convertToCamelCase(input);
        System.out.println("Input: " + input);
        System.out.println("CamelCase: " + camelCase);
    }

    public static String convertToCamelCase(String str) {
        String []words = str.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<words.length;i++){
            String word = words[i].toLowerCase();
            if(i==0){
                sb.append(word);
            }else{
                sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
            }
        }
        return sb.toString();
        }





}
