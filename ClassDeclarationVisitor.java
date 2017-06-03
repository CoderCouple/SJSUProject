import java.io.FileNotFoundException;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassDeclarationVisitor extends VoidVisitorAdapter<ClassInstance> {
	@Override
	public void visit(ClassOrInterfaceDeclaration n, ClassInstance arg) {
		super.visit(n, arg);
		 System.out.println("Class name "+n.getName());
		 arg.setIsInterface(n.isInterface());
		//MainRun.ClassList.add(n.toString().substring(0, n.toString().indexOf("{")).replaceAll("public ", ""));
		String classDeclaration = n.toString().substring(0, n.toString().indexOf("{") + 1).replaceAll("public ", "");
		String classTokens[] = n.toString().substring(0, n.toString().indexOf("{")).split(" ");
		String className = n.getName().toString();
		//System.out.println(classDeclaration);
		arg.setClassDeclaration(classDeclaration);
		arg.setClassName(className);
		
		//System.out.println(arg.getClassName() +" is an interface "+arg.isInterface());
	}
}
