import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionários na mesma ordem e informações da tabela
        System.out.println("========== 3.1 - INSERINDO FUNCIONÁRIOS ==========\n");

        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        System.out.println("funcionários inseridos com sucesso!\n");

        // 3.2 - Remover o funcionário "João" da lista
        System.out.println("========== 3.2 - REMOVENDO JOÃO ==========\n");
        funcionarios.removeIf(f -> f.getNome().equals("João"));
        System.out.println("João removido da lista!\n");

        // 3.3 - Imprimir todos os funcionários com todas suas informações
        System.out.println("========== 3.3 - TODOS OS FUNCIONÁRIOS ==========\n");
        funcionarios.forEach(f -> System.out.println(f));
        System.out.println();

        // 3.4 - Os funcionários receberam 10% de aumento de salário
        System.out.println("========== 3.4 - APLICANDO 10% DE AUMENTO ==========\n");
        BigDecimal aumento = new BigDecimal("0.10");
        funcionarios.forEach(f -> f.aplicarAumento(aumento));
        System.out.println("Aumento de 10% aplicado a todos os funcionários!\n");

        System.out.println("Salários após aumento:\n");
        funcionarios.forEach(f -> System.out.println(f.getNome() + " - " + f.getSalarioFormatado()));
        System.out.println();

        // 3.5 - Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        System.out.println("========== 3.5 - AGRUPANDO POR FUNÇÃO ==========\n");
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        System.out.println("Funcionários agrupados por função!\n");

        // 3.6 - Imprimir os funcionários, agrupados por função
        System.out.println("========== 3.6 - FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO ==========\n");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println("  - " + f.getNome() + " | Salário: " + f.getSalarioFormatado()));
            System.out.println();
        });

        // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("========== 3.8 - ANIVERSARIANTES (OUTUBRO E DEZEMBRO) ==========\n");
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonth() == Month.OCTOBER ||
                        f.getDataNascimento().getMonth() == Month.DECEMBER)
                .collect(Collectors.toList());

        if (aniversariantes.isEmpty()) {
            System.out.println("Nenhum funcionário faz aniversário em outubro ou dezembro.");
        } else {
            aniversariantes.forEach(f -> System.out.println(f.getNome() + " - " + f.getDataFormatada()));
        }
        System.out.println();

        // 3.9 - Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        System.out.println("========== 3.9 - FUNCIONÁRIO COM MAIOR IDADE ==========\n");
        Funcionario maisVelho = funcionarios.stream()
                .max(Comparator.comparingInt(Funcionario::getIdade))
                .orElse(null);

        if (maisVelho != null) {
            System.out.println("Nome: " + maisVelho.getNome());
            System.out.println("Idade: " + maisVelho.getIdade() + " anos");
        }
        System.out.println();

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("========== 3.10 - FUNCIONÁRIOS EM ORDEM ALFABÉTICA ==========\n");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));
        System.out.println();

        // 3.11 - Imprimir o total dos salários dos funcionários
        System.out.println("========== 3.11 - TOTAL DE SALÁRIOS ==========\n");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
        java.text.DecimalFormatSymbols symbols = new java.text.DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(symbols);

        System.out.println("Total de salários: " + df.format(totalSalarios));
        System.out.println();

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        System.out.println("========== 3.12 - SALÁRIOS MÍNIMOS POR FUNCIONÁRIO ==========\n");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        funcionarios.forEach(f -> {
            BigDecimal salariosMinimos = f.calcularSalariosMinimos(salarioMinimo);
            System.out.println(f.getNome() + " ganha " + salariosMinimos + " salários mínimos");
        });
        System.out.println();

        System.out.println("========== FIM DO PROGRAMA ==========");
    }
}
