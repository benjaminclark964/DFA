package fa.dfa;

import java.util.HashSet;
import java.util.Set;
import fa.State;

public class DFA implements DFAInterface {
	
	private State startState;
	private Set<State> all_states;
	private Set<Character> abc;
	private Set<? extends State> states;
	private Set<State> final_states;
	
	public DFA() {
		abc = new HashSet<Character>();
		states = new HashSet<State>();
		all_states = new HashSet<State>();
		final_states = new HashSet<State>();
	}
	
	private State getState(String state) {
		State ret = null;
		
			for(State s : states) {
				if(s.toString().equals(state)) {
					ret = s;
					break;
				}
			}
		
		return ret;
	}

	@Override
	public void addStartState(String s) {
	
		State state = getState(s);
		if(state == null) {
			state = new DFAState(s);
			startState = state;
			addState(s);
		}
	}

	@Override
	public void addState(String name) {
		
		DFAState s = new DFAState(name);
		s.addState(name);
		all_states.add(s);		
	}
	
	

	@Override
	public void addFinalState(String F) {
		
		State state = getState(F);
		
		if(state == null) {
			DFAState finalState = new DFAState(F);
			final_states.add(finalState);
			addState(F);
		}
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<? extends State> getStates() {
		
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
		return null;
	}

	@Override
	public boolean accepts(String s) {
		// TODO Auto-generated method stub
		boolean accepted = false;
		return accepted;
	}

	@Override
	public State getToState(DFAState from, char onSymb) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//add java doc
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
		//add transitions values here
		retVal += '\n';
		
		retVal += "Delta = ";
		//add Delta table here
		retVal += '\n';
		
		retVal += "q0 = " + startState.getName();
		retVal += '\n';
		
		retVal += "F = ";
		retVal += allStatesProperStringFormat(getFinalStates().toString());
		
		return retVal;
	}

}
