#ifndef ENGINE_H
#define ENGINE_H

#include "Gearbox.h"
#include "TorqueCurve.h"

/*
 *Summary: Generates the RPM and power based on the TorqueCurve class.
 *			  This information is then transferred to the Gearbox class
 *Parameters: torqueFile - a char array that contains information about the torque
 *Author: Paul Fioravanti
 */
class Engine
{
	private:
		double rpm;  //current RPM of the car (default 1000)
		TorqueCurve *torqueCurve;  //reference to TorqueCurve which tells the engine how it performs
		Gearbox *gearbox;  //Engine communicates through this reference to turn wheels/diff etc
		
	public: //see implementation files for function descriptions
		explicit Engine(char *torqueFile);
		~Engine();
		void loadTorqueCurve(char *torqueFile);
		void setGearbox(Gearbox *gearbox);
		void setRPM(double rpm);
		double getRPM() const;
		double getTorque();
		void outputPower();
		void printEngineSpecs();
		void printEngineState();
};

#endif
