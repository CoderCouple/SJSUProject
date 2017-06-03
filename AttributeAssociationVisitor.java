import java.io.FileNotFoundException;
import java.util.Arrays;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class AttributeAssociationVisitor extends VoidVisitorAdapter<ClassInstance> {

			
	@Override
	public void visit(FieldDeclaration n, ClassInstance arg) {
		super.visit(n, arg);
		String accessSpecifier = "";
		if(n.toString().contains("public "))
			accessSpecifier = "public";
		else if(n.toString().contains("private "))
			accessSpecifier = "private";
			
		String attributeDeclaration = n.toString();	
		if(attributeDeclaration.contains("="))
			attributeDeclaration = attributeDeclaration.substring(0,attributeDeclaration.indexOf("=")).trim()+";";
		
		////System.out.println("Attribute declaration "+ attributeDeclaration);
		String attributeDecParse[] = attributeDeclaration.split(" ");
		String attributeType = attributeDecParse[attributeDecParse.length - 2];
		String attributeName = attributeDecParse[attributeDecParse.length - 1];
		
		
		if(attributeType.contains("int") || attributeType.contains("float") || attributeType.contains("String") ||
			attributeType.contains("double") || attributeType.contains("byte") || attributeType.contains("short") ||
		    attributeType.contains("long") || attributeType.contains("boolean") || attributeType.contains("char"))
		{
			if(n.toString().contains("protected "))
				return;
			if(!n.toString().contains("public ") && !n.toString().contains("private "))
				return;
			Attribute a = new Attribute();
			a.setName(attributeName);
			a.setType(attributeType);
			if(attributeType.contains("[]"))
				a.setIsPlural(true);
			
			a.setAttributeDeclaration(attributeDeclaration);
			a.setAccessSpecifier(accessSpecifier);
			arg.addAttribute(a);
		}
		
		else
		{
			Assoc as = new Assoc();
			as.setFromClass(arg);
			if(attributeType.contains("<"))
			{
				as.setFromCardinality(true);
				as.setToClass(attributeType.substring(attributeType.indexOf('<')+1, attributeType.indexOf('>')).trim());
				
			}
			else if (attributeType.contains("["))
			{ 
				as.setFromCardinality(true);
				as.setToClass(attributeType.substring(0, attributeType.indexOf('[')).trim());	
			}
			else
			{
				as.setFromCardinality(false);
				as.setToClass(attributeType);
			}
			arg.assocs.add(as);
		}
		
	}
}