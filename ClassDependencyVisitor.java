import java.io.FileNotFoundException;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassDependencyVisitor extends VoidVisitorAdapter<Object> {
	@Override
	public void visit(ClassOrInterfaceDeclaration n, Object arg) {
		super.visit(n, arg);
		// System.out.println(n.isInterface());
		
		String ClassType = null;
		if (n.isInterface()) {
			ClassType = "I";
			MainRun.MethodEndSpecifier = ";";
		} else {
			ClassType = "C";
			MainRun.MethodEndSpecifier = "";
		}
		//MainRun.ClassList.add(n.toString().substring(0, n.toString().indexOf("{")).replaceAll("public ", ""));
		System.out.println(n.toString().substring(0, n.toString().indexOf("{")).replaceAll("public ", ""));
		String ClassDeclaration = n.toString().substring(0, n.toString().indexOf("{")).replaceAll("public ", "");
		try {
			DependencyVisitor.getClassDependencyComment(ClassType, ClassDeclaration);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
