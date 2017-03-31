import java.io.IOException;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodContentVisitor extends VoidVisitorAdapter<Void> {
	@Override
	public void visit(MethodDeclaration n, Void arg) {
		super.visit(n, arg);
	//System.out.println(n.getDeclarationAsString());
		
		 MainRun.MethodList.add(n.toString());
		 String MethodContent=n.toString();
		 //String MethodContent=n.getDeclarationAsString().substring(n.getDeclarationAsString().lastIndexOf("(")+1,n.getDeclarationAsString().lastIndexOf(")"))+MainRun.MethodEndSpecifier;
		 //System.out.println(S.substring(0,S.lastIndexOf(" ")));
		 try {
				FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, MethodContent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
