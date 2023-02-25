package com.example.project;

import java.util.Scanner;

public class Cube {
	private static char[][][] state = {
	{
		{'r', 'r', 'r'},
		{'r', 'r', 'r'},
		{'r', 'r', 'r'}
	},{
		{'b', 'b', 'b'},
		{'b', 'b', 'b'},
		{'b', 'b', 'b'}
	},{
		{'o', 'o', 'o'},
		{'o', 'o', 'o'},
		{'o', 'o', 'o'}
	},{
		{'g', 'g', 'g'},
		{'g', 'g', 'g'},
		{'g', 'g', 'g'}
	},{
		{'y', 'y', 'y'},
		{'y', 'y', 'y'},
		{'y', 'y', 'y'}
	},{
		{'w', 'w', 'w'},
		{'w', 'w', 'w'},
		{'w', 'w', 'w'}
	}};
	public static void main(final String[] args) {
		Cube cube = new Cube();
		// Get input from command line arg
		if (args.length > 1) {
		  Scanner arg = new Scanner(args[1]);
		  while (arg.hasNext("((u|d|r|l|f|b)'?)")) {
			cube.doMove(arg.next("((u|d|r|l|f|b)'?)"));
		  }
		  arg.close();
		}
		// print cube state
	
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				for (int k = 0; k < state[i][j].length; k++) {
					System.out.print(state[i][j][k]);
					if (k == 2) {
						System.out.print(" ");
					} else {
						System.out.print(" | ");
					}
				}
				System.out.println();
			}
			if (i != state.length - 1) {
				System.out.println();
			}
		}
		Scanner keyboard = new Scanner(System.in);
		System.out.print("> ");
		while (keyboard.hasNextLine()) {
			System.out.print("> ");
		  	if (keyboard.hasNext("quit")) {
				break;
		  	}
		  	while (keyboard.hasNext("((u|d|r|l|f|b)'?)")) {
				cube.doMove(keyboard.next("((u|d|r|l|f|b)'?)"));
		  	}
		keyboard.nextLine();
		// print cube state
		

		
		}
		keyboard.close();
	  }


	  

	// Method containing moves
	public void doMove(String moveString){
		switch (moveString) {
			case "u":
			doMoveU();
			break;
			case "d":
			doMoveD();
			break;
			case "r":
			doMoveR();
			break;
			case "l":
			doMoveL();
			break;
			case "f":
			doMoveF();
			break;
			case "b":
			doMoveB();
			break;
			case "u'":
			doMoveU();
			doMoveU();
			doMoveU();
			break;
			case "d'":
			doMoveD();
			doMoveD();
			doMoveD();
			break;
			case "r'":
			doMoveR();
			doMoveR();
			doMoveR();
			break;
			case "l'":
			doMoveL();
			doMoveL();
			doMoveL();
			break;
			case "f'":
			doMoveF();
			doMoveF();
			doMoveF();
			break;
			case "b'":
			doMoveB();
			doMoveB();
			doMoveB();
			break;
		}
	}
	
	// Method forr rotating faces as matrices
	public static char[][] rotateMatrix(char[][] matrix){
		int height = matrix.length;
        int width = matrix[0].length;
        char[][] rotatedMatrix = new char[width][height];
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotatedMatrix[j][(height-1)-i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
	}

	// Method for referencing columns
	public static char[] getColumn(char[][] matrix, int index){
		int width = matrix[0].length;
        char[] column = new char[width];

        for (int i = 0; i < width; i++) {
            column[i] = matrix[index][i];
        }
        return column;
	}

	// Method for setting new columns
	private static void setColumn(char[][] src, int srcIdx, char[][] dest, int destIdx) {
        int srcColumns = src[0].length;

        for (int i = 0; i < srcColumns; i++) {
            dest[destIdx][i] = src[srcIdx][i];
        }
	}

	private static void setColumn(char[] column, char[][] matrix, int index) {
        int sourceLength = column.length;

        for (int i = 0; i < sourceLength; i++) {
            matrix[index][i] = column[i];
        }
    }

	private static char[][] reverseRotateMatrix(char[][] matrix) {
		int height = matrix.length;
		int width = matrix[0].length;
		char[][] rrMatrix = new char[width][height];
			
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				rrMatrix[(width-1)-j][i] = matrix[i][j];
			}
		}
		return rrMatrix;
	}

	public void doMoveU(){
		state[4] = reverseRotateMatrix(state[4]);
		char[] temp = getColumn(state[2], 0);
		setColumn(state[1], 0, state[2], 0);
		setColumn(state[0], 0, state[1], 0);
		setColumn(state[3], 0, state[0], 0);
		setColumn(temp, state[3], 0);
	}

	public void doMoveD(){
		state[5] = reverseRotateMatrix(state[5]);
		char[] temp = getColumn(state[3], 2);
		setColumn(state[0], 2, state[3], 2);
		setColumn(state[1], 2, state[0], 2);
		setColumn(state[2], 2, state[1], 2);
		setColumn(temp, state[2], 2);
	}

	public void doMoveR(){
		state[0] = reverseRotateMatrix(state[0]);
		char[] temp = state[3][0];
		state[3][0] = state[4][2];
		state[4][2] = state[1][2];
		state[1][2] = state[5][2];
		state[5][2] = temp;
	}
	
	public void doMoveL(){
		state[2] = reverseRotateMatrix(state[2]);
		char[] temp = state[3][2];
		state[3][2] = state[5][0];
		state[5][0] = state[1][0];
		state[1][0] = state[4][0];
		state[4][0] = temp;
	}

	public void doMoveF(){
		state[1] = reverseRotateMatrix(state[1]);
		char[] temp = getColumn(state[5], 0);
		setColumn(state[0][0], state[5], 0);
		state[0][0] = getColumn(state[4], 2);
		setColumn(state[2][2], state[4], 2);
		state[2][2] = temp;
	}

	public void doMoveB(){
		state[3] = reverseRotateMatrix(state[3]);
		char[] temp = getColumn(state[4], 0);
		setColumn(state[0][2], state[4], 0);
		state[0][2] = getColumn(state[5], 2);
		setColumn(state[2][0], state[5], 2);
		state[2][0] = temp;
	}
	

}

