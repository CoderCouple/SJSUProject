import java.io.FileNotFoundException;
import java.io.IOException;

public class DependencyVisitor {

	public static void startDepencyComment() {
		try {
			FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, "/**");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	public static void defaultDepencyComment() {
		try {
			FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, " *@opt all");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void endDepencyComment() {
		try {
			FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, " */");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getClassDependencyComment(String ClassType, String ClassDeclaration) throws FileNotFoundException {

		if (ClassDeclaration.contains("extends") && ClassDeclaration.contains("implements")) {
			try {
				FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, "  * @opt inferrel");
				FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, "  * @opt inferdep");
				FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, "  * @opt inferdepinpackage/");
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(" * @opt inferdepinpackage ");
			System.out.println(" * @opt inferrel");
			System.out.println(" * @opt inferdep ");
			}else if (ClassDeclaration.contains("extends") && !ClassDeclaration.contains("implements")) {
				try {
					FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, "  * @opt all ");
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println("/** @opt all */");
		} else if (!ClassDeclaration.contains("extends") && ClassDeclaration.contains("implements")) {
			try {
				FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, "  * @opt inferrel");
				FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, "  * @opt inferdep");
				FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, "  * @opt inferdepinpackage/");
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("/**  * @opt inferrel");
			System.out.println("* @opt inferdep ");
			System.out.println("* @opt inferdepinpackage */");
		}

	}

	public static void getMethodDependencyComment(String MethodParameter) throws FileNotFoundException {
		for (int i = 0; i < MainRun.FileListAcess.size(); i++) {
			if (MainRun.FileListAcess.get(i).equals(MethodParameter)) {
				System.out.println("/** @depend - <uses> - " + MethodParameter + " */");
				try {
					FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, " * @depend - <uses> - " + MethodParameter + "");
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	public static void getAttributeDependencyComment(String AttributeType, String AttributeDeclaration)
			throws FileNotFoundException {

		if (AttributeDeclaration.contains("<") || AttributeDeclaration.contains("[")) {
			for (int i = 0; i < MainRun.FileListAcess.size(); i++) {
				if (MainRun.FileListAcess.get(i).equals(AttributeType)) {
					try {
						FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, "  * @composed 1 Has 1..* " + AttributeType + "");
						} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					System.out.println("/** @composed 1 Has 1..* " + AttributeType + " */");
				}
			}
		}
		for (int i = 0; i < MainRun.FileListAcess.size(); i++) {
			if (MainRun.FileListAcess.get(i).equals(AttributeType)) {
				try {
					FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS, "  * @depend - <uses> - " + AttributeType+"");
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("/** @depend - <uses> - " + AttributeType + " */");
			}
		}

	}

}
