#include <vector>
#include <iostream>
#include <sstream>

#include "TorqueCurve.h"

using namespace std;

/*
 *Summary:	Constructor
 *Parameters:	torqueFile - the information containing the torque information
 *Returns:	None
 *Author: Paul Fioravanti
 */
TorqueCurve::TorqueCurve(char* torqueFile)
{
	FILE *fp = fopen(torqueFile, "r");  //initialize pointer to a file

	string sTorqueFile;
	char ch = getc(fp);  //get first char of Torque file
               
    while (ch != EOF) //get all chars in Torque file and assign to string
    { 
		sTorqueFile += ch;
        ch = getc(fp);
    } 
    fclose(fp);

	char *torqueTokenPtr;  // string token pointer
    vector<char *> torqueArgs; //vector for the arguments in the torque file

	//Turns sTorqueFile into cString and gets rid of const on returned cString
    char *cTorqueFile = const_cast<char *> (sTorqueFile.c_str()); 
               
    torqueTokenPtr = strtok(cTorqueFile, " "); //tokenize input array for values delimited by spaces
               
    while (torqueTokenPtr != NULL)
    {
       torqueArgs.push_back(torqueTokenPtr);  //put token in a separate vector compartment
       torqueTokenPtr = strtok(NULL, " ");  //get next token
    }
	
	/*
		set number of points: at this stage, it is already known that the first 
		argument is an integer, so the char is simply changed into its 
		integer value and assigned.  No further checking needed.
	 */
	points = atoi(torqueArgs.at(0));  

	//create arrays of doubles for the torques and rpms
	rpmValues = new double[points];
	torqueValues = new double[points];

	//put values of torques and rpms in their respective arrays
	int ta = 1;
	for (int i = 0; i < points; i++)
	{
		rpmValues[i] = atof(torqueArgs.at(ta));
		ta+=2;
	}
	
	int tb = 2;
	for (int m = 0; m < points; m++)
	{
		torqueValues[m] = atof(torqueArgs.at(tb));
		tb+=2;
	}

	torqueArgs.clear();
}

/*
 *Summary:	Constructor: default - does nothing
 *Parameters:	None
 *Returns:	None
 *Author: Paul Fioravanti
 */
TorqueCurve::TorqueCurve()
{

}

/*
 *Summary:	Destructor
 *Parameters:	None
 *Returns:	None
 *Author: Paul Fioravanti
 */
TorqueCurve::~TorqueCurve()
{
	//arrays of values for rpm and torque are created here and hence
	//destroyed here
	delete[] rpmValues;
	delete[] torqueValues;
}

/*
 *Summary:	Giving a specific RPM, calculates the torque of the engine.
 *Parameters:	None
 *Returns:	None
 *Author: Paul Fioravanti
 */
double TorqueCurve::getTorqueAt(double rpm)
{
	//torque values for above and below the rpm
	double y2;
	double y1;
	
	//first test: return first torque value if passed in rpm is less than it 
	if (rpm < rpmValues[0])
	{
		return torqueValues[0]; 
	}
	//second test: return last torque value if passed in rpm is more than it
	else if (rpm > rpmValues[getPointCount()-1])
	{
		return torqueValues[getPointCount()-1];
	}
	else
	{
		for (int i = 1; i < getPointCount() -2; i++)
		{
			//third test: if rpm is exactly equal to one in the torque curve, return 
			//corresponding torque value
			if (rpm == rpmValues[i])
				return torqueValues[i];
			else
			{
				if (rpm < rpmValues[i])  //iterate through until rpm is less than a value
				{
					y2 = torqueValues[i-1]; //assign previous value
					y1 = torqueValues[i];   //assign current value, which is more than rpm
					
					//return value based on formula for similar triangles theorem
					//calculate an appropriate torque value for the given rpm value
					//that lies between two rpm points that match up to particular
					//torque values in the curve
					return y2 + (rpm - rpmValues[i-1])*(y1 - y2) / (rpmValues[i] - rpmValues[i-1]);
				}
			}
		}
		return torqueValues[getPointCount()-1];  //return last torque value is pass through fails
	}
}

/*
 *Summary:	Gets how many RPM to Torque points there are in the file, and hence memory
 *Parameters:	None
 *Returns:	None
 *Author: Paul Fioravanti
 */
int TorqueCurve::getPointCount() const
{
	return points;
}
