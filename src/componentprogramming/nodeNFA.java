/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentprogramming;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MultiHashtable;
import java.util.LinkedList;
import java.util.Hashtable;

/**
 *
 * @author yaman
 */
public class nodeNFA {
    
    public nodeNFA(String name)
    {
        nextNodes = new Hashtable<Character ,nodeNFA >();
        this.Name = name ;
    }
    public void link(char ch )
    {
    
           this.nextNodes.put(ch,this);
    }
    
    public void link(char ch ,nodeNFA node)
    {
           this.nextNodes.put(ch,node);
    }
    public nodeNFA getNextNode(char ch)
    {
        if (nextNodes.containsKey(ch))
      
            return nextNodes.get(ch);
        else
            return null ;
    }
    
    public Hashtable<Character ,nodeNFA > getNextNodes()
    {
        return nextNodes ;
    }
    public void setNextNodes(Hashtable<Character ,nodeNFA > nodes)
    {
        this.nextNodes = nodes ;
    }
    public void setFinite()
    {
        this.finite = true ;
    }
    public String getName()
    {
        return Name ;
    }
    public void setNotFinite()
    {
        this.finite = false ;
    }
    public boolean isFiniteState()
    {
        return this.finite;
    }
    
    private String Name;
    private boolean finite;
    private  Hashtable<Character ,nodeNFA > nextNodes;
}
