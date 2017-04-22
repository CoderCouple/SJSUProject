import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {

	public static String getFileExtension(File file) {
		String name = file.getName();
		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}

	public static void readWriteFileContent(String TARGET_JAVA_FILE_ACCESS, String FileContent) throws IOException {

		File FinalOutputFile = new File(TARGET_JAVA_FILE_ACCESS);

		if (!FinalOutputFile.exists()) {
			FinalOutputFile.createNewFile();
		}

		FileWriter FileWriterMachine = new FileWriter(FinalOutputFile.getAbsoluteFile(), true);
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

	public static void readListContent(String SOURCE_FILE_PATH) {
		final File file = new File(SOURCE_FILE_PATH);
		for (final File child : file.listFiles()) {
			if (!child.isDirectory() && getFileExtension(child).toLowerCase().equals("java")) {
				MainRun.FileListAcess.add(getFileName(child, SOURCE_FILE_PATH));
			}
		}

	}
	
	public static void classStart() {
		
		try {
			FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS,"{");
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void classEnd() {
		try {
			FileOperations.readWriteFileContent(MainRun.TARGET_JAVA_FILE_ACCESS,"}");
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void deleteFile(String FileName) throws IOException {

		File FinalOutputFileToBeDeleted = new File(FileName);
        //System.out.println(FileName);
		if (!FinalOutputFileToBeDeleted.exists()) {
			FinalOutputFileToBeDeleted.delete();
		}
		System.out.println(FinalOutputFileToBeDeleted.delete());
	}
	
}
