import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;


public class ClassInstance {
	String classDeclaration;
	String className;
	String acessSpecifier;
	boolean isInterface;
	List<Attribute> attributes;
	List<Method> methods;
	String importStatements;
	List<Assoc> assocs;
	Set<ClassInstance> dependencies;
	static String startUMLComment;
	static String defaultUMLComment;
	static String endUMLComment;
	static String interfaceComment;
    static String notConstructorUMLComment;
	CompilationUnit cu;
	boolean hasConstructor;
	
	static
	{
		startUMLComment=UMLGraphCommentGenerator.startUMLGraphComment();
		defaultUMLComment=UMLGraphCommentGenerator.defaultUMLGraphComment();
		notConstructorUMLComment=UMLGraphCommentGenerator.notConstructorUMLComment();
		endUMLComment= UMLGraphCommentGenerator.endUMLGraphComment();
		interfaceComment = UMLGraphCommentGenerator.getInterfaceUMLComment();
	}
	
    ClassInstance(File f) throws FileNotFoundException
    {
    	    cu = JavaParser.parse(f); 
		    new ImportVisitor().visit(cu, this);
		    new ClassDeclarationVisitor().visit(cu, this);  
    }
    
    public void setAttributes()
    {
    	 attributes = new ArrayList<Attribute>();
    	 assocs = new ArrayList<Assoc>();
		 new AttributeAssociationVisitor().visit(cu, this);
    }

    public void setMethods()
    {
    	dependencies = new HashSet<ClassInstance>();
    	methods = new ArrayList<Method>();
    	new MethodDependencyVisitor().visit(cu,this);
    	new ConstructorDependencyVisitor().visit(cu,this);
    	new MethodContentVisitor().visit(cu,this);
    }

	public void setImportStatements(String string) {
	   importStatements = string;
	}



	public void setIsInterface(boolean interface1) {
		isInterface = interface1;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public void setClassDeclaration(String declaration) {
		classDeclaration = declaration;
		
	}



	public void addAttribute(Attribute a) {
		System.out.println("attribute "+a.attributeDeclaration+ " is being added.");
		attributes.add(a);
		
	}


	public void setClassName(String name) {
		System.out.println("Class "+ name +" is being made.");
		className = name;
	}

	public String getClassName() {
		return className;
	}
	
	public boolean existsAssocOfType(ClassInstance classIns)
	{
		for(Assoc assoc: assocs)
		{
			if(assoc.getToClass().equals(classIns))
				return true;
		}
		return false;
	}

	public void writeImportStatements(FileOperations f) throws IOException {
		if(importStatements!=null)
		f.writeToFileContent(importStatements);
	}

	public void writeComments(FileOperations f) throws IOException {
		f.writeToFileContent(startUMLComment);
		f.writeToFileContent(defaultUMLComment);
		if(!hasConstructor)
			f.writeToFileContent(notConstructorUMLComment);
		if(isInterface)
			f.writeToFileContent(interfaceComment);
		for(Assoc assoc: assocs)
		{
			assoc.writeComments(f);
		}
		
		for(ClassInstance d: dependencies)
		{
			f.writeToFileContent("* @depend - <uses> - "+d.getClassName());
		}
		f.writeToFileContent(endUMLComment);
	}

	public void writeClassContent(FileOperations f) throws IOException {
		f.writeToFileContent(classDeclaration);
		for(Attribute a: attributes)
		{
			a.writeClassContent(f);
		}
		for(Method m: methods)
		{
			m.writeClassContent(f, isInterface);
		}
		f.writeToFileContent("}");
	}

	public Assoc getAssocWhere(ClassInstance classIns) {
		for(Assoc assoc: assocs )
		{
			if(assoc.getToClass().equals(classIns))
				return assoc;
		}
		return null;
	}

	public void addDependency(ClassInstance d) {
		dependencies.add(d);
	}

	public Attribute getAtrributeByName(String attName) {
		
		for(Attribute a : attributes)
		{
			if(a.getName().equals(attName))
			{
				return a;
			}
		}
		return null;
	}

	public void addMethod(Method m) {
		methods.add(m);
	}

	public void setConstructor() {
		hasConstructor=true;
	}
}