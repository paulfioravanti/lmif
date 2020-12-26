#include <iostream>
#include "Differential.h"
#include "Gearbox.h"

using namespace std;

/*
 *Summary:	Constructor
 *Parameters:	ratio - the ratio of the differential to set
 *Returns:	None
 *Author: Paul Fioravanti
 */
Differential::Differential(double ratio)
{
	gearRatio = ratio;
}

/*
 *Summary:	Destructor
 *Parameters:	None
 *Returns:	None
 *Author: Paul Fioravanti
 */
Differential::~Differential()
{
	//creates nothing, so deletes nothing
	//object itself deleted in Car
}

/*
 *Summary:	Set the previous item in the drive train
 *Parameters:	gearbox - the Gearbox object to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Differential::setGearbox (Gearbox *gearbox)
{
	this->gearbox = gearbox;
}

/*
 *Summary:	Set the next item in the drive train
 *Parameters:	wheels - the Wheels object to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Differential::setWheels(Wheels *wheels)
{
	wheel = wheels;
}

/*
 *Summary:	Change the differential gearing ratio
 *Parameters:	ratio - the ratio to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Differential::setRatio(double ratio)
{
	double oldRPM = inputRPM / gearRatio;
	gearRatio = ratio;
	setOutputRPM(oldRPM);
}

/*
 *Summary:		Function that the gearbox uses to set the differential's state
 *Parameters:	rpm - the RPM to set
 *				torque - the torque to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Differential::setInput(double rpm, double torque)
{
	inputRPM = rpm;
	inputTorque = torque;
	//pass input information to wheels: forward propogation
	wheel->setInput(rpm / gearRatio, torque * gearRatio); 
}

/*
 *Summary:	Sets the output RPM of the gearbox.  Reverse propagation method
 *Parameters:	rpm - the RPM to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Differential::setOutputRPM(double rpm)
{
	gearbox->setOutputRPM(rpm * gearRatio);
}

/*
 *Summary:	Prints out the settings for the differential
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Differential::printDifferentialSpecs() 
{
	cout << "Differential:" << endl;
	cout << " Ratio = " << gearRatio << endl;
	cout << "\n";
}

/*
 *Summary:	Prints out the state of the differential
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Differential::printDifferentialState() 
{
	cout << "Differential:" << endl;
	cout << " Input: " << inputRPM << "rpm and " << inputTorque << "NM" << endl;
	cout << " Output: " << inputRPM / gearRatio << "rpm and " << inputTorque * gearRatio << "NM" << endl;
	cout << "\n";
}
