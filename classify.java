import java.io.*;
import java.util.*;

class classify
{
	
	static String csvFile = "/home/aditya/Desktop/buycomputer.csv";	
	static String line = "";
       	static String cvsSplitBy = ",";
       	static float buycount,count;
       	static float c1=0,c2=0,c3=0,c4=0,c5=0,c6=0;
       	static float p1=0,p2=0,p3=0,p4=0,p5=0,p6=0;
       	static float q1=0,q2=0,q3=0,q4=0;
	static float r1=0,r2=0,r3=0,r4=0;
	static float[][] age=new float[3][2];
	static float[][] income=new float[3][2];
	static float[][] student=new float[2][2];
	static float[][] credit=new float[2][2];
	static float notBuy=0;
	static float probyes,probno;
	
	static void calculate()
	{
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
        	{
			while ((line = br.readLine()) != null) 
            		{
				String[] computer = line.split(cvsSplitBy);
				if(computer[4].equals("yes"))
        	     		{
					buycount++;
				}
				count++;  
					
					//Age            
                  			if((computer[0]).equals("<=30")&&computer[4].equals("yes"))
				   		c1++;
				 	if((computer[0]).equals("31-40")&&computer[4].equals("yes"))
				   		c3++;
					if((computer[0]).equals(">40")&&computer[4].equals("yes"))
				   		c5++;
        		 		if((computer[0]).equals("<=30")&&computer[4].equals("no"))
				   		c2++;
				 	if((computer[0]).equals("31-40")&&computer[4].equals("no"))
				   		c4++;
				 	if((computer[0]).equals(">40")&&computer[4].equals("no"))	        		            	
				 		c6++;
					
					//Income
					if(computer[1].equals("high")&&computer[4].equals("yes"))
					   	p1++;
					if(computer[1].equals("medium")&&computer[4].equals("yes"))
				   		p3++;
					if(computer[1].equals("low")&&computer[4].equals("yes"))
				   		p5++;
        		 		if(computer[1].equals("high")&&computer[4].equals("no"))
				   		p2++;
				 	if(computer[1].equals("medium")&&computer[4].equals("no"))
				   		p4++;
				 	if(computer[1].equals("low")&&computer[4].equals("no"))	        		            
				 	    	p6++;
				   
				   	//Student
				 	if(computer[2].equals("yes")&&computer[4].equals("yes"))
				   		q1++;
					if(computer[2].equals("no")&&computer[4].equals("yes"))
				   		q3++;
					if(computer[2].equals("yes")&&computer[4].equals("no"))
				  	 	q2++;
        		 		if(computer[2].equals("no")&&computer[4].equals("no"))
				   		q4++;
					
					//Credit_Rating
				 	if(computer[3].equals("fair")&&computer[4].equals("yes"))
				   		r1++;
					if(computer[3].equals("excellent")&&computer[4].equals("yes"))
				   		r3++;
					if(computer[3].equals("fair")&&computer[4].equals("no"))
				  	 	r2++;
        		 		if(computer[3].equals("excellent")&&computer[4].equals("no"))
				   		r4++;
				 }
            	}catch (IOException e) {e.printStackTrace();}
		notBuy=count-buycount;
		
		probyes=buycount/count;
		probno=notBuy/count;
        }

	static void prob_age()
	{
		notBuy=count-buycount;
		age[0][0]=c1/buycount;
		age[0][1]=c2/notBuy;
		age[1][0]=c3/buycount;
		age[1][1]=c4/notBuy;	
		age[2][0]=c5/buycount;
		age[2][1]=c6/notBuy;
	}
	static void prob_income()
	{
		income[0][0]=p1/buycount;
		income[0][1]=p2/notBuy;
		income[1][0]=p3/buycount;
		income[1][1]=p4/notBuy;	
		income[2][0]=p5/buycount;
		income[2][1]=p6/notBuy;
	}
	static void prob_student()
	{
		student[0][0]=q1/buycount;
		student[0][1]=q2/notBuy;
		student[1][0]=q3/buycount;
		student[1][1]=q4/notBuy;	
		
	}
	static void prob_credit()
	{
		credit[0][0]=r1/buycount;
		credit[0][1]=r2/notBuy;
		credit[1][0]=r3/buycount;
		credit[1][1]=r4/notBuy;	
		
	}
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the string to be tested in form(age,income,student,credit-rating)");
		String s=sc.next();
		String[] guess = s.split(cvsSplitBy);
		calculate();
		prob_age();
		prob_income();
		prob_student();
		prob_credit();
		
