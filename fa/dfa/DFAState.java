package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;

import fa.State;

public class DFAState extends State {
	
	private HashMap<Character, DFAState> delta;
	private boolean isFinal;
	
	/**
	 * Constructor for DFAState class
	 * @param s represents a DFA State
	 */
	public DFAState(DFAState s) {
		isFinal = false;
		delta = new HashMap<Character, DFAState>();
	}
	
	public DFAState(String s, boolean finalState) {
		if (finalState) {
			
			isFinal = true;
			
		} else {
			
			isFinal = false;
			
		}
	}
	
	public DFAState(String s) {
		initString(s);
		isFinal = false;
	}
	
	public void initString(String state) {
		this.name = state;
		delta = new HashMap<Character, DFAState>();
	}
	
	//public addState
	
	/**
	 * Adds a transition state to the DFA
	 * @param onSymb character in delta
	 * @param name State in the DFA
	 */
	public void addTransition(char onSymb, DFAState name) {
		delta.put(onSymb, name);
	}
	
}
