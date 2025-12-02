package activity1;

public class Class1 {

	public static void main(String[] args) {
		Car m_suv = new Car("Black", "Manual", 2025);
		
		System.out.println("Displaying Characteristics");
        m_suv.displayCharacteristics();
        
        System.out.println("Simulating Movement");
        m_suv.accelerate();
        
        m_suv.brake();
		

	}

}
