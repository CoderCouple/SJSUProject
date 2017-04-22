import java.io.IOException;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class ImportVisitor extends VoidVisitorAdapter<Object> {
	@Override
	public void visit(ImportDeclaration n, Object arg) {
		super.visit(n, arg);
		System.out.println(n.toString());
		String FileContent=n.toString();
		try {
			FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS,FileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
