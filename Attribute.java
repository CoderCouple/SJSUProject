import java.io.IOException;

public class Attribute {

	String attributeName;
	String type;
	String attributeDeclaration;
	String accessSpecifier;
	boolean hasSetMethod = false;
	boolean hasGetMethod = false;
	boolean isPlural = false;
    Method setMethod;
    Method getMethod;
	public void setName(String attributeName1) {
		attributeName1 = attributeName1.replace(";", "");
		System.out.println("Attribute "+attributeName1 + " has started its work.");
		attributeName = attributeName1;
	}

	public void setType(String attributeType) {
		System.out.println("Its of type "+attributeType);
		type = attributeType;
		
	}

	public void setAttributeDeclaration(String string) {
		attributeDeclaration = "";
		/**if(isPlural)
		{
			attributeDeclaration = "/** @tagvalue (*) /";
		}*/

		attributeDeclaration += string;
		
	}

	public void setAccessSpecifier(String accessSpecifier1) {
		System.out.println("Its has access specifier "+accessSpecifier1);
		accessSpecifier = accessSpecifier1;
		
	}

	public void setIsPlural(boolean b) {
		isPlural = b;
		
	}

	public String getName() {
		return attributeName;
	}

	public void assignHasSet() {
		hasSetMethod = true;
	}
	
	public void assignHasGet() {
		hasGetMethod = true;
	}
	
	public void writeClassContent(FileOperations f) throws IOException
	{
		System.out.println("Attribute declaration: "+ attributeDeclaration);
		if(hasSetMethod && hasGetMethod)
		{	
			setMethod.setGetSet();
			getMethod.setGetSet();
			accessSpecifier = "public";
			attributeDeclaration = attributeDeclaration.replaceFirst("private", "public");
		}
		System.out.println("Attribute declaration: "+ attributeDeclaration);
		f.writeToFileContent(attributeDeclaration);
	}

	public void assignSetMethod(Method m) {
		setMethod = m;
	}
	public void assignGetMethod(Method m) {
		getMethod = m;
	}

}