		if(guess[0].equals("<=30")&&guess[1].equals("high")&&guess[2].equals("no")&&guess[3].equals("fair"))
		{
			float Y=age[0][0]*income[0][0]*student[1][0]*credit[0][0]*probyes;
			float N=age[0][1]*income[0][1]*student[1][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("<=30")&&guess[1].equals("high")&&guess[2].equals("no")&&guess[3].equals("excellent"))
		{
			float Y=age[0][0]*income[0][0]*student[1][0]*credit[1][0]*probyes;
			float N=age[0][1]*income[0][1]*student[1][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("<=30")&&guess[1].equals("high")&&guess[2].equals("yes")&&guess[3].equals("fair"))
		{
			float Y=age[0][0]*income[0][0]*student[0][0]*credit[0][0]*probyes;
			float N=age[0][1]*income[0][1]*student[0][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("<=30")&&guess[1].equals("high")&&guess[2].equals("yes")&&guess[3].equals("excellent"))
		{
			float Y=age[0][0]*income[0][0]*student[0][0]*credit[1][0]*probyes;
			float N=age[0][1]*income[0][1]*student[0][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("<=30")&&guess[1].equals("low")&&guess[2].equals("no")&&guess[3].equals("fair"))
		{
			float Y=age[0][0]*income[2][0]*student[1][0]*credit[0][0]*probyes;
			float N=age[0][1]*income[2][1]*student[1][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("<=30")&&guess[1].equals("low")&&guess[2].equals("no")&&guess[3].equals("excellent"))
		{
			float Y=age[0][0]*income[2][0]*student[1][0]*credit[1][0]*probyes;
			float N=age[0][1]*income[2][1]*student[1][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("<=30")&&guess[1].equals("low")&&guess[2].equals("yes")&&guess[3].equals("fair"))
		{
			float Y=age[0][0]*income[2][0]*student[0][0]*credit[0][0]*probyes;
			float N=age[0][1]*income[2][1]*student[0][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("<=30")&&guess[1].equals("low")&&guess[2].equals("yes")&&guess[3].equals("excellent"))
		{
			float Y=age[0][0]*income[2][0]*student[0][0]*credit[1][0]*probyes;
			float N=age[0][1]*income[2][1]*student[0][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("<=30")&&guess[1].equals("medium")&&guess[2].equals("no")&&guess[3].equals("fair"))
		{
			float Y=age[0][0]*income[1][0]*student[1][0]*credit[0][0]*probyes;
			float N=age[0][1]*income[1][1]*student[1][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("<=30")&&guess[1].equals("medium")&&guess[2].equals("no")&&guess[3].equals("excellent"))
		{
			float Y=age[0][0]*income[1][0]*student[1][0]*credit[1][0]*probyes;
			float N=age[0][1]*income[1][1]*student[1][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("<=30")&&guess[1].equals("medium")&&guess[2].equals("yes")&&guess[3].equals("fair"))
		{
			float Y=age[0][0]*income[1][0]*student[0][0]*credit[0][0]*probyes;
			float N=age[0][1]*income[1][1]*student[0][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("<=30")&&guess[1].equals("medium")&&guess[2].equals("yes")&&guess[3].equals("excellent"))
		{
			float Y=age[0][0]*income[1][0]*student[0][0]*credit[1][0]*probyes;
			float N=age[0][1]*income[1][1]*student[0][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}

		else if(guess[0].equals("31-40")&&guess[1].equals("high")&&guess[2].equals("no")&&guess[3].equals("fair"))
		{
			float Y=age[1][0]*income[0][0]*student[1][0]*credit[0][0]*probyes;
			float N=age[1][1]*income[0][1]*student[1][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("31-40")&&guess[1].equals("high")&&guess[2].equals("no")&&guess[3].equals("excellent"))
		{
			float Y=age[1][0]*income[0][0]*student[1][0]*credit[1][0]*probyes;
			float N=age[1][1]*income[0][1]*student[1][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("31-40")&&guess[1].equals("high")&&guess[2].equals("yes")&&guess[3].equals("fair"))
		{
			float Y=age[1][0]*income[0][0]*student[0][0]*credit[0][0]*probyes;
			float N=age[0][1]*income[0][1]*student[0][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("31-40")&&guess[1].equals("high")&&guess[2].equals("yes")&&guess[3].equals("excellent"))
		{
			float Y=age[1][0]*income[0][0]*student[0][0]*credit[1][0]*probyes;
			float N=age[1][1]*income[0][1]*student[0][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("31-40")&&guess[1].equals("low")&&guess[2].equals("no")&&guess[3].equals("fair"))
		{
			float Y=age[1][0]*income[2][0]*student[1][0]*credit[0][0]*probyes;
			float N=age[1][1]*income[2][1]*student[1][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("31-40")&&guess[1].equals("low")&&guess[2].equals("no")&&guess[3].equals("excellent"))
		{
			float Y=age[1][0]*income[2][0]*student[1][0]*credit[1][0]*probyes;
			float N=age[1][1]*income[2][1]*student[1][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("31-40")&&guess[1].equals("low")&&guess[2].equals("yes")&&guess[3].equals("fair"))
		{
			float Y=age[1][0]*income[2][0]*student[0][0]*credit[0][0]*probyes;
			float N=age[1][1]*income[2][1]*student[0][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("31-40")&&guess[1].equals("low")&&guess[2].equals("yes")&&guess[3].equals("excellent"))
		{
			float Y=age[1][0]*income[2][0]*student[0][0]*credit[1][0]*probyes;
			float N=age[1][1]*income[2][1]*student[0][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("31-40")&&guess[1].equals("medium")&&guess[2].equals("no")&&guess[3].equals("fair"))
		{
			float Y=age[1][0]*income[1][0]*student[1][0]*credit[0][0]*probyes;
			float N=age[1][1]*income[1][1]*student[1][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("31-40")&&guess[1].equals("medium")&&guess[2].equals("no")&&guess[3].equals("excellent"))
		{
			float Y=age[1][0]*income[1][0]*student[1][0]*credit[1][0]*probyes;
			float N=age[1][1]*income[1][1]*student[1][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("31-40")&&guess[1].equals("medium")&&guess[2].equals("yes")&&guess[3].equals("fair"))
		{
			float Y=age[1][0]*income[1][0]*student[0][0]*credit[0][0]*probyes;
			float N=age[1][1]*income[1][1]*student[0][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals("31-40")&&guess[1].equals("medium")&&guess[2].equals("yes")&&guess[3].equals("excellent"))
		{
			float Y=age[1][0]*income[1][0]*student[0][0]*credit[1][0]*probyes;
			float N=age[1][1]*income[1][1]*student[0][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}

		else if(guess[0].equals(">40")&&guess[1].equals("high")&&guess[2].equals("no")&&guess[3].equals("fair"))
		{
			float Y=age[2][0]*income[0][0]*student[1][0]*credit[0][0]*probyes;
			float N=age[2][1]*income[0][1]*student[1][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals(">40")&&guess[1].equals("high")&&guess[2].equals("no")&&guess[3].equals("excellent"))
		{
			float Y=age[2][0]*income[0][0]*student[1][0]*credit[1][0]*probyes;
			float N=age[2][1]*income[0][1]*student[1][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals(">40")&&guess[1].equals("high")&&guess[2].equals("yes")&&guess[3].equals("fair"))
		{
			float Y=age[2][0]*income[0][0]*student[0][0]*credit[0][0]*probyes;
			float N=age[2][1]*income[0][1]*student[0][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals(">40")&&guess[1].equals("high")&&guess[2].equals("yes")&&guess[3].equals("excellent"))
		{
			float Y=age[2][0]*income[0][0]*student[0][0]*credit[1][0]*probyes;
			float N=age[2][1]*income[0][1]*student[0][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals(">40")&&guess[1].equals("low")&&guess[2].equals("no")&&guess[3].equals("fair"))
		{
			float Y=age[2][0]*income[2][0]*student[1][0]*credit[0][0]*probyes;
			float N=age[2][1]*income[2][1]*student[1][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals(">40")&&guess[1].equals("low")&&guess[2].equals("no")&&guess[3].equals("excellent"))
		{
			float Y=age[2][0]*income[2][0]*student[1][0]*credit[1][0]*probyes;
			float N=age[2][1]*income[2][1]*student[1][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals(">40")&&guess[1].equals("low")&&guess[2].equals("yes")&&guess[3].equals("fair"))
		{
			float Y=age[2][0]*income[2][0]*student[0][0]*credit[0][0]*probyes;
			float N=age[2][1]*income[2][1]*student[0][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals(">40")&&guess[1].equals("low")&&guess[2].equals("yes")&&guess[3].equals("excellent"))
		{
			float Y=age[2][0]*income[2][0]*student[0][0]*credit[1][0]*probyes;
			float N=age[2][1]*income[2][1]*student[0][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals(">40")&&guess[1].equals("medium")&&guess[2].equals("no")&&guess[3].equals("fair"))
		{
			float Y=age[2][0]*income[1][0]*student[1][0]*credit[0][0]*probyes;
			float N=age[2][1]*income[1][1]*student[1][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals(">40")&&guess[1].equals("medium")&&guess[2].equals("no")&&guess[3].equals("excellent"))
		{
			float Y=age[2][0]*income[1][0]*student[1][0]*credit[1][0]*probyes;
			float N=age[2][1]*income[1][1]*student[1][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals(">40")&&guess[1].equals("medium")&&guess[2].equals("yes")&&guess[3].equals("fair"))
		{
			float Y=age[2][0]*income[1][0]*student[0][0]*credit[0][0]*probyes;
			float N=age[2][1]*income[1][1]*student[0][1]*credit[0][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}
		else if(guess[0].equals(">40")&&guess[1].equals("medium")&&guess[2].equals("yes")&&guess[3].equals("excellent"))
		{
			float Y=age[2][0]*income[1][0]*student[0][0]*credit[1][0]*probyes;
			float N=age[2][1]*income[1][1]*student[0][1]*credit[1][1]*probno;
			if(Y>N)
			  System.out.print("Class: YES");
			else
			  System.out.print("Class: NO");
		}

		
		
		
	}




}
