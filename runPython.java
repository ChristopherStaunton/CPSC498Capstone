import java.io.*;
import java.nio.charset.StandardCharsets;

public class runPython {
       Process mProcess;

public void runScript(){
       Process process;
       try{
             process = Runtime.getRuntime().exec(new String[]{"./src/roughDraft498.py","arg1","arg2"});
             mProcess = process;
       }catch(Exception e) {
          System.out.println("Exception Raised" + e.toString());
       }
       InputStream stdout = mProcess.getInputStream();
       BufferedReader reader = new BufferedReader(new InputStreamReader(stdout,StandardCharsets.UTF_8));
       String line;
       try{
          while((line = reader.readLine()) != null){
               System.out.println("stdout: "+ line);
          }
       }catch(IOException e){
             System.out.println("Exception in reading output"+ e.toString());
       }
}
}

class Solution {
      public static void main(String[] args){
          runPython scriptPython = new runPython();
          scriptPython.runScript();
      }

}