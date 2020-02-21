package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import fa.State;

public class DFA implements DFAInterface {
	
	private State startState;
	private DFAState currentState;
	private Set<State> all_states;
	private Set<Character> abc;
	private Set<State> non_final_states;
	//private Set<? extends State> states;
	private Set<State> final_states;
	//private boolean isFinal;
	
	public DFA() {
		abc = new HashSet<Character>();
		//states = new HashSet<State>();
		all_states = new HashSet<State>();
		final_states = new HashSet<State>();
		non_final_states = new HashSet<State>();
		//isFinal = false;
	}
	
	private DFAState getState(String state) {
		State ret = null;
		
			for(State s : getStates()) {
				if(s.toString().equals(state)) {
					ret = s;
					break;
				}
			}
		
		return (DFAState) ret;
	}

	@Override
	public void addStartState(String s) {
	
		//State state = getState(s);
		//if(state == null) {
			State state = new DFAState(s);
			startState = state;
			all_states.add(state);
			non_final_states.add(state);
			currentState = (DFAState)startState;
			//addState(s);
		//}
	}

	@Override
	public void addState(String name) {
		
		DFAState s = new DFAState(name);
		s.addState(name);
		non_final_states.add(s);
		all_states.add(s);
	}
	
	

	@Override
	public void addFinalState(String F) {
		
		//State state = getState(F);
		
		//if(state == null) {
			DFAState finalState = new DFAState(F);
			
			final_states.add(finalState);
		//}
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		abc.add(onSymb);
		getState(fromState).addTransition(onSymb, getState(toState));
	}

	@Override
	public Set<? extends State> getStates() {
		all_states.addAll(final_states);
		return all_states;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		
		return final_states;
	}

	@Override	
	public State getStartState() {
		
		return startState;
	}

	@Override
	public Set<Character> getABC() {
		// TODO Auto-generated method stub
		return abc;
	}

	@Override
	public DFA complement() {
		// TODO Auto-generated method stub
		
		DFA comp = new DFA();
		comp.startState = startState;
		comp.final_states = non_final_states;
		comp.non_final_states = final_states;
		comp.abc = abc;
		
		return comp;
	}

//	@Override
//	public boolean accepts(String s) {
//		// TODO Auto-generated method stub
//		boolean accepted = false;
//		
//		char[] input = s.toCharArray();
//		
//		// Navigate through DFA
//		for(char c : input) {
//			currentState = (DFAState) getToState(currentState, c);
//		}
//		
//		if(currentState != null) {
//			accepted = true;
//		}
//		
//		// Checks if current State ended on a final state
//		for(State state : final_states) {
//			if(currentState == state) {
//				accepted = true;
//			} 
//		}
//		
//		
//		return accepted;
//	}
	
	 public boolean accepts(String s) {
		 boolean accepted = false;
		 char[] inputString = s.toCharArray();
		 State currState =  startState;

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
	public State getToState(DFAState from, char onSymb) {
		return from.getTo(onSymb);
	}
	
	
	/**
	 * Formats the toString of the set of the proper format
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
		retVal += allStatesProperStringFormat(getStates().toString());	// returns proper states, but not proper format.
		retVal += '\n';
		 
		retVal += "Sigma = ";
		retVal += allStatesProperStringFormat(getABC().toString());
		retVal += '\n';
		
		retVal += "Delta = " + '\n';
		retVal += "     ";
		for(int i = 0; i < abc.toArray().length; i++) {
			retVal += abc.toArray()[i].toString();
			retVal += " ";
		}
		retVal += '\n';
		for(State s : all_states) {
			retVal += s.toString();
			retVal += '\n';
		}
		
		retVal += "q0 = " + startState.getName();
		retVal += '\n';
		
		retVal += "F = ";
		retVal += allStatesProperStringFormat(getFinalStates().toString());
		retVal += '\n';
		
		return retVal;
	}
}
