#include <iostream>
#include "Wheels.h"
#include "Differential.h"

using namespace std;

/*
 *Summary:	Constructor
 *Parameters:	rimSize - the rim size of the wheels
 *					width - the width of the wheels
 *					profile - the profile of the wheels
 *Returns:	None
 *Author: Paul Fioravanti
 */
Wheels::Wheels(int rimSize, double width, double profile)
{
	this->rimSize = rimSize;
	this->width = width;
	this->profile = profile;
	circumference = calculateCircumference(rimSize, profile, width);  //circumference of the wheel
}

/*
 *Summary:	Destructor
 *Parameters:	None
 *Returns:	None
 *Author: Paul Fioravanti
 */
Wheels::~Wheels()
{
	//nothing created in Wheels, so nothing destroyed
}

/*
 *Summary:	Calculates the current circumference of the wheels
 *Parameters:	rimSize - the rim size of the wheels
 *					profile - the profile of the wheels
 *					width - the width of the wheels
 *Returns:	double - the circumference
 *Author: Paul Fioravanti
 */
double Wheels::calculateCircumference(int rimsize, double profile, double width)
{	
	//circumference = 2*pi*r = pi*d
	//change rimSize to mm by multiplying by 25.4
	//express profile as a percentage and multiply it by the width
	//add the two together and times by pi.
	double circumference = 3.14 * ((rimSize*25.4)+ 2* ((profile/100)*width));
	
	return circumference;
}

/*
 *Summary:	Set the previous item in the drive train
 *Parameters:	diff - a Differential object
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Wheels::setDifferential(Differential* diff)
{
	differential = diff;
}

/*
 *Summary:	Sets the rim size of the wheels
 *Parameters:	size - the rim size to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Wheels::setRimSize(int size)
{
	rimSize = size;
}

/*
 *Summary:	Primary function that the drive train transfers the state of the system to the Wheels class
 *				Takes input rpm and torque to set internal state
 *Parameters:	rpm - the RPM to set
 *					torque - the torque to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Wheels::setInput(double rpm, double torque)
{
	this->rpm = rpm;
	this->torque = torque;
}

/*
 *Summary:		Set the profile size.
 *Parameters:	profile - the profile to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Wheels::setProfile(double profile)
{
	this->profile = profile;
}

/*
 *Summary:	Sets the speed.  From the parameter speed, and details of the wheels, calculates
 *				what RPM the wheel needs to rotate at, then call setOutputRPM of the Differential class
 *Parameters:	speed - the speed to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Wheels::setSpeed(double speed)
{
	//call opposite equation of getSpeed()
	double circ = calculateCircumference(rimSize, profile, width);
	double tempRPM = (speed * 100000 / 6) / circ;
	differential->setOutputRPM(tempRPM);
}

/*
 *Summary:	Gets the current speed
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
double Wheels::getSpeed()
{
	//circumference * rpm returns mm per minute
	//1 mm per minute = 6 * 10^-5 kph	
	//unknown whether current circumference is actual circumference, so
	//function is called rather than directly from field variable
	double circ = calculateCircumference(rimSize, profile, width);
	double speed = circ * rpm / 100000 * 6;
	return speed;
}

/*
 *Summary:	Gets the torque
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
double Wheels::getTorque() const
{
	return torque;
}

/*
 *Summary:	Prints out the specs of the current wheels
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Wheels::printWheelSpecs()
{
	circumference = calculateCircumference(rimSize, profile, width); //get circumference
	cout << "Wheels:" << endl;
	cout << " Size = " << profile << "\\" << width << "R" << rimSize << endl;
	cout << " Wheel circumference is " << circumference << "mm" << endl; 
	cout << "\n";
}

/*
 *Summary:	Prints the state of the wheels
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Wheels::printWheelState()
{
	cout << "Wheels:" << endl;
	cout << " Wheels turning at " << rpm << "rpm" << endl;
	cout << " Car speed is " << getSpeed() << "kph (" << getSpeed() * 0.621371192 << "mph)" << endl;
	cout << "\n";
}

/*
 *Summary:	Updates system profile
 *Parameters:	width - the width of the new wheels
 *					profile - profile of the new wheels
 *					rimSize - rim size of the new wheels
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Wheels::changeProfile(double width, double profile, int rimSize)
{	
	double oldSpeed = getSpeed();  //assign current speed to temp variable
	//update profile
	this->width = width;
	this->profile = profile;
	this->rimSize = rimSize;
	//pass to differential so speed doesn't change and it calculates with new circumference
	setSpeed(oldSpeed);

}
