// CS272
// Lab 3
// Author: 	Phillip Powell
// Date: 	February 11, 2019
// Purpose: Creates and tests the IntNode linklist class. 
/**
 * @author Phillip Powell
 * @version February 11, 2019
 */
package intnode;

public class IntNode {
	private int value;
	private IntNode link;
	
	public IntNode() {
		value = 0;
		link = null;
	} // end no argument constructor
	
	/** 
	 	* @param _data
	 	* Initial value to be stored in the node.
	 	* @param _node
	 	* Node that is to be linked with the "head" node.
	 	* @precondition
	 	* Parameter _node must be a IntNode node or null.
	 	* @postcondition
	 	* Creates a linked node. If parameter _node is "null" it creates a "head" node. 
	 */
	public IntNode( int _data, IntNode _node ) {
		value = _data;
		link = _node;
	} // end constructor
	
	// Accessors
	
	public int getValue( ) {
		return value;
	} // end getValue accessor
	
	public IntNode getLink( ) {
		return link;
	} // end getLink accessor
	
	// Mutators
	
	public void setValue( int inpVal ) {
		value = inpVal;
	} // end setValue mutator
	
	public void setLink( IntNode newLink ) {
		link = newLink;
	} // end setLink mutator
	
	// IntNode Class Methods
	
	/**
	 	* @precondition
	 	* None.
	 	* @postcondition
	 	* The list's integer values are converted to String and formatted appropriately. 
	 	* @returns
	 	* A String object that contains the list's integer values.
	 */
	
	public String toString( ) {
	
		String retString = "";
		IntNode place = getLink();

		retString = retString + getValue();
			while ( place != null ) {
				retString = retString + " -> " + place.getValue();
				place = place.link;
			} // end while
			return retString;
	
	} // end toString method
	
	/**
	 	* @param newValue
	 	* Value that the new node will have.
	 	* @precondition
	 	* None.
	 	* @postcondition
	 	* The method will add a new node immediately after the initial "head" node.
	 	* The new node will have the parameters value inside of it. 
	 */
	public void addNodeAfterThis( int newValue ) {
		link = new IntNode ( newValue, link );
	} // end addNodeAfterThis method
	
	/**
	 	* @precondition
	 	* None
	 	* @postcondition
	 	* Will remove one node after the initial "head" node. If the link is 
	 	* equal to "null" then the method will indicate the node as the "head" node. 
	 */
	public void removeNodeAfterThis( ) {
		
		if ( link != null )
			link = link.getLink();
		else
			System.out.print( "The next node doesn't exist.");
		
	} // end removeNodeAfterThis method
	
	// Static Methods
	/**
 		* @param head
 		* The list's starting node.
 		* @precondition
 		* None.
 		* @postcondition
 		* Counts the number of nodes and returns the integer value.
 		* @return
 		* Returns an integer value containing the number of nodes.
 	 */
	public static int listLength( IntNode head ) {
	
		int count = 0;
	
		for ( IntNode place = head; place != null; place = place.link )
			count++;
	
		return count;

	} // end listLength method
	/**
	 	* @param head
	 	* The starting node for the list
	 	* @param searchVal
	 	* The value to search for inside the list. 
	 	* @precondition
	 	* None.
	 	* @postcondition
	 	* Will return a boolean value if target value has been found inside the specified list.
	 	* @return
	 	* A boolean value to determine if the specified value was found or not.
	 */
	public static boolean search( IntNode head, int searchVal ) {
		
		for ( IntNode place = head; place != null; place = place.link )
			if ( searchVal == place.getValue() )
				return true;
		
		return false;
	
	} // end search method
	
