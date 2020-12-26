#include <iostream>

#include "Car.h"

using namespace std;

/*
 *Summary:	Constructor
 *Parameters:	None
 *Returns:	None
 *Author: Paul Fioravanti
 */
Car::Car()
{   //insert test code to make sure initial file exists

	//insert default values into Car
	engine = new Engine("torque.txt");
	double ratios[] = {3.35, 1.93, 1.29, 1.00, 0.72}; 
	gearbox = new Gearbox(5, ratios);
	differential = new Differential(3.08);
	wheels = new Wheels(15, 60, 225);

	//call all setter functions on objects Car is linked to
	engine->setGearbox(gearbox);
	gearbox->setEngine(engine);
	gearbox->setDifferential(differential);
	differential->setGearbox(gearbox);
	differential->setWheels(wheels);
	wheels->setDifferential(differential);

	engine->outputPower();  //start the car
}

/*
 *Summary:	Destructor
 *Parameters:	None
 *Returns:	None
 *Author: Paul Fioravanti
 */
Car::~Car()
{
	//delete all objects the car creates
	delete engine;
	delete gearbox;
	delete differential;
	delete wheels;
}

/*
 *Summary:	Sets the RPM in the Engine
 *Parameters:	rpm - the revs per minute of the engine
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Car::setRpm(double rpm)
{
	engine->setRPM(rpm);
	engine->outputPower();  //send rpm information to gearbox
}

/*
 *Summary:	Sets the speed of the wheels
 *Parameters:	speed - the speed to set for the wheels
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Car::setSpeed(double speed)
{
	wheels->setSpeed(speed);
}

/*
 *Summary:	Sets the gear in the gearbox
 *Parameters:	gear - the gear number to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Car::selectGear(int gear)
{
	gearbox->changeGear(gear);
}

/*
 *Summary:	Prints the status of the entire car operation
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Car::printStatus()
{
	printCarState(); //redundant function
}

/*
 *Summary:	Sets the differential ratio in the Differential class
 *Parameters:	ratio - the ratio to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Car::changeDifferentialRatio(double ratio)
{
	differential->setRatio(ratio);
}

/*
 *Summary:	Sets the ratio of the specified gear
 *Parameters:	gear - the gear number whose ratio to set
 *					ratio - the ratio to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Car::changeGearboxRatio(int gear, double ratio)
{
	gearbox->setRatio(gear, ratio);
}

/*
 *Summary:	Sets the filename of the torque curve for the current car
 *Parameters:	filename - the torque curve file
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Car::changeTorqueCurve(char* filename)
{
	setTorque(filename); //redundant function
}

/*
 *Summary:	Sets the number of gears in the Car
 *Parameters:	gears - the number of gears in the car
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Car::setNumberOfGears(int gears)
{
	gearbox->setNumberOfGears(gears);
}

/*
 *Summary:	Sets the profile for the wheels in the Car
 *Parameters:	width - the width of the wheels
 *					profile - the profile of the wheels
 *					rimsize - the rim size of the wheels
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Car::changeWheelProfile(double width, double profile, int rimsize)
{
	wheels->changeProfile(width, profile, rimsize);
}

/*
 *Summary:	Loads a new torque file from the file "torque"
 *Parameters:	torque - the torque file to load
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Car::setTorque(char* torque)
{
	engine->loadTorqueCurve(torque);
}

/*
 *Summary:	Calls the printSpec functions of each of the parts of the car
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Car::printCarSpecs()
{
	cout << "\n";
	cout << "**************************" << endl;
	cout << "*** CAR SPECIFICATIONS ***" << endl;
	cout << "**************************" << endl;
	engine->printEngineSpecs();
	gearbox->printGearboxSpecs();
	differential->printDifferentialSpecs();
	wheels->printWheelSpecs();
	cout << "\n";
}

/*
 *Summary:	Calls the printState functions of each of the parts of the car
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Car::printCarState()
{
	cout << "\n";
	cout << "*****************" << endl;
	cout << "*** CAR STATE ***" << endl;
	cout << "*****************" << endl;
	engine->printEngineState();
	gearbox->printGearboxState();
	differential->printDifferentialState();
	wheels->printWheelState();
	cout << "\n";
}
