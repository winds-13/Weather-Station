package weatherStation;

public class WeatherStation{
	 WeatherReport[] wrArray = new WeatherReport[1000]; //create a main array stores all reports.
	 double highestTemp_Add = -300;
	 WeatherReport Report_HighestTemp = null;
	 int startPosition = 0;
	 int endPosition = 0;

  /** The name of this weather station */
  protected final String name;

  /** The id number of this weather station. Should be unique. */
  protected final int id;

  /** Initializes this weather station's name and id  */
  public WeatherStation(String nameOfStation, int idOfStation){
    this.name = nameOfStation;
    this.id   = idOfStation;
  }

  /** Getter for this weather station's name
   * @return the name of this weather station
   */
  public String getName(){ return this.name;  }

  /** Getter for this weather station's id number
   * @return the ID of this weather station
   */
  public int getID(){ return this.id; }

  /** Creates a weather report
   * @param temperature is a valid temperature object correspinding to
   * @param time is the time that the temoerature was recorded.
   * @return a weather report for this weather station recording
   *         the temperature and time of when the obervation was made.
   *                    an obervation at this weather station.
   */
  public WeatherReport makeReport(Temperature temperature, TimeStamp time){
	return new WeatherReport(temperature, time, this);
  }

	/** A nice String representation of a weather station object */
  @Override
  public String toString(){
    return this.name + " [id:" + this.id + "]";
  }
/* ----------------------------------------------------- */
/*                                                       */
/* END - complete the methods below these comments       */
/*                                                       */
/* ----------------------------------------------------- */
/* --------------------------------------------------------------------------/*
/* --------------------------------------------------------------------------/*



  /** Adds a report to the station.
   *
   * @param report is a WeatherReport to be added to this WeatherStation
   * @return this WeatherStation. (Do NOT alter the return statement of this
   *         method.)
   *
   */
   
  public WeatherStation addReport(WeatherReport report){
	// record report with highest temperature
	if (report.getTemperature().getTemp() >= highestTemp_Add){
		highestTemp_Add = report.getTemperature().getTemp();
		Report_HighestTemp = report; 
	}
	//append the report to first non-null position
	for (int i=0;i<wrArray.length;i++){
		if (wrArray[i]==null){
			wrArray[i] = report;
			break;
		}
	}
	
	return this; 
  }


    /** Returns the most recently added report.
      *
      *
      * @return the most recently added WeatherReport to this weatherstation.
      *         If no reports have ever been added then returns null.
      */
    public WeatherReport getMostRecentReport(){	
	
	if (getReports().length == 0){
		return null;
	}else{
		 return getReports()[0];
    }
	}	


  /** Returns the last 10 weather getReports added to this weather station.
	 * <p>
	 * If there haven't been 10 reports added then return as many as has been added.
   *
   * @return an array of the last 10 added WeatherReports (in the order
   *         that they were added). The most recently added report is the
   *         first element in the array.
   *         If there has been less than 10 reports added to this
   *         weatherstation, then returns as many reports as there is.
   *         The returned array must be the same size as the number of
   *         reports returned.
   */
	public WeatherReport[] getAll(){
		//a method used to return all report ever recorded
		return wrArray;
	}
	
	
	public WeatherReport[] getReports(){
		//cut a section of the main array with size 10, record the start and end index of intercept.
		int size = 0;
		for (int i=wrArray.length-1;i>=0;i--){
			if (wrArray[i]!=null){
				endPosition = i;
				if (i-10>=0){//if there are more than 10 reports in main array
					startPosition = i-10;
					size = endPosition-startPosition-1;
					if (size<=0){//if there is no report, return empty list.
						WeatherReport[] empty = new WeatherReport[0];
						return empty;
					}
					WeatherReport[] reportsArray = new WeatherReport[size+1];
					int x=0;
					for (int j=endPosition;j>startPosition;j--){//append report backwards 
						reportsArray[x] = wrArray[j];		//The most recently added report is the first element in the array.
						x++;
					}
					return reportsArray;
				}else{//if the main array does not have 10 reports
					startPosition = 0;
					size = endPosition-startPosition;
					if (size<=0){
						WeatherReport[] empty = new WeatherReport[0];
						return empty;
					}
					WeatherReport[] reportsArray = new WeatherReport[size+1];
					int x=0;
					for (int y=endPosition;y>=startPosition;y--){
						reportsArray[x] = wrArray[y];
						x++;
					}
					return reportsArray;
				}
				
			}
		}
	WeatherReport[] empty = new WeatherReport[0];
		return empty;
  }


  /** Returns a weather report with highest recorded temperature
   * of any report ever added to this WeatherStation.  If there have been
   * multiple reports with the same highest temperature, the most recently
   * added report is returned,.
   *
   * @return a WeatherReport that has the highest recorded temperature
   *         of any report ever added to this WeatherStation. Returns
   *         null if no report has ever been added to this weatherstation.
   */
  public WeatherReport getReportWithMaxTemp(){
	double maxTemp=-4000;
	WeatherReport report_MaxTemp = null;
	for (int i=0;i<wrArray.length;i++){
		if (wrArray[i]==null){//if there are reports were removed, there will be a position of null, need to skip;
			continue;
		}
		if (wrArray[i].getTemperature().getTemp()>maxTemp){//iterate the main array
			maxTemp = wrArray[i].getTemperature().getTemp();//search for the report with highest temperature
			report_MaxTemp = wrArray[i];
		}
	}
	return report_MaxTemp;
}
}
