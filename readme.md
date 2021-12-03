# Automata

Automata is a java software which can create automatas done during a school project. It has only a learning purpose


## Installation

Get the archive of the files and unzip (or else if it's in an other format) it.

## Usage

To see how the software works you can launch the file named Appli.class by placing yourself in the main folder and executing the following command
```bash
java -classpath ./out/production/Automate Appli
```
You will have a menu displayed and you will have to choose an option
```
~~~~~~~~~~~~~~~Menu~~~~~~~~~~~~~~~
1. Smiley
2. HH:MM
3. Date (JJ/MM/AAAA)
4. Mail
5. Polynomial
6. Add automata from csv file
9. Exit
Your choice : 
```
#### Creating and adding csv file
You can add your own csv files so the automata class reads them and use them. Here is how to create such csv files.
Each attributes are seperated by colons ",". On the first line you will place an `init` expression followed by the name of the first state of the automata. Then, you will write, for each action of each transition, following this template : 
<br/>`curent state`,`action`,`outgoing state`
<br/>with `action`being a character and both states represented by their name.
On the last line, you will have to put a `final`expression followed by a state for each final state you have.
<br/>You can see example of such files in the lib folder

## JavaDoc
The JavaDoc is stored in the doc file but you can acces it from [here](./doc/package-summary.html)

## License
[MIT](https://choosealicense.com/licenses/mit/)