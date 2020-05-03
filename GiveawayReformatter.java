import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
 
public class GiveawayReformatter {
 
                public static void main(String[] args) {
                	if (args.length == 2) {
                        String inputFileName = args[0];
                    	String outputFileName = args[1];
                                                ArrayList<ArrayList<String>> data = parseStreamlabsRaffleFile(inputFileName);
                                                String reformatedData = reformatData(data);
                                                createOutputFile(outputFileName, reformatedData);
                                                               
                    }
                  	else {
                        System.out.println("Usage: java GiveawayReformatter inputFileName outputFileName");
                    }
                               
                }
               
                public static void createOutputFile(String fileName, String content) {
                                Writer writer = null;
 
                                try {
                                    writer = new BufferedWriter(new OutputStreamWriter(
                                          new FileOutputStream(fileName), "utf-8"));
                                    writer.write(content);
                                } catch (IOException ex) {
                                                System.out.println("Writing outputFile failed:" +ex.toString());
                                } finally {
                                  try {
                                                  writer.close();
                                  } catch (Exception ex) {/*ignore*/}
                                }
                }
               
                public static String reformatData(ArrayList<ArrayList<String>> data) {
                                String reformatedData = "";
                                for (int i=0; i<data.size(); i++) {
                                                ArrayList<String> userRaffleData = data.get(i);
                                                String userName = userRaffleData.get(0);
                                                int numTickets = Integer.parseInt(userRaffleData.get(1));
                                                for (int j=0; j<numTickets; j++) {
                                                                if (i==data.size()-1 && j==numTickets-1) {
                                                                                reformatedData = reformatedData + userName;
                                                                }
                                                                else {
                                                                                reformatedData = reformatedData + userName + " ";
                                                                }
                                                }
                                }
                                return reformatedData;
                }
               
                public static ArrayList<ArrayList<String>> parseStreamlabsRaffleFile(String fileName) {
                                ArrayList<ArrayList<String>> fileData = new ArrayList<ArrayList<String>>();
                               
                                File inFile = new File(fileName);
                                BufferedReader br = null;
 
                    try {
                                String line;
                                br = new BufferedReader(new FileReader(inFile));
                        while ((line = br.readLine()) != null) {
                                 ArrayList<String> raffleData = new ArrayList<String>();
                                 //add username
                                 line.replaceAll("\n", "");
                                 raffleData.add(line);
                               
                                 //add numTickets
                                 line = br.readLine();
                                 line.replaceAll("\n", "");
                                 raffleData.add(line);
                                 fileData.add(raffleData);
                                 //System.out.println(line);
                        }
                    } 
 
                    catch (IOException e) {
                        e.printStackTrace();
                    } 
                    finally {
                        try {
                                 if (br != null) {
                                                 br.close();
                                 }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                                return fileData;
                }
               
               
}