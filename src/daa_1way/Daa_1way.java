/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daa_1way;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author user
 */
public class Daa_1way {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner data = new Scanner(System.in);
        int regions; 
        int programmes;
        int paires;
        
        System.out.print("Entre the number of regions in memory :"); 
        regions = data.nextInt();
        int m= regions;
        
        if((m>0) && (m<11))
        {
        
            System.out.print("Entre the number of Programmes :"); 
            programmes = data.nextInt();
            int n=programmes;
            System.out.println();
            if((n>0) && (n<51))
            {

                int[] regionspace = new int[regions];
                int[] region_count = new int[regions];
                int scount=0;

                for (int x = 0; x <= regions-1; x++)
                {
                    scount=scount+1;
                    String region = "region "+x;
                    System.out.print(region+" meoroy sapce: ");
                    regionspace[x] = data.nextInt();
                    region_count[x]=scount;
                } 
                System.out.println();
                    int[] programsize = new int[programmes];
                    int[] exTime= new int[programmes];

                    for (int x = 0; x <= programmes-1; x++)
                    {
                        String programme = "programme "+x;
                        System.out.print("Entre the number of paires per "+programme+":"); 
                        paires = data.nextInt();

                            if(paires==1)
                            {
                                System.out.print(programme+" meoroy size: ");
                                programsize[x] = data.nextInt();

                                System.out.print(programme+" Executable Time: ");
                                exTime[x] = data.nextInt();
                            }
                            else
                            {
                                int[] pair_programsize = new int[paires];
                                int[] pair_exTime= new int[paires];

                                for (int y = 0; y <= paires-1; y++)
                                {
                                    System.out.print(programme+" meoroy size: ");
                                    pair_programsize[y] = data.nextInt();

                                    System.out.print(programme+" Executable Time: ");
                                    pair_exTime[y] = data.nextInt();
                                }
                                String arrayName = Arrays.toString(getSortedStringArray(pair_programsize, pair_exTime));    

                                StringTokenizer st = new StringTokenizer(arrayName,",");
                                String first = st.nextToken();

                                StringTokenizer str = new StringTokenizer(first,"[");
                                String first_1 = str.nextToken();
                                int space = Integer.parseInt(first_1);

                                programsize[x]=space;
                                insertionSort(pair_exTime);
                                exTime[x]=pair_exTime[0];


                            }//Else End
                    }//For Loop End
                    System.out.println();
                    String array = Arrays.toString(getSortedStringArray(programsize, exTime));
                    insertionSort(regionspace);           

                    float tot = 0;
                    int[] temp_time= new int[programmes];
                    for(int rotate_count=0;rotate_count<programsize.length;rotate_count++)
                    {

                        for(int seg = 0;seg<regionspace.length;seg++)
                        {
                            for(int prog = 0;prog<programsize.length;prog++)
                            {
                                if(programsize[prog]==0)
                                {
                                   continue; 
                                }

                                else if(regionspace[seg]>=programsize[prog])
                                {
                                  System.out.println("program "+prog+ " runs in region "+seg+" from "+temp_time[seg]+" to "+(temp_time[seg]=temp_time[seg]+exTime[prog]));
                                  tot=tot+temp_time[seg];
                                  programsize[prog]=0;
                                  break;
                                }


                            }
                        }
                    }


                    System.out.println("Avarage Turn Around Time = "+tot/programmes);
                }
                else
                {
                    System.out.println("out of range! The Programme range shoud between (1 <= n <= 50)");
                }
        }
        else
        {
            System.out.println("out of range! The Region range shoud between (1 <= m <= 10)");
        }
        
        
            
    }//Main method end
    
    public static void insertionSort(int array[]) {
        int n = array.length;
        for (int j = 1; j < n; j++) 
        {
            int key = array[j];
            int i = j-1;
            while ( (i > -1) && ( array [i] > key ) ) 
            {
                array [i+1] = array [i];
                i--;
            }
            array[i+1] = key;
        }
    }
   
   public static Integer[] getSortedStringArray(int[] pmemoryspace1, int[] exTime1) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < pmemoryspace1.length; i++) 
        {
          if (!map.containsKey(exTime1[i])) 
          {
            map.put(exTime1[i], new LinkedList<Integer>());
          }
          map.get(exTime1[i]).add(pmemoryspace1[i]);
        }
        Integer[] ret = new Integer[pmemoryspace1.length];
        int i = 0;
        for (Map.Entry<Integer, List<Integer>> mapEntry : map.entrySet()) 
        {
          for (Integer s : mapEntry.getValue()) 
          {
            ret[i++] = s;
          }
        }
        return ret;
    }
   
}//Class end 

 
    

