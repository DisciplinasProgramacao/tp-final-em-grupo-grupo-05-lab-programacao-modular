package Restaurante;
/*Enum responsável por armazenar as fidelidades e os descontos de direito de cada uma*/
public enum ProgramaDeFidelidade {
    FBRANCO(0),FPRATA(0.05),FPRETO(0.1),FFEV(0.2);
    public double desconto;
    ProgramaDeFidelidade(double desconto){
        this.desconto = desconto;
    }
    public double getDesconto() {
        return desconto;
    }
}
