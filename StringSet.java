// CS272
// Lab2
// Author: 	Phillip Powell
// Date:    February 6, 2019
// Purpose: Creates the StringSet collection class
// 
/**
 		* @author Phillip Powell
 		* @version
 		*  February 6, 2019
 */
package stringset;

public class StringSet {
	
	private String[ ] strArr;
	private int numStr;
	private int strCap;
	
	/**
	 	 * @param - none
	 	 * @postcondition
	 	 * Initializes a StringSet instance with capacity set to two.
	 	 * Creates a new string array with an initial capacity of two.   
	 */
	public StringSet( ) {
		final int INITIAL_CAPACITY = 2;
		numStr = 0;
		strCap = INITIAL_CAPACITY;
		strArr = new String[ INITIAL_CAPACITY ];
	} // end default constructor
	
	/** 
	 	 * @precondition
	 	 * Parameter _cap must not be negative.
	 	 * @postcondition
	 	 * A new object will be created with a specified capacity. 
	 	 * @param _cap
	 	 * The capacity that is inputed.
	 	 * @throws IllegalArgumentException 
	 	 * Indicates that parameter _cap is negative.
	 */
	public StringSet( int _cap ) {
		if ( _cap < 0 )
			throw new IllegalArgumentException
			( "The capacity is negative: " + _cap );
		
		numStr = 0;
		strCap = _cap;
		strArr = new String[ _cap ];
	} // end constructor
	
	/** 
	 	* @param obj
	 	* The source object that is provided.
	 	* @precondition
	 	* Parameter obj must not be null and be an instance of StringSet.
	 	* @postcondition
		* The method will copy the source into the new object being instantiated
	 */
	public StringSet( Object obj ) {
		if ( obj != null ) {
			if ( obj instanceof StringSet ) {
				StringSet ref = ( StringSet ) obj;
				numStr = ref.numStr;
				strCap = ref.strCap;
				strArr = new String[ ref.numStr ];
				System.arraycopy( ref.strArr, 0, strArr, 0, ref.numStr );
			} else 
				System.out.println("obj is not an instance of StringSet."); 
		} else
			System.out.println("obj is null.");
	} // end copy constructor
	
	public int size() {
		return numStr;
	} // end size method
	
	public int capacity() {
		return strArr.length;
	} // end capacity method
	/**	 	
	   * @param addedStr
	 	* Parameter addedStr is the String to be added to an array 
	 	* @precondition
	 	* String addedStr must not be null
	 	* @postcondition
	 	* The string will be added to the String array. If the array has insufficient space
	 	* then the method will call the ensureCapacity method to enlarge the array. After 
	 	* enlarging the array, the String will be added to the array.
	 */
	public void add( String addedStr ) {
		if ( addedStr != null ) {
			if ( numStr == strArr.length ) 
				ensureCapacity( ( numStr + 1 ) * 2 );
			
			strArr[ numStr ] = addedStr;
			numStr++;
		} else
			System.out.println("addedStr is null.");
	} // end add method
	/**
	 	* @param chkStr
	 	* The String parameter that is searched for 
	 	* @postcondition
	 	* Checks if chkStr is in the object's String array or not
	 	* @return
	 	* A boolean value to determine if chkStr is in the String array or not
	 */
	public boolean contains( String chkStr ) {
		if ( chkStr == null  ) 
			return false;
		
		for( int index = 0; index < capacity( ); index++ )
			if ( chkStr.equals( strArr[ index ] ) )
				return true;
		
		return false;
	} // end contains method
	/**
	 	* @param remStr
	 	* String parameter to be removed from the object's String array.
	 	* @precondition
	 	* Parameter remStr must not be null.
	 	* @postcondition
	 	* Parameter remStr will be removed from the object's String array 
	 	* @return
	 	* A boolean value to determine if remStr was removed or not
	 */
	public boolean remove( String remStr ) {
		if ( remStr == null )
			return false;
		
		int index = 0;
		
		while ( ( index < numStr ) && ( !( remStr.equals( strArr[ index ] ) ) ) )
			index++;
		
			if ( index == numStr )
				return false;
			else {
				numStr--;
				strArr[ index ] = strArr[ numStr ];
				return true;
			} // end else
	} // end remove method
	/**
	 	* @param minCap
	 	* The minimum cap needed to extend an array.
	 	* @precondition
	 	* The parameter minCap must be non-negative. 
	 	* @postcondition
	 	* The calling object's String array contents will be copied to a larger array. 
	 	* The array will reference the larger array. 
	 	* @throws IllegalArgumentException 
	 	* Indicates that minCap is negative.
	 */
	private void ensureCapacity( int minCap ) {
		if ( minCap < 0 )
			throw new IllegalArgumentException
			("The capacity is negative: " + minCap );
		
		String[ ] bigArray = new String[ minCap ];
		System.arraycopy(strArr, 0, bigArray, 0, numStr);
		strArr = bigArray;
	} // end ensureCapacity method 
	/**
	 	* @param ordStr
	 	* String that will be added to the calling object's String array.
	 	* @precondition
	 	* The parameter ordStr must not be null.
	 	* @postcondition
	 	* The String will be entered into the calling object's String array. The method
	 	* will sort the array in their natural order, ascending.  
	 */
	public void addOrdered( String ordStr ) {
		if ( ordStr != null ) {
			if ( numStr == strArr.length ) 
				ensureCapacity( ( numStr + 1 ) * 2 );
			
			strArr[ numStr ] = ordStr;
			numStr++;
			
			String insert;
			for ( int index = 1; index < size(); index++ ) {
				int moveIt = index;
				insert = strArr[ index ];
				while ( ( moveIt > 0 ) && ( strArr[ moveIt - 1 ].compareTo( insert ) > 0 ) ) {
					strArr[ moveIt ] = strArr[ moveIt - 1 ];
					strArr[ moveIt - 1 ] = strArr[ moveIt ];
					--moveIt;
				} // end while
				strArr[ moveIt ] = insert;
			} // end for
		} else
			System.out.println( "The parameter ordStr is null." );
	} // end addOrdered method
	
