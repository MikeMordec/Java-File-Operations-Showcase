import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

	private static final String FILENAME = "out.txt";

	public static void main(String[] args) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String field1 = "field1,";
			String field2 = "field2,";
			String field3 = "field3,";
			String field4 = "field4,";
			String field5 = "field5,";
			String field6 = "field6";
			fw = new FileWriter(FILENAME, true);
			bw = new BufferedWriter(fw);
			bw.write(field1);
			bw.write(field2);
			bw.write(field3);
			bw.write(field4);
			bw.write(field5);
			bw.write(field6 + "\n");
			System.out.println("data recorded!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}