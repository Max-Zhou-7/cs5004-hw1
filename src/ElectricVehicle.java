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
   * default constructor.
   * @param batterySize batterySize
   * @param stateOfCharge state of charge
   * @param defaultEfficiency default efficiency
   */
  public ElectricVehicle(double batterySize, double stateOfCharge, double defaultEfficiency) {
    this.name = "unknown EV";
    this.batterySize = batterySize;
    this.stateOfCharge = stateOfCharge;
    this.defaultEfficiency = defaultEfficiency;
}

  /**
   * customize constructor.
   * @param name name
   * @param batterySize battery size
   * @param stateOfCharge state of charge
   * @param defaultEfficiency default efficiency
   */
  public ElectricVehicle(String name, double batterySize, double stateOfCharge, double defaultEfficiency) {
    this.name = name;
    this.batterySize = batterySize;
    this.stateOfCharge = stateOfCharge;
    this.defaultEfficiency = defaultEfficiency;
  }

  /**
   * get the battery size.
   * @return battery size.
   */
  public double getBatterySize(){
      if(this.batterySize < 10) {
        this.batterySize = 10;
      } else if (this.batterySize > 150) {
        this.batterySize = 150;
      }
    return this.batterySize;

  }

  /**
   * get default efficiency.
   * @return default efficiency.
   */
  public double getDefaultEfficiency() {
    if (this.defaultEfficiency < 0.5) {
      this.defaultEfficiency = 0.5;
    } else if (this.defaultEfficiency > 4.5) {
      this.defaultEfficiency = 4.5;
    }
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
   * Get current state of charge.
   * @return state of charge.
   */
  public double getStateOfCharge() {
    if (this.stateOfCharge > 1.0) {
      this.stateOfCharge = 1.0;
    } else if (this.stateOfCharge < 0.15) {
      this.stateOfCharge = 0.15;
    }
    return  this.stateOfCharge;
    }


  /**
   * set new value for state of charge.
   */
  public void setStateOfCharge(double stateOfCharge) {
    this.stateOfCharge = stateOfCharge;
    }


  /**
   * get new efficiency based on current temperature.
   * @param currentTemp currentTemp.
   */
  public void updateEfficiency(double currentTemp) {
      if (currentTemp > 77.0) {
        this.currentEfficiency = 0.85;
      }
      else if(currentTemp < 65.0) {
        if(currentTemp < 15.0) {
          this.currentEfficiency = 0.5;
        }
        else{
          double diff = (65 - currentTemp) / 100;
        this.currentEfficiency = 1 - diff;
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


