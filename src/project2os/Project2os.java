package project2os;

import java.util.*;

class ESCobj
{  
 int value;   
 int rbit ;
 int mbit ;
  
} 

public class Project2os {

    public static int[] fifo(int[] page_ref) {
        int[] mem_fifo = new int[20];
        int index = 0;
        boolean exist = false;
        int page_fault = 0;
         for(int i=0 ; i<20 ; i++)
        {
         mem_fifo[i]=100;   
        }  

        for (int i = 0; i < page_ref.length; i++) {

            for (int j = 0; j < mem_fifo.length; j++) {
                if (page_ref[i] == mem_fifo[j]) {
                    exist = true;
                }
            }

            if (!exist && index < 19) {
                mem_fifo[index] = page_ref[i];
                index++;
                page_fault++;
            } else if (!exist && index == 19) {
                mem_fifo[index] = page_ref[i];
                page_fault++;
                index = 0;
            }
            exist = false;
        }
        System.out.println("Page faults of FIFO = " + page_fault);
        return mem_fifo;
    }
 
    public static int[] lru (int[] page_ref)
    {
      int[] mem_lru = new int [20];
      int[][] mem_lru2 = new int[20][2];
      int page_fault=0;
      boolean exist = false;
      int index = 0;
      
     
      for (int i=0 ; i<20 ; i++)
      {
       mem_lru[i]=100;
       mem_lru2[i][0]=100;
       mem_lru2[i][1]=100;
      }
      
            for (int i = 0; i < page_ref.length; i++)
            {
               for(int j=0 ; j<20 ; j++)
               {
                 if(mem_lru[j]==page_ref[i])
                 {
                   exist=true;
                   for(int k=0 ; k<20 ; k++)
                   {
                    if(mem_lru2[k][0]==page_ref[i])   
                      mem_lru2[k][1] = i ;
                   }
                   
                 }    
               }
               
               if(!exist && index <20)
               {
                
                 mem_lru[index]=page_ref[i];
                 mem_lru2[index][0]=page_ref[i];
                 mem_lru2[index][1]=i;
                 index++;
                 page_fault++;
                   
               }
               
               else if (!exist )    
                  {
                     int min=100, value=0 , ind=0 ;  
                     
                     for(int j=0 ; j<20 ; j++)
                    {
                      if(mem_lru2[j][1] < min)
                       {
                         ind=j;  
                         min = mem_lru2[j][1];   
                         value = mem_lru2[j][0];
                       }
                    
                     }
                    for(int j=0 ; j<20 ; j++)
                    {
                      if(mem_lru[j]==value)
                      {
                       mem_lru[j]=page_ref[i];
                       page_fault++;
                      }
                    }
                    mem_lru2[ind][0]=page_ref[i];
                    mem_lru2[ind][1]=i;
                   }
            
             exist=false;     
            }
     System.out.println("Page faults of LRU = " + page_fault);
     return mem_lru;
    }
    
    public static int[][] secondChance(int[] page_ref) {

        int[][] mem_sc = new int[20][2];
        boolean exist = false;
        int page_fault = 0;
        int index = 0;
        
        for(int i=0 ; i<20 ; i++)
          {
            mem_sc[i][0]=100;   
          } 
        
        for (int i = 0; i < page_ref.length; i++) {

            for (int j = 0; j < 20; j++) {
                if (page_ref[i] == mem_sc[j][0]) {
                    mem_sc[j][1] = 1;
                    exist = true;
                    break;
                }
            }

            if (!exist && index < 19) {
                while (mem_sc[index][1] == 1) {
                    mem_sc[index][1] = 0;
                    index++;
                }
                mem_sc[index][0] = page_ref[i];
                mem_sc[index][1]=0;
                index++;
                page_fault++;              
            } 
            else if (!exist && index == 19) {
                if (mem_sc[index][1] == 1)
                {
                    mem_sc[index][1] = 0;
                    index = 0;
                } 
                else
                {    
                    mem_sc[index][0] = page_ref[i];
                    mem_sc[index][1]=0;
                    page_fault++;
                    index = 0;
                }
 
            }
            exist = false;
        }
        System.out.println("Page faults of second chance = " + page_fault);
        return mem_sc;
    }

