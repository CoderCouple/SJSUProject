import java.io.IOException;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ImportVisitor extends VoidVisitorAdapter<ClassInstance> {
	@Override
	public void visit(ImportDeclaration n, ClassInstance arg) {
		//System.out.println("trying:" +arg.toString());
		super.visit(n, arg);
		//System.out.println(n.toString());
		arg.setImportStatements(n.toString());
	}
}