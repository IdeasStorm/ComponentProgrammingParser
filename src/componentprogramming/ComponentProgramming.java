/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentprogramming;

/**
 *
 * @author mhdsyrwan
 */
public class ComponentProgramming {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CompoLexical myt = new CompoLexical("<11    8,2> <5,6>");
        myt.nextToken();
        String s  = myt.ImageToken();
        myt.nextToken();
        s  = myt.ImageToken();
        myt.nextToken();
        s  = myt.ImageToken();
        myt.nextToken();
        s  = myt.ImageToken();
        myt.nextToken();
        s  = myt.ImageToken();
        myt.nextToken();
        s  = myt.ImageToken();
        myt.nextToken();
        s  = myt.ImageToken();
        myt.nextToken();
        s  = myt.ImageToken();
        myt.nextToken();
        s  = myt.ImageToken();
        myt.nextToken();
        s  = myt.ImageToken();
        myt.nextToken();
        s  = myt.ImageToken();
    }
}