    public static int[] LFU (int[] page_ref)
    {
      int[] mem_lfu = new int [20];
      int[][] mem_lfu2 = new int[20][3];
      int page_fault=0;
      boolean exist = false;
      int index = 0;
      
     
      for (int i=0 ; i<20 ; i++)
      {
       mem_lfu[i]=100;
       mem_lfu2[i][0]=100;
       mem_lfu2[i][1]=100;
       mem_lfu2[i][2]=100;
      }
      
            for (int i = 0; i < page_ref.length; i++)
            {
               for(int j=0 ; j<20 ; j++)
               {
                 if(mem_lfu[j]==page_ref[i])
                 {
                   exist=true;
                   for(int k=0 ; k<20 ; k++)
                   {
                    if(mem_lfu2[k][0]==page_ref[i])  
                    {
                      mem_lfu2[k][1] = mem_lfu2[k][1] + 1;
                      mem_lfu2[k][2]=i;
                    }
                   }
                   
                 }    
               }
               
               if(!exist && index <20)
               {
                
                 mem_lfu[index]=page_ref[i];
                 mem_lfu2[index][0]=page_ref[i];
                 mem_lfu2[index][1]=0;
                 mem_lfu2[index][2]=i;
                 index++;
                 page_fault++;
                   
               }
               
               else if (!exist )    
                  {
                     int min=100, value=0 , ind=0 ;  
                         int r=0;
                     for(int j=0 ; j<20 ; j++)
                    {
                      if(mem_lfu2[j][1] < min)
                       {
                         min = mem_lfu2[j][1];  
                         ind=mem_lfu2[j][2];  
                         value = mem_lfu2[j][0];
                         r=j;
                       }
                  
                       else if (mem_lfu2[j][1] == min)
                       {  
                          if(mem_lfu2[j][2] < ind)
                          {
                            min = mem_lfu2[j][1];
                            ind = mem_lfu2[j][2];
                            value = mem_lfu2[j][0];
                            r=j;
                          }
                       }
                     }
                    for(int j=0 ; j<20 ; j++)
                    {
                      if(mem_lfu[j] == value)
                      {
                       mem_lfu[j]=page_ref[i];
                       page_fault++;
                      }
                    }
                    mem_lfu2[r][0]=page_ref[i];
                    mem_lfu2[r][2]=i;
                   }
            
             exist=false;     
            }
     System.out.println("Page faults of LFU = " + page_fault);
     return mem_lfu;
    }
    
    public static int[] optimal (int[] page_ref)
    {
     int[] mem_optimal = new int[20];
     int[][] mem_optimal2 = new int [page_ref.length][2];
     int page_fault=0;
     boolean exist = false;
     int index=0;
     
        for (int i = 0; i < 20; i++) 
        {
         mem_optimal[i]=100;  
        }
        
        for (int i = 0; i < page_ref.length; i++) 
        {
          mem_optimal2[i][0]=100;
          mem_optimal2[i][1]=100;
        }
        
        for (int i = 0; i < page_ref.length; i++) 
        {
            
            for (int j = 0; j < 20; j++)
            {
              if ( mem_optimal[j] == page_ref[i])
              {
                exist=true; 
                //commented mem_optimal2[j][1]=i;
              }
            }
 
            if(!exist && index<20)
            {
              mem_optimal[index] = page_ref[i];
              mem_optimal2[index][0]= page_ref[i];
              mem_optimal2[index][1]= i;
              index++;
              page_fault++;
             
            }
            
            else if(!exist)
            {
                int max = -1000 , min=100;
                int indx=100 , value=0 ;
                int count=0;
                page_fault++;
              for(int j=0 ; j<mem_optimal.length ; j++)
                {
                   for(int k=i; k<page_ref.length ; k++)
                   {
                     if(mem_optimal[j] == page_ref[k])
                     {
                         for(int m=0; m<mem_optimal2.length ; m++)
                         {
                           if(mem_optimal2[m][0] == page_ref[k])
                           {
                              indx=m;
                           }
                         }
                         mem_optimal2[indx][1]=k;
                         mem_optimal2[indx][0]=mem_optimal[j];
                         count++;
                         break;
                     }
                   }
                }
              if(count == mem_optimal.length)
              {
                  int in=0 , ix=0;
                  
                  for(int j=0 ; j<mem_optimal.length ; j++)
                  {
                    for(int k=0 ; k<mem_optimal2.length ; k++)
                    {
                        if(mem_optimal[j]==mem_optimal2[k][0])
                        {
                         if(mem_optimal2[k][1]>max)
                         {
                           max = mem_optimal2[k][1];
                           value = mem_optimal2[k][0];
                         }
                         in=k;
                        }
                        ix=j;
                    }   
                      
                  }   
                  mem_optimal[ix] = page_ref[i];
                  mem_optimal2[in][0] = page_ref[i];
                  mem_optimal2[in][1] = i;
                  
              }
               if(count<mem_optimal.length)
               {
                int x=0 , z=0;
                   for(int j=0 ; j<mem_optimal.length ; j++)
                   {
                     for(int k=0 ; k<mem_optimal2.length ; k++)
                     {
                       if(mem_optimal2[k][0] == mem_optimal[j])
                       {
                         if(mem_optimal2[k][1]<min)
                         {
                           min = mem_optimal2[k][1];
                           value = mem_optimal2[k][0];
                           x=k;
                           z=j;
                         }
                       }    
                     
                     }
                   
                   }
                   mem_optimal[z] = page_ref[i];
                   mem_optimal2[x][1]=i;
                   mem_optimal2[x][0]=page_ref[i];
                   
               }   
                  
              }
             exist=false;
            }
       System.out.println("Page faults of optimal = " + page_fault);
        
     return mem_optimal;        
    }
   
