import java.io.File;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;


public class MainRun {
	public static final String OS_HOME = "/home/sunil28";
	public static final String SOURCE_FILE_PATH = OS_HOME + "/" + "UML_Test";
	public static final String TARGET_FILE_PATH = OS_HOME + "/" + "UML_Test" + "/" + "UML_Test_Output";
	public static final String TARGET_JAVA_FILE = "MyUMLOutput.java";
	public static final String TARGET_PNG_FILE = "MyUMLOutput.png";
	public static final String TARGET_JAVA_FILE_ACCESS = TARGET_FILE_PATH + "/" + TARGET_JAVA_FILE;
	public static final String TARGET_PNG_FILE_ACCESS = TARGET_FILE_PATH + "/" + TARGET_PNG_FILE;
	
	public static String MethodEndSpecifier = "";
	public static ArrayList<String> FileListAcess=new ArrayList<String>();
	public static ArrayList<String> MethodList = new ArrayList<String>();
	public static ArrayList<String> AttributeList = new ArrayList<String>();
	public static ArrayList<String> ClassList = new ArrayList<String>();
	


	public static void main(String[] args) throws Exception {
		FileOperations.readListContent(SOURCE_FILE_PATH);
		
		FileOperations.deleteFile(TARGET_JAVA_FILE_ACCESS);
		FileOperations.deleteFile(TARGET_PNG_FILE_ACCESS);

		File ImportFile = new File(SOURCE_FILE_PATH);		
		for (final File child : ImportFile.listFiles()) {

				if (!child.isDirectory() && FileOperations.getFileExtension(child).toLowerCase().equals("java")) {
				CompilationUnit cu = JavaParser.parse(child);

				new ImportVisitor().visit(cu, null);
			}
		}

		
		File SourceFile = new File(SOURCE_FILE_PATH);		
		for (final File child : SourceFile.listFiles()) {

				if (!child.isDirectory() && FileOperations.getFileExtension(child).toLowerCase().equals("java")) {
				CompilationUnit cu = JavaParser.parse(child);
				
				DependencyVisitor.startDepencyComment();
				DependencyVisitor.defaultDepencyComment();
				new ClassDependencyVisitor().visit(cu, null);
				new AttributeDependencyVisitor().visit(cu, null);
				new MethodDepedencyVisitor().visit(cu, null);
				DependencyVisitor.endDepencyComment();
				
				new ClassContentVisitor().visit(cu, null);
				FileOperations.classStart();
				new AttributeContentVisitor().visit(cu, null);
				new MethodContentVisitor().visit(cu, null);
				FileOperations.classEnd();

			}
		}

		
		
	}



	
}

