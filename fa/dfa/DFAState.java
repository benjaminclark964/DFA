package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import fa.State;

public class DFAState extends State {
	
	private HashMap<Character, DFAState> delta;
	private boolean isFinal;
	
	/**
	 * Constructor for DFAState class
	 * @param s represents a DFA State
	 */
	public DFAState(DFAState s) {
		initString(s.toString());
		isFinal = false;
		//delta = new HashMap<Character, DFAState>();
	}
	
	public DFAState(String s, boolean finalState) {
		initString(s);
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
	
	public boolean isFinal() {
		return isFinal;
	}
	
	/**
	 * Adds a transition state to the DFA
	 * @param onSymb character in delta
	 * @param name State in the DFA
	 */
	public void addTransition(char onSymb, DFAState name) {
		delta.put(onSymb, name);
	}
	
	public DFAState getTo(char symb){
		return delta.get(symb);
	}
	
	public HashMap<Character, DFAState> getHashMap() {
		return delta;
	}
}