    public static int esc(int[] page_ref) {
        int page_fault = 0;
        int fi = 0;
        Random rand = new Random();
        ESCobj[] pf = new ESCobj[20];
        Boolean exist, b2, b3;
        
        for (int i = 0; i < pf.length; i++) {
            pf[i] = new ESCobj();
            pf[i].value = -1;
        }
        for (int i = 0; i < page_ref.length; i++) {
            exist = false;
            b3 = false;
            
            for (int k = 0; k < pf.length; k++) {
                if (pf[k].value == page_ref[i]) {
                    exist = true;
                    break;
                }
            }
            if (!exist)
            {
                for (int j = 0; j < pf.length; j++) {
                    if (pf[j].value == -1) {
                        pf[j].value = page_ref[i];
                        pf[j].rbit = 1;
                        pf[j].mbit = rand.nextInt(2);
                        page_fault++;
                        b3 = true;
                        break;
                    }
                }
                if (b3)  
                {
                    continue;
                }
                b2 = false;
                while (true) {
                    int counter = fi;
                    for (int k = 0; k < pf.length; k++) {
                        if (pf[counter].rbit == 0 && pf[counter].mbit == 0) {
                            pf[counter].value = page_ref[i];
                            pf[counter].rbit = 1;
                            pf[counter].mbit = rand.nextInt(2);
                            fi = counter + 1;
                            if (fi == pf.length) {
                                fi = 0;
                            }
                            page_fault++;
                            b2 = true;
                            break;
                        }
                        counter++;
                        if (counter == pf.length) {
                            counter = 0;
                        }
                    }
                    if (b2) 
                    {
                        break;
                    }
                    counter = fi;
                    for (int k = 0; k < pf.length; k++) {
                        if (pf[counter].rbit == 0 && pf[counter].mbit == 1) {
                            pf[counter].value = page_ref[i];
                            pf[counter].rbit = 1;
                            pf[counter].mbit = rand.nextInt(2);
                            fi = counter + 1;
                            if (fi == pf.length) {
                                fi = 0;
                            }
                            page_fault++;
                            b2 = true;
                            break;
                        }
                        pf[counter].rbit = 0;
                        counter++;
                        if (counter == pf.length) {
                            counter = 0;
                        }
                    }
                    if (b2) 
                    {
                        break;
                    }
                }
              
            }
        }
        System.out.print("Memory of enhanced second chance = [ ");
        for (int l = 0; l < 19 ; l++) {
            System.out.print(pf[l].value + ", ");
        }
        System.out.print(pf[19].value + " ]");
        System.out.println();

        return page_fault;
    }
    
