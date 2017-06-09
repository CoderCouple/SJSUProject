import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;


public class Method {
	String methodDeclaration;
	boolean isGetSet = false;
	List<MethodCalls> calls = new ArrayList<MethodCalls>();
	

	public void setDeclaration(String declarationAsString) {
		methodDeclaration =  declarationAsString;
	}

	public void writeClassContent(FileOperations f, boolean isInterface) throws IOException {
	 if(!isGetSet)
	 {
		if(isInterface)
		{
			f.writeToFileContent(methodDeclaration);
			f.writeToFileContent(";");
		}
		else
		{
			f.writeToFileContent(methodDeclaration);
			f.writeToFileContent("{ }");
		}
	 }
	}

	public void setGetSet()
	{
		isGetSet = true;
	}
}
