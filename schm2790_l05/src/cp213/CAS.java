package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author
 * @version 2022-02-25
 */
public class CAS extends Professor {

    protected String term = null;

    public CAS(final String lastName, final String firstName, final String department, final String term) {
	super(lastName, firstName, department);
	this.term = term;

    }

    /**
     * getter for term
     * 
     * @return term
     */

    public String getTerm() {
	return this.term;
    }

    /**
     * Creates formatted string version of CAS.
     */
    @Override
    public String toString() {
	return (super.toString() + "\nTerm: " + this.term);
    }

}
