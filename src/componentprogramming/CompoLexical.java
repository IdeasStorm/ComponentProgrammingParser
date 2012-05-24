/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentprogramming;
import com.sun.xml.internal.ws.message.stream.StreamAttachment;
import java.io.*;
import java.util.*;
/**
 *
 * @author mhdsyrwan
 */

public class CompoLexical {

    
    public enum typeToken {
        Num,openTok_brace,closeTok_brace ,openBrace,closeBrace ,ParallelSign,dot,comma ;
    } 
    public class Token {
        
        public void Load(String str)
        {
            
        }
        public String toString()
        {
            return s ;
        }
        public int getInt()
        {
            if (type == typeToken.Num)
                return Integer.parseInt(s);
            return -1 ;
        }
        private  typeToken type ;
        private String s ;
    }


    CompoLexical(String str)
    {
       Input = str ; 
       nfa = new NFA();
       indexInput = 0 ;
       CurrentState = nfa.getStartState() ;
       mCurrentToken = null ;
    }
    
    Token nextToken(){
        //TODO add next token logic
        Token res = new Token();
        String temp = new String() ;
        for (int i =indexInput; i < Input.length(); i++) {
            indexInput++ ; 
            
                nodeNFA nextNode  = CurrentState.getNextNode(Input.charAt(i));
                if (nextNode == null){
                    //TODO Exception
                    throw new RuntimeException("Error in Alphabetic of language");
                }else if (nextNode == nfa.loopState){
                    temp += Input.charAt(i);
                    res.type = typeToken.Num ;
                    int index = i+1 ;
                    while(nextNode.getNextNode(Input.charAt(index)) ==nfa.loopState )
                    {
                        nextNode = nextNode.getNextNode(Input.charAt(index));
                        temp += Input.charAt(index);
                        index++;
                        i++;
                    }
                    indexInput = index ;
                    res.s = temp;
                    CurrentState = nextNode ;
                    break;
                }else if (nextNode.isFiniteState()){
                    temp += Input.charAt(i);
                    switch(Input.charAt(i))
                    {
                        case '(':
                            res.type = typeToken.openBrace ;
                            break;
                        case ')':
                            res.type = typeToken.closeBrace ;
                            break;
                        case '<':
                            res.type = typeToken.openTok_brace ;
                            break;
                        case '>':
                            res.type = typeToken.closeTok_brace ;
                            break;
                        case '.':
                            res.type = typeToken.dot;
                            break;
                        case '&':
                            res.type = typeToken.ParallelSign ;
                            break;
                        case ',':
                            res.type = typeToken.comma ;
                            break;
                    }
                    res.s = temp;
                    CurrentState = nextNode ;
                    break;
                }else{
                    CurrentState = nextNode ;
                }
        }
        if (indexInput <= Input.length())
            mCurrentToken = res ;
        return res ;
    }
    Token currentToken(){
        //TOOD add currnt Token Logic
        return mCurrentToken;
    }
    
    String ImageToken(){
        //TODO return token string
        return currentToken().toString();
    }
    
    boolean End() {
        return (indexInput > Input.length());
    }
    
    NFA nfa ;
    String Input ;
    Token mCurrentToken;
    nodeNFA CurrentState ;
    int indexInput ;
    
}

/*
    Token nextToken(){
        //TODO add next token logic
        Token res = new Token();
        String temp = new String() ;
        for (int i = indexInput; i < Input.length(); i++) {
            if ((Input.charAt(i) == '&')&&(CurrentState.getNextNode(Input.charAt(i))!=null)){
                res.type = typeToken.ParallelSign ;
                CurrentState = CurrentState.getNextNode(Input.charAt(i));
                break;
             }else if ((Input.charAt(i) == '(')&&(CurrentState.getNextNode(Input.charAt(i))!=null)){
                res.type = typeToken.Open ;
                break;
            }else if ((Input.charAt(i) == ')')&&(CurrentState.getNextNode(Input.charAt(i))!=null)){
                res.type = typeToken.Closer ;
                break;
            }else {
                temp += Input.charAt(i);
                nodeNFA nextNode  = CurrentState.getNextNode(Input.charAt(i));
                if (nextNode == null){
                    //TODO Exception
                    int ii = 0 ;
                }else if (nextNode.isFiniteState()){
                    res.type = typeToken.component ;
                    res.Load(temp);
                    CurrentState = nextNode ;
                    break;
                }else{
                    CurrentState = nextNode ;
                }       
            }
        }
        mCurrentToken = res ;
        return res ;
    }
*/    
