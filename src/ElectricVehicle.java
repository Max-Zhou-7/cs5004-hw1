import java.text.DecimalFormat;

/**
 * create the class of EV.
 */
public class ElectricVehicle {
  private String name;
  private double batterySize;
  private double stateOfCharge;
  private double currentEfficiency;
  private double defaultEfficiency;



  /**
   * customize constructor.
   * @param name name
   * @param batterySize battery size
   * @param stateOfCharge state of charge
   * @param defaultEfficiency default efficiency
   */
  public ElectricVehicle(String name, double batterySize, double stateOfCharge,
                         double defaultEfficiency) {
    this.name = clampName(name);
    this.batterySize = clampBatterySize(batterySize);
    this.stateOfCharge = clampStateOfCharge(stateOfCharge);
    this.defaultEfficiency = clampDefaultEfficiency(defaultEfficiency);
    this.currentEfficiency = clampDefaultEfficiency(defaultEfficiency);
  }


  /**
   * clamp name.
   * @param name name
   * @return clamped name
   */
  private String clampName(String name) {
    if (name == null || name.isEmpty()) {
      name = "unknown EV";
    }
    return name;

  }


  /**
   * clamp the battery size.
   * @return clamped battery size
   */
  private double clampBatterySize(double batterySize) {
    final int MinBatterySize = 10;
    final int MaxBatterySize = 150;
    if (batterySize < MinBatterySize) {
      batterySize = MinBatterySize;
    } else if (batterySize > MaxBatterySize) {
      batterySize = MaxBatterySize;
    }
    return batterySize;
  }

  /**
   * get the battery size.
   * @return battery size.
   */
  public double getBatterySize() {
    return this.batterySize;

  }

  /**
   * clamp default efficiency.
   * @return clamped default efficiency.
   */
  private double clampDefaultEfficiency(double defaultEfficiency) {
    final double MIN_DE = 0.5;
    final double MAX_DE = 4.5;
    if (defaultEfficiency < MIN_DE) {
      defaultEfficiency = MIN_DE;
    } else if (defaultEfficiency > MAX_DE) {
      defaultEfficiency = MAX_DE;
    }
    return defaultEfficiency;
  }

  /**
   * get default efficiency.
   * @return default efficiency
   */
  public double getDefaultEfficiency() {
    return this.defaultEfficiency;
  }

  /**
   * Get name.
   * @return name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * clamp current state of charge.
   * @return clamped state of charge.
   */
  private double clampStateOfCharge(double stateOfCharge) {
    final double MIN_SOC = 0.15;
    final double MAX_SOC = 1.0;
    if (stateOfCharge > MAX_SOC) {
      stateOfCharge = MAX_SOC;
    } else if (stateOfCharge < MIN_SOC) {
      stateOfCharge = MIN_SOC;
    }
    return stateOfCharge;
  }

  /**
   * get current state of charge.
   * @return current state of charge.
   */
  public double getStateOfCharge() {
    return this.stateOfCharge;
  }




  /**
   * set new value for state of charge.
   */
  public void setStateOfCharge(double stateOfCharge) {
    this.stateOfCharge = clampStateOfCharge(stateOfCharge);
  }


  /**
   * get new efficiency based on current temperature.
   * @param currentTemp currentTemp.
   */
  public void updateEfficiency(double currentTemp) {
    final double HOT_LIMIT = 77.0;
    final double HOT_COE = 0.85;
    final double UPPER_BOUND = 65.0;
    final double LOWER_BOUND = 15.0;
    final double LOWER_COE = 0.5;
    if (currentTemp > HOT_LIMIT) {
      this.currentEfficiency = HOT_COE * getDefaultEfficiency();
    }
    else if (currentTemp < UPPER_BOUND) {
      if (currentTemp < LOWER_BOUND) {
        this.currentEfficiency = LOWER_COE * getDefaultEfficiency();
      }
      else {
        double diff = (UPPER_BOUND - currentTemp) / 100;
        this.currentEfficiency = (1 - diff) * getDefaultEfficiency();
      }
    }
  }

  /**
   * Get current efficiency.
   * return current efficiency
   */
  public double getEfficiency() {
    return this.currentEfficiency;
  }


  /**
   * get the range for car can drive.
   * @return range.
   */
  public double range() {
    return getEfficiency() * getStateOfCharge() * getBatterySize();
  }



  /**
   * Override string.
   * @return string
   */
  public String toString() {
    DecimalFormat percentFormat = new DecimalFormat("0.0%");
    DecimalFormat rangeFormat = new DecimalFormat("0.0");
    return this.name + " SOC: " + percentFormat.format(getStateOfCharge())
            + " Range (miles): " + rangeFormat.format(range());
  }
}


