CC = 		g++
CFLAGS = 	-Wall -g -c
LIBS = 		-lm
RM = 		rm -rf

######################
# change here
######################
EXEC =		A1Main
OBJS =		Car.o Engine.o Differential.o Gearbox.o A1Main.o Wheels.o TorqueCurve.o
######################

A1Main:	$(OBJS)
	$(CC) -o $(EXEC) $(OBJS) $(LIBS)
	cp $(EXEC) a.out

%.o:	%.cpp %.h
	$(CC) $(CFLAGS) $<

clean:
	$(RM) $(EXEC) $(OBJS) *~ core
