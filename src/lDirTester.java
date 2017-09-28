/*
 * lDirTester.java
 *
 * Displays two of localDir's capabilities, Getting class name (getClassName()) and
 * getting a reference to a File object that points to the local directory
 *
 * Created on December 16, 2002
 */

import java.io.*;
/**
 *
 * @author  Mark Kozel
 * @version 1.4.1
 */
public class lDirTester
{
   
   /** Creates a new instance of lDirTester */
   public lDirTester()
   {
   }
   
   /**
    * @param args the command line arguments
    */
   public static void main(String[] args)
   {
      String newFileLine = null;
      
      System.out.println(System.getProperty("user.dir"));
      //Make localDir object and call it's getClassName() method
      LocalDirectory myLd = new LocalDirectory();
      System.out.println("Class Name = " + myLd.getClass().getName());
      
      //Use the LocalDirectory object to open and print to console the source
      // code for LocalDirectory
      File myFileRef = null;
      BufferedReader myBr = null;
      FileReader myFr = null;
      
      //use localDir's getLocalDirRef to make a File Obj
      myFileRef = myLd.getLocalDirRef();  
      
      try
      {
         myFr = new FileReader(myFileRef.getPath() + "\\..\\src\\\\LocalDirectory.java");
         myBr = new BufferedReader(myFr);
         
         //read first line
         newFileLine = myBr.readLine();
         while(newFileLine != null)
         {
            System.out.println(newFileLine);
            newFileLine = myBr.readLine();
         }//end while
      }//end try
      catch (IOException ioEx)
      {
         System.out.println("ERROR: " + ioEx.getMessage());
      }//end catch

   }//end main
   
}//end lDirTester class
