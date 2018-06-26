package vue;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class Dialogues {
	public static void information(String titre, String message) {
		Alert dialogue = new Alert(AlertType.INFORMATION);
		dialogue.setTitle(titre);
		dialogue.setHeaderText(null);
		dialogue.setContentText(message);
		dialogue.showAndWait();
	}
	public static void avertissement(String titre, String message) {
		Alert dialogue = new Alert(AlertType.WARNING);
		dialogue.setTitle(titre);
		dialogue.setHeaderText(null);
		dialogue.setContentText(message);
		dialogue.showAndWait();
	}
	public static void erreur(String titre, String message) {
		Alert dialogue = new Alert(AlertType.ERROR);
		dialogue.setTitle(titre);
		dialogue.setHeaderText(null);
		dialogue.setContentText(message);
		dialogue.showAndWait();
	}
	public static Confirmation confirmation(String titre) {
		Alert dialogue = new Alert(AlertType.WARNING);
		dialogue.setTitle(titre);
		dialogue.setHeaderText("Cette op�ration fait perdre les modifications non enregistr�es !");
		dialogue.setContentText("Des modifications ont �t� apport�es au dessin sans �tre enregistr�es.\nQue voulez-vous faire ?");
		ButtonType btOui = new ButtonType("Enregistrer");
		ButtonType btNon = new ButtonType("Ne pas enregistrer");
		ButtonType btAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
		dialogue.getButtonTypes().setAll(btOui, btNon, btAnnuler);
		Optional<ButtonType> result = dialogue.showAndWait();
		if (result.get() == btOui) {
		    return Confirmation.CONFIRMATION_AVEC_ENREGISTREMENT;
		} else if (result.get() == btNon) {
		    return Confirmation.CONFIRMATION_SANS_ENREGISTREMENT;
		} else {
		    return Confirmation.ABANDON;
		}
    }
}
