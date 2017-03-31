import java.io.IOException;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class ClassContentVisitor extends VoidVisitorAdapter<Object> {
	@Override
	public void visit(ClassOrInterfaceDeclaration n, Object arg) {
		super.visit(n, arg);
		MainRun.ClassList.add(n.toString().substring(0, n.toString().indexOf("{")).replaceAll("public ", ""));
		System.out.println(n.toString().substring(0, n.toString().indexOf("{")).replaceAll("public ", ""));
		//String ClassDeclaration = n.toString().substring(0, n.toString().indexOf("{")).replaceAll("public ", "");
		String FileContent=n.toString().substring(0, n.toString().indexOf("{")).replaceAll("public ", "");
		try {
			FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS,FileContent);
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	
	}
}
