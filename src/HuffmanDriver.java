import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * This driver reads in a pre-formatted text file whose name is passed as an
 * argument. It writes out the new tree codes, encoded Strings, and decoded
 * Strings to an output file.
 * 
 * @author jam
 *
 */
public class HuffmanDriver {

   public static void main(String[] args)
         throws FileNotFoundException {

      // Read in the frequency data from the input file.

      try {
         HuffmanTree.SymbolNodeData[] symbolsForTree = new HuffmanTree.SymbolNodeData[26];
         String inputFile = args[0];
         boolean exc = true;
         File file = new File(inputFile);
         String inputPath = file.getAbsolutePath();
         BufferedReader br = new BufferedReader(new FileReader(file));
         String outputFileName = br.readLine();

         File output = new File(outputFileName);
         if (output.createNewFile()) {
            System.out.println(
                  "Created new output file: " + output.getName());
         }
         else {
            System.out.println("File already exists.");
            System.exit(0);
         }
         BufferedWriter bw = new BufferedWriter(
               new OutputStreamWriter(new FileOutputStream(output)));

         int i = 0;
         while (exc) {
            String unsplitLine = br.readLine();
            if (unsplitLine.equals("!!!")) {
               exc = false;
               break;
            }
            String[] dataForNode = unsplitLine.split("-");
            char symbolData = dataForNode[0].trim().charAt(0);
            double freqData = Double.parseDouble(dataForNode[1]);
            HuffmanTree.SymbolNodeData node = new HuffmanTree.SymbolNodeData(
                  freqData, symbolData);
            symbolsForTree[i] = node;
            i++;
         }

         // PROCESS ENCODING
         exc = true;
         ArrayList<String> messagesToEncode = new ArrayList<>();

         while (exc) {
            String message = br.readLine();
            if (message.equals("!!!")) {
               exc = false;
               break;
            }
            messagesToEncode.add(message);
            System.out.println(message);
         }

         // PROCESSING DECODING
         exc = true;
         ArrayList<String> messagesToDecode = new ArrayList<>();
         while (exc) {
            String binaryMessage = br.readLine();
            if (binaryMessage.equals("!!!")) {
               exc = false;
               break;
            }
            messagesToDecode.add(binaryMessage);
            System.out.println(binaryMessage);
         }

         HuffmanTree ht = new HuffmanTree();
         ht.buildTree(symbolsForTree);
         bw.write(ht.printCode());

         bw.write("\n---------------------\nENCODED STRINGS\n"
               + "---------------------------------\n");

         for (String m : messagesToEncode) {
            bw.write(m + ":\n");
            bw.write(ht.encodeMessage(m) + "\n\n");
         }

         bw.write("\n---------------------\nDECODED MESSAGES\n"
               + "---------------------------------\n");

         for (String m : messagesToDecode) {
            bw.write(m + ":\n");
            bw.write(ht.decode(m) + "\n\n");
         }
         bw.close();
      }

      catch (IOException e) {
         System.out
               .println("An exception occured reading or writing the"
                     + " files.");
         e.printStackTrace();
      }
   }

}
