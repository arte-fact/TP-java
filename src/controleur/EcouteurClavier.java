package controleur;

import javafx.scene.input.KeyEvent;

public class EcouteurClavier {
	private Controleur ctrl;
	public EcouteurClavier(Controleur c) {
		ctrl = c;
	}
	public void onKeyPressed(KeyEvent evt) {
		if (evt.getText().equals("")) return;
		char c = Character.toUpperCase(evt.getText().charAt(0));
		switch(c) {
			case '1' :
			case '2' :
			case '3' :
			case '4' :
			case '5' :
			case '6' :
			case '7' :
			case '8' :
			case '9' : ctrl.changeEpaisseur(Character.digit(c, 10)); break;
	    }
	}
}
