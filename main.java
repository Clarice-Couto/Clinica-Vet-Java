import java.time.LocalDateTime; 

import java.util.ArrayList; 

import java.util.List; 

import java.util.Scanner; 

  

// Classe Cliente 

class Cliente { 

    String nome; 

    String cpf; 

    String telefone; 

    List<Animal> animais = new ArrayList<>(); 

  

    public Cliente(String nome, String cpf, String telefone) { 

        this.nome = nome; 

        this.cpf = cpf; 

        this.telefone = telefone; 

    } 

  

    public void adicionarAnimal(Animal animal) { 

        animais.add(animal); 

    } 

  

    public List<Animal> listarAnimais() { 

        return animais; 

    } 

  

    public String getCpf() { 

        return cpf; 

    } 

} 

  

// Classe Animal 

class Animal { 

    String nome; 

    String especie; 

    String raca; 

    int idade; 

    Cliente dono; 

  

    public Animal(String nome, String especie, String raca, int idade, Cliente dono) { 

        this.nome = nome; 

        this.especie = especie; 

        this.raca = raca; 

        this.idade = idade; 

        this.dono = dono; 

    } 

  

} 

  

// Classe Veterinario 

class Veterinario { 

    String nome; 

    String crmv; 

    String especialidade; 

  

    public Veterinario(String nome, String crmv, String especialidade) { 

        this.nome = nome; 

        this.crmv = crmv; 

        this.especialidade = especialidade; 

    } 

  

    public void realizarConsulta(Animal animal, String diagnostico) { 

        System.out.println("Consulta realizada no animal " + animal.nome + " com diagnóstico: " + diagnostico); 

    } 

} 

  

// Classe Consulta 

class Consulta { 

    Animal animal; 

    Veterinario veterinario; 

    LocalDateTime data; 

    String diagnostico; 

    boolean realizada; 

  

    public Consulta(Animal animal, Veterinario veterinario, LocalDateTime data) { 

        this.animal = animal; 

        this.veterinario = veterinario; 

        this.data = data; 

        this.realizada = false; 

    } 

  

    public void agendar() { 

        System.out.println("Consulta agendada para o animal " + animal.nome + " com o veterinário " + veterinario.nome); 

    } 

  

    public void finalizar(String diagnostico) { 

        this.diagnostico = diagnostico; 

        this.realizada = true; 

        System.out.println("Consulta finalizada com diagnóstico: " + diagnostico); 

    } 

  

    public Cliente getDonoAnimal() { 

        return animal.dono; 

    } 

  

    public String getResumo() { 

        return "Consulta - Animal: " + animal.nome + ", Veterinário: " + veterinario.nome + 

               ", Data: " + data + ", Diagnóstico: " + (realizada ? diagnostico : "Pendente"); 

    } 

} 

  

// Classe Principal com o método main 

public class Main { 

    static List<Cliente> clientes = new ArrayList<>(); 

    static List<Veterinario> veterinarios = new ArrayList<>(); 

    static List<Consulta> consultas = new ArrayList<>(); 

    static Scanner scanner = new Scanner(System.in); 

  

    public static void main(String[] args) { 

        int opcao; 

  

        do { 

            System.out.println("--- MENU ---"); 

            System.out.println("1. Cadastrar Cliente"); 

            System.out.println("2. Cadastrar Veterinário"); 

            System.out.println("3. Cadastrar Animal"); 

            System.out.println("4. Registrar Consulta"); 

            System.out.println("5. Consultar Histórico por CPF"); 

            System.out.println("0. Sair"); 

            System.out.print("Escolha uma opção: "); 

            opcao = Integer.parseInt(scanner.nextLine()); 

  

            switch (opcao) { 

                case 1 -> cadastrarCliente(); 

                case 2 -> cadastrarVeterinario(); 

                case 3 -> cadastrarAnimal(); 

                case 4 -> registrarConsulta(); 

                case 5 -> consultarHistorico(); 

                case 0 -> System.out.println("Encerrando o sistema."); 

                default -> System.out.println("Opção inválida."); 

            } 

        } while (opcao != 0); 

    } 

  

