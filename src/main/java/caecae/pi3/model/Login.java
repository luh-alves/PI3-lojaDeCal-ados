package caecae.pi3.model;

public class Login {
    private String user;
    private String senha;
    
    public Login(){
    }
    public Login(String user,String senha) {
        this.user = user;
        this.senha = senha;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