    public static void main(String[] args) {
        
        int[] page_reference = new int[40];
        Random rand = new Random();
        int[] memory_fifo = new int[20];
        int[] mem_lru = new int[20];
        int[][] mem_sc = new int[20][2];
        int[] mem_lfu = new int [20];
        int[] mem_optimal = new int [20];
        //int[] mem_esc = new int[20];
        int enhanced =0;
        
        System.out.print("Page reference string = [ ");
        for (int i = 0; i < (page_reference.length - 1); i++) {
            page_reference[i] = rand.nextInt(100);
            if(i%14==0 && i!=0)
            {
             System.out.println("");
             System.out.print("                          ");
            }  
            System.out.print(page_reference[i] + ", ");
        }
        page_reference[39] = rand.nextInt(100);
        System.out.println(page_reference[39] + " ]");
        System.out.println();

        memory_fifo = fifo(page_reference);
        System.out.print("Memory of FIFO = [ ");
        for (int i = 0; i < 19; i++) {
            System.out.print(memory_fifo[i] + ", ");
        }
        System.out.println(memory_fifo[19] + " ]");
        System.out.println();
        
        mem_lru = lru(page_reference);
        System.out.print("Memory of LRU = [ ");
        for (int i = 0; i < 19; i++) {
            System.out.print(mem_lru[i] + ", ");
        }
        System.out.println(mem_lru[19] + " ]");
        System.out.println();
        
        mem_sc = secondChance(page_reference);
        System.out.print("Memory of second chance = [ ");
        for (int i = 0; i < (19); i++) {
            System.out.print(mem_sc[i][0] + ", ");
        }
        System.out.println(mem_sc[19][0] + " ]");
        System.out.println();
        
        mem_lfu = LFU(page_reference);
        System.out.print("Memory of LFU = [ ");
        for (int i = 0; i < (19); i++) {
            System.out.print(mem_lfu[i] + ", ");
        }
        System.out.println(mem_lfu[19] + " ]");
        System.out.println();
        
        mem_optimal = optimal(page_reference);
        System.out.print("Memory of optimal = [ ");
        for (int i = 0; i < (19); i++) {
            System.out.print(mem_optimal[i] + ", ");
        }
        System.out.println(mem_optimal[19] + " ]");
        System.out.println();
        
        enhanced = esc(page_reference);
        System.out.println("Page Fault of enhanced second chance = " + enhanced);
        
        /*mem_esc = ESC(page_reference); 
        System.out.print("Memory of enhanced second chance = [ ");
        for (int i = 0; i < (19); i++) {
            System.out.print(mem_esc[i] + ", ");
        }
        System.out.println(mem_esc[19] + " ]");*/
       
    }
}

     /* public static int[] ESC (int[] page_ref)
    {
        int[] mem_esc = new int[20];
        for (int i = 0; i < 20; i++) {
            mem_esc[i] = 100;
        }
        ESCobj [] bits = new ESCobj [mem_esc.length];
        int index = 0;
        int page_fault = 0;
        boolean exist = false;
        int point=0;
        int a=1;
        int count=0;
        
        boolean xi=false;
        
        for (int i = 0; i < page_ref.length; i++) {
            for (int j = 0; j < 20; j++) {
                if (page_ref[i] == mem_esc[j]) {
                    exist=true;
                }
            }
            if (!exist && index < 20) {
                mem_esc[index] = page_ref[i];
                bits[index] = new ESCobj();
                
               if(index==5 || index==10 ||index==15||index==20)
               {
                   System.out.println();
               }
               System.out.print("Number="+page_ref[i]+" has bits"+"("+bits[index].rbit+","+bits[index].mbit+")");
               page_fault++;
               index++;
            } 
            else if (!exist) {
                 boolean x=true;
              while(x)
              {
                  count=point;
                for(int j=0; j<mem_esc.length; j++)
                {
                    if(bits[count].rbit==0 && bits[count].mbit==0)
                    {
                     mem_esc[count]=page_ref[i];
                     bits[count]=new ESCobj();   
                     System.out.println(); 
                     System.out.println("Value inserted:"+page_ref[i]+"("+bits[count].rbit+","+bits[count].mbit+") at index:" +count);
                     System.out.println("pointer"+point);
                     point++;
                     x=false;
                     break;
                    }   
                  count++;
                    if(count==20)
                     {
                         count=0;
                     }
                }
                    
                if(x)
                {
                    count=point;
                    for(int j=0;j<mem_esc.length;j++)
                    {
                        if(bits[count].rbit==0 && bits[count].mbit==1)
                        {
                            
                         mem_esc[count]=page_ref[i];
                         bits[count]=new ESCobj();
                         System.out.println();        
                         System.out.println("Value inserted:"+page_ref[i]+"("+bits[count].rbit+","+bits[count].mbit+")" +" at index :"+count);
                         System.out.println("pointer"+point);
                         point++;
                         x=false;            
                         break;
                        }
                       
                        bits[count].rbit=0;
                        count++;
                        if(count==20)
                                {
                                    count=0;
                                } 
                    }      
                }
                
              }
          
              if(point==20)
                         {
                            point=0;
                         } 
                    page_fault++;
              }
         exist=false;
        }
        
        System.out.println("Page fault:"+page_fault);
        return mem_esc;
    }*/