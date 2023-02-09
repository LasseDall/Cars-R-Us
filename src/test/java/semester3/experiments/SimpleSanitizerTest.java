package semester3.experiments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleSanitizerTest {

  @Test
  void simpleSanitize() {
    String result = SimpleSanitizer.simpleSanitize("Hello <b>World</b>");
    assertEquals("Hello World",result);
  }
}