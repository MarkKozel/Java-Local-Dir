import java.io.File;

/**
 * Utility class to get String and File reference to the local directory in which
 * the class is executing<BR>
 * CHANGE HISTORY:
 * <TABLE BORDER="1" cellpadding=8>
 * <TR ALIGN = "right">
 * <TD>None</TD>
 * <TD>December 16, 2002</TD>
 * <TD>Mark Kozel</TD>
 * <TD>Created LocalDirectory class</TD>
 * </TR>
 * <TR ALIGN = "right">
 * <TD></TD>
 * <TD></TD>
 * <TD></TD>
 * <TD></TD>
 * </TR>
 * </TABLE>
 * <BR>
 *
 * @author Mark Kozel
 * @version 1.4.2_03
 */
public class LocalDirectory
{
	/**
	 * Holder for parent object reference
	 */
	private Object parentClassRef;

	/**
	 * Creates a LocalDirectory object with a reference to the location of the
	 * LocalDirectory object
	 *
	 * @author Mark Kozel
	 */
	public LocalDirectory()
	{
		parentClassRef = null;
	}

	/**
	 * Creates a LocalDirectory object with a reference to the parents location
	 * rather than the location of the LocalDirectory object
	 *
	 * @param parentObj
	 *            Reference to the class that is using the LocalDirectory
	 *            object.
	 *
	 * @author Mark Kozel
	 */
	public LocalDirectory(final Object parentObj)
	{
		parentClassRef = parentObj;
	}

	/**
	 * Returns the disk file name of the class that is executing.
	 *
	 * @return Name of class that is currently executing
	 * @author Mark Kozel
	 */
	private String getClassName()
	{
		String thisClassName = null;

		if (parentClassRef != null)
		{
			// Process for parent object reference available
			// Build a string with parent class's name
			thisClassName = parentClassRef.getClass().getName();
		} else
		{
			// Process if parent object not available
			// Build a string with LocalDirectory class's name
			thisClassName = super.getClass().getName();
		}

		thisClassName = thisClassName.substring(thisClassName.lastIndexOf(".") + 1, thisClassName.length());
		thisClassName += ".class"; // this is the name of the bytecode file that is executing

		return thisClassName;
	}

	/**
	 * Returns the name of the local directory based on the results of a call to
	 * getClassName()
	 *
	 * @return Name of directory that contains the executing class
	 * @author Mark Kozel
	 */
	public String getLocalDirName()
	{
		String localDirName;
		java.net.URL fileURL = null;

		if (parentClassRef != null)
		{
			// Use that name to get a URL to the directory we are executing in
			fileURL = parentClassRef.getClass().getResource(getClassName()); // Open
																				// a
																				// URL
																				// to
																				// the
																				// parent
																				// .class
																				// file
		} else
		{
			// Use that name to get a URL to the directory we are executing in
			fileURL = super.getClass().getResource(getClassName()); // Open a
																	// URL to
																	// the
																	// LocalDirectory
																	// .class
																	// file
		}

		// Clean up the URL and make a String with absolute path name
		localDirName = fileURL.getPath(); // Strip path to URL object out
		localDirName = fileURL.getPath().replaceAll("%20", " "); // change %20
																	// chars to
																	// spaces

		// Get the current execution directory
		localDirName = localDirName.substring(0, localDirName.lastIndexOf("/")); // clean
																					// off
																					// the
																					// file
																					// name

		return localDirName;
	}

	/**
	 * Returns a File reference to the local directory based on the results of a
	 * call to getClassName()
	 *
	 * @return File object that points to the local directory
	 * @author Mark Kozel
	 */
	public java.io.File getLocalDirRef()
	{
		File fileObj;
		// Make the file object and return it
		fileObj = new File(getLocalDirName());
		return fileObj;
	}

}
