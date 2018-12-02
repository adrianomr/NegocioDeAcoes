package NegocioDeAcoes.NegocioDeAcoes.model;

import javax.persistence.*;

@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(generator = "answer_generator")
    @SequenceGenerator(
            name = "answer_generator",
            sequenceName = "answer_sequence",
            initialValue = 0
    )
    private Long id;

    @Column(columnDefinition = "varchar(100)")
    private String descricao;

    @Column(columnDefinition = "numeric(20,4)")
    private Double saldo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void comprar(){

    }

    public void vender(){

    }
}
