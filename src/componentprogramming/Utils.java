/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentprogramming;
import componentprogramming.CompoLexical.Token;
import java.util.HashSet;
import java.util.Hashtable;
/**
 *
 * @author mhdsyrwan
 */
public class Utils {
    static class Identifier {
        private String name;
        private Token token = null;
        public Identifier(Token t) {
            this.token = t;
        }
        
        public Identifier(String name){
            this.name = name;
        }
        boolean isToken() {
            return (getToken() != null);
        }

        @Override
        public String toString() {
            if (isToken())
                return getToken().toString();
            else 
                return name;
        }

        /**
         * @return the token
         */
        public Token getToken() {
            return token;
        }
        
    }
    static class RulesSet {
        private Hashtable<Identifier,HashSet<Identifier> > rules;
        public RulesSet(){
            rules = new Hashtable<Identifier, HashSet<Identifier>>();
        }
        
        public void addRule(Identifier key, HashSet<Identifier> contents) {
            rules.put(key, contents);
        }
        
        public HashSet<Identifier> getContents(Identifier key) {
            return rules.get(key);
        }
    }
}
