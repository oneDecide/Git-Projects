package a1_tjm604;

public class GameOfLife {
	//Made by: Dylan Cordes
	//main for testing
	public static void main(String[] args) {
		//create initial agar table (agar being table of living cells and empty plots)
		//Initial agar table is preset for example, also table is 6x6 for example provided
		boolean[][] agar = new boolean[][] {{true, false, false, false, true, false},
										   {false, false, false, false, true, true},
										   {false, true, true, false, false, false},
										   {false, true, false, false, true, false},
										   {false, true, false, true, false, false},
										   {false, false, false, false, false, false}};
		
		//print current agar table: _ is false or empty, X is a living cell or true
		for(int i = 0; i < agar.length; i++) {
			for(int u = 0; u < agar[i].length; u++) {
				if(agar[i][u] == false)
					System.out.print("_ ");
				else
					System.out.print("X ");
			}
			System.out.println();
		}
		System.out.println();
		
		//Create as many new agar tables and print them up to numOfLoops int
		int numOfLoops = 2;
		
		for(int r = 0; r < numOfLoops; r++) {
			//start new generation
			agar = createGeneration(agar);
			//print current agar table: _ is false or empty, X is a living cell or true
				for(int i = 0; i < agar.length; i++) {
					for(int u = 0; u < agar[i].length; u++) {
						if(agar[i][u] == false)
							System.out.print("_ ");
						else
							System.out.print("X ");
					}
					System.out.println();
				}
				System.out.println();
		
		
		}
	}
	
	public static boolean[][] createGeneration(boolean[][] agar) {
		//set blueprint for new agar table AKA every spot is false
		boolean[][] ret = new boolean[agar.length][agar[0].length];
		for(int i = 0; i< ret.length; i++) {
			for(int u = 0; u < ret[0].length; u++) {
				ret[i][u] = false;
			}
		}
		ret[0][0] = false;
		
		//find new living cell coordinates comparing function argument agar
		int coords[][] = findLivingCellLocations(agar);
		int x;
		int y;
		
		//transform the new agar table based on coordinated provided by findLivingCellLocations()
		//every valid coordinate is 'coords' will set an agar coordinate to true
		for(int p = 0; p < coords.length; p++) {
			//ensure that coordinate (0,0) is not always true
			if(coords[p][0] == -1)
				break;
			
			x = coords[p][0];
			y = coords[p][1];
			ret[x][y] = true;
		}
		
		//return the new agar table
		return ret;
	}
	
	//returns list of coordinates, these coordinates are true values for the agar tables
	public static int[][] findLivingCellLocations(boolean[][] agar) {
		//make baseline for coordinate list AKA all numbers -1 for base case
		//list length will ge agar rows times columns to ensure maximum length
		int[][] ret = new int[agar.length * agar[0].length][2];
		for(int e = 0; e < ret.length;e++)
			ret[e][0] = -1;
		
		int coordCount = 0;
		int count;
		//loops that checks each cell on an agar table
		for(int i = 0; i < agar.length; i++) {
			for(int u = 0; u < agar[0].length;u++) {
				count = 0;
				//check for living cells around agar square
				//comments with numbers checks for living cells around currently checking cell
				//1  2  3
				//4  C  5
				//6  7  8
				//C == current cell
				if(i-1 >= 0) {
					if(u-1 >= 0) {
						//1
						if (agar[i-1][u-1] == true)
							count++;
					}
					//2
					if(agar[i-1][u] == true)
						count++;
					if(u+1 != agar[0].length) {
						//3
						if(agar[i-1][u+1] == true)
							count++;
					}
				}
				if(u-1 >= 0) {
					//4
					if(agar[i][u-1] == true)
						count++;
					if(i+1 != agar.length) {
						//6
						if(agar[i+1][u-1] == true)
							count++;
					}
				}
				if(u+1 != agar[0].length) {
					//5
					if(agar[i][u+1] == true)
						count++;
				}
				if(i+1 != agar.length) {
					//7
					if(agar[i+1][u] == true)
						count++;
					if(u+1 != agar[0].length) {
						//8
						if(agar[i+1][u+1] == true)
							count++;
					}
				}
				
				//determine if square should be living or not
				if(agar[i][u] == true && (count == 2 || count == 3)) {
					ret[coordCount][0] = i;
					ret[coordCount][1] = u;
					coordCount++;
					
				}
				else if(agar[i][u] == false && count == 3){
					ret[coordCount][0] = i;
					ret[coordCount][1] = u;
					coordCount++;
				}
			}
		}
		//return the coordinate list
		return ret;
	}
	

}
