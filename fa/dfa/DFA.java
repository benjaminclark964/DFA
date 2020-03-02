package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import fa.State;

/**
 * Creates a DFA and DFA complement
 * 
 * @author Kyle Tupper and Benjamin Clark
 *
 */
public class DFA implements DFAInterface {
	
	private DFAState startState;
	private DFAState currentState;
	private LinkedHashSet<DFAState> all_states;       // all DFA states
	private LinkedHashSet<Character> abc;		      // DFA alphabet
	private LinkedHashSet<DFAState> non_final_states; // all DFA non-final States
	private LinkedHashSet<DFAState> final_states;	  // all DFA final States
	
	/**
	 * Constructor for DFA
	 */
	public DFA() {
		abc = new LinkedHashSet<Character>();
		all_states = new LinkedHashSet<DFAState>();
		final_states = new LinkedHashSet<DFAState>();
		non_final_states = new LinkedHashSet<DFAState>();
	}
	
	/**
	 * Returns the desired DFAState if the state exists within currently
	 * found states.
	 * 
	 * @param state state to be returned if state exists in current set
	 * @return state if the desired state exists
	 */
	private DFAState getState(String state) {
		DFAState ret = null;
		
			for(DFAState s : getStates()) {
				if(s.toString().equals(state)) {
					ret = s;
					break;
				}
			}
		
		return (DFAState) ret;
	}

	@Override
	public void addStartState(String s) {
			DFAState state = new DFAState(s);
			startState = state;
			non_final_states.add(state);
			currentState = (DFAState)startState;
	}

	@Override
	public void addState(String name) {
		DFAState s = new DFAState(name);
		all_states.add(s);
		non_final_states.add(s);
	}
	
	@Override
	public void addFinalState(String F) {
			DFAState finalState = new DFAState(F);
			final_states.add(finalState);
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		abc.add(onSymb);
		getState(fromState).addTransition(onSymb, getState(toState));
	}

	@Override
	public LinkedHashSet<? extends DFAState> getStates() {
		LinkedHashSet<DFAState> temp = new LinkedHashSet<DFAState>();
		temp.addAll(final_states);
		temp.addAll(non_final_states);
		temp.addAll(all_states);
		all_states = temp;
		return all_states;	
	}

	@Override
	public LinkedHashSet<? extends DFAState> getFinalStates() {
		
		return final_states;
	}

	@Override	
	public DFAState getStartState() {
		
		return startState;
	}

	@Override
	public LinkedHashSet<Character> getABC() {
		
		return abc;
	}

	@Override
	public DFA complement() {		
		DFA comp = new DFA();
		comp.startState = startState;
		comp.final_states = non_final_states;
		comp.non_final_states = final_states;
		comp.abc = abc;
		
		return comp;
	}
	
	@Override
	 public boolean accepts(String s) {
		 boolean accepted = false;
		 char[] inputString = s.toCharArray();
		 DFAState currState =  startState;

		 //iterate over the chars
		 if(! (inputString.length == 1 && inputString[0] == 'e'))
		 {
		 for(char c : inputString)
		 {
			 currState = getToState((DFAState) currState, c);
		 }
		 }
		 if(final_states.contains(currState))
		 {
			 accepted = true;
		 }
		 return accepted;
		 }

	@Override
	public DFAState getToState(DFAState from, char onSymb) {
		return from.getTo(onSymb);
	}
	
	
	/**
	 * Formats the toString of the set to the proper format
	 * 
	 * @param set Set being passed into the string
	 * @return re-formated toString of set
	 */
	private String allStatesProperStringFormat(String set) {
		StringBuilder retVal = new StringBuilder(set.toString() + "  ");
		String currentStatesString = set.toString();
		int retValIndex = 0;
		
		for(int i = 0; i < currentStatesString.length(); i++) {
			if(currentStatesString.charAt(i) == '[') {
				retVal.setCharAt(retValIndex, '{');
				retVal.setCharAt(retValIndex+1, ' ');
				retValIndex += 2;
			} else if(currentStatesString.charAt(i) == ']') {
				retVal.setCharAt(retValIndex+1, '}');
				retVal.setCharAt(retValIndex, ' ');
				retValIndex += 2;
				retVal.delete(retValIndex,retVal.length());
			} else if(currentStatesString.charAt(i) == ',') {
				continue;
			} else {
				retVal.setCharAt(retValIndex, currentStatesString.charAt(i));
				retValIndex++;
			}
		}
		
		return retVal.toString();
	}
	
	@Override
	public String toString() {
		String retVal = "";
		retVal += "Q = ";
		retVal += allStatesProperStringFormat(getStates().toString());
		retVal += '\n';
		 
		retVal += "Sigma = ";
		retVal += allStatesProperStringFormat(getABC().toString());
		retVal += '\n';
		
		retVal += "Delta = " + '\n';
		retVal += "          ";
		for(int i = 0; i < abc.toArray().length; i++) {
			retVal += String.format("%10s", abc.toArray()[i].toString());
			retVal += "";
		}
		retVal += '\n';
		for(DFAState s : all_states) {

			retVal += String.format("%10s", s.toString());
			for(char c : abc)
			{
				retVal += String.format("%10s", s.getTo(c).toString());
			}
			retVal += "\n";
		}
		
		retVal += "q0 = " + startState.getName();
		retVal += '\n';
		
		retVal += "F = ";
		retVal += allStatesProperStringFormat(getFinalStates().toString());
		retVal += '\n';
		
		return retVal;
	}
}
