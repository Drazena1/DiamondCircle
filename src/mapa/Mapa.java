package mapa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import application.ControllerMain;





public class Mapa {

	
    private static final int SIZE = Integer.parseInt(ControllerMain.d);
	public static Field[][] map = new Field[SIZE][SIZE];
	public static ArrayList<Field> path = new ArrayList<>();
	private static ArrayList<Integer> br = new ArrayList<>();
	
	public Mapa() {
		for(int i=0; i<SIZE; i++)
			for(int j=0; j<SIZE; j++) {
				map[i][j] = new Field(i, j);
			}
			
				diamondSpiral(map);
				
				
			}

	
	
	
	
	    public void spiralDiamondView(Field [][] matrix, 
	        int x, int y, int m, int n, int k)
	    {
	        
	        int midCol = y + ((n - y) / 2);
	        int midRow = midCol;
	    
	        for (int i = midCol, j = 0; 
	             i < n && k > 0; ++i, k--, j++)
	        {
	        
	        	path.add(getField((x+j),i));
	            
	        }
	       
	        for (int i = n, j = 0; 
	             i >= midCol && k > 0; --i, k--, j++)
	        {
	        	path.add(getField(((midRow) + j),i));
	           // System.out.print("  " + matrix[(midRow) + j][i]);
	        }
	        
	        for (int i = midCol - 1, j = 1; 
	             i >= y && k > 0; --i, k--, j++)
	        {
	        	path.add(getField(((n) - j),i));
	          //  System.out.print("  " + matrix[(n) - j][i]);
	        }
	       
	        for (int i = y + 1, j = 1; 
	             i < midCol && k > 0; ++i, k--, j++)
	        {
	        	path.add(getField(((midRow) - j),i));
	           // System.out.print(" yadnja " + (midRow-j) + i + matrix[(midRow) - j][i]);
	        }
	        if (x + 1 <= m - 1 && k > 0)
	        {
	            // Recursive call
	            spiralDiamondView(matrix, x + 1, 
	                              y + 1, m - 1, n - 1, k);
	        }
	    }
	    
