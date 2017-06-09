import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Package {
	static List<ClassInstance> classList;
	static Set<String> allAssocs;
	static
	{
		allAssocs = new HashSet<String>();
	}
	static void assignClassList (String SOURCE_FILE_PATH) 
	{
		try
		{
			classList = new ArrayList<ClassInstance>();
			File ImportFile = new File(SOURCE_FILE_PATH);
			for (final File child : ImportFile.listFiles()) {
	
				if (!child.isDirectory() && child.getName().contains(".java")) {
					System.out.println("File "+child.getName()+" is being opened");
					ClassInstance newClass = new ClassInstance(child);
					System.out.println("class "+newClass.getClassName()+" is made");
					classList.add(newClass);
				}
			}
			for(ClassInstance classIns: classList)
			{
				classIns.setAttributes();
				classIns.setMethods();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	static ClassInstance getClassByName(String searchName)
	{
		for(ClassInstance classIns : classList)
		{
			if(classIns.getClassName().equals(searchName))
				return classIns;
		}
		return null;
	}
	
	static void writeOutputFile(FileOperations f) throws IOException
	{
		for(ClassInstance classIns: classList)
		{
			classIns.writeImportStatements(f);
		}
		for(ClassInstance classIns: classList)
		{
			classIns.writeComments(f);
			classIns.writeClassContent(f);
		}
	}

}
