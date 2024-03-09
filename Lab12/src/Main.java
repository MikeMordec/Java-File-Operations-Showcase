import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		File file = new File("./data.csv");
		Scanner scan = null;

		int recordCount = 0;
		double totalRewardCash = 0.0;
		double totalCustomerPurchases = 0.0;
		double ratio = 0.0;
		String nonMembers = "";
		try {
			BufferedWriter bw = null;
			FileWriter fw = null;
			fw = new FileWriter("out.txt", true);
			bw = new BufferedWriter(fw);
			
			scan = new Scanner(file);
			
			//Skip Header
			scan.nextLine();
			
			Date minDate = new Date(Long.MAX_VALUE);
			Date maxDate = new Date(0);
			
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] arr = line.split(",");
				String custNo = arr[0];
				String member = arr[1];
				String transDate = arr[2];
				Date transDateDate = new SimpleDateFormat("MM/dd/yyyy").parse(transDate);
				long transDateLong = transDateDate.getTime();
				if(transDateLong > maxDate.getTime())
				{
					maxDate = transDateDate;
				}
				if(transDateLong < minDate.getTime())
				{
					minDate = transDateDate;
				}
				double totalPurchase = Double.parseDouble(arr[3]);
				double couponAmtUsed = Double.parseDouble(arr[4]);
				double rewardCashIssued = Double.parseDouble(arr[5]);
				totalRewardCash += rewardCashIssued; 
				totalCustomerPurchases += totalPurchase;
				recordCount++;
				if("No".equals(member))
				{
					nonMembers += custNo + " ";
				}
				//System.out.println(transDateLong);
				
			}
			ratio = totalRewardCash / totalCustomerPurchases;
			bw.write("The count of the records in the data file is: "+recordCount+"\n");
			bw.write("The dollar amount of the total Reward Cash that was issued is: "+totalRewardCash+"\n");
			bw.write("Determine the ratio of the total Reward Cash that was issued to the total customer purchases is: "+ratio+"\n");
			bw.write("List the distinct customers that are not members of the Reward Program:" + nonMembers + "\n");
			bw.write("The range of the transaction dates: " + minDate.toString() + " - " + maxDate.toString()+"\n");
			bw.flush(); 
			bw.close();
			System.out.println("Done."); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			scan.close();
		}
	}
}
