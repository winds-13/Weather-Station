package weatherStation;


public class Temperature{
	Scale scale;
	double temp;
	/** Initializes a temperature object with given value in Celsius
	 *  <p>
	 *  If the initial temperature is less than -273.15C then the temperature
	 *  object will be initialized with -273.15C.
   *
	 * @param temp is the initial temperature in Celsius.
	 */
  public Temperature(double temp){
	double a =temp;
		if (a<-273.15){
			a=-273.15;
		}
		this.temp = a;
		this.scale = Scale.CELSIUS;
		}

	


	/** Initializes a temperature object with given value using the specified scale
   * <p>
	 * If the temperature is lower than absolute zero, in the given scale,
	 * then set the temperature to absolute zero (in that scale).
   * <p>
 	 * Usage: new Temperature(12.3, Scale.KELVIN)
	 *
	 * @param temp is the initial temperature in the scale provided in
   *        the second argument.
	 * @param scale is the scale of initial temperature and must be a constant
	 *        defined in the Scale enum type.
	 */
  public Temperature(double temp, Scale scale){
	switch(scale){
		case CELSIUS:
			if (temp<-273.15){
				this.temp = -273.15;
			}else{
				this.temp = temp;
			}
			this.scale = Scale.CELSIUS;
			break;
		case FAHRENHEIT:
			if (temp<-459.67){
				this.temp = -459.67;
			}else{
				this.temp = temp;
			}
			this.scale = Scale.FAHRENHEIT;
			break;
		case KELVIN:
			if (temp<0){
				this.temp = 0;
			}else{
				this.temp = temp;
			}
			this.scale = Scale.KELVIN;
			break;
		default:
			this.scale = Scale.CELSIUS;
			break;
  
	}
  
  
  }

	/** Initializes a new temperature object that is a copy of the
   *  temperature object parameter.
   *
   * That is, it makes a new object that is a copy of the input object.
   *
	 * @param temp is a non-null temperature object
	 */
  public Temperature(Temperature temp){
	this.temp = temp.getTemp();
	char tempScale = temp.getScale();
	if (tempScale == 'C'){
		this.scale = Scale.CELSIUS;
  }else if (tempScale == 'F'){
	  this.scale = Scale.FAHRENHEIT;
  }else if (tempScale =='K'){
	  this.scale = Scale.KELVIN;
  }
  }



	/** getter for the scale
	 * <p>
	 * The output of this getter method must always be the first letter of one
	 * of the names in the Scales enum class. It must be the upper case letter.
	 * <p>
	 * Example: t = new Temperature(12.3, Scale.KELVIN);
	 *          t.getScale() will then output 'K'
	 *
	 * @return the first letter (in upper case) of the string representation of the
	 *         current scale of this object.
	 */
  public char getScale(){
    if (this.scale.equals(Scale.CELSIUS)){
		return 'C';
	}else if(this.scale.equals(Scale.FAHRENHEIT)){
		return 'F';
	}else if(this.scale.equals(Scale.KELVIN)){
		return 'K';
	}
	
	
	return 'X';
  }

	/** getter for the temperature
	 *
	 * @return the temperature of the object using the current scale
	 */
  public double getTemp(){
     return this.temp;
  }


  /** setter for scale
	 *
	 * @param scale is the new scale of the temperature and must be
	 *        a constant from the Scale enum type. The next time you
	 *        call getTemp(), the temperature will be output in this scale.
   * @return a reference to this object.
	 */
  public Temperature setScale(Scale scale){
	switch(scale){
		case CELSIUS:
			if (this.scale==Scale.FAHRENHEIT){
				this.temp = (this.temp - 32)*5/9;
			}else if (this.scale == Scale.KELVIN){
				this.temp -= 273.15;
			}
			this.scale = Scale.CELSIUS;
			break;
		case FAHRENHEIT:
			if (this.scale==Scale.CELSIUS){
				this.temp = (this.temp*1.8)+32;
			}else if (this.scale==Scale.KELVIN){
				this.temp = this.temp *1.8 -459.67;
			}
			this.scale = Scale.FAHRENHEIT;
			break;
		case KELVIN:
			if (this.scale==Scale.CELSIUS){
				this.temp = this.temp + 273.15;
			}else if (this.scale==Scale.FAHRENHEIT){
				this.temp = (this.temp + 459.67)*5/9;
			}
			this.scale = Scale.KELVIN;
			break;
		default:
			this.temp = -3670;
	}

  return this; 

	}


	/** setter for temperature
	 *
	 * @param temp is the new temperature (in the object's current scale)
   * @return a reference to this object.
	 */
  public Temperature setTemp(double temp){
	this.temp = temp;
    return this;  
	}

	/** setter for temperature
	 *
	 * @param temp is the new temperature
	 * @param scale is the scale of the new temperature. It must be
	 *        a constant from the Scale enum type.
   * @return a reference to this object.
   */
  public Temperature setTemp(double temp, Scale scale){
    this.temp = temp;
	switch(scale){
		case CELSIUS:
			this.scale = Scale.CELSIUS;
			if (temp<-273.15){
				this.temp = -273.15;
			}else{
				this.temp = temp;
			}
			break;
	
		case FAHRENHEIT:
			this.scale = Scale.FAHRENHEIT;
			
			if (temp<-459.67){
				this.temp = -459.67;
			}else{
				this.temp = temp;
		}
			break;

		case KELVIN:
			this.scale = Scale.KELVIN;
			
			if (temp<0){
				this.temp = 0;
			}else{
				this.temp = temp;
		}
			break;
		default:
			this.temp = 0;
			this.scale = Scale.CELSIUS;
			break;
  
	}
	
	return this; 
	}

	/** setter for temperature
	 *
	 * @param temp is the new temperature.
	 * @param scale is a string representing one of the three scales.
   * @return a reference to this object.
   */
  public Temperature setTemp(double temp, String scale){
	
	char a = scale.charAt(0);
	
    if(a==('C')||a==('c')){
		this.scale = Scale.CELSIUS;
		if (temp<-273.15){
			this.temp = -273.15;
		}else{
			this.temp = temp;
		}
	}else if(a==('F')||a==('f')){
		this.scale = Scale.FAHRENHEIT;
		if (temp<-459.67){
			this.temp = -459.67;
		}else{
			this.temp = temp;
		}
		
	}else if(a==('K')|a==('k')) {
		this.scale = Scale.KELVIN;
		if (temp<0){
			this.temp = 0;
		}else{
			this.temp = temp;
		}
	}		
		
		return this;  
  }



  /** String representation of a temperature object    */
	@Override
  public String toString(){
    return "" + this.getTemp() + this.getScale();
  }

}
