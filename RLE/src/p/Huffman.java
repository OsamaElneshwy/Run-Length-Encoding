package p;

import java.util.Vector;

public class Huffman {
	public	String s = "";
	public float prob;
	public String Code= "";
	public float count=0;
	public static Vector<Huffman> vec = new Vector<Huffman>();
	 Huffman left; 
	 Huffman right; 
	    

	public static void calcProb(Huffman obj , int size)
	{
		obj.prob = obj.count/size;
		
	}
	
	
	 public void Sort(Vector<Huffman> v) 
		{ 
	    	Huffman temp=new Huffman();
			int i, j;
			boolean swapped; 
			for (i = 0; i < v.size() - 1; i++)  
			{ 
				swapped = false; 
				for (j = 0; j <  v.size() - i - 1; j++)  
				{ 
					if (v.get(j).prob > v.get(j+1).prob)  
					{ 
						temp = v.get(j); 
						v.set(j,v.get(j+1));
						v.set(j+1,temp);
						swapped = true; 	
					} 
				} 
				if (swapped == false)
				{
					break;
				} 
			} 
		}

	 public static Huffman SetCode(Vector<Huffman> v , Vector<data> Original) 
	    { 
	    	Huffman root = null;   
	        Vector <Huffman> q=new Vector <Huffman>();
	        Huffman t1 = new Huffman(); 
	        
	        for (int i = 0; i < v.size(); i++) 
	        { 
	            Huffman t = new Huffman(); 
	  
	            t.s = v.get(i).s; 
	            t.prob = v.get(i).prob; 
	  
	            t.left = null; 
	            t.right = null; 
	  
	            q.add(t); 
	            t1.Sort(q);
	             
	        } 
	        
	        while (q.size() > 1)
	        { 
	  
	            Huffman x = q.get(0); 
	            q.remove(0); 
	  
	            Huffman y = q.get(0); 
	            q.remove(0);
	 
	            Huffman f = new Huffman(); 
	 
	            f.prob = x.prob + y.prob; 
	            f.s = "*"; 

	            f.left = x; 
	            f.right = y; 
	            root = f; 

	            q.add(f); 
	            t1.Sort(q);
	        }

	        printCode(root ,"");
	        
	        for(int i = 0 ; i < Original.size() ; i++)
	        {
	        	for(int j = 0 ; j < vec.size() ; j++)
	        	{
	        		if(Original.get(i).descriptor.equals(vec.get(j).s))
	        		{
	        			Original.get(i).Code = vec.get(j).Code;
	        			Original.get(i).compCode = vec.get(j).Code + "," + Original.get(i).additonalBits;
	        		}
	        	}
	        }

	        return root;
	        
	    } 
	    

	    
	    public static void printCode(Huffman root  , String s) 
	    { 
	        if (root.left == null && root.right == null )
	        { 
	            System.out.println(root.s + " Code = " + s);
	            Huffman d = new Huffman();
	            d.s = root.s;
	            d.Code = s;
	            vec.add(d);
	            return; 
	        } 
	        printCode(root.left , s + "0"); 
	        printCode(root.right ,s + "1"); 
	    }
    
}
