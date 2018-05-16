package fr.ledevedec.mainmenu;

public class StopSoft extends MenuEntry {

	
	@Override
	public String display() {

		msg = "Arrêt du progamme";
		return msg;
	}

	@Override
	public void exec() {
		System.out.println("Arrêt du Programme");
		System.exit(-1);

	}

}

