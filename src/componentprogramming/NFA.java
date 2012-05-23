/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentprogramming;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author yaman
 */
class NFA {
    
    public NFA(){
        
    AllStates = new HashSet<nodeNFA>();
    finitStates = new HashSet<nodeNFA>();
    startState = new nodeNFA("q0") ;
    Alphabetic = new LinkedList<Character>();
    AllStates.add(startState);

  /*  Finit_wordsState = new NodeNFA(">");
    Finit_wordsState->setFinite();
    FinitStates.insert(Finit_wordsState);
    AllStates.insert(Finit_wordsState);*/
    for (char ch = '0';ch<='9';ch++)
    {
        Alphabetic.add(ch);
    }
    Alphabetic.add('(');
    Alphabetic.add(')');
    Alphabetic.add('<');
    Alphabetic.add('>');
    Alphabetic.add('&');
    Alphabetic.add(',');
    LoadNFA();
    }
   
    
    public  void LoadNFA()
    {
    nodeNFA CurrentState = startState, NextState = startState;
    Integer CounterState = 1 ; //for generate and save name of states(NodeDFA) q0,1,2,3....
    NextState = new nodeNFA("q"+CounterState.toString());
    CounterState ++ ;
    startState.link('<', NextState);
    CurrentState = NextState ;
    for (char ch = '0';ch<='9';ch++)
    {
        CurrentState.link(ch);
    }
    NextState = new nodeNFA("q"+CounterState.toString());
    CounterState ++ ;
    CurrentState.link(',', NextState);
    CurrentState = NextState ;
    for (char ch = '0';ch<='9';ch++)
    {
        CurrentState.link(ch);
    }
    NextState = new nodeNFA("q"+CounterState.toString());
    CounterState ++ ;
    CurrentState.link('>', NextState);
    finitStates.add(NextState);
    NextState.setFinite();
    CurrentState = NextState ;
    CurrentState.link('<',startState.getNextNode('<'));
    NextState = new nodeNFA("q"+CounterState.toString());
    CounterState ++ ;
    CurrentState.link('&', NextState);
    }
    
    public void SimulateNFA(String str)
    {
        
    }
    
    //Get
    LinkedList<Character> getAlphabetic() {return this.Alphabetic ;}
    nodeNFA getStartState() {return this.startState ;}
    Set<nodeNFA> getFinitStates(){return this.finitStates ;}
    Set<nodeNFA> getAllStates(){return this.AllStates;}
   
    
    private LinkedList<Character> Alphabetic ;
    private nodeNFA startState ; 
    private Set<nodeNFA> finitStates;
    private Set<nodeNFA> AllStates ; 
}
