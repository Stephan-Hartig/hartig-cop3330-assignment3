package shared.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;


public class InputOutput implements Closeable {

   private BufferedReader in;
   private PrintStream out;

   public InputOutput() {
      this.in = new BufferedReader(new StdInputStreamReader());
      this.out = System.out;
   }

   /**
    * Please call this when done with this object.
    *
    * If it breaks, it breaks.
    */
   @Override
   public void close() throws IOException {
      this.in.close();
   }

   /**
    * Wrapper for `System.out.printf`.
    *
    * @param fmt     Msg to print.
    * @param args    Args to interpolate.
    */
   public void printf(String fmt, Object ... args) { this.out.printf(fmt, args); }

   /**
    * Wrapper for `System.out.print`.
    *
    * @param msg     Msg to print.
    */
   public void print(String msg) { this.out.print(msg); }

   /**
    * Wrapper for `System.out.println`.
    *
    * @param msg     Msg to print.
    */
   public void println(String msg) { this.out.println(msg); }
   
   /**
    * Wrapper for `System.out.println`.
    */
    public void println() { this.out.println(); }


   /**
    * Read and trim a line from input. Defaults to blank string, eg "".
    *
    * @return  Line of input, trimmed.
    */
   public String getTrimmed() {
      try { return this.in.readLine().trim(); }
      catch (IOException e) {
         System.err.println(e.getMessage());
         return "";
      }
   }

   /**
    * Read line from input. Defaults to blank string, eg "".
    *
    * @return  Line of input.
    */
   public String getLine(String def) {
      try { return this.in.readLine(); }
      catch (IOException e) {
         System.err.println(e.getMessage());
         return def;
      }
   }
   
   public String promptTrimmed(String msg) {
      this.out.print(msg);
      this.out.flush();
      return this.getTrimmed();
   }
   
   public String promptLine(String msg) {
      this.out.print(msg);
      this.out.flush();
      return this.getLine("");
   }
   
   /**
    * Prompt for a float, but return default value in case of no input.
    *
    * @param msg     Message to display.
    * @param def     Default value.
    * @return        A float.
    */
   public float promptFloatDefaulting(String msg, float def) {
      this.out.print(msg);
      this.out.flush();
      try { return Integer.parseInt(this.in.readLine()); }
      catch (NumberFormatException | IOException e) { return def; }
   }
   
   /**
    * Prompt for an int, but return default value in case of no input.
    *
    * @param msg     Message to display.
    * @param def     Default value.
    * @return        An integer.
    */
   public int promptIntDefaulting(String msg, int def) {
      this.out.print(msg);
      this.out.flush();
      try { return Integer.parseInt(this.in.readLine()); }
      catch (NumberFormatException | IOException e) { return def; }
   }

   /**
    * Aggressively prompt the user until they input a valid float.
    *
    * @param msg        Message to display.
    * @return {float}   A valid float.
    */
   public float demandFloat(String msg) {
      return this.demandFloat(msg, "It needs to be a _number_: ");
   }
   public float demandFloat(String msg, String demand) {
      this.out.print(msg);
      while (true) {
         this.out.flush();
         try  { return Float.parseFloat(this.in.readLine()); }
         catch (NumberFormatException e) { this.out.print(demand); }
         catch (IOException e) { System.err.println(e.getMessage()); }
      }
   }

   /**
    * Aggressively prompt the user until they input a valid int.
    *
    * @param msg        Message to display.
    * @return {int}     A valid integer.
    */
   public int demandInt(String msg) {
      return this.demandInt(msg, "It needs to be an _integer_: ");
   }
   public int demandInt(String msg, String demand) {
      this.out.print(msg);
      while (true) {
         this.out.flush();
         try  { return Integer.parseInt(this.in.readLine()); }
         catch (NumberFormatException e) { this.out.print(demand); }
         catch (IOException e) { System.err.println(e.getMessage()); }
      }
   }
}
