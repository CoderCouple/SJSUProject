import java.io.IOException;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

	public class AttributeContentVisitor extends VoidVisitorAdapter<Void> {
		
		@Override
		public void visit(FieldDeclaration n, Void arg) {
			super.visit(n, arg);
			System.out.println(n.toString());
			MainRun.AttributeList.add(n.toString());
			String AttributeDeclaration = n.toString();
			System.out.println(
					n.toString().substring(n.toString().substring(0, n.toString().lastIndexOf(" ")).lastIndexOf(" ") + 1,
							n.toString().lastIndexOf(" ")));
				try {
				FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS,AttributeDeclaration);
				} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
