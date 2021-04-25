import java.awt.image.Kernel;
import java.io.*;
import java.text.*;
import java.util.Vector;

public class StandardKernel extends Kernel {


  private int numDigits;
 public static StandardKernel createKernel(Reader reader)
   throws IOException {
    return createKernel(reader, false);
  }

  public static StandardKernel createKernel(Reader reader, boolean normalise)
   throws IOException {
    StreamTokenizer parser = new StreamTokenizer(new BufferedReader(reader));
    parser.commentChar('#');
    int w = getInteger(parser);
    int h = getInteger(parser);
    if (w < 1 || h < 1)
      throw new IOException("invalid kernel dimensions");
    int n = getInteger(parser);
    int size = w*h;
    float[] data = new float[size];
    for (int i = 0; i < size; ++i)
      data[i] = getFloat(parser);
    if (normalise)
      normaliseCoefficients(data);
    return new StandardKernel(w, h, data, n);
  }


  /**
   * @return an integer extracted from a token stream.
   */

  private static int getInteger(StreamTokenizer input) throws IOException {
    input.nextToken();
    if (input.ttype == StreamTokenizer.TT_NUMBER)
      return (int) input.nval;
    else if (input.ttype == StreamTokenizer.TT_EOF)
      throw new EOFException("kernel appears to be truncated");
    else
      throw new IOException("invalid kernel data");
  }


  /**
   * @return a float value extracted from a token stream.
   */

  private static float getFloat(StreamTokenizer input) throws IOException {
    input.nextToken();
    if (input.ttype == StreamTokenizer.TT_NUMBER)
      return (float) input.nval;
    else if (input.ttype == StreamTokenizer.TT_EOF)
      throw new EOFException("kernel appears to be truncated");
    else
      throw new IOException("invalid kernel data");
  }


  /**
   * Normalises an array of coefficients if their sum exceeds 1.
   * @param coeff array of coefficients
   */

  private static void normaliseCoefficients(float[] coeff) {
    float sum = 0.0f;
    for (int i = 0; i < coeff.length; ++i)
      sum += coeff[i];
    if (sum > 1.001f)
      for (int i = 0; i < coeff.length; ++i)
        coeff[i] /= sum;
  }

  
  public StandardKernel(int w, int h, float[] data) {
    this(w, h, data, 4);
  }

  /**
   * Constructs a StandardKernel with the specified dimensions,
   * coefficients and coefficient formatting.
   * @param w width of kernel
   * @param h height of kernel
   * @param data array of coefficients, in row-major order
   * @param n number of fraction digits used when writing coefficients
   */

  public StandardKernel(int w, int h, float[] data, int n) {
    super(w, h, data);
    setFractionDigits(n);
  }


  public String toString() {
    return new String(
     getClass().getName() + ": " + getWidth() + "x" + getHeight());
  }


  /**
   * Sets number of digits used to format fractional part of a coefficient.
   */

  public void setFractionDigits(int n) {
    numDigits = Math.max(n, 0);
  }


  /**
   * @return number of digits used format fractional part of a coefficient.
   */

  public int getFractionDigits() {
    return numDigits;
  }


  /**
   * Writes kernel data.
   * @param writer destination for kernel data
   */

  public void write(Writer writer) {

    PrintWriter out = new PrintWriter(new BufferedWriter(writer));
    out.println("# convolution kernel");
    out.println(getWidth() + " " + getHeight() + " " + numDigits);

    float[] data = getKernelData(null);
    NumberFormat nf = getFormat();
    Vector coefficients = new Vector();

    // Format each coefficient into a string

    int i, maxLength = 0;
    for (i = 0; i < data.length; ++i) {
      String string = nf.format(data[i]);
      coefficients.addElement(string);
      if (string.length() > maxLength)
        maxLength = string.length();
    }

    // Print coefficient strings

    i = 0;
    for (int k = 0; k < getHeight(); ++k) {
      for (int j = 0; j < getWidth(); ++j, ++i)
        out.print(StringTools.rightJustify(
         (String) coefficients.elementAt(i), maxLength+1));
      out.println();
    }
    out.flush();

  }


  /**
   * @return a NumberFormat that can be used to format coefficients
   * when printing them.
   */

  private NumberFormat getFormat() {
    StringBuffer patternBuffer = new StringBuffer("0");
    if (numDigits > 0) {
      patternBuffer.append('.');
      for (int i = 0; i < numDigits; ++i)
        patternBuffer.append('0');
    }
    return new DecimalFormat(patternBuffer.toString());
  }


}
