import java.util.*;

public class SuffixAutomaton {
    static class State {
        int length;
        int link;
        Map<Character, Integer> transitions = new HashMap<>();

        State(int length, int link) {
            this.length = length;
            this.link = link;
        }
    }

    List<State> states;
    int last;

    public SuffixAutomaton(String s) {
        states = new ArrayList<>();
        states.add(new State(0, -1)); // Initial state
        last = 0;

        for (char c : s.toCharArray()) {
            addCharacter(c);
        }
    }

    private void addCharacter(char c) {
        int cur = states.size();
        states.add(new State(states.get(last).length + 1, 0));
        int p = last;

        while (p != -1 && !states.get(p).transitions.containsKey(c)) {
            states.get(p).transitions.put(c, cur);
            p = states.get(p).link;
        }

        if (p == -1) {
            states.get(cur).link = 0;
        } else {
            int q = states.get(p).transitions.get(c);
            if (states.get(p).length + 1 == states.get(q).length) {
                states.get(cur).link = q;
            } else {
                int clone = states.size();
                states.add(new State(states.get(p).length + 1, states.get(q).link));
                states.get(clone).transitions.putAll(states.get(q).transitions);
                while (p != -1 && states.get(p).transitions.get(c) == q) {
                    states.get(p).transitions.put(c, clone);
                    p = states.get(p).link;
                }
                states.get(q).link = clone;
                states.get(cur).link = clone;
            }
        }

        last = cur;
    }

    public boolean containsSubstring(String t) {
        int currentState = 0;
        for (char c : t.toCharArray()) {
            if (!states.get(currentState).transitions.containsKey(c)) {
                return false;
            }
            currentState = states.get(currentState).transitions.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abac";
        SuffixAutomaton automaton = new SuffixAutomaton(s);

        System.out.println(automaton.containsSubstring("ab")); // Output: true
        System.out.println(automaton.containsSubstring("bac")); // Output: true
        System.out.println(automaton.containsSubstring("ac")); // Output: true
        System.out.println(automaton.containsSubstring("abc")); // Output: false
    }
}



/*
A Suffix Automaton is a powerful data structure used in computer science for string processing tasks. 
It represents all substrings of a given string efficiently and allows for fast substring search, 
pattern matching, and other operations. 
The Suffix Automaton of a string is a minimal deterministic finite automaton (DFA) that recognizes
the set of all substrings of that string.

Key Concepts

States: Each state represents a set of substrings that end at that state.
Transitions: Directed edges labeled with characters that indicate state transitions.
Suffix Links: Links from each state to another state representing the longest proper suffix.
Construction of Suffix Automaton
The Suffix Automaton can be built incrementally by processing each character of the string
one by one and updating the automaton to include the new character.

Example of Suffix Automaton
Let's construct a Suffix Automaton for the string "abac".

Steps
Initialize: Start with an initial state (state 0).

Add 'a':
Create a new state (state 1) for "a".
Add a transition from state 0 to state 1 with 'a'.
State 0 is updated to have a suffix link to state 0 (itself).

Add 'b':
Create a new state (state 2) for "ab".
Add a transition from state 1 to state 2 with 'b'.
Add a transition from state 0 to state 2 with 'b'.
Update suffix links.

Add 'a':
Create a new state (state 3) for "aba".
Add a transition from state 2 to state 3 with 'a'.
Add a transition from state 1 to state 3 with 'a'.
Update suffix links.

Add 'c':
Create a new state (state 4) for "abac".
Add a transition from state 3 to state 4 with 'c'.
Add a transition from state 0 to state 4 with 'c'.
Update suffix links.
*/
