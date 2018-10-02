import java.util.HashMap;

public class Scanner {
    public static int pos;
    static String input;
    static char ch;
    static final char EOF = '\u0080';
    static int line;
    static int col;
    static HashMap<String,Double> constants = new HashMap<>();
    
    static {
    	constants.put("PI", 3.1416);
    	constants.put("E", 2.7183);
    }
    
    private static void readName(Token t) {
    	t.kind = Token.IDENT;
    	t.str = "";
    	while(ch >= '0' && ch <= 'Z') {
    		t.str += ch;
    		nextCh();
    	}
    	if(constants.containsKey(t.str)) {
    		t.val = constants.get(t.str);
    	}  	
    }

    private static void readNumber(Token t){
       t.kind = Token.NUMBER;
       t.str = "";
       int state = 0;
       for (;;) {
           switch (state) {
               case 0 : if (ch >= '0' && ch <= '9') {t.str += ch; nextCh(); } 
               			else if (ch=='.') { t.str += ch; nextCh(); state = 1; } 
               			else state = 2; break;
               case 1 : if (ch >= '0' && ch <= '9') {t.str += ch; nextCh(); } else state = 2; break;
               case 2 : t.val = Double.parseDouble(t.str); return;
            }
       }
    }
    
    private static void nextCh() {
        if (pos < input.length()) {
            ch = input.charAt(pos++);
            if (ch == '\n') {line++;col++;}
        }
        else
            ch = EOF;
    }

    public static void init(String s){
        input = s;
        pos = 0;
        nextCh();
    }

    public static Token next() {
         while (ch <= ' ') nextCh(); // skip blanks, tabs, eols
         Token t = new Token(); t.pos = pos; t.line = line;
         switch (ch) {        
         	 case 'A' : readName(t); break;
         	 case 'B' : readName(t); break;
         	 case 'C' : readName(t); break;
         	 case 'D' : readName(t); break;
         	 case 'E' : readName(t); break;
         	 case 'F' : readName(t); break;
         	 case 'G' : readName(t); break;
         	 case 'H' : readName(t); break;
         	 case 'I' : readName(t); break;
         	 case 'J' : readName(t); break;
         	 case 'K' : readName(t); break;
         	 case 'L' : readName(t); break;
         	 case 'M' : readName(t); break;
         	 case 'N' : readName(t); break;
         	 case 'O' : readName(t); break;
         	 case 'P' : readName(t); break;
         	 case 'Q' : readName(t); break;
         	 case 'R' : readName(t); break;
         	 case 'S' : readName(t); break;
         	 case 'T' : readName(t); break;
         	 case 'U' : readName(t); break;
         	 case 'V' : readName(t); break;
         	 case 'W' : readName(t); break;
         	 case 'X' : readName(t); break;
         	 case 'Y' : readName(t); break;
         	 case 'Z' : readName(t); break;       
             case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
                        readNumber(t); break;
             case '+' : t.kind = Token.PLUS; nextCh(); break;
             case '-' : t.kind = Token.MINUS; nextCh(); break;
             case '*' : t.kind = Token.TIMES; nextCh(); break;
             case '/' : t.kind = Token.SLASH; nextCh(); break;
             case '(' : t.kind = Token.LBRACK; nextCh(); break;
             case ')' : t.kind = Token.RBRACK; nextCh(); break;
             case EOF : t.kind = Token.EOF; break;
             default :  t.kind = Token.NONE; nextCh(); break;      	 
         }
        return t;
    }

  public static Token token;  // zuletzt erkanntes Token
  public static int la; // kind von lookahead token
  public static Token laToken;  // lookahead token

  // lookahead Methoden
  public static void scan () {
    token = laToken;
    laToken = Scanner.next();
    la = laToken.kind;
  }

  public static void check (int expected) throws Exception {
    if (la == expected) scan();  // erkannt, daher weiterlesen
    else error(Token.names[expected]+" expected");
  }

  public static void error(String msg) throws Exception {
    throw new Exception(msg+" at " + Scanner.laToken.pos);
  }

  /* Test */
    public static void main(String[] args){
        init("PI*45 + (32)*78+45-56.23");
        Token t = next();
        while (t.kind != Token.EOF) {
           System.out.println("<"+Token.names[t.kind]+":"+t.val+">");
           t = next();
        }
        System.out.println();

    }

}
