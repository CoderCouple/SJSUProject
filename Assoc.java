import java.io.IOException;

public class Assoc {

	ClassInstance fromClass;
	boolean isFromPluralCardinality;
	ClassInstance toClass;
	boolean isTwoWay = false;
	boolean isToPluralCardinality;

	
	public void setFromClass(ClassInstance arg) {
		System.out.println("An assoc starts from " +arg.getClassName());
		fromClass = arg;
	}

	public void setFromCardinality(boolean b) {
		System.out.println("Its cardinality is plural: " +b);
		isFromPluralCardinality = b;
		
	}

	public void setToClass(String name) {
		System.out.println("to class name is "+name);
		toClass = Package.getClassByName(name);
		
		String assocStr="";
		if(fromClass.getClassName().compareTo(toClass.getClassName())>0)
			assocStr=toClass.getClassName()+"-"+fromClass.getClassName();
		else
			assocStr=fromClass.getClassName()+"-"+toClass.getClassName();
		
		if(Package.allAssocs.contains(assocStr))
		{
			System.out.println("Found a duplicate assoc");
			Assoc duplicateAssoc = toClass.getAssocWhere(fromClass);
			duplicateAssoc.isTwoWay= true;
			duplicateAssoc.isToPluralCardinality=isFromPluralCardinality;
			fromClass= null;
			toClass=null;
		}
		else
			Package.allAssocs.add(assocStr);
	}

	public ClassInstance getToClass() {
		return toClass;
	}

	public ClassInstance getFromClass() {
		return fromClass;
	}

	
	public void writeComments(FileOperations f) throws IOException {
		if(getFromClass() == null)
			return;
		if(!isTwoWay)
		{
			if(!isFromPluralCardinality)
				f.writeToFileContent("* @assoc 1 - 1 "+getToClass().getClassName());
			else
				f.writeToFileContent("* @assoc 1 - * "+getToClass().getClassName());
		}
		else
		{
			if(!isFromPluralCardinality)
			{
				if(!isToPluralCardinality)
					f.writeToFileContent("* @assoc 1 - 1 "+getToClass().getClassName());
				else
					f.writeToFileContent("* @assoc * - 1 "+getToClass().getClassName());
			}
			else
			{ 
				if(!isToPluralCardinality)
					f.writeToFileContent("* @assoc 1 - * "+getToClass().getClassName());
				else
					f.writeToFileContent("* @assoc * - * "+getToClass().getClassName());
			}
		}
	}
}
