import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class MainRun {

	// Folder Structure for running the project on LINUX OS

	// Setting Up the local directory structure for reading the source JAVA
	// files (SOURCE_FILE_PATH) and also placing the generated JAVA output files (TARGET_FILE_PATH)
	
	 //public static final String OS_HOME = "/home/sunil28";
	 public static final String OS_HOME = "/home/sunil28";
	 public static final String SOURCE_FILE_PATH = OS_HOME + "/" + "UML_Test";
	 public static final String TARGET_FILE_PATH = OS_HOME + "/" + "UML_Test"+ "/" + "UML_Test_Output";
	 public static final String TARGET_JAVA_FILE = "MyUMLOutput.java";
	 public static final String TARGET_PNG_FILE = "MyUMLOutput.png";
	 public static final String TARGET_JAVA_FILE_ACCESS = TARGET_FILE_PATH +"/" + TARGET_JAVA_FILE;
	 public static final String TARGET_PNG_FILE_ACCESS = TARGET_FILE_PATH +"/" + TARGET_PNG_FILE;

	// Folder Structure for running the project on WINDOWS OS

	// Setting Up the local directory structure for reading the source JAVA
	// files (SOURCE_FILE_PATH) and also placing the generated JAVA output files (TARGET_FILE_PATH)
	//public static final String OS_HOME = "C:\\Users\\admin\\Desktop\\UMLGraph-5.7_2.32-SNAPSHOT";
	//public static final String SOURCE_FILE_PATH = OS_HOME + "\\" + "UML_Test";
	//public static final String TARGET_FILE_PATH = OS_HOME + "\\" + "UML_Test" + "\\" + "UML_Test_Output";
	//public static final String TARGET_JAVA_FILE = "MyUMLOutput.java";
	//public static final String TARGET_PNG_FILE = "MyUMLOutput.png";
	//public static final String TARGET_JAVA_FILE_ACCESS = TARGET_FILE_PATH + "\\" + TARGET_JAVA_FILE;
	//public static final String TARGET_PNG_FILE_ACCESS = TARGET_FILE_PATH + "\\" + TARGET_PNG_FILE;

	// Array list to hold the list of all the classes,methods and attributes present in the the source directory
	public static ArrayList<String> FileListAccess = new ArrayList<String>();
	public static ArrayList<ClassInstance> ClassList = new ArrayList<ClassInstance>();

	public static void main(String[] args){
		FileOperations fJava = new FileOperations(TARGET_JAVA_FILE_ACCESS);
		try {
			fJava.deleteFile();
			FileOperations fPng = new FileOperations(TARGET_PNG_FILE_ACCESS);
			fPng.deleteFile();
			Package.assignClassList(SOURCE_FILE_PATH);
			
			FileOperations fOutput = new FileOperations(TARGET_JAVA_FILE_ACCESS);
			Package.writeOutputFile(fOutput);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

