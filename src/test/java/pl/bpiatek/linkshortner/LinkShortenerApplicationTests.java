package pl.bpiatek.linkshortner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkShortenerApplicationTests {

  @Test
  public void contextLoads() {
  }

  @Test
  public void assertTrue() {
    Assert.assertTrue(true);
  }


  @Test
  public void fail() {
    Assert.assertEquals(1, 2);
  }

  @Test
  public void fail2() {
    Assert.assertEquals(1, 2);
  }

  @Test
  public void fail23() {
    Assert.assertEquals(1, 2);
  }
}
