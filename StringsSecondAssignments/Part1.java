
/**
 * Finding Many Genes:  this assignment is to write the code from
 * the lession to make the following improvements:
 * A. Find a gene in a strand of DNA where the stop codon is one of
 *    "TAA", "TAG" or "TGA".  Previously only looked for "TAA".
 * B. Find all the genes (where any of the 3 stop codons are used)
 *    in a strand of DNA.
 * 
 * @author Jeff Brown
 * @version 1
 */
public class Part1 {
    String START_CODON = "ATG";

    /** Locate the next valid stop codon after `startIndex` in `dna`.
     * @param dna   the DNA strand to search
     * @param startIndex   where to start searching
     * @param stopCodon    the codon to search for.
     * @returns the index of the first occurrence of `stopCodon`
     * past `startIndex` that is a multiple of 3 away from `startCodon`.
     * If not found, return dna.length() (which works well with searching
     * for a minimum value, as dna.length() is always greater than any
     * possible stop codon index.)
     */
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        // Check parameters.  If any are not valid, return "not found"
        // (which is dna.length()).
        if (dna.isEmpty() || stopCodon.isEmpty() || startIndex < 0 || startIndex >= dna.length()) {
            return dna.length();
        }
        
        // Keep searching for stop codons until none found.  When
        // one is found, if it is a multiple of 3 away from startIndex
        // return that index.  If not, keep searching at the next letter.
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) { // found
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        
        // Not found, so return the dna's length
        return dna.length();
    }
    
    private void testFSC(String desc, String dna, int startIndex, String stopCodon, int expected) {
        int index = findStopCodon(dna, startIndex, stopCodon);
        if (index == expected) {
            System.out.println("PASS: "+desc+": dna='"+dna+"'");
        } else {
            System.out.println("FAIL: "+desc+": dna='"+dna+"' expected "+expected+", got "+index);
        }
    }
    
    /** Test driver for findStopCodon(). */
    public void testFindStopCodon () {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        testFSC("test 1", dna, 0, "TAA", 9);
        testFSC("test 2", dna, 9, "TAA", 21);
        testFSC("test 3", dna, 1, "TAA", dna.length());
        testFSC("test 4", dna, 0, "TAG", dna.length());
        testFSC("empty dna", "", 0, "TAG", 0);
        testFSC("empty stop codon", dna, 0, "", dna.length());
        testFSC("negative start index", dna, -1, "TAA", dna.length());
        testFSC("start index too large", dna, dna.length(), "TAA", dna.length());
        testFSC("smallest gene", "ATGTAA", 0, "TAA", 3);
        testFSC("2 TAA, one not in right place", "ATGCTAACCTAA", 0, "TAA", 9);
        testFSC("multiple stop codons TAG", "ATGTAGTGATAA", 0, "TAG", 3);
        testFSC("multiple stop codons TGA", "ATGTAGTGATAA", 0, "TGA", 6);
        testFSC("multiple stop codons TAA", "ATGTAGTGATAA", 0, "TAA", 9);
        System.out.println("tests completed");
    }
    
    /** Locates the next valid gene sequence in `dna` and returns it.
     * @param dna   the DNA strand to search
     * @returns a valid gene sequence or "" if `dna` does not contain a gene.
     */
    public String findGene (String dna) {
        
        // Not found, return "".
        return "";
    }
    
    private void testFG(String desc, String dna, String expected) {
       String gene = findGene(dna);
        if (gene.equals(expected)) {
            System.out.println("PASS: "+desc+": dna='"+dna+"'");
        } else {
            System.out.println("FAIL: "+desc+": dna='"+dna+"' expected '"+expected+"', got '"+gene+"'");
        }
    }
    /** Test driver for findGene() */
    public void testFindGene() {
        testFG("smallest gene", "ATGTAA", "ATGTAA");
        testFG("2 TAA, one not in right place", "ATGCTAACCTAA", "ATGCTAACCTAA");
        testFG("multiple stop codons TGA", "ATGTAGTGATAA", "ATGTAG");
        System.out.println("tests completed");
    }
    
    /** Given a DNA strand `dna`, print each gene it contains to
     * standard output.
     * @param dna   the DNA strand to search
     */
    public void printAllGenes (String dna) {
    }
}
