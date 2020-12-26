#include <iostream>
#include "Gearbox.h"

using namespace std;

/*
 *Summary:	Constructor
 *Parameters:	gears - the number of gears in the gearbox
 *				ratios - the ratios for each of the gears
 *Returns:	None
 *Author: Paul Fioravanti
 */
Gearbox::Gearbox(int gears, double *ratios)
{
	this->gears = gears; //set initial number of gears
	gear = 1; //sets car into default of first gear
	gearRatios = new double[gears]; //create set of gear ratios
	for (int i = 0; i < gears; i++)  //assign values to gearRatios
	{
		gearRatios[i] = ratios[i];
	}
}

/*
 *Summary:	Destructor
 *Parameters:	None
 *Returns:	None
 *Author: Paul Fioravanti
 */
Gearbox::~Gearbox()
{
	delete[] gearRatios; //gearRatios created in Gearbox, so destroyed here.
}

/*
 *Summary:	Sets the Differential of the gearbox
 *Parameters:	differential - a Differential object containing differential information
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Gearbox::setDifferential(Differential *differential)
{
	this->differential = differential;
}

/*
 *Summary:	Sets the Engine
 *Parameters:	engine - an Engine object
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Gearbox::setEngine(Engine *engine)
{
	this->engine = engine;
}

/*
 *Summary:	Given a specific gear, sets the ratio of that gear
 *Parameters:	gear - the gear whose ratio to set
 *				ratio - the ratio to set for the gear
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Gearbox::setRatio(int gear, double ratio)
{
	/*
		Negative checking done in main class, so checking here is done for positive
		gears that are not part of the gear box.  Error is thrown if invalid gear
		chosen.
	 */
	if (gear <= gears)  
	{
		double oldRPM = inputRPM / gearRatios[this->gear-1];
		gearRatios[gear-1] = ratio;
		//let engine know of change to gearbox ratio if change is in current gear
		if (gear == this->gear)
		{
			//double oldRPM = inputRPM / ratio;  //get current rpm
			setOutputRPM(oldRPM); //pass rpm information to engine
		}
	}
	else
	{
		cout << "Error - Selected gear is higher than the number of gears in the gearbox." << endl;
	}
}

/*
 *Summary:	Sets the number of gears in this gearbox
 *Parameters:	gears - the number of gears to set
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Gearbox::setNumberOfGears(int gears)
{
	if (gears > gear)
	{
		int oldGears = this->gears;

		//copy all values of oldGear ratios into newGearRatios and fill the rest of the
		//blanks with the final value of oldGear ratios.  Then delete oldGear Ratios.
		if (gears > oldGears)
		{
			double *newGearRatios = new double[gears];
			for (int i = 0; i < oldGears; i++)
			{
				newGearRatios[i] = gearRatios[i];
			}
			for (int j = oldGears; j < gears; j++)
			{
				newGearRatios[j] = gearRatios[oldGears-1];
			}
			delete[] gearRatios;
			gearRatios = newGearRatios;
		}
		//copy all values of oldGear ratios into newGearRatios up to
		//the length of newGearRatios.  Then delete oldGear Ratios.
		else if (gears < oldGears)
		{
			double *newGearRatios = new double[gears];
			for (int i = 0; i < gears; i++)
			{
				newGearRatios[i] = gearRatios[i];
			}
			delete[] gearRatios;
			gearRatios = newGearRatios;
		}

		this->gears = gears;  //set number of gears
	}
	else
	{
		cout << "Error - Selected number of gears is lower than current gear.\nChange into a lower gear and set the number of gears within your range." << endl;
	}
}

/*
 *Summary:	Engine uses this function to tell the gearbox what the current RPM and torque is
 *Parameters:	rpm - the current rpm
 *					torque - the current torque
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Gearbox::setInput(double rpm, double torque)
{
	inputRPM = rpm;
	inputTorque = torque;
	//pass input to the differential
	differential->setInput(rpm/gearRatios[gear-1], torque*gearRatios[gear-1]);
}

/*
 *Summary:	Changes the gear: affects how fast the output RPM is to the differential
 *Parameters:	gear - the gear to change into
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Gearbox::changeGear(int gear)
{
	/*
		Negative checking done in main class, so checking here is done for positive
		gears that are not part of the gear box.  Error is thrown if invalid gear
		chosen.
	 */
	if (gear <= gears)
	{
		double rpm = inputRPM / gearRatios[this->gear-1];  //get current rpm
		this->gear = gear;  //change gear
		setOutputRPM(rpm); //pass rpm information to engine
	}
	else
	{
		cout << "Error - Selected gear is higher than the number of gears in the gearbox." << endl;
	}
	
}

/*
 *Summary:	Works out how fast the input should spin to achieve this output RPM.
 *				Information is then passed back to the Engine.
 *Parameters:	rpm - the output RPM
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Gearbox::setOutputRPM(double rpm)
{
	inputRPM = rpm * gearRatios[gear-1]; //work out input rpm for engine
	engine->setRPM(inputRPM); //pass information to engine
}

/*
 *Summary:	Prints out the current specification of this gearbox
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Gearbox::printGearboxSpecs()
{
	cout << "Gearbox:" << endl;
	cout << " Number of gears = " << gears << endl;
	cout << " Gear Ratios = ";
	for (int i = 0; i < gears; i++)
	{
		cout << gearRatios[i] << " ";
	}
	cout << "\n";
	cout << "\n";
}

/*
 *Summary:	Prints out current state of this gearbox
 *Parameters:	None
 *Returns:	void
 *Author: Paul Fioravanti
 */
void Gearbox::printGearboxState()
{
	cout << "Gearbox:" << endl;
	cout << " In gear " << gear << " (" << gearRatios[gear -1] << ":1)" << endl;
	cout << " Input: " << inputRPM << " rpm and " << inputTorque << " NM" << endl;
	cout << " Output: " << inputRPM / gearRatios[gear -1] << "rpm and " << inputTorque * gearRatios[gear -1] << "NM" << endl;
	cout << "\n";
}
