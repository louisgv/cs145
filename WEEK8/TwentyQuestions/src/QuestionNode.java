
/**
 * This class Models a leaf OR branch of a Question tree.
 *
 *  It first store a dictionary for word reference.
 *  Then pre-process data from the dictionary, forming an
 *  Anagram Dictionary for faster anagram look-up.
 *
 *  When Given a word, the print function looks for
 *  all anagrams from the Dictionary
 *  and print anagrams from the Anagram Dictionary
 * *
 * <ul>
 * <li> Name: QuestionNode.java
 * <li> Description: Question Node
 * <li> Class: Java 145
 * <li> Instructor: Ken Hang && Janet Ash
 * <li> Date: March 10 2015
 * </ul>
 * @author  Hai H Nguyen (Bill)
 * @version Winter 2015
 */
public class QuestionNode {

    public QuestionNode yes;     // Yes == Hai in Jap

    public QuestionNode no;      // No == Iee in Jap

    private String data;

    public QuestionNode(String data){
        this(null, null, data);
    }

    public QuestionNode(QuestionNode yes,
                        QuestionNode no,
                        String data){
        this.yes = yes;
        this.no = no;
        this.data = data;
    }

    public boolean isAnswer(){
        return yes == null && no == null;
    }

    public String toString(){
        return data;
    }
}
