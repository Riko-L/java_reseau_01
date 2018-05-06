package fr.ledevedec.reseausocial;

import java.util.InputMismatchException;

public class ErrSaisieException extends InputMismatchException {

	
	private static final long serialVersionUID = 1L;

	public ErrSaisieException() {
		super();
	}

	@Override
	public String getMessage() {

		return "Merci de saisir un IntrÃ©gral";
	}
}
