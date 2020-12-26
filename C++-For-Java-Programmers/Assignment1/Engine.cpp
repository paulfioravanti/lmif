#include <iostream>
#include <vector>
#include <sstream>

#include "Engine.h"

using namespace std;

/*
 *Summary:	Constructor
 *Parameters:	torqueFile - the file containing torque information
 *Returns:	None
 *Author: Paul Fioravanti
 */
Engine::Engine(char* torqueFile)
{
	//create new torqueCurve to use for default torque file
	torqueCurve = new TorqueCurve(torqueFile);   
	rpm = 1000;  //default value on Engine creation
}

/*
 *Summary:	Destructor
 *Parameters:	None
 *Returns:	None
 *Author: Paul Fioravanti
 */
Engine::~Engine()
{
	delete torqueCurve;  //torqueCurve created in Engine, so is deleted here
}

/*
 *Summary:	Gets the torque curve from the torqueFile.
 *				Old torqueFile is destroyed and replaced. 
 *Parameters:	torqueFile - the file containing torque information to load
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Engine::loadTorqueCurve(char* torqueFile)
{
	delete torqueCurve;  //deletes any old torqueFiles still in memory
	torqueCurve = new TorqueCurve(torqueFile); //new replacement torque file
}

/*
 *Summary:	Sets the gearbox for the Car
 *Parameters:	gearbox - A Gearbox object to be set as gearbox for the Car
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Engine::setGearbox(Gearbox* gearbox)
{
	this->gearbox = gearbox;
}

/*
 *Summary:	Sets the current RPM of the engine
 *Parameters:	rpm - the number of revs per minute
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Engine::setRPM(double rpm)
{
	this->rpm = rpm;
	outputPower();  //notify gearbox of rpm change
}

/*
 *Summary:	Gets the current RPM for the caller.
 *Parameters:	None
 *Returns:	double - the RPM
 *Author: Paul Fioravanti
 */
double Engine::getRPM() const
{
	return rpm;
}

/*
 *Summary:	Calculate the torque at the current RPM
 *Parameters:	None
 *Returns:	double - the torque at the current rpm
 *Author: Paul Fioravanti
 */
double Engine::getTorque()
{
	return torqueCurve->getTorqueAt(rpm); //interrogate TorqueCurve class
}

/*
 *Summary:	Transfer the rpm and torque to the gearbox input
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Engine::outputPower()
{
	gearbox->setInput(rpm, getTorque());
}

/*
 *Summary:	Print out the current settings
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Engine::printEngineSpecs()
{
	cout << "Engine:" << endl;
	cout << " " << torqueCurve->getPointCount() << " torque/rpm pairs plotted" << endl;
	cout << "\n";
}

/*
 *Summary:	Print out the current state of the engine
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Engine::printEngineState()
{
	cout << "Engine:" << endl;
	cout << " Engine at " << getRPM() << "rpm outputting " << torqueCurve->getTorqueAt(getRPM()) << " NM of torque" << endl;
	cout << "\n";
}
