# ITPM_desktop_application-JAVAFX-
ITPM project on Time table management system develop for ABC company.
package lambda2;

//Method reference way in a lambda expression

interface Calculator {
  void add (int num1, int num2);
  
}

class calC{
	
	public static void addsomething (int num1 , int num2) {  // <- static method
		System.out.println(num1+ " add " +num2+ " addition is: "+(num1+num2) );
	}

	public void letsAdd (int num1 , int num2 ) {			// <- non static method
		System.out.println(num1+ " add " +num2+ " adds to: " +(num1+num2));
	}
	
}


//this is functional interface cuz its contain one method
interface Messenger {
	Message getMessage(String msg); 
}

class Message {
	Message (String msg){
		System.out.println("Message is " +msg);
	}	
}



public class lambdamethod {

	public static void main (String[] args) {
		
		//calC.addsomething(10, 20);
		
		//reference a static method - 1
		Calculator cRef1 = calC::addsomething; //Method reference
		cRef1.add(11, 25);   
		
		
		//Reference to non static method or instance method - 2 
		//Object construction statement for calC
		calC calc = new calC ();
		Calculator cRef2 = calc::letsAdd;    //Method reference
		cRef2.add(20, 45);
		
		//Reference to constructor  - 3 
		Messenger mRef = Message::new;  //Method reference
		mRef.getMessage("Search Tanks and Troops , then execute them");
	}
	
	
}






