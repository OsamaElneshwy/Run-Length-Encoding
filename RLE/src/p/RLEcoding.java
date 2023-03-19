package p;

import java.util.Scanner;
import java.util.Vector;

public class RLEcoding {
	
	/*
	 * input stream
	-2,0,0,2,0,0,3,2,0,1,0,0,-2,0,-1,0,0,1,0,0,-1,E 
*/
	
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		String input , temp="" , compressCode="";
		Vector<Category> cat = new Vector<Category>();
		Vector<String> vec = new Vector<String>();
		Vector<data> v = new Vector<data>();
		Vector<String> uniqueDes = new Vector<String>();
		Vector<Huffman> uniqueDesWithCodes = new Vector<Huffman>();
		
		Huffman root = new Huffman();
		Huffman t = new Huffman();
		data obj = new data();
		System.out.print("Enter your input stream: ");
		input = in.nextLine();
		for(int i = 0 ; i < input.length() ; i++)
		{
			data n = new data();
			char c = input.charAt(i);
			String x = String.valueOf(c);
			temp+=x;
			if (!("0".equals(x)) &&  !(",".equals(x)) &&  !("-".equals(x)))
			{
				vec.add(temp);
				n.stream = temp;
				v.add(n);
				temp="";
			}
			else if(!("0".equals(x)) &&  !(",".equals(x)) &&  ("-".equals(x)))
			{
				char cc = 0;
				if(i+1 != input.length())
				{
					 cc = input.charAt(i+1);
				}
				
				String xx = String.valueOf(cc);
				temp+=xx;
				vec.add(temp);
				n.stream = temp;
				v.add(n);
				temp="";
				i++;
			}
			else if("E".equals(x))
			{
				vec.add(temp);
				n.stream = temp;
				v.add(n);
				temp="";
				break;
			}
		}
		
		for(int i = 0 ; i < v.size() ; i++)
		{
			obj.calcDescriptor(v.get(i) ,cat);
		}
		
		for(int i = 0 ; i < v.size() ; i++)
		{
			Huffman d = new Huffman();
			if(!uniqueDes.contains((v.get(i)).descriptor))
			{
				uniqueDes.add(v.get(i).descriptor);
				d.s = v.get(i).descriptor;
				uniqueDesWithCodes.add(d);
			}
		}
		
		for(int i = 0 ; i < v.size() ; i++)
		{
			for(int j = 0 ; j < uniqueDesWithCodes.size() ; j++ )
			{
				if(v.get(i).descriptor.equals(uniqueDesWithCodes.get(j).s))
				{
					uniqueDesWithCodes.get(j).count++;
				}
			}
		}
		
		for(int i = 0 ; i < uniqueDesWithCodes.size() ; i++)
		{
			Huffman.calcProb(uniqueDesWithCodes.get(i), v.size());	
		}
		
		root = Huffman.SetCode(uniqueDesWithCodes , v);
		
		for(int i = 0 ; i < v.size() ; i++)
		{
			compressCode+= v.get(i).compCode + " ";	
		}
	
		System.out.println("CompressCode  = " + compressCode);
		DeCompression(compressCode, v , cat);
		
		
		
	}
	
	public static void DeCompression(String Comp , Vector<data> v  , Vector<Category> cat)
	{
		String output = "" ,temp = "";
		for(int i = 0; i < Comp.length() ; i++)
		{
			char c = Comp.charAt(i);
			String x = String.valueOf(c);
			if(" ".equals(x))
			{
				for(int n = 0 ; n < cat.size() ; n++)
				{
					if(cat.get(n).addBits.equals(temp))
					{
						output+= cat.get(n).num;
						break;
					}
				}
				output+= " ";
				temp="";
			}
			if (!",".equals(x) && !" ".equals(x))
			{
				temp+=x;
			}
			if(",".equals(x))
			{
				int numOfZeros;
				String des , firstChar;
				for(int j = 0 ; j < v.size() ; j++)
				{
					if(v.get(j).Code.equals(temp))
					{
						des = v.get(j).descriptor;
						char p = des.charAt(0);
						firstChar = String.valueOf(p);
						if("E".equals(firstChar))
						{
							output+="EOB";
						}
						else
						{
							numOfZeros = Integer.parseInt(firstChar);
							for(int n = 0 ; n < numOfZeros ; n++)
							{
								output+="0,";
							}
						}
						break;
					}
				}
				temp = "";
			}
			
			
		}
		
		System.out.println("Decompression = " + output);
	}
	

}
