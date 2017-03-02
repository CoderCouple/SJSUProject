import java.io.FileNotFoundException;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class AttributeDependencyVisitor extends VoidVisitorAdapter<Void> {

	@Override
	public void visit(FieldDeclaration n, Void arg) {
		super.visit(n, arg);
		System.out.println(n.toString());
		//MainRun.AttributeList.add(n.toString());
		String AttributeDeclaration = n.toString();
		//System.out.println(
		//		n.toString().substring(n.toString().substring(0, n.toString().lastIndexOf(" ")).lastIndexOf(" ") + 1,
		//				n.toString().lastIndexOf(" ")));
		String AttributeType = n.toString().substring(
				n.toString().substring(0, n.toString().lastIndexOf(" ")).lastIndexOf(" ") + 1,
				n.toString().lastIndexOf(" "));

		if (AttributeType.contains("<")) {
			AttributeType = AttributeType.substring(0, AttributeType.lastIndexOf("<"));
			System.out.println(AttributeType);
			try {
				DependencyVisitor.getAttributeDependencyComment(AttributeType, AttributeDeclaration);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (AttributeType.contains("[")) {
			AttributeType = AttributeType.substring(0, AttributeType.lastIndexOf("["));
			System.out.println(AttributeType);
			try {
				DependencyVisitor.getAttributeDependencyComment(AttributeType, AttributeDeclaration);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				DependencyVisitor.getAttributeDependencyComment(AttributeType, AttributeDeclaration);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}