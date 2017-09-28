import java.io.File;

public class RunLocalDir
{

	public static void main(String[] args)
	{
		LocalDirectory localDir = new LocalDirectory();
		System.out.println("Running the the " + localDir.getLocalDirName() + " directory");

		File localDirFileHandle = localDir.getLocalDirRef();
		if (localDirFileHandle.isDirectory())
		{
			System.out.println("Is a Directory");
			String[] fileList = localDirFileHandle.list();

			System.out.println("Files in this Directory are:");
			for (int counter = 0; counter < fileList.length; ++counter)
			{
				System.out.println("   " + fileList[counter]);
			}
		} else
		{
			System.out.println("Is not a Directory");
		}

		System.out.println("Here is a handy URI: " + localDirFileHandle.toURI().toString());

		//Cleanup
		localDir = null;
		localDirFileHandle = null;
		System.gc();
	}

}
