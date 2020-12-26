#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <fstream>

using namespace std;

#include "Car.h"

/*
*Summary: 		Print out the Command list
*Parameters: 	None
*Return: 		Nothing
*Author: 		Paul Fioravanti
*/
void help()
{
	cout << "Command list:" << endl;
	cout << "Wheel" << endl;
	cout << " set wheel [width] [profile] [rim radius] - Change wheel profile" << endl;
	cout << "\n";
	
	cout << "Differential" << endl;
	cout << " set diff [ratio] - Change the differential ratio" << endl;
	cout << "\n";
	
	cout << "Gearbox" << endl;
	cout << " set gears [gears] - Change the number of gears in the gearbox" << endl;
	cout << " set gear [gear] [ratio] - Change the ratio of a gear" << endl;
	cout << " select gear [gear] - Change the gearbox to selected gear" << endl;
	cout << "\n";
	
	cout << "Engine" << endl;
	cout << " set torque [filename] - Change the torque curve of an engine" << endl;
	cout << " select rpm [rpm] - Changes the engine rpm" << endl;
	cout << " select speed [speed] - Sets rpm for speed in current gear" << endl;
	cout << "\n";
	
	cout << "General" << endl;
	cout << " print state - prints the current state of the car" << endl;
	cout << " print specifications - displays the specification of the drivetrain" << endl;
	cout << " help - print this message" << endl;
	cout << " quit - quits this program" << endl;
	cout << "\n";
}

