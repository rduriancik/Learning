package chapterApps;

/**
 * Created by robert on 14.01.16.
 */
public class RecursivePalindromeUsingSubstring {
    public static void main(String [] args){
        System.out.println("Is moon a palindrome "
                + isPalindrome("moon"));
        System.out.println("Is noon a palindrome "
                + isPalindrome("noon"));
        System.out.println("Is a a palindrome "
                + isPalindrome("a"));
        System.out.println("Is aba a palindrome "
                + isPalindrome("aba"));
        System.out.println("Is ab a palindrome "
                + isPalindrome("ab"));
    }

    public static boolean isPalindrome(String text){
        if(text.length() <= 1) // Base case
            return true;
        else if(text.charAt(0) != text.charAt(text.length() - 1))
            return false;
        else
            return isPalindrome(text.substring(1, text.length() - 1));
    }
}
