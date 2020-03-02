package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import fa.State;

/**
 * Creates a DFA state with its transitions
 * 
 * @author Benjamin Clark and Kyle Tupper
 *
 */
public class DFAState extends State {
	
	private HashMap<Character, DFAState> delta;
	
	/**
	 * Constructor for DFAState
	 * @param s represents a DFA State
	 */
	public DFAState(DFAState s) {
		initString(s.toString());
	}
	
	/**
	 * Constructor for DFA State with a string representing a state
	 * 
	 * @param s DFA State
	 */
	public DFAState(String s) {
		initString(s);
	}
	
	/**
	 * Helper for DFA State constructor to initialize delta and name
	 * 
	 * @param state DFA state
	 */
	public void initString(String state) {
		this.name = state;
		delta = new HashMap<Character, DFAState>();
	}
	
	/**
	 * Adds a transition state to the DFA
	 * @param onSymb character in delta
	 * @param name State in the DFA
	 */
	public void addTransition(char onSymb, DFAState name) {
		delta.put(onSymb, name);
	}
	
	/**
	 * Gets to a new state from the previous state
	 * 
	 * @param symb character in DFA alphabet
	 * @return returns state that can be transitioned to
	 */
	public DFAState getTo(char symb){
		return delta.get(symb);
	}
	
	/**
	 * Returns the HashMap with transition states
	 * @return
	 */
	public HashMap<Character, DFAState> getHashMap() {
		return delta;
	}
}
