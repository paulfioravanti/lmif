#ifndef TORQUECURVE_H
#define TORQUECURVE_H

/*
 *Summary: Models the torque of the engine.  It reads in the torque information from a file and calculates
 *			  the approximate torque at any rpm.
 *Parameters: torqueFile - a char array that contains information about the torque
 *Author: Paul Fioravanti
 */
class TorqueCurve
{
	private:
		int points;  //number of points from the torque file
		double* rpmValues;  //list of RPM values that correspond to the torqueValues
		double* torqueValues;  //list of torqueValues at the rpmValues
		
	public: //see implementation files for function descriptions
		explicit TorqueCurve(char *torqueFile);
		TorqueCurve();
		~TorqueCurve();
		double getTorqueAt(double rpm);
		int getPointCount() const;
};

#endif
