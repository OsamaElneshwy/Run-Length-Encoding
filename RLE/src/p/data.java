package p;

import java.util.Vector;

public class data extends RLEcoding {
	String stream="";
	String descriptor="";
	String Code = "";
	String additonalBits="";
	String compCode="";
	
	public void calcDescriptor(data x , Vector<Category> cat)
	{
		int zeroCounter = 0 , addBits = 0;
		for(int i = 0 ; i < x.stream.length() ; i++)
		{
			char c = x.stream.charAt(i);
			String y = String.valueOf(c);

			if(y.equals("0"))
			{
				zeroCounter++;
			}
			else if (y.equals("-"))
			{
				Category obj = new Category();
				char cc = x.stream.charAt(i+1);
				String yy = String.valueOf(cc);
				int b = Integer.parseInt(yy);
				String z = Integer.toBinaryString(b);
				String temp = "";
				for(int p = 0 ; p < z.length() ;p++)
				{
					char ccc= z.charAt(p);
					String yyy = String.valueOf(ccc);
					if(yyy.equals("0"))
					{
						temp+="1";
					}
					else
					{
						temp+="0";
					}
					
				}
				x.additonalBits = temp;
				addBits = z.length();
				obj.addBits = temp;
				obj.num = y+cc;
				cat.add(obj);

				if(i+1 != x.stream.length())
				{
					i++;
				}
			}
			else if (!y.equals("0") && !y.equals(",") && !y.equals("E"))
			{
				Category obj = new Category();
				int b = Integer.parseInt(y);
				String z = Integer.toBinaryString(b);
				x.additonalBits = z;
				addBits = z.length();
				obj.addBits = z;
				obj.num = y;
				cat.add(obj);
			}
			else if (y.equals("E"))
			{
				break;
			}
		}
		
		char ccc = x.stream.charAt(x.stream.length()-1);
		String yyy = String.valueOf(ccc);
		if(yyy.equals("E"))
		{
			x.descriptor = "EOB";
		}
		else
		{
			x.descriptor = zeroCounter+"/"+addBits;
		}
		
	}
	
}
