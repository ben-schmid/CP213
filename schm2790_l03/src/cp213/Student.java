package cp213;

import java.time.LocalDate;

/**
 * Student class definition.
 *
 * @author your name here
 * @version 2022-01-17
 */
public class Student implements Comparable<Student> {

    // Attributes
    private LocalDate birthDate = null;
    private String forename = "";
    private int id = 0;
    private String surname = "";

    /**
     * Instantiates a Student object.
     *
     * @param id        student ID number
     * @param surname   student surname
     * @param forename  name of forename
     * @param birthDate birthDate in 'YYYY-MM-DD' format
     */

    // constructor
    public Student(int id, String surname, String forename, LocalDate birthDate) {

	// assign attributes here
	this.id = id;
	this.surname = surname;
	this.forename = forename;
	this.birthDate = birthDate;
	return;

    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of student data.
     */
    @Override
    public String toString() {

	return String.format("Name: %11s, %4s \nID: %14s \nBirthdate: %11s", surname, forename, id, birthDate);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Student target) {
	int result = 0;

	result = surname.compareTo(target.surname);
	if (result == 0) {
	    result = forename.compareTo(target.forename);
	    if (result == 0) {
		result = Integer.compare(id, target.id);
	    }
	}

	return result;

    }

    public String getForename() {
	return forename;
    }

    public String getSurname() {
	return surname;
    }

    public int getId() {
	return id;
    }

    public LocalDate getBirthDate() {
	return birthDate;
    }

    public void setForename(String forename) {
	this.forename = forename;
    }

    public void setSurname(String surname) {
	this.surname = surname;

    }

    public void setId(int id) {
	this.id = id;

    }

    public void setBirthDate(LocalDate birthDate) {
	this.birthDate = birthDate;
    }

}
