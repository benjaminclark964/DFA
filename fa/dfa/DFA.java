package fa.dfa;

import java.util.HashSet;
import java.util.Set;

import fa.State;

public class DFA implements DFAInterface {
	
	private String finalStates;
	private String startState;
	private Set<String> allStates;
	private String transitions;
	private Set<Character> abc;
	
	public DFA() {
		allStates = new HashSet<String>();
		finalStates = "";
		startState = "";
		abc = new HashSet<Character>();
	}

	@Override
	public void addStartState(String s) {
		// TODO Auto-generated method stub
		startState = s;
		allStates.add(s);
	}

	@Override
	public void addState(String name) {
		// TODO Auto-generated method stub
		allStates.add(name);
	}

	@Override
	public void addFinalState(String F) {
		// TODO Auto-generated method stub
		finalStates += F;
		allStates.add(F);
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<? extends State> getStates() {
		// TODO Auto-generated method stub
		//return allStates;
		return null;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		// TODO Auto-generated method stub
		
		//return finalStates; 
		return null;
	}

	@Override
	public State getStartState() {
		// TODO Auto-generated method stub
		return null;
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
	private String allStatesProperStringFormat(Set<String> allStates) {
		StringBuilder retVal = new StringBuilder(allStates.toString() + "  ");
		String currentStatesString = allStates.toString();
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
		retVal += allStatesProperStringFormat(allStates);
		retVal += '\n';
		
		retVal += "Sigma = ";
		//add transitions values here
		retVal += '\n';
		
		retVal += "Delta = ";
		//add Delta table here
		retVal += '\n';
		
		retVal += "q0 = " + startState;
		retVal += '\n';
		
		retVal += "F = ";
		retVal += "{ " + finalStates.toString() + " }";
		
		return retVal;
	}

}
