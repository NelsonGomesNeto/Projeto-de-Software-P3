package MainPackage;

/**
 * Represents things that vary with the passing of time.
 * 
 * @author Curt Clifton. Created Jan 22, 2011.
 */
public interface Temporal {

	/**
	 * Signals to this object that an "moment" of time has passed and the object
	 * should update to its next state in time.
	 */
	void timePassed();

	/**
	 * Signals to this object that it's useful life is over.
	 */
	void die();

}
