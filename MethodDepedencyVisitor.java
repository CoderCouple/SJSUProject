import java.io.FileNotFoundException;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class MethodDepedencyVisitor extends VoidVisitorAdapter<Void> {
	@Override
	public void visit(MethodDeclaration n, Void arg) {
		super.visit(n, arg);
		System.out.println(n.getDeclarationAsString().lastIndexOf("("));
		System.out.println(n.getDeclarationAsString().charAt(n.getDeclarationAsString().lastIndexOf(")")));
		String S = n.getDeclarationAsString().substring(n.getDeclarationAsString().lastIndexOf("(") + 1,
				n.getDeclarationAsString().lastIndexOf(")"));
		if (n.getDeclarationAsString().contains("()") || n.getDeclarationAsString().contains("( )")) {
		} else {
			try {
				DependencyVisitor.getMethodDependencyComment(S.substring(0, S.lastIndexOf(" ")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
