
package NovaConta;

public class Conta {

    public static int numContaGeraCPF;
    public static int numConta;
    public static int idConta;
    private int saldo;
    public static String usuario;
    private int perfUsuario;
    private static int Tipo_conta; //0 - Conta Salário / 1 - Conta Poupança / 2 - Conta Corrente
    public static String CPF;
    public static String Status;

    public int getnumConta() {
        return numConta;
    }

    public void setnumConta(int numConta) {
        Conta.numConta = numConta;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        Conta.usuario = usuario;
    }

    public int getPerfUsuario() {
        return perfUsuario;
    }

    public void setPerfUsuario(int perfUsuario) {
        this.perfUsuario = perfUsuario;
    }

    public int getTipo_conta() {
        return Tipo_conta;
    }

    public void setTipo_conta(int tipo_conta) {
        Tipo_conta = tipo_conta;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public static int getIdConta() {
        return idConta;
    }

    public static void setIdConta(int idConta) {
        Conta.idConta = idConta;
    }

    public static String getStatus() {
        return Status;
    }

    public static void setStatus(String status) {
        Status = status;
    }

}
