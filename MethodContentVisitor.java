import java.io.IOException;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodContentVisitor extends VoidVisitorAdapter<ClassInstance> {
	@Override
	public void visit(MethodCallExpr cu, ClassInstance classInstance) {
		super.visit(cu, classInstance);
		System.out.println(cu.getName());
		System.out.println("parent method: " + cu.getParentNode().get().getParentNode().get().getParentNode().get().getChildNodes().get(0).toString());
		System.out.println("new "+ cu.toString());
		
}
}