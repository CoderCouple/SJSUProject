import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class MethodDependencyVisitor extends VoidVisitorAdapter<ClassInstance> {
	@Override
	public void visit(MethodDeclaration n, ClassInstance arg) {
		//System.out.println("\n\nMaking dependencies for class "+ arg.getClassName());
		System.out.println("\nThis is for method: "+n.getDeclarationAsString());
		
		super.visit(n, arg);
		String parameter_declaration = n.getDeclarationAsString().substring(n.getDeclarationAsString().lastIndexOf("(") + 1,
				n.getDeclarationAsString().lastIndexOf(")")).trim();
		//System.out.println("This is parameter declaration "+parameter_declaration);
		if(!parameter_declaration.equals(""))
			
		{		
			//System.out.println("This is parameter declaration "+parameter_declaration);
			String parameters[] = parameter_declaration.split(",");
			for(String parameter : parameters)
			{
				String parameterType = parameter.split(" ")[0];
				//System.out.println("This is parameter type "+parameterType);
				
				if(parameterType.contains("int") || parameterType.contains("float") || parameterType.contains("String") ||
						parameterType.contains("double") || parameterType.contains("byte") || parameterType.contains("short") ||
						parameterType.contains("long") || parameterType.contains("boolean") || parameterType.contains("char"))
					continue;
				
				if(parameterType.contains("<"))
				{
					parameterType= parameterType.substring(parameterType.indexOf('<')+1, parameterType.indexOf('>')).trim();	
				}
				if(parameterType.contains("["))
				{
					parameterType= parameterType.replace("[", "").replace("]","").trim();
				}
				
				//System.out.println("This is parameter type "+parameterType);
				ClassInstance dependency_to = Package.getClassByName(parameterType);
				//System.out.println("This is class type "+dependency_to.getClassName());
				
				//System.out.println("May make dependecy from "+arg.getClassName()+" to "+dependency_to.getClassName());
				if(dependency_to.isInterface() && !arg.isInterface())
				{
					//System.out.println("Making dependecy from "+arg.getClassName()+" to "+dependency_to.getClassName());
					arg.addDependency(dependency_to);
				}
			}
		
		}
		
		
		if(n.getBody().isPresent())
		{
			BlockStmt statement = n.getBody().get();
			List<Statement> statementList = null;
			if(statement!=null)
			{
				statementList = statement.getStatements();
				for(Statement s : statementList)
				{
					ClassInstance dependency_to = Package.getClassByName(s.toString().split(" ")[0]);
					if(dependency_to!=null && dependency_to.isInterface)
						arg.addDependency(dependency_to);
				}
					
			}	
		}	
		
		if(n.isPublic()) 
		{
			
			Method m = new Method();
			m.setDeclaration(n.getDeclarationAsString());
			
			String methodName = n.getName().toString();
			
			
			if(methodName.contains("set"))
			{
				BlockStmt statement = n.getBody().get();
				List<Statement> statementList = statement.getStatements();
				
				if(statementList.size()==1)
				{
					String attName = statementList.get(0).toString().split("=")[0].replaceFirst("this.","").trim();
					//System.out.println("This is the substring of set "+attName);
					Attribute a = arg.getAtrributeByName(attName);
					a.assignHasSet();
					a.assignSetMethod(m);
				}
				
			}
			else if(methodName.contains("get"))
			{
				BlockStmt statement = n.getBody().get();
				List<Statement> statementList = statement.getStatements();
				if(statementList.size()==1)
				{
					if(statementList.get(0).toString().split(" ")[0].equals("return"))
					{
						String attName = statementList.get(0).toString().split(" ")[1].replaceFirst("this.","").replace(';', ' ').trim();
						//System.out.println("This is the substring of get "+attName);
						Attribute a = arg.getAtrributeByName(attName);
						a.assignHasGet();
						a.assignGetMethod(m);
					}
				}
			}
			
			arg.addMethod(m);
			
		}
	}
	
}
