/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentprogramming;


import java.util.LinkedList;
import componentprogramming.Utils.*;
import componentprogramming.CompoLexical.*;
import java.util.Vector;

public class CompoParser {
    
    private RulesSet rules;
    private CompoLexical lex;
    
    public CompoParser(String text) {
        lex = new CompoLexical(text);
    }
    
    private Vector<Utils.Identifier> getIdentifiers(Vector<Utils.Identifier> v1, 
            Vector<Utils.Identifier> v2) {
        Vector<Utils.Identifier> res = new Vector<Utils.Identifier>();
        if (v1.isEmpty()) {
            for (Utils.Identifier iden : v2) {
                Vector<Utils.Identifier> vect = new Vector<Utils.Identifier>();
                vect.add(iden);
                res.add(rules.getKey(vect));
            }
        }
        else if (v2.isEmpty()){
            for (Utils.Identifier identifier : v1) {
                Vector<Utils.Identifier> vect = new Vector<Utils.Identifier>();
                vect.add(identifier);
                res.add(rules.getKey(vect));
            }
        }
        for (Utils.Identifier identifier : v1) {
            for (Utils.Identifier iden : v2) {
                Vector<Utils.Identifier> vect = new Vector<Utils.Identifier>();
                vect.add(identifier);
                vect.add(iden);
                res.add(rules.getKey(vect));
            }
        }
        return res;
    }
    
    public boolean parse(Utils.RulesSet rules) {
        int n = 0;
        this.rules = rules;
        LinkedList<LinkedList<Vector<Utils.Identifier>> > Table  
                = new LinkedList<LinkedList<Vector<Utils.Identifier>>>();
        
        LinkedList<Vector<Utils.Identifier>> row = 
                    new LinkedList<Vector<Utils.Identifier>>();
        while(lex.nextToken() != null) {
            // GET Gramer for input and put it in hash
            Vector<Identifier> vect = rules.getMultiKey(new Identifier(lex.currentToken()));
            row.add(vect);
            n++;
        }
        // Add Linked list to Table
        Table.add((LinkedList<Vector<Identifier>>)row.clone());
        
        Vector<Utils.Identifier> V = new Vector<Utils.Identifier>();
        for (int j=1; j<n; j++) {
            row.clear();
            for (int i=0; i<n-j; i++) {
                V.clear();
                for (int k=0; k<j; k++) {
                    // GET String Gramer and put it in Set
                    LinkedList<Vector<Utils.Identifier>> FirstList = Table.get(k);
                    LinkedList<Vector<Utils.Identifier>> SecondList = Table.get(j-k-1);
                    Vector<Utils.Identifier> h1 = FirstList.get(i);
                    Vector<Utils.Identifier> h2 = SecondList.get(i+k+1);
                    for (Utils.Identifier Identifier :getIdentifiers(h1, h2)) {
                        if (Identifier != null)
                            if (!V.contains(Identifier))
                                V.add(Identifier);
                    }
                }
                row.add((Vector<Identifier>) V.clone());   
            }
            Table.add((LinkedList<Vector<Identifier>>)row.clone());
        }
        if (Table.getLast().getLast().contains(rules.getStart()))
            return true;
        else
            return false;
    }
}
