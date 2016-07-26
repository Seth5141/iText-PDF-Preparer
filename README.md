# iText-PDF-Preparer
I created this application as a personal project to help automate some of my tasks at work. It utilizes the iText library to 
fill form fields in pdf files. It also contains several useful classes for serializing and deserializing objects into 
persistent files. Other highlights of the project are the file preparer class which takes a csv file as input, and produces 
either an array or an arraylist of array obects for further use in the program.

Program Operation

The program is a GUI application. It stores several classes of objects persistantly, namely Solar Modules, Iverters, Utility 
Copanies, and Preparers. These objects can be created and viewed from the secondary tabs of the program. It takes as input a
csv file which is converted into an array. When the create new file button is pressed on the main page, the template pdf
files stored at the location specified in the program are opened and filled with the proper information. The copied files are
then stored in the new file location. 

What needs improved

Better error handling

A better way to store the file locations for easy editing outside of the program (i.e. a config file)

I'm sure there are many more things. I am open to any feedback or suggestions.
