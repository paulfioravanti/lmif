#ifndef CAR_H
#define CAR_H

#include "Engine.h"
#include "Gearbox.h"
#include "Differential.h"
#include "Wheels.h"

/*
 *Summary: Contains all the components of a car; is the top level of the car 
 *		   containment hierarchy
 *Parameters: None
 *Author: Paul Fioravanti
 */
class Car
{
	private:  //references to different classes
		Engine *engine;
		Gearbox *gearbox;
		Differential *differential;
		Wheels *wheels;
	
	public:  //see implementation files for function descriptions
		Car();
		~Car();
		void setRpm(double rpm); 
		void setSpeed(double speed); 
		void selectGear(int gear);
		void printStatus();
		void changeDifferentialRatio(double ratio);
		void changeGearboxRatio(int gear, double ratio);
		void changeTorqueCurve(char *filename);
		void setNumberOfGears(int gears);
		void changeWheelProfile(double width, double profile, int rimsize);
		void setTorque(char *torque);
		void printCarSpecs();
		void printCarState();
};

#endif
