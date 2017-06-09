import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {
	File f;
	FileOperations(String file)
	{
		f= new File(file);
	}

	public String getFileExtension(File file) {
		String name = file.getName();
		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}

	public void writeToFileContent( String FileContent) throws IOException {
		if (!f.exists()) {
			f.createNewFile();
		}

		FileWriter FileWriterMachine = new FileWriter(f.getAbsoluteFile(), true);
		BufferedWriter BufferedWriterMachine = new BufferedWriter(FileWriterMachine);

		BufferedWriterMachine.write(FileContent);
		BufferedWriterMachine.append('\n');

		if (BufferedWriterMachine != null)
			BufferedWriterMachine.close();

		if (FileWriterMachine != null)
			FileWriterMachine.close();

	}

	public static String getFileName(File file, String SOURCE_FILE_PATH) {
		String name = file.getName();
		try {
			// System.out.println(name.substring(name.lastIndexOf("/")+1,name.lastIndexOf(".")));
			return name.substring(name.lastIndexOf("/") + 1, name.lastIndexOf("."));

		} catch (Exception e) {
			return "";
		}
	}

	public void readListContent(String SOURCE_FILE_PATH) {
		final File file = new File(SOURCE_FILE_PATH);
		for (final File child : file.listFiles()) {
			if (!child.isDirectory() && getFileExtension(child).toLowerCase().equals("java")) {
				MainRun.FileListAccess.add(getFileName(child, SOURCE_FILE_PATH));
			}
		}

	}
	
	public void classStart() {
		
		try {
			writeToFileContent("{");
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void classEnd() {
		try {
			writeToFileContent("}");
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteFile() throws IOException {

        //System.out.println(FileName);
		if (f.exists()) {
			f.delete();
		}
	}
	
}
