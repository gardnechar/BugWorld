package com.karlund.javafx;
import java.util.Scanner;

public class Bug {

	//fields
	String species; 
	String name;
	private char symbol = 0; 
	int xposition; //horizontal position 
	int yposition; //vertical position 
	int energy;
	int uniqueID;
	
	//constructor 1
	public Bug() {
		
	species = "ladybug"; 
	name = "Mr Smith";
	symbol = 'L'; 
	xposition = 10; //horizontal position 
	yposition = 10; //vertical position 
	energy = 20;
	uniqueID = 5;
	
	}
	
	//constructor 2
	public Bug(String spec, String nom, char symb, int x, int y, int ener, int unID){	
		species = spec;
		name = nom;
		symbol = symb;
		xposition = x;
		yposition = y;
		energy = ener;
		uniqueID = unID;
	}

		//Returns all attributes of the bug
		public String toString(){
			return "(" + species + ", " + name + ", " + symbol + ", " + xposition + ", " + yposition + ", " + energy + ", " + uniqueID + ")"; 	
		}

		
		public String getSpecies(){
			return species;
		}
		
		public void setSpecies(String speci){
			species = speci;
		}
		
		public String getName(){
			return name;
		}
		
		public void setName(String nam){
			name = nam;
		}
		
		public char getSymbol(){
			return symbol;
		}
		
		public void setSymbol(char symbo){
			symbol = symbo;
		}
		
		public void removeSymbol(){
			symbol = 0;
		}
		
		public int getXpos(){
			return xposition;
		}
		
		public void setXpos (int xpo){
			xposition = xpo;
		}
		
		public int getYpos(){
			return yposition;
		}
		
		public void setYpos (int ypo){
			yposition = ypo;
		}
		
		public int getEnergy(){
			return energy;
		}
		
		public void setEnergy (int energ){
			energy = energ;
		}
		
		public int getID(){
			return uniqueID;
		}
		
		public void setID (int uniID){
			uniqueID = uniID;
		}
		
		//move bug method
		public void moveBug (int direction, int steps){
			
			// 0 = North, 1 = East, 2 = South, 3 = West
			if (direction == 0) {
				yposition -= steps;
			}
			else if (direction == 1) {
				xposition += steps;
			}
			else if (direction == 2) {
				yposition += steps;
			}
			else if (direction == 3) {
				xposition -= steps;
			}
			
		}
		
		//find bug position
		public String findBug(){
			
			return "X = " + xposition + ", " + "Y = " + yposition + ", " + symbol;
		}
		
		//find bug position Y
		public int bugYPosition(){
			
			return yposition;
		}
		
		//find bug position X
		public int bugXPosition(){
			
			return xposition;
		}
		
		//find bug symbol
		public char bugSymbol(){
			
			return symbol;
		}

		//Create a bug using the user input
		
		public void askUser() {

			Scanner scan = new Scanner(System.in);

			// Ask the name of the species
			System.out.println("Please enter a species: ");
			this.species = scan.next();
			
			// Ask the name of the bug
			System.out.println("Please enter the name for your bug: ");
			this.name = scan.next();

			// Ask for the symbol
			System.out.println("Please enter a symbol: ");
			String sy = scan.next();
			this.symbol = sy.charAt(0);

			// Ask for x position
			System.out.println("Please select an x-coordinate: ");
			this.xposition = scan.nextInt();

			// Ask for y position
			System.out.println("Please select an y-coordinate: ");
			this.yposition = scan.nextInt();

			// Ask for the energy
			System.out.println("Please select an energy value: ");
			this.energy = scan.nextInt();

			// Assign a unique ID
			this.uniqueID = ((int)(Math.random() * 10000));
			
			//scan.close();
			
			
		}

		public static int checkLocation(int n, int k) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	
}
