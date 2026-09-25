import java.math.BigDecimal;
import java.time.LocalDate;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getSalarioFormatado() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return df.format(this.salario);
    }

    public void aplicarAumento(BigDecimal percentual) {
        BigDecimal aumento = this.salario.multiply(percentual);
        this.salario = this.salario.add(aumento);
    }

    public BigDecimal calcularSalariosMinimos(BigDecimal salarioMinimo) {
        return this.salario.divide(salarioMinimo, 2, java.math.RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + getNome() + '\'' +
                ", dataNascimento=" + getDataFormatada() +
                ", salario=" + getSalarioFormatado() +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}
