import java.util.*;
import java.util.regex.*;


public class Lab6
{
    public static void main(String[] args)
    {
        Pattern p = Pattern.compile("([^ ])(ing)(\\b)");
        Matcher matcher = p.matcher(args[0]);
        System.out.println(matcher.replaceAll("$1ed$3"));
    }
}
