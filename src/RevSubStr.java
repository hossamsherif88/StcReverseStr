import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RevSubStr {

    public static void main (String[] args) throws InterruptedException {
        inputPatternIterative();
    }


    public static String inputPatternIterative() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String inputStr = "";
        int loopSize = Integer.MAX_VALUE;

        while (loopSize > 0) {
            loopSize--;
            System.out.print("Enter a string: ");
            boolean isMatched=true;
            inputStr = scanner.next();

/*            Pattern pattern = Pattern.compile("[a-z\\(\\)]+");
            Matcher m = pattern.matcher(inputStr);
            isMatched = m.matches();
            if(!isMatched)
                System.err.println("only lower case English characters and parentheses is accepted !");
*/
            int sz = inputStr.length();
            if(sz < 1) {
                System.err.println("string length should be greater than 0 !");
                Thread.sleep(20);
                continue;
            }
            else if(sz > 2000) {
                System.err.println("string length should be less than 2000 !");
                Thread.sleep(20);
                continue;
            }
            int parenthesesCNT = 0;
            char inChar;
            Stack<Integer> stck = new Stack<Integer>();
            for (int i = 0; i < sz; i++) {
                inChar = inputStr.charAt(i);
                if (/*(Character.isLetter(inChar) == false) &&*/ (Character.isLowerCase(inChar) == false)  && (inChar != '(') && (inChar != ')')) {
                    System.err.println("only lower case English characters and parentheses is accepted !");
                    isMatched = false;
                    Thread.sleep(20);
                    break;
                }
                if(inChar == '('){
                    ++parenthesesCNT;
                    //opening bracket, Push the index of the current
                    stck.push(i);
                }
                else if(inChar == ')') {
                    --parenthesesCNT;
                    if(parenthesesCNT < 0){
                        System.err.println("parentheses not balanced !");
                        Thread.sleep(20);
                        isMatched = false;
                        break;
                    }
                    // Reverse the substring starting after the last encountered opening
                    // parenthesis till the current character
                    char[] strArr = inputStr.toCharArray();
                    reverse(strArr, stck.peek() + 1, i-1);
                    inputStr = String.copyValueOf(strArr);
                    stck.pop();
                }

            }

            if(!isMatched)
                continue;
            if(parenthesesCNT != 0){
                System.err.println("parentheses not balanced !");
                Thread.sleep(20);
                continue;
            }
            System.out.println(inputStr);
        }
        scanner.close(); // Closing scanner resource after use.
        return inputStr;
    }

        static void reverse(char arrChr[], int l, int h)
        {
            if (l < h)
            {
                char chr = arrChr[l];
                arrChr[l] = arrChr[h];
                arrChr[h] = chr;
                reverse(arrChr, l + 1, h - 1);
            }
        }

        // Function to return the modified string
        static String reverseWthinParentheses(String str, int len)
        {
            Stack<Integer> st = new Stack<Integer>();
            for (int i = 0; i < len; i++)
            {
                //opening bracket, Push the index of the current
                if (str.charAt(i) == '(')
                {
                    st.push(i);
                }
                // Reverse the substring starting
                // after the last encountered opening
                // bracket till the current character
                else if (str.charAt(i) == ')')
                {
                    char[] A = str.toCharArray();
                    reverse(A, st.peek() + 1, i);
                    str = String.copyValueOf(A);
                    st.pop();
                }
            }

            // To store the modified string
            String res = "";
            for (int i = 0; i < len; i++)
            {
                if (str.charAt(i) != ')' && str.charAt(i) != '(')
                {
                    res += (str.charAt(i));
                }
            }
            return res;
        }
}
