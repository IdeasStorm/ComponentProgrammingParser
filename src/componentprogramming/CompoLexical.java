/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentprogramming;
import java.io.*;
import java.util.*;
/**
 *
 * @author mhdsyrwan
 */

public class CompoLexical {
    
    public enum typeToken {
        component ,Open,Closer ,ParallelSign  ;
    } 
    public class Token {
        private  typeToken type ;
        
    }

    CompoLexical()
    {
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
        for (int i = indexInput; i < Input.length(); i++) {
            if ((Input.charAt(i) == '&')&&(CurrentState.getNextNode(Input.charAt(i))!=null)){
                res.type = typeToken.ParallelSign ;
                CurrentState = CurrentState.getNextNode(Input.charAt(i));
                mCurrentToken = res ;
                break;
             }else if ((Input.charAt(i) == '(')&&(CurrentState.getNextNode(Input.charAt(i))!=null)){
                res.type = typeToken.Open ;
            }else if ((Input.charAt(i) == ')')&&(CurrentState.getNextNode(Input.charAt(i))!=null)){
                res.type = typeToken.Closer ;
            }else {
                
            }
            
        }
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
    
    NFA nfa ;
    String Input ;
    Token mCurrentToken;
    nodeNFA CurrentState ;
    int indexInput ;
    
}