    public static void cadastrarCliente() { 

        System.out.print("Nome do cliente: "); 

        String nome = scanner.nextLine(); 

        System.out.print("CPF do cliente: "); 

        String cpf = scanner.nextLine(); 

        System.out.print("Telefone do cliente: "); 

        String telefone = scanner.nextLine(); 

  

        Cliente cliente = new Cliente(nome, cpf, telefone); 

        clientes.add(cliente); 

        System.out.println("Cliente cadastrado com sucesso!"); 

    } 

  

    public static void cadastrarVeterinario() { 

        System.out.print("Nome do veterinário: "); 

        String nome = scanner.nextLine(); 

        System.out.print("CRMV: "); 

        String crmv = scanner.nextLine(); 

        System.out.print("Especialidade: "); 

        String especialidade = scanner.nextLine(); 

  

        Veterinario vet = new Veterinario(nome, crmv, especialidade); 

        veterinarios.add(vet); 

        System.out.println("Veterinário cadastrado com sucesso!"); 

    } 

  

    public static void cadastrarAnimal() { 

        System.out.print("CPF do dono do animal: "); 

        String cpf = scanner.nextLine(); 

  

        Cliente dono = buscarClientePorCpf(cpf); 

        if (dono == null) { 

            System.out.println("Cliente não encontrado."); 

            return; 

        } 

  

        System.out.print("Nome do animal: "); 

        String nome = scanner.nextLine(); 

        System.out.print("Espécie: "); 

        String especie = scanner.nextLine(); 

        System.out.print("Raça: "); 

        String raca = scanner.nextLine(); 

        System.out.print("Idade: "); 

        int idade = Integer.parseInt(scanner.nextLine()); 

  

        Animal animal = new Animal(nome, especie, raca, idade, dono); 

        dono.adicionarAnimal(animal); 

        System.out.println("Animal cadastrado com sucesso!"); 

    } 

  

    public static void registrarConsulta() { 

        System.out.print("CPF do dono do animal: "); 

        String cpf = scanner.nextLine(); 

  

        Cliente dono = buscarClientePorCpf(cpf); 

        if (dono == null) { 

            System.out.println("Cliente não encontrado."); 

            return; 

        } 

  

        if (dono.listarAnimais().isEmpty()) { 

            System.out.println("Este cliente não possui animais cadastrados."); 

            return; 

        } 

  

        System.out.println("Animais do cliente:"); 

        for (int i = 0; i < dono.listarAnimais().size(); i++) { 

            System.out.println(i + " - " + dono.listarAnimais().get(i).nome); 

        } 

  

        System.out.print("Escolha o índice do animal: "); 

        int indiceAnimal = Integer.parseInt(scanner.nextLine()); 

        Animal animal = dono.listarAnimais().get(indiceAnimal); 

  

        if (veterinarios.isEmpty()) { 

            System.out.println("Nenhum veterinário cadastrado."); 

            return; 

        } 

  

        System.out.println("Veterinários disponíveis:"); 

        for (int i = 0; i < veterinarios.size(); i++) { 

            System.out.println(i + " - " + veterinarios.get(i).nome); 

        } 

  

        System.out.print("Escolha o índice do veterinário: "); 

        int indiceVet = Integer.parseInt(scanner.nextLine()); 

        Veterinario vet = veterinarios.get(indiceVet); 

  

        Consulta consulta = new Consulta(animal, vet, LocalDateTime.now()); 

        consulta.agendar(); 

  

        System.out.print("Informe o diagnóstico da consulta: "); 

        String diagnostico = scanner.nextLine(); 

        consulta.finalizar(diagnostico); 

  

        consultas.add(consulta); 

    } 

  

    public static void consultarHistorico() { 

        System.out.print("Digite o CPF do cliente: "); 

        String cpf = scanner.nextLine(); 

  

        boolean encontrou = false; 

        for (Consulta consulta : consultas) { 

            if (consulta.getDonoAnimal().getCpf().equals(cpf)) { 

                System.out.println(consulta.getResumo()); 

                encontrou = true; 

            } 

        } 

  

        if (!encontrou) { 

            System.out.println("Nenhuma consulta encontrada para este CPF."); 

        } 

    } 

  

    public static Cliente buscarClientePorCpf(String cpf) { 

        for (Cliente cliente : clientes) { 

            if (cliente.getCpf().equals(cpf)) { 

                return cliente; 

            } 

        } 

        return null; 

    } 

} 

 