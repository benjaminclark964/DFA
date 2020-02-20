package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import fa.State;

public class DFA implements DFAInterface {
	
	private State startState;
	private Set<State> all_states;
	private Set<Character> abc;
	private Set<State> delta;
	private Set<? extends State> states;
	private Set<State> final_states;
	private boolean isFinal;
	
	public DFA() {
		abc = new HashSet<Character>();
		states = new HashSet<State>();
		all_states = new HashSet<State>();
		delta = new HashSet<State>();
		final_states = new HashSet<State>();
		isFinal = false;
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
			//addState(F);
		}
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		abc.add(onSymb);
		DFAState transState = new DFAState(toState);
		DFAState trans = new DFAState(fromState);
		trans.addTransition(onSymb, transState);
		delta.add(trans);
	}

	@Override
	public Set<? extends State> getStates() {
		all_states.addAll(getFinalStates());
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
		return from.getTo(onSymb);
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
	
	// add java doc
	private String deltaProperStringFormat(String delta) {
		String ret = "";
		for(int i = 0;  i < delta.length(); i++) {
			if(delta.charAt(i) == '[' || delta.charAt(i) == ']' || delta.charAt(i) == ',') {
				
				continue;
				
			} else if(delta.charAt(i) >= 'a' && delta.charAt(i) <= 'z') {
				
				ret += delta.charAt(i);
				ret += '\n';
				
			} else {
				
				ret += delta.charAt(i);
				
			}
		}
		return ret;
	}
	
	@Override
	public String toString() {
		String retVal = "";
		retVal += delta.toString() + '\n';
		retVal += "Q = ";
		retVal += allStatesProperStringFormat(getStates().toString());	// returns proper states, but not proper format.
		retVal += '\n';
		 
		retVal += "Sigma = ";
		retVal += allStatesProperStringFormat(getABC().toString());
		retVal += '\n';
		
		retVal += "Delta = " + '\n';
		retVal += "  " + deltaProperStringFormat(getABC().toString()) + '\n';
		retVal += deltaProperStringFormat(getStates().toString());
		retVal += '\n';
		
		retVal += "q0 = " + startState.getName();
		retVal += '\n';
		
		retVal += "F = ";
		retVal += allStatesProperStringFormat(getFinalStates().toString());
		
		return retVal;
	}
}