/*
*Summary: 		Main function: begins program
*Parameters: 	None
*Return: 		0
*Author: 		Paul Fioravanti
*/
int main()
{
	//check that default file exists and that it is named "torque.txt", then
	//check whether default torque file has valid syntax to be passed in.
	//If default file is anything other than what is expected, program
	//prints a relevant message and then closes.

	////Begin test of initial Torque file///////

	FILE *initFp; //file pointer to initial file
	char initCh; //char to read in for initial file
	string initTorqueFile;
	if ((initFp = fopen("torque.txt", "r")) == NULL) //throw error if file doesn't exist
	{
		cout << "Error - Unable to find default file named \"torque.txt\".  Please check if default file exists." << endl;
		return 1;
	}

	initCh = getc(initFp);  //get first char of Torque file
					
	while (initCh != EOF) //get all chars in Torque file and assign to string
	{ 
		initTorqueFile += initCh;
		initCh = getc(initFp);
	} 
	fclose(initFp);  //close file once all chars have been added to string

	char *initTorqueTokenPtr;  // string token pointer
	vector<char *> initTorqueArgs; //vector for the arguments in the torque file
					
	//Turns sTorqueFile into cString and gets rid of const on returned cString
	char *initcTorqueFile = const_cast<char *> (initTorqueFile.c_str()); 
					
	initTorqueTokenPtr = strtok(initcTorqueFile, " "); //tokenize input array for words delimited by spaces
					
	while (initTorqueTokenPtr != NULL)
	{
		initTorqueArgs.push_back(initTorqueTokenPtr);  //put token in a separate vector compartment
		initTorqueTokenPtr = strtok(NULL, " ");  //get next token
	}
	
	/*
		Checks number of gears in initial torque file.
		If number of pairs is not an integer, or the number of gears is less than one
		(assumed that a torque file must at least have one pair plotted), then error thrown and
		program terminates.
     */
	string initTryNumGears = (string)initTorqueArgs.at(0);  //get pairs number
	istringstream initSt0(initTryNumGears);  //put through an istringstream
	int initNumGears;
	initSt0 >> initNumGears; //assign pairs number as integer
	if (initSt0.fail()) 
	{
		initTorqueArgs.clear();
		cout << "Error - First element of default torque file (number of rpm/torque pairs) is not an integer, therefore, file cannot be used." << endl;
		return 1;
	}
	string initJunk0;
	if (initSt0 >> initJunk0)
	{
		initTorqueArgs.clear();
		cout << "Error - First element of default torque file (number of rpm/torque pairs) is not an integer, therefore, file cannot be used." << endl;
		return 1;
	}
	if (initNumGears < 1)
	{
		initTorqueArgs.clear();
		cout << "Error - First element of default torque file (number of rpm/torque pairs) cannot be less than 1, therefore, file cannot be used." << endl;
		return 1;
	}
					
	//boolean that determines double error; needed so that only this error is thrown
	//when there are double errors and the size of the vector is incorrect
	bool initDoubleError = false;  
			
	//parse each other remaining values in torqueArgs to check if they are doubles
	//in a similar way to number of torque/rpm above.
	for (int i = 1; i < initTorqueArgs.size(); i++)  
	{
		string initValue = (string)initTorqueArgs.at(i);
		istringstream initSt(initValue);
		double initDoubleValue;
		initSt >> initDoubleValue;
		if (initSt.fail())
		{
			initTorqueArgs.clear();
			initDoubleError = true;
			cout << "Error - A rpm value or torque value in the default torque file is not a floating-point value, therefore, file cannot be used." << endl;
			return 1;
		}
		string initJunk;
		if (initSt >> initJunk)
		{
			initTorqueArgs.clear();
			initDoubleError = true;
			cout << "Error - A rpm value or torque value in the default torque file is not a floating-point value, therefore, file cannot be used." << endl;
			return 1;
		}
		if (initDoubleValue < 0)  //check if double value is negative 
		{
			initTorqueArgs.clear();
			initDoubleError = true;
			cout << "Error - A rpm value or torque value in the default torque file is less than 0, therefore, file cannot be used." << endl;
			return 1;
		}
		//check if torque curve is formed properly: rpm values must be in ascending order
		if (i != 1 && i % 2 == 1)   
		{
			string compValue = (string)initTorqueArgs.at(i-2);
			istringstream st(compValue);
			double compDouble;
			st >> compDouble;
			if (initDoubleValue < compDouble)
			{
				initTorqueArgs.clear();
				initDoubleError = true;
				cout << "Error - A rpm value in the default torque file is less than its predecessor, therefore, file cannot be used." << endl;
				return 1;
			}
		}
	}
					
	//test to make sure there are a correct number of values in the vector, in that there must
	//be a rpm value and a torque value for every declared pair
	if  (initDoubleError == false && (initNumGears * 2 + 1) != initTorqueArgs.size())
	{
		initTorqueArgs.clear();
		cout << "Error - Default torque file contains rpm or torque values for gears that do not exist, therefore, file cannot be used." << endl;
		return 1;
	}

	initDoubleError = false;
	initTorqueArgs.clear(); 

	////end Test of initial Torque File///////
	/*
		If the program proceeds to this stage, the initial torque file has been validated
		and can be used in the creation of this car.
     */
	
	Car *car = new Car();  //create new Car

	cout << "command or \"help\"> ";

	vector<char *> args; //vector of char * to store arguments from the user
	
	while (true)  //endless loop until user quits
	{
		//Due to the limitation in Visual Studio 6.0 concerning using the
		//getLine(cin, sInput) command (need to press return twice to get command,
		//and not allowing 2 commands consecutively to be entered),
		//I have been forced to read in user commands as a char[]
		//rather than a string object and use cin.getLine(input, 100).
		//I will set a constraint on input according to the longest relatively *reasonable*
		//legal string that can be input: set wheel xxxx.xxxx xxxx.xxxx xxxx, 
		//with some extra space
		//for good measure.  Therefore, command input will be limited to char[100].
		//Initial implementation of string object version caused crashes and I could
		//not find a work-around.  I did succeed with a string implementation on OSX and can
		//demonstrate if necessary.  Since the example application also suffers from
		//buffer overflow problems, it would seem this problem is unavoidable in VS 6.
		  
		char input[100]; //Command input read in string
		//puts the inputted line up to 50 chars into the input string 
		cin.getline(input, 100);
		//band-aid patch but at least the program doesn't crash unless you deliberately
		//and maliciously set out to do so, it just ends up printing many
		//'invalid command' lines.
		// ORIG: cin.clear(1000);  

		char *tokenPtr;  // string token pointer
		
		tokenPtr = strtok(input, " "); //tokenize input array for words delimited by spaces
		
		while (tokenPtr != NULL)
		{
            args.push_back(tokenPtr);  //put token into a separate vector compartment
            tokenPtr = strtok(NULL, " ");  //get next token
		}
		
		/*
		- 	Input will now be parsed against all possible menu options, including no command,
			calling a relevant function if the command is formatted properly. 
		-	Invalid command returned if incorrect command, or correct commands but extra unnecessary syntax added. 
		- 	Elements in vector are simply char arrays so they are cast into string objects for easier comparison.  
		- 	Every time a command parse finishes, the args vector is cleared before loop continues.
		*/
		if (args.size() == 0)  //for commands that consist of line break only
		{
            args.clear();
            cout << "command or \"help\"> ";
            continue;
		}
		
		if ((string)args.at(0) == "help")
		{
            if (args.size() == 1)  //continue parsing if no args
            {
				help(); //print out command list
				args.clear();
				cout << "command or \"help\"> ";
				continue;
            }
            else 
            {
				args.clear();
				cout << "Error - help command incorrectly formatted.\nType \"help\" (no quotes) for valid command details." << endl;
				cout << "command or \"help\"> ";
				continue;
            }
		}
		else if ((string)args.at(0) == "quit")
		{
            if (args.size() == 1) //continue parsing if no args
            {
				args.clear();
				break; //quits program
            }
            else 
            {
				args.clear();
				cout << "Error - quit command incorrectly formatted.\nType \"help\" (no quotes) for valid command details." << endl;
				cout << "command or \"help\"> ";
				continue;
            }
		}
		else if ((string)args.at(0) == "set" && args.size() > 1)
		{
            if ((string)args.at(1) == "wheel")
            {
				if (args.size() == 5)  //only continue parsing the set wheel command if 3 other args are entered
				{
               	/*
				- Each value in vector args 2-4 is cast to a string object
				- an istringstream object is called on each of the strings
				- istringstream attempts assignment to a primative type
				- istringstream object parses width and profile to ensure they are doubles (ints promoted)
				- istringstream object parses radius to ensure it is an int
				- specific error messages are printed if assignments fail and loops back to command line.
				- variables used relate to the arg value they're parsing (stX, junkX)
				*/
					string tryWidth = (string)args.at(2);
					istringstream st2(tryWidth);
					double width;
					st2 >> width;  //attempted assignment
					if (st2.fail())
					{
						args.clear();
						cout << "Error - width must be a floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					string junk2;
					if (st2 >> junk2)
					{
						args.clear();
						cout << "Error - width must be a floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					if (width <= 0)  //bounds checking for width: must not be negative or 0
					{
						args.clear();
						cout << "Error - width must be a positive floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}

					
					string tryProfile = (string)args.at(3);
					istringstream st3(tryProfile);
					double profile;
					st3 >> profile;
					if (st3.fail())
					{
						args.clear();
						cout << "Error - profile must be a floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					string junk3;
					if (st3 >> junk3)
					{
						args.clear();
						cout << "Error - profile must be a floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					if (profile <= 0)  //bounds checking for profile: must not be negative
					{
						args.clear();
						cout << "Error - profile must be a positive floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					
					string tryRimsize = (string)args.at(4);
					istringstream st4(tryRimsize);
					int rimsize;
					st4 >> rimsize;
					if (st4.fail())
					{
						args.clear();
						cout << "Error - rimsize must be an integer number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					string junk4;
					if (st4 >> junk4)
					{
						args.clear();
						cout << "Error - rimsize must be an integer number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					if (rimsize < 1)  //bounds checking for rimsize: must not be less than 1
					{
						args.clear();
						cout << "Error - rimsize must be a positive integer number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					
					car->changeWheelProfile(width, profile, rimsize);  //call to set wheel in car

					args.clear();  
					cout << "command or \"help\"> "; 
					continue;  
				}
				else
				{
					args.clear();
					cout << "Error - set wheel command requires 3 parameters: width (floating-point), profile (floating-point), and rim size (integer).\nType \"help\" (no quotes) for valid command details." << endl;
					cout << "command or \"help\"> ";
					continue;
				}
            }
            else if ((string)args.at(1) == "diff")
            {
				if (args.size() == 3)  //only continue parsing the set diff command if 1 other arg is entered
				{
               	/*
				- Value in vector args 2 is cast to a string object
				- an istringstream object is called on it
				- istringstream object parses ratio to ensure it is a double (ints promoted)
				- specific error messages are printed if assignment fails and loops back to command line.
				- variables used relate to the arg value they're parsing (stX, junkX)
				*/
					string tryRatio = (string)args.at(2);
					istringstream st2(tryRatio);
					double ratio;
					st2 >> ratio;
					if (st2.fail())
					{
						args.clear();
						cout << "Error - ratio must be a floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					string junk2;
					if (st2 >> junk2)
					{
						args.clear();
						cout << "Error - ratio must be a floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					if (ratio <= 0)  //bounds checking for ratio: must not be negative
					{
						args.clear();
						cout << "Error - ratio must be a positive floating-point number..\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					
					car->changeDifferentialRatio(ratio);  //call to set diff in the car

					args.clear(); 
					cout << "command or \"help\"> "; 
					continue;
				}
				else
				{
					args.clear();
					cout << "Error - set diff command requires 1 parameter: ratio (floating-point).\nType \"help\" (no quotes) for valid command details." << endl;
					cout << "command or \"help\"> ";
					continue;
				}
            }
            else if ((string)args.at(1) == "gears")
            {
				if (args.size() == 3)  //only continue parsing the set gears command if 1 other arg is entered
				{
					
               	/*
				- Value in vector args 2 is cast to a string object
				- an istringstream object is called on it
				- istringstream object parses gears to ensure it is an integer
				- specific error messages are printed if assignment fails and loops back to command line.
				- variables used relate to the arg value they're parsing (stX, junkX)
				*/
					string tryGears = (string)args.at(2);
					istringstream st2(tryGears);
					int gears;
					st2 >> gears;
					if (st2.fail())
					{
						args.clear();
						cout << "Error - gears must be an integer number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					string junk2;
					if (st2 >> junk2)
					{
						args.clear();
						cout << "Error - gears must be an integer number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					if (gears < 1)  //bounds checking for number of gears: must not be less than 1
					{
						args.clear();
						cout << "Error - gears must be a positive integer number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					
					car->setNumberOfGears(gears);  //call to set number of gears in the car
					
					args.clear(); 
					cout << "command or \"help\"> ";
					continue;
				}
				else
				{
					args.clear();
					cout << "Error - set gears command requires 1 parameter: gears (integer).\nType \"help\" (no quotes) for valid command details." << endl;
					cout << "command or \"help\"> ";
					continue;
				}
            }
            else if ((string)args.at(1) == "gear")
            {
				if (args.size() == 4)  //only continue parsing the set gear command if 2 other args are entered
				{
					
               	/*
				- Each value in vector args 2-3 is cast to a string object
				- an istringstream object is called on each of the strings
				- istringstream object parses ratio to ensure it is a double (ints promoted)
				- istringstream object parses gear to ensure it is an int
				- specific error messages are printed if assignments fail and loops back to command line.
				- variables used relate to the arg value they're parsing (stX, junkX)
					*/
					string tryGear = (string)args.at(2);
					istringstream st2(tryGear);
					int gear;
					st2 >> gear;
					if (st2.fail())
					{
						args.clear();
						cout << "Error - gear must be an integer number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					string junk2;
					if (st2 >> junk2)
					{
						args.clear();
						cout << "Error - gear must be an integer number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					if (gear < 1)  //bounds checking for gear: must not be less than 1
					{
						args.clear();
						cout << "Error - gear must be a positive integer number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					//Further checking of gear number will occur in the Gearbox to ensure that
					//gear actually exists.

					
					string tryRatio = (string)args.at(3);
					istringstream st3(tryRatio);
					double ratio;
					st3 >> ratio;
					if (st3.fail())
					{
						args.clear();
						cout << "Error - ratio must be a floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					string junk3;
					if (st3 >> junk3)
					{
						args.clear();
						cout << "Error - ratio must be a floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					if (ratio <= 0)  //bounds checking for ratio: must not be negative
					{
						args.clear();
						cout << "Error - ratio must be a positive floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					
					car->changeGearboxRatio(gear, ratio);  //call to change gearbox ratio in car
					
					args.clear(); 
					cout << "command or \"help\"> ";
					continue; 
				}
				else
				{
					args.clear();
					cout << "Error - set gear command requires 2 parameters: gear (integer) and ratio (floating-point).\nType \"help\" (no quotes) for valid command details." << endl;
					cout << "command or \"help\"> ";
					continue;
				}
            }
            else if ((string)args.at(1) == "torque")
            {
				if (args.size() == 3)  //only continue parsing the set torque command if 1 other arg is entered
				{	
					FILE *fp;  //pointer to torque file
					char ch; //character to be read in from file
					string sTorqueFile; //the torqueFile as a string object
					
                    /*
                     -   Following code attempts to open and parse the torque file in order to determine
                         its validity.
                     -   Attempts to open file; throws error if filename is not found
                     -   If file open successful, loops through the file, getting each character and adding it
                         to the sTorqueFile: the string object.
                     -   File is then closed
                     -   sTorqueFile is turned into a c-string so it can be tokenized
                     -   Token delimiter is " ", and tokens are inserted into an argument vector
                     -   First argument is parsed to check whether it is an integer (number of gears for the car),
                         error thrown if it is not
                     -   The rest of the arguments are parsed to check whether they are doubles (rpm values and torque
                         values), error thrown if they are not
                     -   Final check is to make sure that the number of arguments is correct ie an rpm value and a
                         torque value are included for every gear, errors are thrown if too few or too many arguments.
						 - This is done in a similar style to how the initial torque file is set.
                    */
					
					if ((fp = fopen(args.at(2), "r")) == NULL) //throw error if file doesn't exist
					{
						args.clear();
						cout << "Error - Unable to open file.  Please check if file exists and filename inputted correctly.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					
					ch = getc(fp);  //get first char of Torque file
					
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
					
					torqueTokenPtr = strtok(cTorqueFile, " "); //tokenize input array for words delimited by spaces
					
					while (torqueTokenPtr != NULL)
					{
						torqueArgs.push_back(torqueTokenPtr);  //put token in a separate vector compartment
						torqueTokenPtr = strtok(NULL, " ");  //get next token
					}
					
					string tryPairs = (string)torqueArgs.at(0);  //get number of torque pairs
					istringstream st0(tryPairs);  //put through an istringstream
					int numPairs;
					st0 >> numPairs; //assign gear number as integer
					if (st0.fail()) 
					{
						torqueArgs.clear();
						args.clear();
						cout << "Error - First element of torque file (number of rpm/torque pairs) is not an integer, therefore, file cannot be used.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					string junk0;
					if (st0 >> junk0)
					{
						torqueArgs.clear();
						args.clear();
						cout << "Error - First element of torque file (number of rpm/torque pairs) is not an integer, therefore, file cannot be used.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					if (numPairs < 1)  //bounds checking for number of torque pairs: must not be less than 1
					{
						torqueArgs.clear();
						args.clear();
						cout << "Error - First element of torque file (number of rpm/torque pairs) is less than 1, therefore, file cannot be used.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					
					//boolean that determines double error; needed so that only this error is thrown
					//when there are double errors and the size of the vector is incorrect
					bool doubleError = false;  
					
					//parse each other remaining values in torqueArgs to check if they are doubles
					for (int i = 1; i < torqueArgs.size(); i++)  
					{
						string value = (string)torqueArgs.at(i);
						istringstream st(value);
						double doubleValue;
						st >> doubleValue;
						if (st.fail())
						{
							doubleError = true;
							cout << "Error - A rpm value or torque value in the torque file is not a floating-point value, therefore, file cannot be used.\nType \"help\" (no quotes) for valid command details." << endl;
							break;
						}
						string junk;
						if (st >> junk)
						{
							doubleError = true;
							cout << "Error - A rpm value or torque value in the torque file is not a floating-point value, therefore, file cannot be used.\nType \"help\" (no quotes) for valid command details." << endl;
							break;
						} 
						if (doubleValue <= 0)  //bounds checking for rpm and torque values: must not be negative
						{
							doubleError = true;
							cout << "Error - A rpm value or torque value in the torque file is less than 0, therefore, file cannot be used.\nType \"help\" (no quotes) for valid command details." << endl;
							break;
						}
						//check if torque curve is formed properly: rpm values must be in ascending order
						if (i != 1 && i % 2 == 1)   
						{
							string compValue = (string)torqueArgs.at(i-2);
							istringstream sts(compValue);
							double compDouble;
							sts >> compDouble;
							if (doubleValue < compDouble)
							{
								torqueArgs.clear();
								doubleError = true;
								cout << "Error - A rpm value in the torque file is less than its predecessor, therefore, file cannot be used." << endl;
								break;
							}
						}
					}
						
					//test to make sure there are a correct number of values in the vector
					if  (doubleError == false && (numPairs * 2 + 1) != torqueArgs.size())
					{
						torqueArgs.clear();
						args.clear();
						cout << "Error - Torque file contains rpm or torque values for gears that do not exist,\nor not enough rpm or torque values for the number of pairs plotted,\ntherefore, file cannot be used.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					
					if (doubleError == false)  //prevent bad double from setting after break
						car->setTorque(args.at(2));  //call to set the torque file in the car
					
					//clean up
					doubleError = false;
					torqueArgs.clear();
					args.clear(); 
					cout << "command or \"help\"> ";
					continue; 	
               }
               else
               {
				   args.clear();
				   cout << "Error - set torque command requires 1 parameter: filename (FILE).\nType \"help\" (no quotes) for valid command details." << endl;
				   cout << "command or \"help\"> ";
				   continue;
               }
            }
            else
            {
				args.clear();
				cout << "Error - settable car parts are limited to \"wheel\", \"diff\", \"gears\", \"gear\", and \"torque\".\nType \"help\" (no quotes) for valid command details." << endl;
				cout << "command or \"help\"> ";
				continue;
            }
			
         }
         else if ((string)args.at(0) == "select" && args.size() > 1)
         {
			 if ((string)args.at(1) == "gear")
			 {
				 if (args.size() == 3)  //only continue parsing the select gear command if 1 other arg is entered
				 {
					 
				 /*
               		- Value in vector args 2 is cast to a string object
					- an istringstream object is called on it
					- istringstream object parses gear to ensure it is a integer
					- specific error messages are printed if assignment fails and loops back to command line.
					- variables used relate to the arg value they're parsing (stX, junkX)
				  */
					 string tryGear = (string)args.at(2);
					 istringstream st2(tryGear);
					 int gear;
					 st2 >> gear;
					 if (st2.fail())
					 {
						 args.clear();
						 cout << "Error - gear must be an integer number.\nType \"help\" (no quotes) for valid command details." << endl;
						 cout << "command or \"help\"> ";
						 continue;
					 }
					 string junk2;
					 if (st2 >> junk2)
					 {
						 args.clear();
						 cout << "Error - gear must be an integer number.\nType \"help\" (no quotes) for valid command details." << endl;
						 cout << "command or \"help\"> ";
						 continue;
					 }
					 if (gear < 1)  //bounds checking for gear: must not be less than 1
					 {
						args.clear();
						cout << "Error - gear must be a positive integer number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					 }
					 //Further checking done in the Gearbox to make sure selected gear actually exists
					 
					 car->selectGear(gear); //call to select gear in car
					 
					 args.clear();  
					 cout << "command or \"help\"> ";
					 continue;  
				 }
				 else
				 {
					 args.clear();
					 cout << "Error - select gear command requires 1 parameter: gear (integer).\nType \"help\" (no quotes) for valid command details." << endl;
					 cout << "command or \"help\"> ";
					 continue;
				 }
			 }
			 else if ((string)args.at(1) == "rpm")
			 {
				 if (args.size() == 3)  //only continue parsing the select rpm command if 1 other arg is entered
				 {
					 
				 /*
               		- Value in vector args 2 is cast to a string object
					- an istringstream object is called on it
					- istringstream object parses rpm to ensure it is a double (ints promoted)
					- specific error messages are printed if assignment fails and loops back to command line.
					- variables used relate to the arg value they're parsing (stX, junkX)
				 */
					 string tryRpm = (string)args.at(2);
					 istringstream st2(tryRpm);
					 double rpm;
					 st2 >> rpm;
					 if (st2.fail())
					 {
						 args.clear();
						 cout << "Error - rpm must be a floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						 cout << "command or \"help\"> ";
						 continue;
					 }
					 string junk2;
					 if (st2 >> junk2)
					 {
						 args.clear();
						 cout << "Error - rpm must be a floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						 cout << "command or \"help\"> ";
						 continue;
					 }
					 if (rpm < 0)  //bounds checking for rpm: must not be negative
					 {
						args.clear();
						cout << "Error - rpm must be a non-negative floating point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					}
					 
					 car->setRpm(rpm);  //call to set rpm in car

					 args.clear();  
					 cout << "command or \"help\"> "; 
					 continue;
				 }
				 else
				 {
					 args.clear();
					 cout << "Error - select rpm command requires 1 parameter: rpm (floating-point).\nType \"help\" (no quotes) for valid command details." << endl;
					 cout << "command or \"help\"> ";
					 continue;
				 }
			 }
			 else if ((string)args.at(1) == "speed")
			 {
				 if (args.size() == 3)  //only continue parsing the select speed command if 1 other arg is entered
				 {
					 
				 /*
               		- Value in vector args 2 is cast to a string object
					- an istringstream object is called on it
					- istringstream object parses speed to ensure it is a double (ints promoted)
					- specific error messages are printed if assignment fails and loops back to command line.
					- variables used relate to the arg value they're parsing (stX, junkX)
				  */
					 string trySpeed = (string)args.at(2);
					 istringstream st2(trySpeed);
					 double speed;
					 st2 >> speed;
					 if (st2.fail())
					 {
						 args.clear();
						 cout << "Error - speed must be a floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						 cout << "command or \"help\"> ";
						 continue;
					 }
					 string junk2;
					 if (st2 >> junk2)
					 {
						 args.clear();
						 cout << "Error - speed must be a floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						 cout << "command or \"help\"> ";
						 continue;
					 }
					 if (speed < 0)  //bounds checking for speed: must not be negative
					 {
						args.clear();
						cout << "Error - speed must be a non-negative floating-point number.\nType \"help\" (no quotes) for valid command details." << endl;
						cout << "command or \"help\"> ";
						continue;
					 }
					  
					 car->setSpeed(speed);  //call to set the speed of the car

					 args.clear();  
					 cout << "command or \"help\"> ";  
					 continue; 
				 }
				 else
				 {
					 args.clear();
					 cout << "Error - select speed command requires 1 parameter: speed (floating-point).\nType \"help\" (no quotes) for valid command details." << endl;
					 cout << "command or \"help\"> ";
					 continue;
				 }
			 }
			 else
			 {
				 args.clear();
				 cout << "Error - selectable properties are limited to \"gear\", \"rpm\", and \"speed\".\nType \"help\" (no quotes) for valid command details." << endl;
				 cout << "command or \"help\"> ";
				 continue;
			 }
         }
         else if ((string)args.at(0) == "print")
         {
			 if (args.size() > 1)
			 {
				 if ((string)args.at(1) == "state")
				 {
					 if (args.size() == 2) //only continue parsing the select speed command if no args present
					 {
						 car->printCarState();  //call printState in Car

						 args.clear();  
						 cout << "command or \"help\"> ";  
						 continue; 
						 
					 }
					 else
					 {
						 args.clear();
						 cout << "Error - print state command requires 0 parameters.\nType \"help\" (no quotes) for valid command details." << endl;
						 cout << "command or \"help\"> ";
						 continue;
					 }
				 }
				 else if ((string)args.at(1) == "specifications")
				 {
					 if (args.size() == 2) //only continue parsing the select speed command if no args present
					 {
						 car->printCarSpecs();  //call print specs in Car
						 
						 args.clear();  
						 cout << "command or \"help\"> ";  
						 continue;
					 }
					 else
					 {
						 args.clear();
						 cout << "Error - print specifications command requires 0 parameters.\nType \"help\" (no quotes) for valid command details." << endl;
						 cout << "command or \"help\"> ";
						 continue;
					 }
				 }
				 else
				 {
					 args.clear();
					 cout << "Error - printable properties are limited to car's \"state\" and \"specifications\".\nType \"help\" (no quotes) for valid command details." << endl;
					 cout << "command or \"help\"> ";
					 continue;
				 }
			 }
			 else
			 {
				 args.clear();	
				 cout << "Error - print command needs another argument: \"state\" or \"specifications\".\nType \"help\" (no quotes) for valid command details." << endl;
				 cout << "command or \"help\"> ";
				 continue;
			 }
		 }
         else
         {
			 args.clear();
			 cout << "Error - Invalid command.\nType \"help\" (no quotes) for valid command details." << endl;
			 cout << "command or \"help\"> ";
			 continue;
         }
		 
      }
	  
      delete car;  //clean up Car

      return 0; 
   }