	// start main
	public static void main( String[ ] args ) {
		
		StringSet defConstr = new StringSet( );
		StringSet constructor = new StringSet( 3 );
		StringSet cpyConstr = new StringSet( constructor );
		StringSet addTest = new StringSet( 4 );
		
		// size method and capacity method tests
		System.out.println( "Testing size and capacity methods...");
		System.out.println( " Default Constructor's size is " + defConstr.size() );
		System.out.println( " Default Constructor's capacity is " + defConstr.capacity( ) );
		System.out.println( );
		
		// copy constructor test
		//  w/ additional size and capacity method tests
		
		System.out.println( "Testing copy constructor...");
		System.out.println( "Testing for null object." );
		StringSet nullConstr = new StringSet( null );
		
		System.out.println();
		System.out.println("Testing if obj is instanceof StringSet.");
		Object instTest = (Object) 2;
		StringSet instConstr = new StringSet( instTest );

		System.out.println( " Constructor's size is " + constructor.size() );
		System.out.println( " Constructor's capacity is " + constructor.capacity( ) );
		System.out.println( );
		
		System.out.println( " Copied constructor's size is " + cpyConstr.size() );
		System.out.println( " Copied constructor's capacity is " + cpyConstr.capacity( ) );
		System.out.println( );
		
		// add method and ensureCapacity method test
		System.out.println( "Testing add method...");
		System.out.println( " addTest's initial size is " + addTest.size( ) );
		System.out.println( " addTest's initial capacity is " + addTest.capacity( ) );
		
		System.out.println( "\nAdding String: \"Tom\", \"Dick\", \"Harry\", \"John\", \"Alex\", \"Pete\", \"Tim\".\n" );
		
		addTest.add( "Tom" );
		addTest.add( "Dick" );
		addTest.add( "Harry" );
		addTest.add( "John" );
		addTest.add( "Alex" );
		addTest.add( "Pete" );
		addTest.add( "Tim" );
		
		System.out.println( " addTest's new size is " + addTest.size( ) );
		System.out.println( " addTest's new capacity is " + addTest.capacity( ) );
		System.out.println( );
		
		System.out.print( " addTest's contents: " );
		for ( int prt = 0; prt < addTest.size( ) - 1; prt++ )
			if ( addTest.strArr[ prt ] != null )
				System.out.print( addTest.strArr[ prt ] + ", " );
		System.out.println( addTest.strArr[ addTest.size( ) - 1 ] );
		System.out.println( );
		
		// contains method test
		StringSet containsTest = new StringSet( addTest );
		
		// prints out containsTest contents
		System.out.println("Starting contains method test.");
		System.out.print( " containsTest contents: " );
		for ( int prt = 0; prt < containsTest.size( ) - 1; prt++ )
			if ( containsTest.strArr[ prt ] != null )
				System.out.print( containsTest.strArr[ prt ] + ", " );
		System.out.println( containsTest.strArr[ containsTest.size( ) - 1 ] );
		
		System.out.println( );
		
		 // Testing with true parameter
		System.out.println( "Testing contains method with a true parameter..." );
		System.out.println( " Searching for string \"Harry\"..." );
		System.out.println( " Result: " + containsTest.contains("Harry") );
		
		System.out.println( );
		
		// Testing with false parameter
		System.out.println( "Testing contains method with a false parameter... " );
		System.out.println( " Searching for string \"Alice\"..." );
		System.out.println( " Result: " + containsTest.contains( "Alice" ) );
		
		System.out.println();
		
		// Testing with null
		System.out.println( "Testing contains method with null..." );
		System.out.println( " Searching for null..." );
		System.out.println( " Result: " + containsTest.contains( null ) );
		
		System.out.println();
		
		// remove method test
		StringSet removeTest = new StringSet( addTest );
		
		System.out.println("Testing remove method...");
		
		System.out.print( " removeTest contents pre-removal: " );
		for ( int prt = 0; prt < removeTest.size( ) - 1; prt++ )
			if ( removeTest.strArr[ prt ] != null )
				System.out.print( removeTest.strArr[ prt ] + ", " );
		System.out.println( removeTest.strArr[ removeTest.size( ) - 1 ] );
		
		System.out.println( );
		
		// removing string "Harry" 
		System.out.println( " Removing \"Harry\"..." );
		System.out.println( " Result: " + removeTest.remove("Harry") );
		
		System.out.print( " removeTest contents post-removal: " );
		for ( int prt = 0; prt < removeTest.size( ) - 1; prt++ )
			if ( removeTest.strArr[ prt ] != null )
				System.out.print( removeTest.strArr[ prt ] + ", " );
		System.out.println( removeTest.strArr[ removeTest.size( ) - 1 ] );
		System.out.println( );
		
		System.out.println( " Removing \"Dick\"...");
		System.out.println( " Result: " + removeTest.remove("Dick") );
		
		System.out.print( " removeTest contents post-removal: " );
		for ( int prt = 0; prt < removeTest.size( ) - 1; prt++ )
			if ( removeTest.strArr[ prt ] != null )
				System.out.print( removeTest.strArr[ prt ] + ", " );
		System.out.println( removeTest.strArr[ removeTest.size( ) - 1 ] );
		
		System.out.println( );
		
		System.out.println( " Removing \"Alice\".");
		System.out.println( " Result: " + removeTest.remove("Alice") );
		
		System.out.println( );
		
		System.out.println( " Removing null.");
		System.out.println( " Result: " + removeTest.remove( null ) );
		
		System.out.println( );
		
		// Testing addOrdered method
		StringSet addOrdTest = new StringSet( addTest );
		
		System.out.println( "Testing addOrdered method...");
		
		addOrdTest.addOrdered("Alice");
		addOrdTest.addOrdered("Rebecca");
		addOrdTest.addOrdered("Zoe");
		
		System.out.println("	Adding string: \"Alice\", \"Rebecca\", \"Zoe\".\n");
		System.out.print( " addOrdTest contents: " );
		for ( int prt = 0; prt < addOrdTest.size( ) - 1; prt++ )
			if ( addOrdTest.strArr[ prt ] != null )
				System.out.print( addOrdTest.strArr[ prt ] + ", " );
		System.out.println( addOrdTest.strArr[ addOrdTest.size( ) - 1 ] );
		
		System.out.println( );
		
		System.out.println( "Testing addOrdered with null...");
		System.out.print(" Result: " );
		addOrdTest.addOrdered( null );
	} // end main
} // end StringSet class