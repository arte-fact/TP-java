package modele;

@SuppressWarnings("serial")
public class ExceptionVersionInconnue extends Exception {
	public ExceptionVersionInconnue(byte version) {
		super("Cette version de l'application reconnait les fichiers de la version 1 seulement. L'ouverture d'un fichier de version " + version + "a échoué !");
	}
}