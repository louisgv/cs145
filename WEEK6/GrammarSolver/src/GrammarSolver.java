import java.util.*;

/**
 * This class transforms Grammar rules into Random sentences.
 *
 *  It first read in the rule file, extract data into a map
 *  with non_terminal as key, and values as associated rules
 *  for each non_terminal.
 *
 *  Upon receiving a symbol, the object pre-process the symbol,
 *  strip it from non-rule characters, then compares it with
 *  the set of key to generate the random sentence.
 *
 *  User can generate sentence without the '<' and '>' brackets
 * <ul>
 * <li> Name: GrammarSolver.java
 * <li> Description: Grammar Solver
 * <li> Class: Java 145
 * <li> Instructor: Ken Hang && Janet Ash
 * <li> Date: March 1 2015
 * </ul>
 * @author  Hai H Nguyen (Bill)
 * @version Winter 2015
 */
public class GrammarSolver {
    private static final String TERM_SEPARATOR = "::=";

    private static final String NON_TERM_REGEX = "(<|>)+";

    private static final String RULE_REGEX = "(\\|\\B)|(\\|\\b)+";

    private static final String TOKEN_REGEX = "[ \t]+";

    private Map<String, String[]> grammarRulesMap = new TreeMap<String, String[]>();

    /**
     * Constructor, given a rule set, does the Following:
     * <ul>
     * <li> If rule set is null or empty, throw IllegalArgumentException
     * <li> If the grammar definition is duplicated, throw IllegalArgumentException
     * <li> Initializes a new grammar solver
     * <ul/>
     * @param rules                         BNF grammar rules
     * @throws IllegalArgumentException     If rules List is Empty or Duplicate Found
     */
    public GrammarSolver(List<String> rules) {
        if (rules == null || rules.isEmpty()) {
            throw new IllegalArgumentException("List is Empty!");
        } else {
            for(String str : rules){
                extractGrammarRule(str);
            }
        }
    }

    /**
     * @param rule                          A line from the Grammar file
     * @throws IllegalArgumentException     If Duplicate Found
     */
    private void extractGrammarRule(String rule){
        String[] data = rule.split(TERM_SEPARATOR);

        String key = bracketedSymbol(data[0]);

        String[] value = data[1].replaceFirst("^\\|", "").trim().split(RULE_REGEX);

        if(contains(key)){
            throw new IllegalArgumentException("Duplicated Non-terminal!");
        } else {
            grammarRulesMap.put(key, value);
        }
    }

    /**
     * Check if a Symbol is Non-terminal or not.
     * @param symbol                        Symbol to be check
     * @return                              True if symbol is a non-terminal
     * @throws IllegalArgumentException     If Symbol is empty
     */
    public boolean contains (String symbol) {
        if (symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol is Empty!");
        } else {
            return getSymbols().contains(bracketedSymbol(symbol));
        }
    }

    /**
     * @return              A set of Non-terminals from the maps
     */
    public Set<String> getSymbols() {
        return grammarRulesMap.keySet();
    }

    /**
     * Generate Sentences with the given Non-terminal
     * @param symbol        Non-terminal to look for rules
     * @return              A Random sentence
     */
    public String generate(String symbol){
        if (contains(symbol)){
            // Get the Rules associated with the symbol
            String[] values = grammarRulesMap.get(bracketedSymbol(symbol));
            // Extract one random Rule and Split it into symbols
            String[] symbols = values[randomIndex(values.length)].trim().split(TOKEN_REGEX);
            // Initialize output using the first symbol
            String out = generate(symbols[0]);
            // Implement the sentence, treating each symbols as non-terminal
            for (int i = 1; i < symbols.length; ++i ) {
                out += " " + generate(symbols[i]);
            }
            // Return the sentence
            return out;
        } else { // If it is a Terminal, Return it directly
            return symbol;
        }
    }

    /**
     * @param symbol    Symbol to be Bracketed
     * @return          Symbol with brackets
     */
    private String bracketedSymbol(String symbol){
        return "<" + simplifiedSymbol(symbol) + ">";
    }

    /**
     * @param symbol    Symbol to be simplified
     * @return          Symbol without non-words
     */
    private String simplifiedSymbol(String symbol){
        return symbol.replaceAll(NON_TERM_REGEX, "").trim();
    }

    /**
     * @param bound     Upper bound Exclusive bound
     * @return          Random Index within the bound
     */
    private int randomIndex (int bound){
        Random randomVault = new Random();

        return randomVault.nextInt(bound);
    }
} // IS29