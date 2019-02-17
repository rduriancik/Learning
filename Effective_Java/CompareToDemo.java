public class CompareToDemo {

}

class PhoneNumber implements Comparable<PhoneNumber>{
  private final short areaCode;
  private final short prefix;
  private final short lineNumber;

  public PhoneNumber(int areaCode, int prefix, int lineNumber) {
    rangeCheck(areaCode, 999, "area code");
    rangeCheck(prefix, 999, "prefix");
    rangeCheck(lineNumber, 9999, "line number");

    this.areaCode = (short) areaCode;
    this.prefix = (short) prefix;
    this.lineNumber = (short) lineNumber;
  }

  private static void rangeCheck(int arg, int max, String name) {
    if (arg < 0 || arg > max) {
      throw new IllegalArgumentException(name + ": " + arg);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if(!(o instanceof PhoneNumber))
      return false;
    PhoneNumber pn = (PhoneNumber) o;
    return pn.lineNumber == lineNumber
      && pn.prefix == prefix
      && pn.areaCode == areaCode;
  }

  //@Override
  //public int hashCode() {return 42;}

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + areaCode;
    result = 31 * result + prefix;
    result = 31 * result + lineNumber;
    return result;
  }

/*
  @Override
  public int compareTo(PhoneNumber pn) {
    // Compare area codes
    if (areaCode < pn.areaCode)
      return -1;
    if (areaCode > pn.areaCode)
      return 1;

    // Area codes are equal, compare prefixes
    if (prefix < pn.prefix)
      return -1;
    if (prefix > pn.prefix)
      return 1;

    // Area codes and prefixes are equal, compare line numbers
    if (lineNumber < pn.lineNumber)
      return -1;
    if (lineNumber > pn.lineNumber)
      return 1;

    return 0; // All fields are equal
  }
  */

  // Improved version, works only if you are certain that the fields are non-negative
  // or difference between the lowest and highest possible field values is less than
  // or equal to Integer.MAX_VALUE
  @Override
  public int compareTo(PhoneNumber pn) {
    // Compare area codes
    int areaCodeDiff = areaCode - pn.areaCode;
    if (areaCodeDiff != 0)
      return areaCodeDiff;

    // Area codes are equal, compare prefixes
    int prefixDiff = prefix - pn.prefix;
    if (prefixDiff != 0)
      return prefixDiff;

    // Area codes and prefixes are equal, compare line numbers
    return lineNumber - pn.lineNumber;
  }
}