	    public void diamondSpiral(Field [][] matrix)
	    {
	        // Get the size
	        int row = SIZE;
	       
	        int col = SIZE;
	       
	        // Print result
	        if(SIZE % 2 != 0)
	        spiralDiamondView(matrix, 0, 0, row - 1, 
	            col - 1, (row * col) - ((col + 1) / 2) * 4);
	        else
	        	spiralDiamondView(matrix, 0, 0, row- 2, col - 2, (row * col) - ((SIZE + 1) / 2) * 4);
	        
	        
	      if (SIZE == 7)
	      {
	    	  br.add(4);
				br.add(12);
				br.add(20);
				br.add(28);
				br.add(34);
				br.add(40);
				br.add(46);
				br.add(38);
				br.add(30);
				br.add(22);
				br.add(16);
				br.add(10);
				br.add(11);
				br.add(19);
				br.add(27);
				br.add(33);
				br.add(39);
				br.add(31);
				br.add(23);
				br.add(17);
				br.add(18);
				br.add(26);
				br.add(32);
				br.add(24);
				br.add(25);
	    	 /* try {
	    			BufferedReader in= new BufferedReader(new FileReader("size7.txt"));
	    			String s;
	    			try {
	    				while ((s=in.readLine())!=null)
	    				{
	    					String [] numbers = s.split(",");
	    					for (String i : numbers)
	    					br.add(Integer.parseInt(i));
	    				}
	    			} catch (NumberFormatException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    		} catch (FileNotFoundException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	 
	    	*/
			
			for(int k=0; k<path.size(); k++) {
				path.get(k).setNumber(br.get(k));
			}
	      }
	     if (SIZE == 8)
	     {
	    	   br.add(4);
				br.add(13);
				br.add(22);
				br.add(31);
				br.add(38);
				br.add(45);
				br.add(52);
				br.add(43);
				br.add(34);
				br.add(25);
				br.add(18);
				br.add(11);
				br.add(12);
				br.add(21);
				br.add(30);
				br.add(37);
				br.add(44);
				br.add(35);
				br.add(26);
				br.add(19);
				br.add(20);
				br.add(29);
				br.add(36);
				br.add(27);
				br.add(28);
				/*
	    	 
	    	  try {
	    			BufferedReader in= new BufferedReader(new FileReader("size8.txt"));
	    			String s;
	    			try {
	    				while ((s=in.readLine())!=null)
	    				{
	    					String [] numbers = s.split(",");
	    					for (String i : numbers)
	    					br.add(Integer.parseInt(i));
	    				}
	    			} catch (NumberFormatException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    		} catch (FileNotFoundException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}*/
								for(int k=0; k<path.size(); k++) {
					path.get(k).setNumber(br.get(k));
				}
	    	 
	     }
	     
	     if (SIZE == 9)
	     {
	 	/*	br.add(5);
    		br.add(15);
    		br.add(25);
    		br.add(35);
    		br.add(45);
    		br.add(53);
    		br.add(61);
    		br.add(69);
    		br.add(77);
    		br.add(67);
    		br.add(57);
    		br.add(47);
    		br.add(37);
    		br.add(39);
    		br.add(21);
    		br.add(13);
    		br.add(14);
    		br.add(24);
    		br.add(34);
    		br.add(44);
    		br.add(52);
    		br.add(60);
    		br.add(63);
    		br.add(58);
    		br.add(48);
    		br.add(38);
    		br.add(30);
    		br.add(22);
    		br.add(23);
    		br.add(33);
    		br.add(43);
    		br.add(51);
    		br.add(59);
    		br.add(49);
    		br.add(39);
    		br.add(31);
    		br.add(32);
    		br.add(42);
    		br.add(50);
    		br.add(40);
    		br.add(41);*/
	    	 
	    	  try {
	    			BufferedReader in= new BufferedReader(new FileReader("size9.txt"));
	    			String s;
	    			try {
	    				while ((s=in.readLine())!=null)
	    				{
	    					String [] numbers = s.split(",");
	    					for (String i : numbers)
	    					br.add(Integer.parseInt(i));
	    				}
	    			} catch (NumberFormatException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    		} catch (FileNotFoundException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
    		    		for(int k=0; k<path.size(); k++) 
    			path.get(k).setNumber(br.get(k));
    		
	    	 
	     }
	     if (SIZE==10)
	     {
	    	    /*br.add(5);
	    		br.add(16);
	    		br.add(27);
	    		br.add(38);
	    		br.add(49);
	    		br.add(58);
	    		br.add(67);
	    		br.add(76);
	    		br.add(85);
	    		br.add(74);
	    		br.add(63);
	    		br.add(52);
	    		br.add(41);
	    		br.add(32);
	    		br.add(23);
	    		br.add(14);
	    		br.add(15);
	    		br.add(26);
	    		br.add(37);
	    		br.add(48);
	    		br.add(57);
	    		br.add(66);
	    		br.add(75);
	    		br.add(64);
	    		br.add(53);
	    		br.add(42);
	    		br.add(33);
	    		br.add(24);
	    		br.add(25);
	    		br.add(36);
	    		br.add(47);
	    		br.add(56);
	    		br.add(65);
	    		br.add(54);
	    		br.add(43);
	    		br.add(34);
	    		br.add(35);
	    		br.add(46);
	    		br.add(55);
	    		br.add(44);
	    		br.add(45);*/
	    	  try {
	    			BufferedReader in= new BufferedReader(new FileReader("size10.txt"));
	    			String s;
	    			try {
	    				while ((s=in.readLine())!=null)
	    				{
	    					String [] numbers = s.split(",");
	    					for (String i : numbers)
	    					br.add(Integer.parseInt(i));
	    				}
	    			} catch (NumberFormatException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    		} catch (FileNotFoundException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		
	    		for(int k=0; k<path.size(); k++) 
	    			path.get(k).setNumber(br.get(k));
	    		 
	     }
	    }
	
	
	
	
	
	
	    public void setField(int i, int j, Field f) {
			map[i][j]=f;
		}
		
		public Field getField(int i, int j) {
			return map[i][j];
		}
	
		
		public static  ArrayList<Field> getPath(){ return path; }
		
	


	
}
