	import java.io.FileNotFoundException;

	import com.github.javaparser.ast.body.ConstructorDeclaration;
	import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

	public class ConstructorDependencyVisitor extends VoidVisitorAdapter<ClassInstance> {
		@Override
		public void visit(ConstructorDeclaration n, ClassInstance arg) {
			arg.setConstructor();
			System.out.println("Calling constructor vistor");
			System.out.println("\n\nMaking dependencies for class "+ arg.getClassName());
			System.out.println("\nThis is for method: "+n.getDeclarationAsString());
			Method m = new Method();
			m.setDeclaration(n.getDeclarationAsString());
			arg.addMethod(m);
			
			super.visit(n, arg);
			String parameter_declaration = n.getDeclarationAsString().substring(n.getDeclarationAsString().lastIndexOf("(") + 1,
					n.getDeclarationAsString().lastIndexOf(")")).trim();
			System.out.println("This is parameter declaration "+parameter_declaration);
			if(!parameter_declaration.equals(""))
			{
				
				System.out.println("This is parameter declaration "+parameter_declaration);
				String parameters[] = parameter_declaration.split(",");
				for(String parameter : parameters)
				{
					String parameterType = parameter.split(" ")[0];
					System.out.println("This is parameter type "+parameterType);
					
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
					
					System.out.println("This is parameter type "+parameterType);
					ClassInstance dependency_to = Package.getClassByName(parameterType);
					System.out.println("This is class type "+dependency_to.getClassName());
					
					if(dependency_to.isInterface())
					{
						arg.addDependency(dependency_to);
					}
				}
				
			}
		
		}
	}


