package fr.ledevedec.reseausocial;

public class App {
	
	
	public ReseauSocial reseau;
	public MysqlAccess bdd;
	
	

	public static void main(String[] args) {
		App app = new App();
		
	}
	
	public App() {
		
		reseau = new ReseauSocial();
		reseau.start();
	}

}
