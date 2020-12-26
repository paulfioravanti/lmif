#ifndef WHEELS_H
#define WHEELS_H

class Differential;  //forward declaration of class Differential to resolve circular dependancy

/*
 *Summary: 		Uses the RPMs along with the wheel measurements to calculate the speed the car is going.
 *			  		When the gear changes, the Wheels class recalculates the change in RPM necessary to 
 *			  		maintain the same speed.  This is then passed back to the Differential class.
 *Parameters: 	rimsize - the size of the wheel's rim
 *				  	width - the width of the wheels
 *				  	profile - profile of the wheel
 *Author: 		Paul Fioravanti
 */
class Wheels
{
	private:
		int rimSize;  //rim size of the current wheel
		double profile;  
		double rpm;  //current RPM of the wheel
		double torque;  //current torque applied to the wheel
		double width;  //width of the wheel  ****Check if this is okay
		double circumference;  //circumference of the wheel
		Differential* differential;  //reference to previous item in the drive train
		double calculateCircumference(int rimsize, double profile, double width);
		
	public: //see implementation files for function descriptions
		Wheels(int rimSize, double width, double profile);
		~Wheels();
		void setDifferential(Differential* diff);
		void setRimSize(int size);
		void setInput(double rpm, double torque);
		void setProfile(double profile);
		void setSpeed(double speed);
		double getSpeed();
		double getTorque() const;
		void printWheelSpecs();
		void printWheelState();
		void changeProfile(double width, double profile, int rimSize);
};

#endif