	public static void main(String[] args) {
		
		IntNode strTest = new IntNode( );
		IntNode strTest2 = new IntNode( 99999, null );
		IntNode strTest3 = new IntNode( );
		IntNode linkTest = new IntNode( );
		IntNode linkTest2 = new IntNode( 61, null );
		IntNode linkTest3 = new IntNode( 60, null );
		IntNode lengthTest = new IntNode( );
		
		// Testing toString method with IntNode default constructor
		System.out.print("Testing toString method with default constructor...\n Result: ");
		System.out.println( strTest.toString( ) );
		
		System.out.println( );
		
		// Testing toString method with IntNode constructor
		System.out.print("Testing toString method with normal constructor..\n Result: ");
		System.out.println( strTest2.toString( ) );
		
		System.out.println( );
		
		// Testing setLink method
		System.out.println("Testing the setLink method.");
		System.out.print( " Link pre-method: " + linkTest.toString( ) + "\n Link post-method: " );
		linkTest.setLink( linkTest2 );
		System.out.println( linkTest.toString( ) );
		
		System.out.println( );
		
		// Testing setValue method
		linkTest3.setValue( 3 );
		
		// Testing addNodeAfterThis method
		System.out.println("Testing addNodeAfterThis method...");
		
		System.out.println("Adding 42 after node...");
		linkTest3.addNodeAfterThis( 42 );
		System.out.println( " Result: " + linkTest3.toString( ) + "\n" );
		
		System.out.println("Adding 13 after node...");
		linkTest3.addNodeAfterThis( 13 );
		System.out.println( " Result: " + linkTest3.toString( ) + "\n" );
		
		System.out.println("Adding 25 after node...");
		linkTest3.addNodeAfterThis( 25 );
		System.out.println( " Result: " + linkTest3.toString( ) + "\n" );
		
		System.out.println("Adding 81 after node...");
		linkTest3.addNodeAfterThis( 81 );
		System.out.println( " Result: " + linkTest3.toString( ) + "\n" );
		
		strTest3.addNodeAfterThis( 1 );
		strTest3.addNodeAfterThis( 2 );
		strTest3.addNodeAfterThis( 3 );
		strTest3.addNodeAfterThis( 4 );
		
		// Testing toString method node by node
		System.out.println( "Testing toString method node by node...");
		System.out.println( " Original: " + strTest3.toString( ) );
		System.out.println( " From Node #2: " + strTest3.getLink( ).toString( ) );
		System.out.println( " From Node #3: " + strTest3.getLink( ).getLink( ).toString( ) );
		System.out.println( " From Node #4: " + strTest3.getLink( ).getLink( ).getLink( ).toString( ) );
		System.out.println( " From Node #5: " + strTest3.getLink( ).getLink( ).getLink( ).getLink( ).toString( ) );
		
		// Testing listLength method
		System.out.println( "Testing listLength method...");
		System.out.println( " linkTest3's contents: " + linkTest3.toString( ) );
		System.out.println( " linkTest3's length: " + listLength( linkTest3 ) + "\n" );
		
		System.out.println( "Testing listLength method with a default constructor...");
		System.out.println( " linkTest's contents: " + lengthTest.toString( ) );
		System.out.println( " linkTest's length: " + listLength( lengthTest ) + "\n" );
		 
		// Testing search method
		System.out.println( "Testing search method on linkTest3..." );
		System.out.println( " linkTest3 Contents: " + linkTest3.toString( ) + "\n" );
		System.out.println( " Searching for value \"42\"..." );
		System.out.println( " Resulting boolean value: " + search( linkTest3, 42 ) );
		
		System.out.println("\n Searching for value \"95\"..." ); 
		System.out.println( " Resulting boolean value: " + search( linkTest3, 95 ) );
		
		System.out.println( );
		
		// Testing removeNodeAfterThis method
		System.out.println( "Testing removeNodeAfterThis method..." );
		System.out.print( " Contents of linkTest3 pre-removal:\n List: ");
		System.out.println( linkTest3.toString( ) );
		
		System.out.println( );
		
		System.out.println( " Removing the next node.");
		linkTest3.removeNodeAfterThis( );
		System.out.println( "  linkTest3's length: " + listLength( linkTest3 ) );
		System.out.println( "  List's Remaining Contents: " + linkTest3.toString( ) );
		
		System.out.println( "\n Removing the next node.");
		linkTest3.removeNodeAfterThis( );
		System.out.println( "  linkTest3's length: " + listLength( linkTest3 ) );
		System.out.println( "  List's Remaining Contents: " + linkTest3.toString( ) );
		
		System.out.println( "\n Removing the next node.");
		linkTest3.removeNodeAfterThis( );
		System.out.println( "  linkTest3's length: " + listLength( linkTest3 ) );
		System.out.println( "  List's Remaining Contents: " + linkTest3.toString( ) );
		
		System.out.println( "\n Removing the next node.");
		linkTest3.removeNodeAfterThis( );
		System.out.println( "  linkTest3's length: " + listLength( linkTest3 ) );
		System.out.println( "  List's Remaining Contents: " + linkTest3.toString( ) );
		
		System.out.print( "\n Removing the next node.\n  Result: ");
		linkTest3.removeNodeAfterThis( );
		System.out.println( "\n  linkTest3's length: " + listLength( linkTest3 ) );
		System.out.println( "  List's Remaining Contents: " + linkTest3.toString( ) );
		
	} // end main
} // end class
