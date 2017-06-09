import java.io.FileNotFoundException;
import java.io.IOException;

public class UMLGraphCommentGenerator {

	public static String startUMLGraphComment() {
			return "/**";
	}
	
	public static String defaultUMLGraphComment() {
			return "*@opt all";
	}
	public static String endUMLGraphComment() {
		return " */";
	}

	public static String getInterfaceUMLComment() {
		return " *@opt inferrel\n* @opt inferdep\n* @opt inferdepinpackage";
	}

	public static String getMethodDependencyComment(String MethodParameter) throws FileNotFoundException {
		
		String str ="";
		for (int i = 0; i < MainRun.FileListAccess.size(); i++) {
			if (MainRun.FileListAccess.get(i).equals(MethodParameter)) {
				str += "/** @depend - <uses> - " + MethodParameter + " */";}
		}
	    return str;
	}

	public static String notConstructorUMLComment() {
		return "*@opt !constructors";
	}

	/*public static void getAttributeAssociationComment(String AttributeType, String AttributeDeclaration,String ParentClassNameoftheAttribute)
			throws FileNotFoundException {
			
		System.out.println("Declaration 1:"+AttributeDeclaration);
		System.out.println("Declaration 2:"+ (AttributeDeclaration.contains("<") || AttributeDeclaration.contains("[")));
		
		if (AttributeDeclaration.contains("<") || AttributeDeclaration.contains("[") ) {
			System.out.println("Declaration Level 1:"+AttributeDeclaration);
			for (int i = 0; i < MainRun.FileListAcess.size(); i++) {
				System.out.println("Declaration Level 1/2:"+MainRun.FileListAcess.get(i)+" "+MainRun.FileListAcess.get(i).equals(ParentClassNameoftheAttribute)+" "+ParentClassNameoftheAttribute);
				if (MainRun.FileListAcess.get(i).equals(ParentClassNameoftheAttribute)) {
					System.out.println("Declaration level 2:"+AttributeDeclaration);
					try {
						
						FileOperations.writeToFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, " *@assocs 1 - * " + AttributeType + "");
						} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
		}else{
		for (int i = 0; i < MainRun.FileListAcess.size(); i++) {
			if (MainRun.FileListAcess.get(i).equals(ParentClassNameoftheAttribute)) {
				try {
					FileOperations.writeToFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, " *@assocs 1 - 1 " + AttributeType+"");
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

	}
  }*/
}
