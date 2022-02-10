import java.nio.file.Path;
import java.util.ArrayList;
public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if (nextOpenBracket == -1) break;
            
            String closeBracket = markdown.substring(nextCloseBracket+1, nextCloseBracket+2); 
            if (!closeBracket.equals("(")) break;
            
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            //System.out.println("nextCloseB: " + nextCloseBracket + "; nextOpenB: "+ nextOpenBracket + "; currentIdx" + currentIndex +"\n");
            if ( (nextOpenBracket ==0) || markdown.charAt(nextOpenBracket-1) != '!')
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
            //System.out.println(currentIndex);
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}