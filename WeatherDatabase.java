package weatherStation;
/** A very simple database storing weather data */ 

public class WeatherDatabase{
	
	WeatherStation[] wsArray = new WeatherStation[500]; //create an main array stores all reports

	
	/** Creates an empty weather database  */
	public WeatherDatabase(){}
	


	/** Returns all weather stations in the database
	* in no particular order
	*
	* @return an array containing all weather stations that are in
	*         this database. Note that the size of the array should be equal to
	*         the result of <code>this.numberOfWeatherStations()</code>.
	*/
	public WeatherStation[] getWeatherStations(){
	
	//count how many reports in the main array.
		int count=0;
		int x = 0;
		for (int i=0;i<wsArray.length;i++){
			if (wsArray[i]!=null){
				count++;
			}
		}
		
	// create a new array with size same as the number of reports found in the main array.
	WeatherStation[] result = new WeatherStation[count];
	
	//add reports in new array
		for (int i=0;i<wsArray.length;i++){
			if (wsArray[i]!=null){
				result[x] = wsArray[i];
				x++;
			}
		}
	//return the new array with size equal number of reports
		return result;
	}

	/** Returns the number of weather stations in the database
	*
	* @return the number of weather stations in this database
	*/
	public int numberOfWeatherStations(){
		
		return getWeatherStations().length;
	}


	/** adds a weather station to this database
		* @param station is the weather station to add. It will always be non-null.
		* @return this weather station.
		*/
	
	public WeatherDatabase addWeatherStation(WeatherStation station){
		//check if the station is already added.(prevent repetition)
		for (int i=0; i<wsArray.length;i++){
			if(wsArray[i] == station){
				break;
			}
		//if no repetition, append this station to the first non-null position.
			if(wsArray[i] == null){
				wsArray[i] = station;
				break;
			}
		}
		return this;    
	}


	/** removes weather station with given id from this database
		* @param id is the ID number of the weather station to remove.
		* @return true if the specified weather station is successfully removed,
		*         returns false otherwise (i.e., if there was no weather station
		*         with the specified ID number in this database to begin with).
		*/
	public boolean removeWeatherStation(int id){
		
		//if a station was removed, its value will be null
		//skip null. set the station to null.
		for(int i=0; i<wsArray.length;i++){
			if (wsArray[i] == null){
				continue;
			}
			//skip all null value while looping. set the station to null.
			if (wsArray[i].getID() == id){
				wsArray[i]=null;
				return true;
			}
		}
		return false;
	}
	/** returns the highest temperature ever recorded by any weather station
	  * in the data base.
	  */
	public Temperature getMaxTemperature(){
		
		WeatherStation[] result = getWeatherStations();
		Temperature temperature_MaxTemp = null;
		double maxTemp = -3000;
		Temperature temperature_Max = null;
		for(int i=0;i<result.length;i++){
			if(result[i].getReportWithMaxTemp()==null||result[i].getReportWithMaxTemp().getTemperature()==null){
				continue;
			}else if(result[i].getReportWithMaxTemp().getTemperature().getTemp()>maxTemp){
				maxTemp = result[i].getReportWithMaxTemp().getTemperature().getTemp();
				temperature_Max = result[i].getReportWithMaxTemp().getTemperature();
			}
	}
	return temperature_Max;
	}
	/** returns the highest temperature on a specified day
		* that is recorded in a report in the weather stations in this database.
		*
		* @param day is the day to find the max temperature on. Note that this
		*            might be the special max temperature day that a
		*            weather station records.
		* @return  the temperature object with the highest recorded Temperature
		*          in the data base on the specified day.
		*/
	public Temperature getMaxTemperature(int day){
		
		WeatherStation[] result = getWeatherStations();
		Temperature returned = null;
		double max = -3000;
		for (int i=0;i<getWeatherStations().length;i++){
			for (int j=0;j<getWeatherStations()[i].getAll().length;j++){
				if (getWeatherStations()[i].getAll()[j]==null){
					continue;
				}
				if (getWeatherStations()[i].getAll()[j].getTime().getDay() == day){
					if (getWeatherStations()[i].getAll()[j].getTemperature().getTemp() >= max){
						max = getWeatherStations()[i].getAll()[j].getTemperature().getTemp();
						returned = getWeatherStations()[i].getAll()[j].getTemperature();
					}
				}
			}
		}
		return returned;
	}

	/** Computes the average temperature (over all weather stations) for the
	*  time period starting at startDay and ending at endDay (inclusive)
	*
	* @param startDay is the starting day
	* @param endDay is the ending endDay
	* @return the average temperature of all temperature reports for the time
	*         period startDay to endDay (inclusive) taken from all weather
	*         stations in this weather database.
	*/
	public double averageTemperature(int startDay, int endDay){
		WeatherStation[] result = getWeatherStations();
		int dayMin = 1000;
		int dayMax = -1000;
		double totalDay = endDay - startDay +1;
		int countDay = startDay;
		int testOccurTime=0;
		double testSum = 0;
		for (int x=0;x<totalDay;x++){
			double daySum = 0;
			double dayAve = 0;
			int y=1;
			for (int i=0;i<result.length;i++){
				for (int j=0;j<result[i].getAll().length;j++){
					if (result[i].getAll()[j] == null){
						continue;}
					if (result[i].getAll()[j].getTime().getDay() == countDay){
						testOccurTime+=1;
						testSum+=result[i].getAll()[j].getTemperature().getTemp();
						y++;
						}	
					}
				}
			countDay+=1;
		}
		double resulted = testSum/testOccurTime;		//sumTotal/totalDay;
		return resulted;
	}
}
		

		
	





