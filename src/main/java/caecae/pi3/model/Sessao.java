package caecae.pi3.model;

/**
 *
 * @author Matheus
 */
public class Sessao {
    private String user;
    private int filial;
    private String cargo;
        
    public Sessao(String user, int filial, String cargo){
        this.user = user;
        this.filial=filial;
        this.cargo=cargo;
    }

    public Sessao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    } 
}
