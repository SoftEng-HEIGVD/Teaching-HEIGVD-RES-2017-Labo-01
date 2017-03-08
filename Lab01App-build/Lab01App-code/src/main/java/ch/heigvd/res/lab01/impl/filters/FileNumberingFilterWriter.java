package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 * @author Loan Lassalle
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private static boolean isCarriageReturn = false;
  private int number;

  private void numberingFile() throws IOException {
        out.write(String.valueOf(++number));
        out.write('\t');
  }

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    
    try
    {
        numberingFile();
    }
    catch(IOException ex)
    {
        LOG.log(Level.SEVERE, "Could not write file numbering. {0}", ex.getMessage());
        ex.printStackTrace();
    }
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; ++i)
    {
        write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
        if(isCarriageReturn && c != '\n')
        {
            numberingFile();
        }

        isCarriageReturn = false;

        out.write(c);

        if(c == '\r')
        {
            isCarriageReturn = true;
        }
        else if(c == '\n')
        {
            numberingFile();
        }
  }

}
