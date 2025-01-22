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

//  /**
//   * default constructor.
//   * @param batterySize batterySize
//   * @param stateOfCharge state of charge
//   * @param defaultEfficiency default efficiency
//   */
//  public ElectricVehicle(double batterySize, double stateOfCharge, double defaultEfficiency) {
//    this.name = "unknown EV";
//    this.batterySize = batterySize;
//    this.stateOfCharge = stateOfCharge;
//    this.defaultEfficiency = defaultEfficiency;
//    this.currentEfficiency = defaultEfficiency;
//}

  /**
   * customize constructor.
   * @param name name
   * @param batterySize battery size
   * @param stateOfCharge state of charge
   * @param defaultEfficiency default efficiency
   */
  public ElectricVehicle(String name, double batterySize, double stateOfCharge, double defaultEfficiency) {
    this.name = clampName(name);
    this.batterySize = clampBatterySize(batterySize);
    this.stateOfCharge = clampStateOfCharge(stateOfCharge);
    this.defaultEfficiency = clampDefaultEfficiency(defaultEfficiency);
    this.currentEfficiency = clampDefaultEfficiency(defaultEfficiency);
  }


  /**
   * clamp name.
   * @param name
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
    if(batterySize < 10) {
      batterySize = 10;
    } else if (batterySize > 150) {
      batterySize = 150;
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
    if (defaultEfficiency < 0.5) {
      defaultEfficiency = 0.5;
    } else if (defaultEfficiency > 4.5) {
      defaultEfficiency = 4.5;
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
    if (stateOfCharge > 1.0) {
      stateOfCharge = 1.0;
    } else if (stateOfCharge < 0.15) {
      stateOfCharge = 0.15;
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
      if (currentTemp > 77.0) {
        this.currentEfficiency = 0.85 * defaultEfficiency;
      }
      else if(currentTemp < 65.0) {
        if(currentTemp < 15.0) {
          this.currentEfficiency = 0.5 * defaultEfficiency;
        }
        else{
          double diff = (65 - currentTemp) / 100;
        this.currentEfficiency = (1 - diff) * defaultEfficiency;
        }
      }

      }

//      private double clampCurrentEfficiency(double temperature) {
//        if (null != temperature) {
//          updateEfficiency(temperature);
//          return this.currentEfficiency * getDefaultEfficiency();
//        else{
//            return this.currentEfficiency;
//          }
//
//      }
//  }
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
   * Override string
   * @return string
   */
  public String toString() {
    DecimalFormat percentFormat = new DecimalFormat("0.0%");
    DecimalFormat rangeFormat = new DecimalFormat("0.0");
    return this.name + " SOC: " + percentFormat.format(getStateOfCharge()) +
            " Range (miles): " + rangeFormat.format(range());
  }
}


