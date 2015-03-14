
/**
 * This class Models both an Answer OR a
 * Question node of a
 * Question tree.
 *
 * The data field was restricted in order to distinguish
 * between a Question node and an Answer node.
 * The Constructor with only data as variable generates
 * an Answer Node whereas the other generates
 * a Question Node which spans to two
 * other Nodes.
 *
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
    private String data;

    /**
     *  Pointer to the Yes node
     */
    public QuestionNode yes;

    /**
     *  Pointer to the No node
     */
    public QuestionNode no;

    /**
     *  Constructor, Initialize an Answer Node
     * @param data          Answer issues to user
     */
    public QuestionNode(String data){
        this(data, null, null);
    }

    /**
     *  Constructor, Initialize a Question Node
     * @param yes            Yes Node answer
     * @param no             No Node answer
     * @param data           Question to Ask
     */
    public QuestionNode(String data,
                        QuestionNode yes,
                        QuestionNode no){
        this.data = data;
        this.yes = yes;
        this.no = no;
    }

    /**
     * @return              True if the node doesn't have any child, False otherwise
     */
    public boolean isAnswer(){
        return yes == null && no == null;
    }

    /**
     * @return              Data of the node, either a question or an answer
     */
    public String toString(){
        return data;
    }
}//IS29