#ifndef DIFFERENTIAL_H
#define DIFFERENTIAL_H

#include "Wheels.h"

class Gearbox;  //forward declaration of class Gearbox to resolve circular dependancy

/*
 *Summary: Operates in a similar way to the Gearbox class except it only has one ratio (gear).
 *			  It outputs the modified RPMs to the wheels.  When the gear changes, it takes the information
 *			  from the Wheels class to recalculate the new RPMs. 
 *Parameters: ratio - the set of ratios to be used in the differential
 *Author: Paul Fioravanti
 */
class Differential
{
	private:
		double gearRatio;  //gearRatio of the differential
		double inputRPM;  //the input RPM (from the gearbox)
		double inputTorque;  //the inputTorque (from the gearbox)
		Gearbox* gearbox;  //reference to previous item in the drive train
		Wheels* wheel;  //reference to next item in the drive train
		
	public: //see implementation files for function descriptions
		explicit Differential(double ratio);
		~Differential();
		void setGearbox (Gearbox* gearbox);
		void setWheels(Wheels* wheels);
		void setRatio(double ratio);
		void setInput(double rpm, double torque);
		void setOutputRPM(double rpm);
		void printDifferentialSpecs();
		void printDifferentialState();
};

#endif
