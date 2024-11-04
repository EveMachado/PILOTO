package ufu.piloto.error.user;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(String email) {
        super("Usuário " + email + " já está cadastrado!");
    }
}
