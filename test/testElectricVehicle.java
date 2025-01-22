import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


/**
 * Beginning of test class.
 */
public class testElectricVehicle {
  ElectricVehicle a;
  ElectricVehicle b;
  private final double delta = 0.01;
/**
 * set up for car a and b.
 */
@Before
public void setup() {
  a = new ElectricVehicle("",130.734534534, 0.07, 0.3);
  b = new ElectricVehicle("Xiaomi SU7", 10000.999, 100.99,4.5);
}

/**
 * Test get car name.
 */
@Test
public void getName() {
  assertEquals("unknown EV", a.getName());
  assertEquals("Xiaomi SU7", b.getName());
}

/**
 * Test get Battery size.
 */
@Test
public void testBatterySize() {
  assertEquals(130.73, a.getBatterySize(), delta);
  assertEquals(150.0, b.getBatterySize(), delta);
}

/**
 * Test get default efficiency.
 */
@Test
public void testGetDefaultEfficiency() {
  assertEquals(0.5, a.getDefaultEfficiency(),0);
  assertEquals(4.5, b.getDefaultEfficiency(),0);
}

/**
 * Test the set and get state of charge.
 */
@Test
public void testGetStateOfCharge() {
  assertEquals(0.15, a.getStateOfCharge(), delta);
  assertEquals(1, b.getStateOfCharge(), delta);
  a.setStateOfCharge(0.856);
  b.setStateOfCharge(0.0723);
  assertEquals(0.86, a.getStateOfCharge(), delta);
  assertEquals(0.15, b.getStateOfCharge(), delta);
}

/**
 * Test get current efficiency.
 */
@Test
public void testGetEfficiency() {
  a.updateEfficiency(79);
  b.updateEfficiency(64.1234234);
  assertEquals(0.85 * 0.5, a.getEfficiency(), delta);
  assertEquals(0.99 * 4.5, b.getEfficiency(), delta);
}

/**
 * Test Range.
 */
@Test
public void testRange() {
  assertEquals(a.getEfficiency() * a.getStateOfCharge() * a.getBatterySize(), a.range(), delta);
  assertEquals(b.getEfficiency() * b.getStateOfCharge() * b.getBatterySize(), b.range(), delta);
}


/**
 * test get toString.
 */
@Test
public void testString() {
  a.setStateOfCharge(0.856);
  b.setStateOfCharge(0.0723);
  a.updateEfficiency(79);
  b.updateEfficiency(64.1234234);
  assertEquals("unknown EV SOC: 85.6% Range (miles): 95.1", a.toString());
  assertEquals("Xiaomi SU7 SOC: 15.0% Range (miles): 22.3", b.toString());
}






}
