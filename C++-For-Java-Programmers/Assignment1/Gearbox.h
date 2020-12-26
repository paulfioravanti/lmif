#ifndef GEARBOX_H
#define GEARBOX_H

#include "Differential.h"
#include "Engine.h"

class Engine;  //forward declaration of class Engine to resolve circular dependancy

/*
 *Summary: Using the torque and the RPM along with the gear ratios, the gearbox calculates 
 *			  the approximate torque at any RPM.
 *Parameters: gears - the number of gears in the gearbox
 *				  ratios - the set of ratios used in the gearbox
 *Author: Paul Fioravanti
 */
class Gearbox
{
	public:
		int gears;  //number of gears in the gearbox
		int gear;  //current gear
		double *gearRatios;  //list of all gear-ratios to use
		double inputRPM;  //current RPM
		double inputTorque;  //current Torque
		Differential *differential;  //reference to next item in drive train
		Engine *engine;  //reference to Engine connected to the gearbox
		
		Gearbox(int gears, double *ratios); //see implementation files for function descriptions
		~Gearbox();
		void setDifferential(Differential *differential);
		void setEngine(Engine *engine);
		void setRatio(int gear, double ratio);
		void setNumberOfGears(int gears);
		void setInput(double rpm, double torque);
		void changeGear(int gear);
		void setOutputRPM(double rpm);
		void printGearboxSpecs();
		void printGearboxState();
};

#endif
